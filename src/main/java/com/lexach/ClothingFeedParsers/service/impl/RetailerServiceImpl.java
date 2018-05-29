package com.lexach.ClothingFeedParsers.service.impl;

import com.lexach.ClothingFeedParsers.model.Retailer;
import com.lexach.ClothingFeedParsers.repository.RetailerRepository;
import com.lexach.ClothingFeedParsers.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RetailerServiceImpl implements RetailerService {

    @Autowired
    private RetailerRepository retailerRepository;

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
