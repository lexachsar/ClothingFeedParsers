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

        // Product Document Object Model
        Document doc = Jsoup.connect("https://www.wildberries.ru//catalog/5218132/detail.aspx?targetUrl=GP").timeout(10 * 1000).get();

        // Get inside container, where big part of product info is situated.
        Element productInfo = doc.getElementById("insideContainer");

        Elements sizesTable = productInfo.getElementById("sizes").select("td");

        for (Element row : sizesTable) {

            if (row.text().equals("Российский размер:")) {
                System.out.println(row.nextElementSibling().text());
            }

        }



    }
}