package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.exception.EntityNotFoundException;
import com.lexach.clothing.feed.parsers.model.Colour;
import com.lexach.clothing.feed.parsers.repository.ColourRepository;
import com.lexach.clothing.feed.parsers.service.ColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ColourServiceImpl implements ColourService {

    @Autowired
    private ColourRepository colourRepository;

    @Override
    public Colour save(Colour colour) {
        return colourRepository.save(colour);
    }

    @Override
    public Colour findByName(String name) throws EntityNotFoundException {

        Colour colour = colourRepository.findByName(name);

        if(Objects.isNull(colour)) {
            throw new EntityNotFoundException(Colour.class, name);
        } else {
            return colour;
        }

    }


}
