package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByNameAndBrandName(String name, String brandName);

}
