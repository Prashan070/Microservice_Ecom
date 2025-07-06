package com.ecom.product_service.mapper;

import com.ecom.product_service.dto.ProductRequestDto;
import com.ecom.product_service.dto.ProductResponseDto;
import com.ecom.product_service.entity.Product;

public class ProductMapper {

    public static Product ProductRequestDTOToProductMapper(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStockQuantity(productRequestDto.getStockQuantity());
        return product;
    }

    public static ProductResponseDto ProductToProductResponseDTOMapper(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(product.getProductId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStockQuantity(product.getStockQuantity());
        productResponseDto.setInStock(product.getInStock());
        productResponseDto.setCategoryName(product.getCategory().getName());
        return productResponseDto;
    }
}
