package com.lexach.ClothingFeedParsers.parsers;

import org.jsoup.select.Elements;

import java.io.IOException;

public interface ParserInterface {

    /**
     * Main method, that parses etire site.
     */
    void parseRoot();

    /**
     * Method, that parses some category.
     * @param categoryLink Link to category
     */
    void parseCategory(String categoryLink) throws IOException;

    /**
     * Parse product.
     */
    void parseProduct(String productLink) throws IOException;


}