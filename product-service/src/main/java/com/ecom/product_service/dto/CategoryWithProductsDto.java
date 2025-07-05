package com.ecom.product_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryWithProductsDto extends CategoryResponseDto {
    private List<ProductResponseDto> product;
}
