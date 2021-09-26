package com.pitsik.flagdownloader.controller;

import com.pitsik.flagdownloader.exception.EmptyBodyException;
import com.pitsik.flagdownloader.service.FlagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class FlagController {

    FlagService downloadAndSaveFlagServiceImpl;

    @GetMapping("/download-flags")
    public List<String> getAndDownloadFlags() {
        log.info("Получен GET запрос");
        List<String> resultFlags;
        try {
            resultFlags = downloadAndSaveFlagServiceImpl.getAndDownloadFlags();
        } catch (EmptyBodyException e) {
            log.error("Не удалось получить список флагов", e);
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Не удалось получить список флагов у стороннего API"
            );
        }
        log.info("Сохранено {} флагов стран: {}", resultFlags.size(), resultFlags);
        return resultFlags;
    }

}
