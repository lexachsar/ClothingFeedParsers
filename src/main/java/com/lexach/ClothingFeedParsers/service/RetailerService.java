package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.Retailer;

/**
 * Retailer must have it's own parser named RETAILERNAMEParser.
 */
public interface RetailerService {

    public Retailer save(Retailer retailer);

    /**
     * @param parserClassName Full class name of parser, that do the parsing for this retailer
     * @return Retailer object.
     */
    public Retailer loadRetailerByParserClassName(String parserClassName);

    /**
     * Gets retailer if it's presented in database.
     * Otherwise creates new instance of Retailer object.
     * @return New or existing retailer.
     * @param retailerParam Existing retailer, created outside of the database.
     */
    public Retailer getOrCreate(Retailer retailerParam);

}
