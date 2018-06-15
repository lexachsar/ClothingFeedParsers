package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.ProductSize;
import org.springframework.data.repository.CrudRepository;

public interface ProductSizeRepository extends CrudRepository<ProductSize, Long> {

    public ProductSize findBySize(String size);

}
