package com.arif.bootcamp.controller;

import com.arif.bootcamp.dto.BaseResponseDto;
import com.arif.bootcamp.dto.request.ProductRequestDto;
import com.arif.bootcamp.exception.ProductNotFoundException;
import com.arif.bootcamp.service.ProductService;
import org.hibernate.annotations.SoftDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/id={id}")
    public ResponseEntity<BaseResponseDto> getSingleProduct(@PathVariable Long id) {
        BaseResponseDto responseDto = productService.getSingleProduct(id);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAllProduct() throws ProductNotFoundException {
        try {
            BaseResponseDto responseDto = productService.getAllProduct();
            return new ResponseEntity<>(responseDto, responseDto.getStatus());
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<BaseResponseDto> createProduct(@RequestBody ProductRequestDto product) {
        try {
            BaseResponseDto responseDto = productService.createProduct(product);
            return new ResponseEntity<>(responseDto, responseDto.getStatus());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/id={id}")
    public ResponseEntity<BaseResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto product) throws ProductNotFoundException {
        BaseResponseDto responseDto = productService.updateProduct(id, product);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @DeleteMapping("/delete/id={id}")
    public ResponseEntity<BaseResponseDto> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        BaseResponseDto responseDto = productService.deleteProduct(id);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @DeleteMapping("/soft-delete/id={id}")
    public ResponseEntity<BaseResponseDto> softDeleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        BaseResponseDto responseDto = productService.softDeleteProduct(id);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

}
