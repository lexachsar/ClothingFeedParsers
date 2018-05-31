package com.lexach.ClothingFeedParsers.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class Main {

    private static void parseProduct(String productLink) throws IOException {

    }

    public static void main(String[] args) throws IOException {

        for(int i = 1; i <= 500; i++) {

            Document doc = Jsoup.connect("https://www.wildberries.ru/catalog/muzhchinam/odezhda/futbolki-i-mayki" + "?page=" + i).get();

            Elements links = doc.getElementsByClass("catalog-prev-link");

            System.out.println(i);

            for (Element link : links) {

                // get product info
                System.out.println("https://www.wildberries.ru/" + link.attr("href"));

            }
        }


    }
}
