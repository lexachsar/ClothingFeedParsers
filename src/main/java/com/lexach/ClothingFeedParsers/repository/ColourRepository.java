package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.Colour;
import org.springframework.data.repository.CrudRepository;

public interface ColourRepository extends CrudRepository<Colour, Long> {

    Colour findByName(String name);

}
