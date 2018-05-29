package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.Product;

public interface ProductService {

    Product save(Product product);

    /**
     * Gets product if it's presented in database.
     * Otherwise creates new instance of Product object.
     * @return New or existing product.
     * @param productParam New product instance created outside of the database.
     */
    public Product getOrCreate(Product productParam);

}
