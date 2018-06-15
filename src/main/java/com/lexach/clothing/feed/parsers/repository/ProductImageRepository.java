package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.ProductImage;
import org.springframework.data.repository.CrudRepository;


public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

    ProductImage findByImageUrl(String imageUrl);

}
