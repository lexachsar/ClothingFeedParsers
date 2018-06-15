package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.model.ProductColour;
import com.lexach.clothing.feed.parsers.repository.ProductColourRepository;
import com.lexach.clothing.feed.parsers.service.ProductColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductColourServiceImpl implements ProductColourService {
    @Autowired
    private ProductColourRepository productColourRepository;

    @Override
    public ProductColour getOrCreate(ProductColour productColourParam) {
      /*  ProductColour databaseProductColour = productColourRepository.findByProductAndColour(productColourParam.getProduct(), productColourParam.getColour());

        // If existing databaseProductColour is null -- return productColourParam.
        // Otherwise -- return existing ProductCategory from database.
        if(Objects.isNull(databaseProductColour)) {
            return productColourParam;
        } else {
            return databaseProductColour;
        }
        */
        return null;
    }

    @Override
    public ProductColour save(ProductColour productColour) {
        return productColourRepository.save(productColour);
    }
}
