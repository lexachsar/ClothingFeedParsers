package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.ProductColour;
import org.springframework.stereotype.Service;

@Service
public interface ProductColourService {

    /**
     * Gets ProductColour if it's presented in database.
     * Otherwise creates new instance of ProductColour object.
     * @return New or existing ProductColour.
     * @param productParam New ProductColour instance created outside of the database.
     */
    public ProductColour getOrCreate(ProductColour productParam);

    public ProductColour save(ProductColour productColour);
}