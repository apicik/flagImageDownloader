package com.pitsik.flagdownloader.publisher;

import com.pitsik.flagdownloader.dto.FlagRsDto;

import java.util.List;

public interface FlagPublisher {
    List<String> publish(List<FlagRsDto> flagInBytes);
}
