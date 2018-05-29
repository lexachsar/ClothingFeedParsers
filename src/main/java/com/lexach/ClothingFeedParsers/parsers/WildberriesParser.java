package com.lexach.ClothingFeedParsers.parsers;

import com.lexach.ClothingFeedParsers.model.Gender;
import com.lexach.ClothingFeedParsers.model.Product;
import com.lexach.ClothingFeedParsers.model.ProductCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class WildberriesParser extends AbstractParser {

    WildberriesParser() {
        super("https://www.wildberries.ru", "Wildberries",
                "https://www.wildberries.ru/catalog/muzhchinam/odezhda",
                "https://www.wildberries.ru/catalog/zhenshchinam/odezhda");

    }

    public static void main(String[] args) {
        //parseRoot();
    }

    @Override
    protected void parseGender(String genderLink, String genderName) throws IOException {
        // Get gender DOM
        Document doc = Jsoup.connect(genderLink).get();

        Gender resultGender = new Gender(genderName);
        resultGender = genderService.getOrCreate(resultGender);

        // TODO find category and parse.

        genderService.save(resultGender);
    }


    @Override
    // TODO add IOexception handling.
    protected void parseCategory(String categoryLink, String categoryName) throws IOException {

        String linkNext = "1";

        for(int i = 1; i <= 500; i++) {

            Document doc = Jsoup.connect(categoryLink + "?page=" + i).get();

            Elements links = doc.getElementsByClass("catalog-prev-link");

            System.out.println(linkNext);

            for (Element link : links) {

                // get product info
                //this.parseProduct("https://www.wildberries.ru/" + link.attr("href"));

            }
        }

    }

    @Override
    protected void parseProduct(String productLink, ProductCategory categoryParam, Gender genderParam) throws IOException {

        try {
            // Product Document Object Model
            Document doc = Jsoup.connect(productLink).timeout(10*1000).get();

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

            // Other tables
            //TODO set sizes

            // TODO set colours

            // TODO set images

            /* get category
            for (Element row : productTable) {
                if (row.text().equals("Комплектация:")) {
                    System.out.println(row.nextElementSibling().text());
                }
            }
            */

            // TODO set manufactured country id
            for (Element row : productTable) {
                if (row.text().equals("Страна производитель:")) {
                    registerAndSetManufacturedCountry( product, row.nextElementSibling().text() );
                }
            }


        } catch(SocketTimeoutException exception) {
            // TODO add logging.
            System.out.println("Socket timeout EXCEPTION in: " + productLink);
            System.out.println("Change timout option in Jsoup.connect() method if you want this product to be parsed.");
        }

        /* Note: Need to create dedicated methods for this operations.
        // TODO Register ProductColours

        // TODO Register ProductImages

        // TODO Register ProductSize
        */
    }



}
