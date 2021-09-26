package com.pitsik.flagdownloader.service.impl;

import com.pitsik.flagdownloader.dto.RestCountriesRsDto;
import com.pitsik.flagdownloader.exception.EmptyBodyException;
import com.pitsik.flagdownloader.service.CountryProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CountryFlagProducerImpl implements CountryProducer {

    @Value("${url.country}")
    private String URL_COUNTRY;

    @Override
    public List<RestCountriesRsDto> getCountry() throws EmptyBodyException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<RestCountriesRsDto>> responseEntity = restTemplate.exchange
                (
                        URL_COUNTRY,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<RestCountriesRsDto>>() {
                        }
                );

        List<RestCountriesRsDto> countries = new ArrayList<>(responseEntity.getBody()); // add Exception and get in try-catch EmptyBodyException extends Exception(Пустое тело)
        checkNullOrEmpty(responseEntity);
        log.info("Flags received: {}, Country flags: {}", countries.size(),
                countries.stream().map(country -> country.getName().getCommon()).collect(Collectors.toList()));
        return countries;
    }

    private void checkNullOrEmpty(ResponseEntity<List<RestCountriesRsDto>> responseEntity) throws EmptyBodyException {
        if (responseEntity.getBody() == null || responseEntity.getBody().isEmpty()) {
            throw new EmptyBodyException("Empty body in responseEntity");
        }
    }
}
