package com.lexach.ClothingFeedParsers.parsers;

import com.lexach.ClothingFeedParsers.model.*;
import com.lexach.ClothingFeedParsers.service.*;
import com.lexach.ClothingFeedParsers.service.impl.CountryServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class AbstractParser {

    // Services
    @Autowired
    protected RetailerService retailerService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected ProductCategoryService productCategoryService;

    @Autowired
    protected CountryService countryService;

    @Autowired
    protected GenderService genderService;

    // Other Data.
    protected Document root;

    protected Retailer retailer;

    // Gender name oppsoite gender link.
    protected Map<String, String> genders;

    AbstractParser(String rootLink, String name, String menGenderLink, String womenGenderLink) {

        try {
            this.root = Jsoup.connect(rootLink).get();

            Elements links = root.select("a[href]");

        } catch (IOException exception) {
            // TODO add exception handler.
        }

        // Assign retailer field.
        this.retailer = new Retailer(name, rootLink, this.getClass().getName());
        // Check database for retailer, if it exists.
        this.retailer = retailerService.getOrCreate(this.retailer);

        // Put men category link
        this.genders.put("Men", menGenderLink);
        // Put women category link
        this.genders.put("Women", womenGenderLink);
    }

    /**
     * Parse product.
     * @param productLink Link to an product.
     * @throws IOException TODO add IOexception handling.
     */
    protected abstract void parseProduct(String productLink, ProductCategory categoryParam, Gender genderParam) throws IOException;

    /**
     * Main method, that parses etire site.
     * @throws IOException TODO add IOException handling.
     */
    protected void parseRoot() throws IOException {

        String menCategoryLink = genders.get("Men");
        parseGender(menCategoryLink, "Men");

        String womenCategoryLink = genders.get("Women");
        parseGender(womenCategoryLink, "Women");

    }

    /**
     * Method that parses some gender and saves categories.
     * @param genderLink Link to gender
     * @throws IOException TODO add IOException handling.
     */
    protected abstract void parseGender(String genderLink, String genderName) throws IOException;

    /**
     * Method, that parses some category.
     * @param categoryLink Link to category
     */
    protected abstract void parseCategory(String categoryLink, String categoryName) throws IOException;


    /**
     * Cretes or finds country in the database and assign corresponding product field.
     * @param product Product with "country" field to assign.
     * @param countryName Name of the country to create or find + assign.
     * @return Finded in the database, or created country object.
     */
    protected Country registerAndSetManufacturedCountry(Product product, String countryName) {
        Country varCountry = new Country(countryName);

        countryService.getOrCreate(varCountry);

        product.setManufacturedCountry(varCountry);

        return varCountry;
    }
}
