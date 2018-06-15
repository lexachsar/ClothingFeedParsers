package com.lexach.clothing.feed.parsers.service;

import com.lexach.clothing.feed.parsers.model.Gender;
import org.springframework.stereotype.Service;

@Service
public interface GenderService {

    Gender save(Gender gender);

    /**
     * Gets gender if it's presented in database.
     * Otherwise creates new instance of Gender object.
     * @return New or existing gender.
     * @param genderParam New gender instance created outside of the database.
     */
    public Gender getOrCreate(Gender genderParam);


}
