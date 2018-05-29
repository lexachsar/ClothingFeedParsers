package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findByName(String name);
}
