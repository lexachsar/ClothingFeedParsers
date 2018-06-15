package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByUrl(String url);

}
