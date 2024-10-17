package com.arif.bootcamp.dto.response;

import com.arif.bootcamp.dto.AuditingDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResponseDto extends AuditingDto {
    private Long id;
    private String productName;
    private double productPrice;
    private int productQuantity;

    @JsonIgnore
    private String message;
}
