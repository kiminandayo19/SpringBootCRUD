package com.arif.bootcamp.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class BaseResponseDto {
    public HttpStatus status;
    public String message;
    private Object data;
}
