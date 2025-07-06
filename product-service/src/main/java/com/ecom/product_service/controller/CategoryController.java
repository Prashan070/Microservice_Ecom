package com.ecom.product_service.controller;

import com.ecom.product_service.dto.CategoryRequestDto;
import com.ecom.product_service.dto.CategoryResponseDto;
import com.ecom.product_service.dto.CategoryWithProductsDto;
import com.ecom.product_service.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody  CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<CategoryWithProductsDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.FOUND);
    }

    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryWithProductsDto> getCategoryById(@PathVariable String categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public  ResponseEntity<String> deleteCategoryById(@PathVariable String categoryId){
        return new ResponseEntity<>(categoryService.deleteCategoryById(categoryId),HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategoryById(@PathVariable String categoryId, @RequestBody CategoryRequestDto categoryRequestDto){
        return new ResponseEntity<>(categoryService.updateCategoryById(categoryId,categoryRequestDto), HttpStatus.OK);
    }


}
