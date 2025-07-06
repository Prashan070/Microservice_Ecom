package com.ecom.product_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryWithProductsDto extends CategoryResponseDto {
    private List<ProductResponseDto> product;

    public CategoryWithProductsDto(String categoryId, String name, String description, List<ProductResponseDto> product) {
        super(categoryId,name,description);
        this.product = product;
    }
}
