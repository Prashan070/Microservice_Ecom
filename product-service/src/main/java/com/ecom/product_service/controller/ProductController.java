package com.ecom.product_service.controller;

import com.ecom.product_service.dto.ProductRequestDto;
import com.ecom.product_service.dto.ProductResponseDto;
import com.ecom.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {

        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable String productId){
        return  new ResponseEntity<>(productService.getProductById(productId), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return  new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<ProductResponseDto> updateStock(@PathVariable String productId,@RequestParam Integer stockQuantity){
     return new ResponseEntity<>(productService.updateStock(productId,stockQuantity),HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId){
        return  new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }
}
