package com.lexach.ClothingFeedParsers.parsers.wildberries;

import com.lexach.ClothingFeedParsers.parsers.AbstractClothingParserCategory;
import com.lexach.ClothingFeedParsers.service.ProductCategoryService;

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
