package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.ProductImage;
import org.springframework.data.repository.CrudRepository;


public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

    ProductImage findByImageUrl(String imageUrl);

}
