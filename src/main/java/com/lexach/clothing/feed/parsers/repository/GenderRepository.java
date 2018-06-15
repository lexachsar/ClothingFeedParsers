package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.Gender;
import org.springframework.data.repository.CrudRepository;

public interface GenderRepository extends CrudRepository<Gender, Long> {
    Gender findByName(String name);
}
