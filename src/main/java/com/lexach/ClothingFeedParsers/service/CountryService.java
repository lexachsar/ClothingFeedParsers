package com.lexach.ClothingFeedParsers.service;

import com.lexach.ClothingFeedParsers.model.Country;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {

    Country save(Country country);

    /**
     * Gets country if it's presented in database.
     * Otherwise creates new instance of Country object.
     * @return New or existing country.
     * @param countryParam New country instance created outside of the database.
     */
    public Country getOrCreate(Country countryParam);


}
