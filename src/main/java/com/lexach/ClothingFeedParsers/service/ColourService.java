package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.exception.EntityNotFoundException;
import com.lexach.ClothingFeedParsers.model.Colour;
import org.springframework.stereotype.Service;

@Service
public interface ColourService {

    public Colour save(Colour colour);

    public Colour findByName(String name) throws EntityNotFoundException;

}
