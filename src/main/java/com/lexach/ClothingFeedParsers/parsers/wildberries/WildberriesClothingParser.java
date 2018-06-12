package com.lexach.ClothingFeedParsers.parsers.wildberries;

import com.lexach.ClothingFeedParsers.exception.EntityNotFoundException;
import com.lexach.ClothingFeedParsers.model.*;
import com.lexach.ClothingFeedParsers.parsers.AbstractClothingParser;
import com.lexach.ClothingFeedParsers.parsers.AbstractClothingParserCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;

/**
 * wildberries.ru parser.
 * Gets info about products, categories, genders.
 */
@Component
public class WildberriesClothingParser extends AbstractClothingParser {

    private LinkedList<WildberriesCategoryClothing> womenWildberriesCategories;

    private LinkedList<WildberriesCategoryClothing> menWildberriesCategories;

    @PostConstruct
    public void init() {
        super.init("https://www.wildberries.ru", "Wildberries",
                "https://www.wildberries.ru/catalog/muzhchinam/odezhda",
                "https://www.wildberries.ru/catalog/zhenshchinam/odezhda");

        setWildberriesCategories();

        parseRoot();


        log.info(this.getClass().getName() + " initialised.");
    }

    @Override
    protected void parseGender(String genderLink, String genderName) {

        try {
            // Get gender DOM
            Document doc = Jsoup.connect(genderLink).get();

            Gender resultGender = new Gender(genderName);
            resultGender = genderService.getOrCreate(resultGender);

            genderService.save(resultGender);

            if (resultGender.getName().equals("Women")) {
                for (AbstractClothingParserCategory category : womenWildberriesCategories) {
                    parseCategory(category, resultGender);
                }
            } else {
                for (AbstractClothingParserCategory category : menWildberriesCategories) {
                    parseCategory(category, resultGender);
                }
            }
        } catch (IOException e) {
            log.error("IO exception in parseGender() method:" + e.getMessage());
        }

    }


    @Override
    protected void parseCategory(AbstractClothingParserCategory wildberriesCategory, Gender genderParam) {


        wildberriesCategory.save();

        // Go to all pages in category (max i value is 500).
        for (int i = 1; i <= 20; i++) {
            try {

                Document doc = null;

                doc = Jsoup.connect(wildberriesCategory.getLink() + "?page=" + i).get();

                Elements links = doc.getElementsByClass("catalog-prev-link");

                for (Element link : links) {

                    // get product info
                    parseProduct("https://www.wildberries.ru/" + link.attr("href"), wildberriesCategory.getProductCategory(), genderParam);


                }
            } catch (IOException e) {
                log.error("IO exception in parseCategory() method:" + e.getMessage());
            }
        }

        log.info("Parsed category" + wildberriesCategory.getProductCategory().getName());


    }

    @Override
    protected void parseProduct(String productLink, ProductCategory categoryParam, Gender genderParam) {

        try {
            // Product Document Object Model
            Document doc = Jsoup.connect(productLink).timeout(10 * 1000).get();

            // Get inside container, where big part of product info is situated.
            Element productInfo = doc.getElementById("insideContainer");

            // Product to save.
            Product product = new Product();

            // Set retailer.
            product.setRetailer(this.retailer);

            // Set product name.
            product.setName(productInfo.getElementsByAttributeValue("itemprop", "name").attr("content"));

            // set category
            product.setCategory(categoryParam);

            // Set price
            product.setPrice(Double.valueOf(productInfo.getElementsByAttributeValue("itemprop", "price").attr("content")));

            // TODO Set old price
            // product.setOldPrice();

            // Set priceCurrency
            product.setPriceCurrency(productInfo.getElementsByAttributeValue("itemprop", "priceCurrency").attr("content"));

            // Get product brandName
            product.setBrandName(productInfo.getElementsByAttributeValue("itemprop", "brand").attr("content"));

            // Get product url
            product.setUrl(productLink);

            // Set gender
            product.setGender(genderParam);

            // Sub table of productInfo with additional info about the product.
            Elements productTable = productInfo.select("td");

            /* Get product gender
            for (Element row : productTable) {
                if (row.text().equals("Пол:")) {
                    System.out.println(row.nextElementSibling().text());
                }
            }
            */

            /* get category
            for (Element row : productTable) {
                if (row.text().equals("Комплектация:")) {
                    System.out.println(row.nextElementSibling().text());
                }
            }
            */

            /*
            // TODO set manufactured country id
            for (Element row : productTable) {
                if (row.text().equals("Страна производитель:")) {
                    registerAndSetManufacturedCountry( product, row.nextElementSibling().text() );
                }
            }
            */

            // !!! TEMPORARY !!! set main product image
            product.setMainImageLink("https:" + productInfo.getElementsByAttributeValue("itemprop", "image").first().attr("content"));

            // Save product entity.
            productService.save(product);


            // ### Set foreign keys ###

            // Register Product Images
            setProductImages(productInfo, product);


            // Register all ProductColours
            setProductColours(productInfo, product);


            // TODO Register ProductSizeproductService.save(product);

            // #########################

            log.info("Parsed product " + product.getName() + " " + product.getBrandName());

        } catch (SocketTimeoutException exception) {
            log.error("Socket timeout EXCEPTION in: " + productLink + "\nChange timeout option in Jsoup.connect() method if you want this product to be parsed.");
        } catch (IOException exception) {
            log.error("IO exception in parseCategory() method:" + exception.getMessage());
        } catch (Exception exception) {
            log.error("Exception");
            exception.printStackTrace();
        }
    }

