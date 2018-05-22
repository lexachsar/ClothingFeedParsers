package com.lexach.netcracker.jsoup.parsers;

import org.jsoup.select.Elements;

public interface ParserInterface {

    Elements parseRoot();

    Elements parseCategory(String categoryLink);

    Elements parseProduct();


}
