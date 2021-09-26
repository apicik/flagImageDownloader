package com.pitsik.flagdownloader.publisher.impl;

import com.pitsik.flagdownloader.dto.FlagRsDto;
import com.pitsik.flagdownloader.publisher.FlagPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FlagWriter implements FlagPublisher {
    @Value("${base.dir}")
    private String BASE_DIR;
    @Value("${end.dir}")
    private String END_DIR;

    @Override
    public List<String> publish(List<FlagRsDto> flagInBytes) {
        List<String> flagNameList = new ArrayList<>();
        flagInBytes.parallelStream().forEach(flag ->
                {
                    Path outputFile = Paths.get(BASE_DIR + END_DIR + flag.getCodeFlag() + ".gif");
                    try {
                        Files.write(outputFile, flag.getFlagInBytes());
                        flagNameList.add(flag.getCountryName());
                        log.info
                                (
                                        "Сохранили флаг страны: {} в файл: {}", flag.getCountryName(),
                                        outputFile.getFileName().toString()
                                );
                    } catch (IOException e) {
                        log.error("Не удалось сохранить файл: {}", outputFile.getFileName().toString(), e);
                    }
                }
        );
        return flagNameList.stream().sorted(String::compareTo).collect(Collectors.toList());
    }

    @PostConstruct
    private void createFolders() {
        new File(BASE_DIR + END_DIR).mkdirs();
    }

}
