package com.lexach.ClothingFeedParsers.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;


public abstract class AbstractParser {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    // Root Document of the site.
    protected Document root;

    public abstract void parseRoot();

    public void init(String rootLink) {
        try {
            this.root = Jsoup.connect(rootLink).get();
        } catch(IOException e) {
            log.error("IO exception in root link parsing." + e.getMessage());
        }
    }

}
