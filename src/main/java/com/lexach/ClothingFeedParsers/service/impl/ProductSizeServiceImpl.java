package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.model.ProductSize;
import com.lexach.ClothingFeedParsers.repository.ProductSizeRepository;
import com.lexach.ClothingFeedParsers.service.ProductSizeService;
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
