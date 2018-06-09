package com.lexach.ClothingFeedParsers.parsers.colorscheme;

import com.lexach.ClothingFeedParsers.parsers.AbstractParser;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * colorscheme.ru/color-names.html parser.
 * Gets info about colours.
 */
@Component
public class ColorschemeParser extends AbstractParser {

    @PostConstruct
    public void init() {
        super.init("https://colorscheme.ru/color-names.html");

        parseRoot();


        log.info(this.getClass().getSimpleName() + " initialised.");
    }

    @Override
    public void parseRoot() {
        // TODO: add color table parsing.

    }
}
