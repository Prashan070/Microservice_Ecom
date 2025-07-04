package com.ecom.product_service.service.impl;

import com.ecom.product_service.dto.ProductRequestDto;
import com.ecom.product_service.dto.ProductResponseDto;
import com.ecom.product_service.entity.Category;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.mapper.ProductRequestDTOToProduct;
import com.ecom.product_service.mapper.ProductToProductResponseDTO;
import com.ecom.product_service.repository.CategoryRepository;
import com.ecom.product_service.repository.ProductRepository;
import com.ecom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = ProductRequestDTOToProduct.ProductRequestDTOToProductMapper(productRequestDto);
        product.setCategory(category);
        Product savedproduct = productRepository.save(product);
        ProductResponseDto productResponseDto = ProductToProductResponseDTO.ProductToProductResponseDTOMapper(savedproduct);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProductById(String productId) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductToProductResponseDTO::ProductToProductResponseDTOMapper).toList();
    }

    @Override
    public ProductResponseDto updateProduct(String productId, ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public ProductResponseDto updateStock(String productId, Integer stockQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStockQuantity(stockQuantity);
        productRepository.save(product);
        return ProductToProductResponseDTO.ProductToProductResponseDTOMapper(product);
    }

}
