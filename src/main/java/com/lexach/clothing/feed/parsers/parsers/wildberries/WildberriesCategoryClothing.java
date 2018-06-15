package com.lexach.clothing.feed.parsers.parsers.wildberries;

import com.lexach.clothing.feed.parsers.parsers.AbstractClothingParserCategory;
import com.lexach.clothing.feed.parsers.service.ProductCategoryService;

/**
 * Wildberries category entity
 */
public class WildberriesCategoryClothing extends AbstractClothingParserCategory {

    WildberriesCategoryClothing(String categoryName, String uri, ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
        this.link = "https://www.wildberries.ru/catalog" + uri;
        this.setProductCategory(categoryName);
    }




}
