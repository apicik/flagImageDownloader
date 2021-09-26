package com.pitsik.flagdownloader.service.impl;

import com.pitsik.flagdownloader.dto.FlagRsDto;
import com.pitsik.flagdownloader.dto.RestCountriesRsDto;
import com.pitsik.flagdownloader.exception.EmptyBodyException;
import com.pitsik.flagdownloader.publisher.impl.FlagWriter;
import com.pitsik.flagdownloader.service.CountryProducer;
import com.pitsik.flagdownloader.service.FlagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class DownloadAndSaveFlagServiceImpl implements FlagService {

    final CountryProducer countryFlags;
    final GeoNameImageService geoNameImageService;
    final FlagWriter writePublisher;

    public List<String> getAndDownloadFlags() throws EmptyBodyException {

        List<RestCountriesRsDto> restCountriesRsDtoList = countryFlags.getCountry();
        List<FlagRsDto> flagImageInBytes = geoNameImageService.getFlagImagesInBytes(restCountriesRsDtoList);

        return writePublisher.publish(flagImageInBytes);
    }

}
