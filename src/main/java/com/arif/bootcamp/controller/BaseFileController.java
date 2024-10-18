package com.arif.bootcamp.controller;

import com.arif.bootcamp.dto.BaseResponseDto;
import com.arif.bootcamp.service.BaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/report")
public class BaseFileController {

    @Autowired
    BaseFileService baseFileService;

    @GetMapping
    public ResponseEntity<BaseResponseDto> saveReport() throws IOException {
        BaseResponseDto baseResponseDto = baseFileService.saveFile();
        return new ResponseEntity<>(baseResponseDto, baseResponseDto.getStatus());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BaseResponseDto> getReport(@PathVariable Long id) throws Exception {
        BaseResponseDto baseResponseDto = baseFileService.getReport(id);
        return new ResponseEntity<>(baseResponseDto, baseResponseDto.getStatus());
    }
}
