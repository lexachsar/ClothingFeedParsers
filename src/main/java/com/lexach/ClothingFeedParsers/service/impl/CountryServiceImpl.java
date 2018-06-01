package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.model.Country;
import com.lexach.ClothingFeedParsers.repository.CountryRepository;
import com.lexach.ClothingFeedParsers.service.CountryService;
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
