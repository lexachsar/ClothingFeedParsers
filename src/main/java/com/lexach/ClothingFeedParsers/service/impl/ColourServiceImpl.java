package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.exception.EntityNotFoundException;
import com.lexach.ClothingFeedParsers.model.Colour;
import com.lexach.ClothingFeedParsers.repository.ColourRepository;
import com.lexach.ClothingFeedParsers.service.ColourService;
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
