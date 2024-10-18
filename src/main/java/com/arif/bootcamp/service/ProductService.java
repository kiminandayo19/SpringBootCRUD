package com.arif.bootcamp.service;

import com.arif.bootcamp.dto.BaseResponseDto;
import com.arif.bootcamp.dto.request.ProductRequestDto;
import com.arif.bootcamp.exception.ProductNotFoundException;

public interface ProductService {
    // Interface for service method
    BaseResponseDto getSingleProduct(Long id);
    BaseResponseDto getAllProduct() throws ProductNotFoundException;
    BaseResponseDto createProduct(ProductRequestDto product) throws ProductNotFoundException;
    BaseResponseDto updateProduct(Long id, ProductRequestDto product) throws ProductNotFoundException;
    BaseResponseDto deleteProduct(Long id) throws ProductNotFoundException;
    BaseResponseDto softDeleteProduct(Long id) throws ProductNotFoundException;
}
