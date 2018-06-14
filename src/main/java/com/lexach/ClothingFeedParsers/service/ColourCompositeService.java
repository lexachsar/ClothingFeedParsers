package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.exception.EntityNotFoundException;
import com.lexach.ClothingFeedParsers.model.ColourComposite;
import com.lexach.ClothingFeedParsers.model.Product;
import com.lexach.ClothingFeedParsers.model.ProductColour;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ColourCompositeService {

    public Set<ColourComposite> getOrCreateFromNamesAndProductColour(String names, ProductColour productColour) throws EntityNotFoundException;

    public ColourComposite save(ColourComposite colourComposite);

}
