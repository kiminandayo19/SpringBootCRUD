package com.arif.bootcamp.dto.request;

import com.arif.bootcamp.dto.AuditingDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductRequestDto extends AuditingDto {
    private String productName;
    private double productPrice;
    private int productQuantity;
}
