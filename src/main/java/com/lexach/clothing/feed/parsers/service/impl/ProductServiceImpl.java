package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.model.Product;
import com.lexach.clothing.feed.parsers.repository.ProductRepository;
import com.lexach.clothing.feed.parsers.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl() {
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getOrCreate(Product productParam) {
        Product databaseProduct = productRepository.findByUrl(productParam.getUrl());

        // If existing databaseProduct is null -- return productParam.
        // Otherwise -- return existing Product from database.
        if(Objects.isNull(databaseProduct)) {
            return productParam;
        } else {
            return databaseProduct;
        }
    }
}
