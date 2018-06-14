package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.exception.EntityNotFoundException;
import com.lexach.ClothingFeedParsers.model.Colour;
import com.lexach.ClothingFeedParsers.model.ColourComposite;
import com.lexach.ClothingFeedParsers.model.ProductColour;
import com.lexach.ClothingFeedParsers.repository.ColourCompositeRepository;
import com.lexach.ClothingFeedParsers.repository.ColourRepository;
import com.lexach.ClothingFeedParsers.service.ColourCompositeService;
import com.lexach.ClothingFeedParsers.service.ColourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ColourCompositeServiceImpl implements ColourCompositeService {

    @Autowired
    private ColourCompositeRepository colourCompositeRepository;

    @Autowired
    private ColourService colourService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Set<ColourComposite> getOrCreateFromNamesAndProductColour(String names, ProductColour productColour) throws EntityNotFoundException {


        // Function result.
        Set<ColourComposite> coloursComposite = new LinkedHashSet<>();

        // Split all colour names
        String[] namesArr = names.split(", ");

        // For each colour name...
        for (String name : namesArr) {

            try {
                // Find colour
                Colour colour = colourService.findByName(name);

                // Create new instance of composite colour
                ColourComposite colourComposite = new ColourComposite(productColour, colour);

                // Add new Colour Composite to result set.
                coloursComposite.add(colourComposite);
            } catch (EntityNotFoundException e) {
                log.error("Didn't found entity " + e.getEntityClass().getName() + " matching word " + e.getSearchTerm());
            }

        }

        return coloursComposite;

    }

    @Override
    public ColourComposite save(ColourComposite colourComposite) {
        return colourCompositeRepository.save(colourComposite);
    }

}
