package com.ecom.product_service.mapper;


import com.ecom.product_service.dto.CategoryRequestDto;
import com.ecom.product_service.dto.CategoryResponseDto;
import com.ecom.product_service.entity.Category;

public class CategoryMapper {

    public static CategoryResponseDto CategoryToCategoryResponseDTOMapper(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(category.getCategoryId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDiscription(category.getDescription());
        return categoryResponseDto;
    }


    public static Category CategoryRequestDTOToCategoryMapper(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        return category;
    }
}
