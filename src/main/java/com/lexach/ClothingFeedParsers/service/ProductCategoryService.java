package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.ProductCategory;
import org.springframework.stereotype.Service;

@Service
public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    /**
     * Gets ProductCategory if it's presented in database.
     * Otherwise creates new instance of ProductCategory object.
     * @return New or existing ProductCategory.
     */
    public ProductCategory getOrCreate(ProductCategory categoryParam);

}
