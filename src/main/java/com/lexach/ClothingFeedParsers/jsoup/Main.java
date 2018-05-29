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

        String productLink = "https://www.wildberries.ru/catalog/zhenshchinam/odezhda";

        // Product Document Object Model
        Document doc = Jsoup.connect(productLink).timeout(10*1000).get();

        // Get inside container, where big part of product info is situated.
        //Element topmenus = doc.getElementsByClass("topmenus").first();

        //System.out.println(topmenus);

        Element womenGender = doc.select("sidemenu").first();

        System.out.println(womenGender);

        //Elements womenCategories = womenGender.select("a[href]");
    }
}
