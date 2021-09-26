package com.pitsik.flagdownloader.service;

import com.pitsik.flagdownloader.dto.RestCountriesRsDto;
import com.pitsik.flagdownloader.exception.EmptyBodyException;

import java.util.List;

public interface CountryProducer {
    List<RestCountriesRsDto> getCountry() throws EmptyBodyException;
}
