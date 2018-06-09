package com.lexach.ClothingFeedParsers.parsers.colorscheme;

import com.lexach.ClothingFeedParsers.model.Colour;
import com.lexach.ClothingFeedParsers.parsers.AbstractParser;
import com.lexach.ClothingFeedParsers.service.ColourService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * colorscheme.ru/color-names.html parser.
 * Gets info about colours.
 */
@Component
public class ColorschemeParser extends AbstractParser {

    @Autowired
    private ColourService colourService;

    @PostConstruct
    public void init() {
        super.init("https://colorscheme.ru/color-names.html");

        // TODO remove this part, when spring.jpa.hibernate.ddl-auto is changed.
        parseRoot();

        log.info(this.getClass().getSimpleName() + " initialised.");
    }

    /**
     * Parses color table and creates all colours.
     */
    @Override
    public void parseRoot() {
        try {
            // Product Document Object Model
            Document doc = Jsoup.connect("https://colorscheme.ru/color-names.html").timeout(10 * 1000).get();

            // Table with all colour names.
            Element table = doc.getElementById("color-names");
            Elements colourRows = table.select("tr");

            for (int i = 1; i < colourRows.size(); i++) {
                Element row = colourRows.get(i);
                Elements cols = row.select("td");

                Colour colour = new Colour();

                // Set Name
                colour.setName(cols.get(1).text());
                // Set HEX
                colour.setHex(cols.get(2).text());

                // Set R
                colour.setR(Integer.parseInt(cols.get(3).text()));
                // Set G
                colour.setG(Integer.parseInt(cols.get(4).text()));
                // Set B
                colour.setB(Integer.parseInt(cols.get(5).text()));

                colourService.save(colour);
            }
        } catch (IOException exception) {
            log.error("IO Exception error " + exception.getMessage());
        }

    }
}
