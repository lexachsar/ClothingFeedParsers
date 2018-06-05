package com.lexach.ClothingFeedParsers.parsers.wildberries;

import com.lexach.ClothingFeedParsers.model.Gender;
import com.lexach.ClothingFeedParsers.model.Product;
import com.lexach.ClothingFeedParsers.model.ProductCategory;
import com.lexach.ClothingFeedParsers.model.ProductImage;
import com.lexach.ClothingFeedParsers.parsers.AbstractParser;
import com.lexach.ClothingFeedParsers.parsers.AbstractParserCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.LinkedList;

@Component
public class WildberriesParser extends AbstractParser {

    private LinkedList<WildberriesCategory> womenWildberriesCategories;

    private LinkedList<WildberriesCategory> menWildberriesCategories;

    @PostConstruct
    public void init() {
        super.init("https://www.wildberries.ru", "Wildberries",
                "https://www.wildberries.ru/catalog/muzhchinam/odezhda",
                "https://www.wildberries.ru/catalog/zhenshchinam/odezhda");

        setWildberriesCategories();

        try {
            parseRoot();
        } catch (Exception exception) {

        }

    }

    @Override
    protected void parseGender(String genderLink, String genderName) {

        try {
            // Get gender DOM
            Document doc = Jsoup.connect(genderLink).get();

            Gender resultGender = new Gender(genderName);
            resultGender = genderService.getOrCreate(resultGender);

            genderService.save(resultGender);

            // TODO find categories and parse.
            if (resultGender.getName().equals("Women")) {
                for (AbstractParserCategory category : womenWildberriesCategories) {
                    parseCategory(category, resultGender);
                }
            } else {
                for (AbstractParserCategory category : menWildberriesCategories) {
                    parseCategory(category, resultGender);
                }
            }
        } catch(IOException e) {
            System.out.println("IO exception in gender.");
        }

    }


    @Override
    // TODO add IOexception handling.
    protected void parseCategory(AbstractParserCategory wildberriesCategory, Gender genderParam) {

        try {

        wildberriesCategory.save();

        for (int i = 1; i <= 500; i++) {

            Document doc = null;

                doc = Jsoup.connect(wildberriesCategory.getLink() + "?page=" + i).get();

            Elements links = doc.getElementsByClass("catalog-prev-link");

            for (Element link : links) {

                // get product info
                parseProduct("https://www.wildberries.ru/" + link.attr("href"), wildberriesCategory.getProductCategory(), genderParam);

            }
        }
        } catch (IOException e) {
            // TODO add exception handling
            System.out.println("IO Category exception.");;
        }


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


            // TODO Register all ProductColours


            // TODO Register ProductSizeproductService.save(product);

            // #########################

        } catch (SocketTimeoutException exception) {
            // TODO add logging.
            System.out.println("Socket timeout EXCEPTION in: " + productLink);
            System.out.println("Change timeout option in Jsoup.connect() method if you want this product to be parsed.");
        } catch (IOException exception) {
            System.out.println("IO exception");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception");
        }
    }

    /**
     * Set all product images.
     * @param productInfo html element with product info.
     * @param product product to bind images to.
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
     * @param productInfo html element with product info.
     * @param product product to bind sizes to.
     */
    private void setProductSizes(Element productInfo, Product product) {

    }


    private void setProductColours() {

    }

    // TODO this method is temporary. Try to find another way to set categories.
    private void setWildberriesCategories() {
        this.womenWildberriesCategories = new LinkedList<WildberriesCategory>();
        this.womenWildberriesCategories.add(new WildberriesCategory("Dresses", "/zhenshchinam/odezhda/platya", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Shirts", "/zhenshchinam/odezhda/futbolki-i-topy", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Pants", "/zhenshchinam/odezhda/bryuki-i-shorty", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Blouses", "/zhenshchinam/odezhda/bluzki-i-rubashki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Jumpers", "/zhenshchinam/odezhda/dzhempery-i-kardigany", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Turtlenecks", "/zhenshchinam/odezhda/vodolazki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Skirts", "/zhenshchinam/odezhda/yubki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Outerwear", "/zhenshchinam/odezhda/verhnyaya-odezhda", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Jeans", "/zhenshchinam/odezhda/dzhinsy-dzhegginsy", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Jackets", "/zhenshchinam/odezhda/pidzhaki-i-zhakety", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Home clothes", "/zhenshchinam/odezhda/odezhda-dlya-doma", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Tunics", "/zhenshchinam/odezhda/tuniki", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Overalls", "/zhenshchinam/odezhda/kombinezony-polukombinezony", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Costumes", "/zhenshchinam/odezhda/kostyumy", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Vests", "/zhenshchinam/odezhda/zhilety", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Mantles", "/zhenshchinam/odezhda/mantii", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Hoodies", "/zhenshchinam/odezhda/hudi", productCategoryService));
        this.womenWildberriesCategories.add(new WildberriesCategory("Sweatshirts", "/zhenshchinam/odezhda/svitshoty", productCategoryService));

        this.menWildberriesCategories = new LinkedList<WildberriesCategory>();
        this.menWildberriesCategories.add(new WildberriesCategory("Shirts", "/muzhchinam/odezhda/futbolki-i-mayki", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Pants", "/muzhchinam/odezhda/bryuki-i-shorty", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Blouses", "/muzhchinam/odezhda/rubashki", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Jeans", "/muzhchinam/odezhda/dzhinsy", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Outwear", "/muzhchinam/odezhda/verhnyaya-odezhda", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Jumpers", "/muzhchinam/odezhda/dzhempery-i-kardigany", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Mantles", "/muzhchinam/mantii", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Hoodies", "/muzhchinam/odezhda/hudi", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Costumes", "/muzhchinam/odezhda/kostyumy", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Jackets", "/muzhchinam/odezhda/pidzhaki-i-zhakety", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Sweatshirts", "/muzhchinam/odezhda/svitshoty", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Home clothes", "/muzhchinam/odezhda/odezhda-dlya-doma", productCategoryService));
        this.menWildberriesCategories.add(new WildberriesCategory("Turtlenecks", "/muzhchinam/odezhda/vodolazki", productCategoryService));

    }

}
