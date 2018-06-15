package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.model.ProductSize;
import com.lexach.clothing.feed.parsers.repository.ProductSizeRepository;
import com.lexach.clothing.feed.parsers.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public ProductSize save(ProductSize product) {
        return productSizeRepository.save(product);
    }

    @Override
    public ProductSize getOrCreate(ProductSize productSizeParam) {
        ProductSize databaseProductSize = productSizeRepository.findBySize(productSizeParam.getSize());

        if(Objects.isNull(databaseProductSize)) {
            return productSizeParam;
        } else {
            return databaseProductSize;
        }
    }
}
