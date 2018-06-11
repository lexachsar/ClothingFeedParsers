package com.lexach.ClothingFeedParsers.parsers;

import com.lexach.ClothingFeedParsers.model.*;
import com.lexach.ClothingFeedParsers.service.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public abstract class AbstractClothingParser extends AbstractParser {

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

    @Autowired
    protected ProductImageService productImageService;

    @Autowired
    protected  ColourService colourService;
    @Autowired
    protected  ProductColourService productColourService;

    protected Retailer retailer;

    // Gender name opposite gender link.
    protected Map<String, String> genders;

    public void init(String rootLink, String name, String menGenderLink, String womenGenderLink) {

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
        this.retailerService.save(this.retailer);

        this.genders = new HashMap();
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
     * Main method, that parses entire site.
     * @throws IOException TODO add IOException handling.
     */
    @Scheduled(cron = "30 2 * * * MON-FRI")
    public void parseRoot() {

        String womenCategoryLink = genders.get("Women");
        parseGender(womenCategoryLink, "Women");

        String menCategoryLink = genders.get("Men");
        parseGender(menCategoryLink, "Men");


    }

    /**
     * Method that parses some gender and saves categories.
     * @param genderLink Link to gender
     * @throws IOException TODO add IOException handling.
     */
    protected abstract void parseGender(String genderLink, String genderName);

    /**
     * Method, that parses some category.
     * @param category category to parse.
     * @param genderParam gender entity, needed for parseProduct() method.
     */
    protected abstract void parseCategory(AbstractClothingParserCategory category, Gender genderParam) throws IOException;


    /**
     * Cretes or finds country in the database and assign corresponding product field.
     * @param product Product with "country" field to assign.
     * @param countryName Name of the country to create or find + assign.
     * @return Finded in the database, or created country object.
     */
    // TODO Fix org.hibernate.exception.ConstraintViolationException exception
    protected Country registerAndSetManufacturedCountry(Product product, String countryName) {
        Country varCountry = new Country(countryName);

        countryService.getOrCreate(varCountry);

        product.setManufacturedCountry(varCountry);

        return varCountry;
    }
}
