package com.ecom.product_service.mapper;

import com.ecom.product_service.dto.ProductRequestDto;
import com.ecom.product_service.dto.ProductResponseDto;
import com.ecom.product_service.entity.Product;

public class ProductRequestDTOToProduct {

    public static Product ProductRequestDTOToProductMapper(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setStockQuantity(product.getStockQuantity());
        return product;
    }
}
