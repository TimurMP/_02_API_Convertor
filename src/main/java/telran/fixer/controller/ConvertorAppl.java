package telran.fixer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import telran.fixer.dto.ResponseDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertorAppl {
    static RestTemplate restTemplate = new RestTemplate();
    static final String API_Key = "jBbJxkkiuvm0jgNETyTx0DCqYe8NGsMA";
    static String baseUrl = "https://api.apilayer.com/fixer/convert";


    public static void main(String[] args) throws IOException {
        String[] arr = inputReader();

        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", API_Key);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("to", arr[1].toUpperCase())
                .queryParam("from", arr[0].toUpperCase())
                .queryParam("amount", arr[2]);


        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, builder.build().toUri() );
        ResponseEntity<ResponseDto> responseEntity = restTemplate.exchange(requestEntity, ResponseDto.class);
//        responseEntity.getBody().getResult();
        String res = responseEntity.getBody().getResult();
//        System.out.println(res);
        printResult(arr[0], arr[1], res);




    }

    public static String[] inputReader() throws IOException {
        String[] strings = new String[3];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Convert From (Example: USD)");
        String from = reader.readLine();
        strings[0] = from;
        System.out.println("Convert To (Example: CAD)");
        String to = reader.readLine();
        strings[1] = to;
        System.out.println("Amount");
        String amount = reader.readLine();
        strings[2] = amount;
        return strings;
    }

    public static void printResult(String from, String to, String result){
        System.out.printf("--------------------------------------%n");
        System.out.printf("       Currency Convertor         %n");
//        System.out.printf("    (printf table example)      %n");
        System.out.printf("--------------------------------------%n");
        System.out.printf("| %-10s | %-8s | %10s |%n", "From", "To", "Result");
        System.out.printf("--------------------------------------%n");
        System.out.printf("| %-10s | %-8s | %10s |%n", from.toUpperCase(), to.toUpperCase(),  result);
        System.out.printf("--------------------------------------%n");





    }


}
