package com.ecom.product_service.config;


public class IdGenerator {
    private static int categoryIdCounter = 0;
    private static int productIdCounter = 0;

    public static synchronized int generateCategoryId() {
        categoryIdCounter++;
        return categoryIdCounter;
    }

    public static synchronized int generateProductId() {
        productIdCounter++;
        return productIdCounter;
    }

}
