package com.lexach.clothing.feed.parsers.service;

import com.lexach.clothing.feed.parsers.exception.EntityNotFoundException;
import com.lexach.clothing.feed.parsers.model.Colour;
import org.springframework.stereotype.Service;

@Service
public interface ColourService {

    public Colour save(Colour colour);

    public Colour findByName(String name) throws EntityNotFoundException;

}
