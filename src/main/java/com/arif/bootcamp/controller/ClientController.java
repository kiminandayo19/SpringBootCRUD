package com.arif.bootcamp.controller;

import com.arif.bootcamp.dto.response.CatFactResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<CatFactResponseDto> getCat() {
        String url = "https://catfact.ninja/fact";
        return restTemplate.getForEntity(url, CatFactResponseDto.class);
    }
}
