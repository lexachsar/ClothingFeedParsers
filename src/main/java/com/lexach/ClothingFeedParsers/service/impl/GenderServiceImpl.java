package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.model.Gender;
import com.lexach.ClothingFeedParsers.repository.GenderRepository;
import com.lexach.ClothingFeedParsers.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    private GenderRepository genderRepository;

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
