package com.lexach.ClothingFeedParsers.repository;

import com.lexach.ClothingFeedParsers.model.Gender;
import org.springframework.data.repository.CrudRepository;

public interface GenderRepository extends CrudRepository<Gender, Long> {
    Gender findByName(String name);
}
