package telran.fixer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        System.out.println(responseEntity.getBody());



    }

    public static String[] inputReader() throws IOException {
        String[] strings = new String[3];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Convert From (Example: USD)");
        String from = reader.readLine();
        strings[0] = from;
        System.out.println("Convert To (Example: CAD");
        String to = reader.readLine();
        strings[1] = to;
        System.out.println("Amount");
        String amount = reader.readLine();
        strings[2] = amount;
        return strings;
    }


}
