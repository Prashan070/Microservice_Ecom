package com.ecom.product_service.service.impl;

import com.ecom.product_service.dto.ProductRequestDto;
import com.ecom.product_service.dto.ProductResponseDto;
import com.ecom.product_service.entity.Category;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.mapper.ProductMapper;
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
        Product product = ProductMapper.ProductRequestDTOToProductMapper(productRequestDto);
        product.setCategory(category);
        Product savedproduct = productRepository.save(product);
        ProductResponseDto productResponseDto = ProductMapper.ProductToProductResponseDTOMapper(savedproduct);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Not Found"));
        return ProductMapper.ProductToProductResponseDTOMapper(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::ProductToProductResponseDTOMapper).toList();
    }

    @Override
    public ProductResponseDto updateProduct(String productId, ProductRequestDto productRequestDto) {

        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Not Found"));

        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


        existingProduct.setName(productRequestDto.getName());
        existingProduct.setDescription(productRequestDto.getDescription());
        existingProduct.setPrice(productRequestDto.getPrice());
        existingProduct.setStockQuantity(productRequestDto.getStockQuantity());
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.ProductToProductResponseDTOMapper(updatedProduct);
    }

    @Override
    public ProductResponseDto updateStock(String productId, Integer stockQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStockQuantity(stockQuantity);
        productRepository.save(product);
        return ProductMapper.ProductToProductResponseDTOMapper(product);
    }

}
