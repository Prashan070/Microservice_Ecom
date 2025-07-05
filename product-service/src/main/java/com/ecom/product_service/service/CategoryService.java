package com.ecom.product_service.service;

import com.ecom.product_service.dto.CategoryRequestDto;
import com.ecom.product_service.dto.CategoryResponseDto;
import com.ecom.product_service.dto.CategoryWithProductsDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryWithProductsDto getCategoryById(String categoryId);
    List<CategoryWithProductsDto> getAllCategories();
    CategoryResponseDto updateCategoryById(String categoryId, CategoryRequestDto categoryRequestDto);
    void deleteCategoryById(String categoryId);
}
