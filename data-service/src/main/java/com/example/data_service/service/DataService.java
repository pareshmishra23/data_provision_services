package com.example.data_service.service;


import com.example.data_service.Repository.SalesDataRepository;
import com.example.data_service.dto.ProvisionRequest;
import com.example.data_service.entity.SalesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class DataService {

    @Autowired
    private SalesDataRepository salesDataRepository;

    public List<Map<String, Object>> fetchFilteredData(ProvisionRequest request) {
        System.out.println("Fetching data for dataset: " + request.getDataset() + " fields: " + request.getFields());

        List<String> fields = request.getFields();
        System.out.println("Data services "+fields);
        if (fields == null || fields.isEmpty()) {
            throw new IllegalArgumentException("Fields must not be null or empty");
        }
        List<SalesData> allData = salesDataRepository.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (SalesData row : allData) {
            Map<String, Object> filteredRow = new HashMap<>();
            for (String field : request.getFields()) {
                switch (field) {
                    case "amount" -> filteredRow.put("amount", row.getAmount());
                    case "region" -> filteredRow.put("region", row.getRegion());
                    case "date" -> filteredRow.put("date", row.getDate());
                    case "product" -> filteredRow.put("product", row.getProduct());
                }
            }
            result.add(filteredRow);
        }
        return result;
    }

    public SalesData save(@RequestBody SalesData data) {
        return salesDataRepository.save(data);
    }
}
