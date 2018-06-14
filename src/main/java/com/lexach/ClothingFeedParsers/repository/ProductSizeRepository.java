package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.ProductSize;
import org.springframework.data.repository.CrudRepository;

public interface ProductSizeRepository extends CrudRepository<ProductSize, Long> {

    public ProductSize findBySize(String size);

}
