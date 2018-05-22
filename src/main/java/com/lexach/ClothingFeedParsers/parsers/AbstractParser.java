package com.lexach.netcracker.jsoup.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public abstract class AbstractParser implements ParserInterface {

    protected Document root;

    protected String menCategory;

    protected String womenCategory;

    protected String childrenCategory;

    AbstractParser(String rootLink) {

        try {
            this.root = Jsoup.connect(rootLink).get();

            Elements links = root.select("a[href]");

        } catch (IOException exception) {
            // TODO add exception handler.
        }


    }

    public Document getRoot() {
        return root;
    }

    public String getMenCategory() {
        return menCategory;
    }

    public String getWomenCategory() {
        return womenCategory;
    }

    public String getChildrenCategory() {
        return childrenCategory;
    }
}
