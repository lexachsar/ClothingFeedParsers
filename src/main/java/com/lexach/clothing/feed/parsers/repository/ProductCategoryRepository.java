package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

    ProductCategory findByName(String Name);

}
