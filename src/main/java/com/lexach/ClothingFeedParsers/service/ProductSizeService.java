package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.ProductSize;
import org.springframework.stereotype.Service;

@Service
public interface ProductSizeService {


    ProductSize save(ProductSize product);

    /**
     * Gets product size if it's presented in database.
     * Otherwise creates new instance of ProductSize object.
     * @return New or existing product size.
     * @param productSizeParam New product size instance created outside of the database.
     */
    public ProductSize getOrCreate(ProductSize productSizeParam);


}
