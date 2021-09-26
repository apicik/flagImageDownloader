package com.pitsik.flagdownloader.service;

import com.pitsik.flagdownloader.dto.FlagRsDto;
import com.pitsik.flagdownloader.dto.RestCountriesRsDto;

import java.util.List;

public interface ImageService {
    List<FlagRsDto> getFlagImagesInBytes(List<RestCountriesRsDto> restCountriesRsDtoList);
}
