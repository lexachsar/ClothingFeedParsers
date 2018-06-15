package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.model.Gender;
import com.lexach.clothing.feed.parsers.repository.GenderRepository;
import com.lexach.clothing.feed.parsers.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public GenderServiceImpl() {
    }

    @Override
    public Gender save(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public Gender getOrCreate(Gender genderParam) {
        Gender databaseGender = genderRepository.findByName(genderParam.getName());

        if(Objects.isNull(databaseGender)) {
            return genderParam;
        } else {
            return databaseGender;
        }
    }
}
