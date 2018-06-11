package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.Colour;
import com.lexach.ClothingFeedParsers.model.Product;
import com.lexach.ClothingFeedParsers.model.ProductColour;
import org.springframework.data.repository.CrudRepository;

public interface ProductColourRepository extends CrudRepository<ProductColour, Long> {

    ProductColour findByProductAndColour(Product product, Colour colour);

}
