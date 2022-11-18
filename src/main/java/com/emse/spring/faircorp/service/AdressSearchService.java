package com.emse.spring.faircorp.service;

import com.emse.spring.faircorp.service.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.service.dto.ApiGouvFeatureDto;
import com.emse.spring.faircorp.service.dto.ApiGouvResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdressSearchService {
    private final RestTemplate restTemplate;

    public AdressSearchService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.rootUri("https://api-adresse.data.gouv.fr").build();
    }

    public List<ApiGouvAdressDto> findAdress(List<String> search){
        String params = String.join("+",search);
        String uri = UriComponentsBuilder.fromUriString("/search").queryParam("q",params).queryParam("limit",15).build().toUriString();

        List<ApiGouvFeatureDto> featureResult = restTemplate.getForObject(uri, ApiGouvResponseDto.class).getFeatures();
        List<ApiGouvAdressDto> dressResult = new ArrayList<>();

        for (ApiGouvFeatureDto feature : featureResult){
            dressResult.add(feature.getProperties());
        }

        return dressResult;
    }
}
