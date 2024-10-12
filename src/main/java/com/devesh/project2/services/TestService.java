package com.devesh.project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TestService {

    @Value("${api.host}")
    private String apiHost;

    @Value("${api.test-url}")
    private String testApi;

    @Autowired
    private RestTemplate restTemplate;

    public String callOtherMicroService() {
        String apiUrl = apiHost + testApi;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        URI uri = uriComponentsBuilder.buildAndExpand().toUri();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        return response.getBody();

    }
}
