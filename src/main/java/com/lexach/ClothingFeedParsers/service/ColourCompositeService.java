package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.ColourComposite;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ColourCompositeService {

    public Set<ColourComposite> getOrCreateFromNames(String names);

}
