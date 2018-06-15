package com.lexach.clothing.feed.parsers.service.impl;

import com.lexach.clothing.feed.parsers.model.Retailer;
import com.lexach.clothing.feed.parsers.repository.RetailerRepository;
import com.lexach.clothing.feed.parsers.service.RetailerService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RetailerServiceImpl implements RetailerService {

    private RetailerRepository retailerRepository;

    public RetailerServiceImpl(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    @Override
    public Retailer save(Retailer retailer) {
        return retailerRepository.save(retailer);
    }

    @Override
    public Retailer loadRetailerByParserClassName(String parserClassName) {
        Retailer retailer = retailerRepository.findByParserClassName(parserClassName);

        // TODO add exception handling.

        return retailer;
    }

    @Override
    public Retailer getOrCreate(Retailer retailerParam) {
        // Trying to find existing database retailer
        Retailer databaseRetailer = retailerRepository.findByParserClassName(retailerParam.getParserClassName());

        // If existing retailer is null -- return retailer param.
        // Otherwise -- return existing retailer from database.
        if(Objects.isNull(databaseRetailer)) {
            return retailerParam;
        } else {
            return databaseRetailer;
        }
    }
}
