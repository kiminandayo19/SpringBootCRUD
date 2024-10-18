package com.arif.bootcamp.service;

import com.arif.bootcamp.dto.BaseResponseDto;
import com.arif.bootcamp.exception.ProductNotFoundException;

import java.io.IOException;

public interface BaseFileService {
    BaseResponseDto saveFile() throws IOException;
    BaseResponseDto getReport(Long id) throws ProductNotFoundException;
}
