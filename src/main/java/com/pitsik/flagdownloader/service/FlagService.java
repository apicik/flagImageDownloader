package com.pitsik.flagdownloader.service;

import com.pitsik.flagdownloader.exception.EmptyBodyException;

import java.util.List;

public interface FlagService {
    List<String> getAndDownloadFlags() throws EmptyBodyException;
}
