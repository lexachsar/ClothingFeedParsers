package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.Colour;
import org.springframework.data.repository.CrudRepository;

public interface ColourRepository extends CrudRepository<Colour, Long> {

    Colour findByName(String name);

}
