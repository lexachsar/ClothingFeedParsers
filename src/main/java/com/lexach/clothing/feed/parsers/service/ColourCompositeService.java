package com.lexach.clothing.feed.parsers.service;

import com.lexach.clothing.feed.parsers.exception.EntityNotFoundException;
import com.lexach.clothing.feed.parsers.model.ColourComposite;
import com.lexach.clothing.feed.parsers.model.ProductColour;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ColourCompositeService {

    public Set<ColourComposite> getOrCreateFromNamesAndProductColour(String names, ProductColour productColour) throws EntityNotFoundException;

    public ColourComposite save(ColourComposite colourComposite);

}
