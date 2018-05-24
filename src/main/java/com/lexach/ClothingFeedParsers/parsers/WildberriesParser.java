package com.lexach.ClothingFeedParsers.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class WildberriesParser extends AbstractParser {



    WildberriesParser() {
        super("https://www.wildberries.ru");

        this.menCategory = "https://www.wildberries.ru/catalog/muzhchinam/odezhda";
        this.womenCategory = "https://www.wildberries.ru/catalog/zhenshchinam/odezhda";
        this.childrenCategory = "https://www.wildberries.ru/catalog/detyam/odezhda";
    }


    @Override
    public void parseRoot() {


    }

    @Override
    // TODO add IOexception handling.
    public void parseCategory(String categoryLink) throws IOException {
        String linkNext = "1";

        for(int i = 1; i <= 500; i++) {

            Document doc = Jsoup.connect(categoryLink + "?page=" + i).get();

            Elements links = doc.getElementsByClass("catalog-prev-link");

            System.out.println(linkNext);

            for (Element link : links) {

                // get product info
                parseProduct("https://www.wildberries.ru/" + link.attr("href"));

            }
        }
    }

    @Override
    // TODO add IOexception handling.
    public void parseProduct(String productLink) throws IOException {

        try {
            Document doc = Jsoup.connect(productLink).timeout(10*1000).get();

            Element productInfo = doc.getElementById("insideContainer");

            // TODO set idRetailer

            // Get product name.
            System.out.println(productInfo.getElementsByAttributeValue("itemprop", "name").attr("content"));

            // TODO set idCategory

            // Get price
            System.out.println(productInfo.getElementsByAttributeValue("itemprop", "price").attr("content"));

            // TODO set priceCurrencyId
            System.out.println(productInfo.getElementsByAttributeValue("itemprop", "priceCurrency").attr("content"));

            // Get product brandName
            System.out.println(productInfo.getElementsByAttributeValue("itemprop", "brand").attr("content"));

            // Get product url
            System.out.println(productLink);

            // TODO set gender id
            //Get product gender

            // Sub table of productInfo with additional info about the product.
            Elements productTable = productInfo.select("td");

            for (Element row : productTable) {
                if (row.text().equals("Пол:")) {
                    System.out.println(row.nextElementSibling().text());
                }
            }

            // Other tables
            //TODO set sizes

            // TODO set colours

            // TODO set images

            // TODO set category

            for (Element row : productTable) {
                if (row.text().equals("Комплектация:")) {
                    System.out.println(row.nextElementSibling().text());
                }
            }

            // TODO set manufactured country id
        } catch(SocketTimeoutException exception) {
            // TODO add logging.
            System.out.println("Socket timeout EXCEPTION in: " + productLink);
            System.out.println("Change timout option in Jsoup.connect() method if you want this product to be parsed.");
        }


    }
}
