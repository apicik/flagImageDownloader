package com.pitsik.flagdownloader.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlagRsDto {

    private String codeFlag;
    private String countryName;
    private byte[] flagInBytes;

}
