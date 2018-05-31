package com.lexach.ClothingFeedParsers.parsers.wildberries;

import com.lexach.ClothingFeedParsers.model.ProductCategory;
import com.lexach.ClothingFeedParsers.parsers.AbstractParserCategory;
import com.lexach.ClothingFeedParsers.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Wildberries category entity
 */
public class WildberriesCategory extends AbstractParserCategory {

    WildberriesCategory(String categoryName, String uri, ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
        this.link = "https://www.wildberries.ru/catalog" + uri;
        this.setProductCategory(categoryName);
    }




}
