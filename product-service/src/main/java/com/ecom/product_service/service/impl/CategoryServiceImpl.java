package com.ecom.product_service.service.impl;

import com.ecom.product_service.dto.CategoryRequestDto;
import com.ecom.product_service.dto.CategoryResponseDto;
import com.ecom.product_service.dto.CategoryWithProductsDto;
import com.ecom.product_service.entity.Category;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.mapper.CategoryMapper;
import com.ecom.product_service.mapper.ProductMapper;
import com.ecom.product_service.repository.CategoryRepository;
import com.ecom.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = CategoryMapper.CategoryRequestDTOToCategoryMapper(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.CategoryToCategoryResponseDTOMapper(savedCategory);
    }

    @Override
    public CategoryWithProductsDto getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("No Found"));
        return convertToCategoryWithProductsDto(category);
    }

    @Override
    public List<CategoryWithProductsDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryWithProductsDto> categoryWithProductsDtos = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryWithProductsDto categoryWithProductsDto = convertToCategoryWithProductsDto(category);
            categoryWithProductsDtos.add(categoryWithProductsDto);
        }
        return categoryWithProductsDtos;
    }

    @Override
    public CategoryResponseDto updateCategoryById(String categoryId, CategoryRequestDto categoryRequestDto) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("No Found"));
        savedCategory.setName(categoryRequestDto.getName());
        savedCategory.setDescription(categoryRequestDto.getDescription());
        Category updatedCategory = categoryRepository.save(savedCategory);
        return CategoryMapper.CategoryToCategoryResponseDTOMapper(updatedCategory);
    }

    @Override
    public String deleteCategoryById(String categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Not Found"));
        categoryRepository.deleteById(categoryId);
        return "Deleted";
    }


    private CategoryWithProductsDto convertToCategoryWithProductsDto(Category category) {
        List<Product> productList = category.getProducts();
        CategoryWithProductsDto categoryWithProductsDto = new CategoryWithProductsDto();
        categoryWithProductsDto.setCategoryId(category.getCategoryId());
        categoryWithProductsDto.setName(category.getName());
        categoryWithProductsDto.setDescription(category.getDescription());
        categoryWithProductsDto.setProduct(productList.stream().map(ProductMapper::ProductToProductResponseDTOMapper).toList());
        return categoryWithProductsDto;
    }
}
