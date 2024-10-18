package com.arif.bootcamp.service.impl;

import com.arif.bootcamp.dto.BaseResponseDto;
import com.arif.bootcamp.entity.BaseFile;
import com.arif.bootcamp.exception.ProductNotFoundException;
import com.arif.bootcamp.repository.BaseFileRepository;
import com.arif.bootcamp.service.BaseFileService;
import com.arif.bootcamp.service.GenerateCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class BaseFileServiceImpl implements BaseFileService {

    @Autowired
    BaseFileRepository baseFileRepository;

    @Autowired
    GenerateCsvService generateCsvService;

    @Override
    public BaseResponseDto saveFile() {
        try {
            BaseFile baseFile = new BaseFile();
            baseFile.setFileName("product.csv");
            baseFile.setFileType("text/csv");
            baseFile.setFileData(generateCsvService.generateReportCsv());
            BaseFile fileMetaData = baseFileRepository.save(baseFile);
            Map<String, Long> responseId = new HashMap<>();
            responseId.put("id", fileMetaData.getId());
            List<Map<String, Long>> responseIdDto = Collections.singletonList(responseId);
            return BaseResponseDto.builder().status(HttpStatus.OK).message("Success Generating File").data(responseIdDto).build();
        } catch (IOException e) {
           return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();
        }
    }

    @Override
    public BaseResponseDto getReport(Long id) {
        try {
            BaseFile baseFile = baseFileRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Report with id: " + id + "not found"));
            return BaseResponseDto.builder().status(HttpStatus.OK).message("Success Get Report").data(Collections.singletonList(baseFile)).build();
        } catch (Exception e) {
            return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();
        }
    }

}
