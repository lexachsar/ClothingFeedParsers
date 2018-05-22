package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.Product;

public interface ProductService {

    /**
     * Create Product Object.
     * @return
     */
    Product createProduct();

    Product compareProducts(Product prod1, Product prod2);

    Product registerProduct();

    Product updateProduct();

}
