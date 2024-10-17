package com.arif.bootcamp.service.impl;

import com.arif.bootcamp.dto.BaseResponseDto;
import com.arif.bootcamp.dto.request.ProductRequestDto;
import com.arif.bootcamp.dto.response.ProductResponseDto;
import com.arif.bootcamp.entity.Product;
import com.arif.bootcamp.exception.ProductNotFoundException;
import com.arif.bootcamp.repository.ProductRepository;
import com.arif.bootcamp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    private ProductResponseDto querySingleProduct(Long id) throws ProductNotFoundException {
        try {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            Product product = productRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
            productResponseDto.setId(id);
            productResponseDto.setProductName(product.getProductName());
            productResponseDto.setProductPrice(product.getProductPrice());
            productResponseDto.setProductQuantity(product.getProductQuantity());
            return productResponseDto;
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(e.getMessage());
        }
    }

    private BaseResponseDto saveOrUpdate(Long id, ProductRequestDto requestProduct, HttpStatus httpStatus, String message) {
        try {
            Product product = new Product();
            if (id != null) {
                querySingleProduct(id);
                product.setId(id);
            }
            product.setProductName(requestProduct.getProductName());
            product.setProductPrice(requestProduct.getProductPrice());
            product.setProductPrice(requestProduct.getProductPrice());

            Product savedProduct = productRepository.save(product);
            Map<String, Long> responseId = new HashMap<>();
            responseId.put("id", savedProduct.getId());
            List<Map<String, Long>> responseDto = Collections.singletonList(responseId);
            return BaseResponseDto.builder().status(httpStatus).message(message).data(responseDto).build();
        } catch (ProductNotFoundException e) {
            return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();
        }
    }

    @Override
    public BaseResponseDto getSingleProduct(Long id) {
        try {
            List<ProductResponseDto> products = new ArrayList<>();
            ProductResponseDto product = querySingleProduct(id);
            if (product.getMessage() == null) {
                products.add(product);
                return BaseResponseDto.builder().status(HttpStatus.OK).message("Product Found").data(products).build();
            } else {
                return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message("Bad Request").data(new ArrayList<>()).build();
            }
        } catch (ProductNotFoundException e) {
            return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();

        }
    }

    @Override
    public BaseResponseDto getAllProduct() throws ProductNotFoundException {
        try {
            List<Product> products = productRepository.findAllByIsDeletedFalse();
            if (!products.isEmpty()) {
                return BaseResponseDto.builder().status(HttpStatus.OK).message("Products Found").data(products).build();
            } else {
                throw new ProductNotFoundException("Failed to get all products");
            }
        } catch (ProductNotFoundException e) {
            return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();
        }
    }

    @Override
    public BaseResponseDto createProduct(ProductRequestDto product) {
        return saveOrUpdate(null, product, HttpStatus.CREATED, "Product Added");
    }

    @Override
    public BaseResponseDto updateProduct(Long id, ProductRequestDto updateProduct) throws ProductNotFoundException {
        return saveOrUpdate(id, updateProduct, HttpStatus.OK, "Product Updated");
    }

    @Override
    public BaseResponseDto deleteProduct(Long id) throws ProductNotFoundException {
        try {
            ProductResponseDto product = querySingleProduct(id);
            productRepository.deleteById(product.getId());
            Map<String, Long> responseId = new HashMap<>();
            responseId.put("id", product.getId());
            List<Map<String, Long>> responseDto = Collections.singletonList(responseId);
            return BaseResponseDto.builder().status(HttpStatus.OK).message("Delete Success").data(responseDto).build();
        } catch (ProductNotFoundException e) {
            return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();
        }
    }

    @Override
    public BaseResponseDto softDeleteProduct(Long id) {
        try {
            ProductResponseDto product = querySingleProduct(id);
            Product updatedProduct = new Product();
            updatedProduct.setId(product.getId());
            updatedProduct.setProductName(product.getProductName());
            updatedProduct.setProductPrice(product.getProductPrice());
            updatedProduct.setProductQuantity(product.getProductQuantity());
            updatedProduct.setIsDeleted(true);
            Product newProduct = productRepository.save(updatedProduct);
            if (newProduct.getId() != null) {
                Map<String, Long> productId = new HashMap<>();
                productId.put("id", newProduct.getId());
                return BaseResponseDto.builder().status(HttpStatus.OK).message("Delete Success").data(Collections.singletonList(productId)).build();
            } else {
                throw new ProductNotFoundException("Delete Failed");
            }
        } catch (ProductNotFoundException e) {
            return BaseResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).data(new ArrayList<>()).build();
        }
    }

}
