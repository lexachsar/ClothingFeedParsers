package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.model.Country;
import com.lexach.clothing.feed.parsers.repository.CountryRepository;
import com.lexach.clothing.feed.parsers.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    public CountryServiceImpl() {
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getOrCreate(Country countryParam) {
        Country databaseCountry = countryRepository.findByName(countryParam.getName());

        if(Objects.isNull(databaseCountry)) {
            countryRepository.save(countryParam);
            return countryParam;
        } else {
            return databaseCountry;
        }
    }
}
