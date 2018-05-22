package com.lexach.ClothingFeedParsers.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    private static String parseProduct(String productLink) throws IOException {
        Document doc = Jsoup.connect(productLink).get();

        Element productInfo = doc.getElementById("insideContainer");

        System.out.println(productInfo.getElementsByAttributeValue("itemprop", "name"));

//        System.out.println(productInfo);

        return productLink;
    }

    public static void main(String[] args) throws IOException {

        String linkNext = "1";

        //for(int i = 1; i <= 500; i++) {

            Document doc = Jsoup.connect("https://www.wildberries.ru/catalog/zhenshchinam/odezhda?page=" + 1).get();

            //Elements links = doc.select("a[href]");

            Elements links = doc.getElementsByClass("catalog-prev-link");

            System.out.println(linkNext);

          //  for (Element link : links) {

                // get product info
                parseProduct("https://www.wildberries.ru/" + links.get(0).attr("href"));

          //  }

        //}
    }
}
