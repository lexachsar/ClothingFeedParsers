package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.model.ColourComposite;
import com.lexach.ClothingFeedParsers.service.ColourCompositeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ColourCompositeServiceImpl implements ColourCompositeService {
    @Override
    public Set<ColourComposite> getOrCreateFromNames(String names) {
        return null;
    }
}
