package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.model.ProductImage;
import com.lexach.ClothingFeedParsers.repository.ProductImageRepository;
import com.lexach.ClothingFeedParsers.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public ProductImage getOrCreate(ProductImage imageParam) {

        ProductImage databaseImage = productImageRepository.findByImageUrl(imageParam.getImageUrl());

        if(Objects.isNull(databaseImage)) {
            return imageParam;
        } else {
            return databaseImage;
        }

    }

    @Override
    public ProductImage save(ProductImage image) {
        return productImageRepository.save(image);
    }
}