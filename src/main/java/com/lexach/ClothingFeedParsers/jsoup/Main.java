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
        Document doc = Jsoup.connect("https://colorscheme.ru/color-names.html").timeout(10 * 1000).get();

        Element table = doc.getElementById("color-names");
        Elements colourRows = table.select("tr");

        for (int i = 1; i < colourRows.size(); i++) {
            Element row = colourRows.get(i);
            Elements cols = row.select("td");

            // Name
            System.out.println(cols.get(1).text());
            // HEX
            System.out.println(cols.get(2).text());

            // R
            System.out.println(cols.get(3).text());
            // G
            System.out.println(cols.get(4).text());
            // B
            System.out.println(cols.get(5).text());
        }



    }
}