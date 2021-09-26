package com.pitsik.flagdownloader.service.impl;

import com.pitsik.flagdownloader.dto.FlagRsDto;
import com.pitsik.flagdownloader.dto.RestCountriesRsDto;
import com.pitsik.flagdownloader.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GeoNameImageService implements ImageService {

    @Value("${url.image}")
    private String URL_IMAGE;

    public List<FlagRsDto> getFlagImagesInBytes(List<RestCountriesRsDto> restCountriesRsDtoList) { // get flag from service

        List<FlagRsDto> flagImagesInBytes = restCountriesRsDtoList.stream()
                .map(country -> {
                            try {
                                String codeFlag = country.getCca2().toLowerCase();
                                String countryName = country.getName().getCommon();
                                byte[] flagBytes = new RestTemplate().getForObject
                                        (
                                                URL_IMAGE + codeFlag + ".gif",
                                                byte[].class
                                        );
                                log.info("Сохраняем флаг: {} страны: {}", codeFlag, countryName);
                                return new FlagRsDto(codeFlag, countryName, flagBytes);
                            } catch (RestClientException e) {
                                log.error("Не удалось получить байты для флага: {} страны: {}",
                                        country.getCca2(), country.getName().getCommon());
                                return null;
                            }
                        }
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("Получены байты для {} флагов: {}", flagImagesInBytes.size(),
                flagImagesInBytes.stream().map(FlagRsDto::getCountryName).collect(Collectors.toList()));

        return flagImagesInBytes;
    }

}
