package com.lexach.clothing.feed.parsers.service;

import com.lexach.clothing.feed.parsers.model.Product;
import org.springframework.stereotype.Service;

@Service
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
