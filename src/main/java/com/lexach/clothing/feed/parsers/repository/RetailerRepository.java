package com.lexach.clothing.feed.parsers.repository;

import com.lexach.clothing.feed.parsers.model.Retailer;
import org.springframework.data.repository.CrudRepository;

public interface RetailerRepository extends CrudRepository<Retailer, Long> {

    /**
     * Get retailer object by parser class name.
     * @param parserClassName class name of retailer parser.
     * @return Reailer object.
     */
    Retailer findByParserClassName(String parserClassName);
}
