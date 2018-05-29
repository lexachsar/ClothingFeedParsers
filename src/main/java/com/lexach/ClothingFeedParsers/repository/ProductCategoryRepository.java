package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

    ProductCategory findByName(String Name);

}