    /**
     * Set all product images.
     *
     * @param productInfo html element with product info.
     * @param product     product to bind images to.
     */
    private void setProductImages(Element productInfo, Product product) {

        Elements images = productInfo.getElementsByAttribute("data-sl-original");

        for (Element imageElement : images) {

            String imageLink = "https:" + imageElement.attr("data-zoom");

            ProductImage image = productImageService.getOrCreate(new ProductImage(product, imageLink));

            productImageService.save(image);

        }

    }

    /**
     * Set all product sizes.
     *
     * @param productInfo html element with product info.
     * @param product     product to bind sizes to.
     */
    private void setProductSizes(Element productInfo, Product product) {

    }


    /**
     * Set all product colours.
     *
     * @param productColours html element with product info.
     * @param product        product to bind sizes to.
     */
    private void setProductColours(Element productColours, Product product) {

        try {

            Elements colors = productColours.getElementsByAttribute("data-cod1s");

            for (Element color : colors) {

                // Get colour images.
                Elements images = color.getElementsByAttribute("alt");

                for (Element image : images) {
                    Colour colour = colourService.findByName(image.attr("alt"));
                    ProductColour productColour = productColourService.getOrCreate(new ProductColour(product, colour));
                    productColourService.save(productColour);


                }

            }

        } catch (EntityNotFoundException e) {
            log.error("Didn't found entity " + e.getEntityClass().getName() + " matching word " + e.getSearchTerm());
        } catch (Exception e) {
            log.error("Exception in colour parsing.");
            //e.printStackTrace();
        }
    }

    // TODO this method is temporary. Try to find another way to set categories.
    private void setWildberriesCategories() {
        this.womenWildberriesCategories = new LinkedList<WildberriesCategoryClothing>();
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Dresses", "/zhenshchinam/odezhda/platya", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Shirts", "/zhenshchinam/odezhda/futbolki-i-topy", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Pants", "/zhenshchinam/odezhda/bryuki-i-shorty", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Blouses", "/zhenshchinam/odezhda/bluzki-i-rubashki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Jumpers", "/zhenshchinam/odezhda/dzhempery-i-kardigany", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Turtlenecks", "/zhenshchinam/odezhda/vodolazki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Skirts", "/zhenshchinam/odezhda/yubki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Outerwear", "/zhenshchinam/odezhda/verhnyaya-odezhda", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Jeans", "/zhenshchinam/odezhda/dzhinsy-dzhegginsy", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Jackets", "/zhenshchinam/odezhda/pidzhaki-i-zhakety", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Home clothes", "/zhenshchinam/odezhda/odezhda-dlya-doma", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Tunics", "/zhenshchinam/odezhda/tuniki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Overalls", "/zhenshchinam/odezhda/kombinezony-polukombinezony", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Costumes", "/zhenshchinam/odezhda/kostyumy", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Vests", "/zhenshchinam/odezhda/zhilety", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Mantles", "/zhenshchinam/odezhda/mantii", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Hoodies", "/zhenshchinam/odezhda/hudi", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategoryClothing("Sweatshirts", "/zhenshchinam/odezhda/svitshoty", productCategoryService));

        this.menWildberriesCategories = new LinkedList<WildberriesCategoryClothing>();
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Shirts", "/muzhchinam/odezhda/futbolki-i-mayki", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Pants", "/muzhchinam/odezhda/bryuki-i-shorty", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Blouses", "/muzhchinam/odezhda/rubashki", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Jeans", "/muzhchinam/odezhda/dzhinsy", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Outwear", "/muzhchinam/odezhda/verhnyaya-odezhda", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Jumpers", "/muzhchinam/odezhda/dzhempery-i-kardigany", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Mantles", "/muzhchinam/mantii", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Hoodies", "/muzhchinam/odezhda/hudi", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Costumes", "/muzhchinam/odezhda/kostyumy", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Jackets", "/muzhchinam/odezhda/pidzhaki-i-zhakety", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Sweatshirts", "/muzhchinam/odezhda/svitshoty", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Home clothes", "/muzhchinam/odezhda/odezhda-dlya-doma", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategoryClothing("Turtlenecks", "/muzhchinam/odezhda/vodolazki", productCategoryService));

    }

}
