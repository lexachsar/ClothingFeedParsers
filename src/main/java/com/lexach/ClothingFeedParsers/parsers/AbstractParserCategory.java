package com.lexach.ClothingFeedParsers.parsers;

import com.lexach.ClothingFeedParsers.model.ProductCategory;
import com.lexach.ClothingFeedParsers.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractParserCategory {

    @Autowired
    protected ProductCategoryService productCategoryService;

    /**
     * Product category entity.
     */
    protected ProductCategory productCategory;

    /**
     * Link to an category
     */
    public String link;

    /**
     * Set product category field by category name.
     * @param categoryName name of the category to be created
     */
    public void setProductCategory(String categoryName) {
        // Creating var ProductCategory entity, defined by name.
        ProductCategory varProductCategory = new ProductCategory(categoryName);

        // Getting entity from database, or creating new one.
        this.productCategory = productCategoryService.getOrCreate(varProductCategory);

        // If product category is new -- save it.
        productCategoryService.save(this.productCategory);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getLink() {
        return link;
    }

    public ProductCategory save() {
        return this.productCategoryService.save(this.productCategory);
    }

}
