package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findByName(String name);
}
