package com.example.data_service.service;


import com.example.data_service.dto.ProvisionRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataService {

    private static final Map<String, List<Map<String, Object>>> dummyData = new HashMap<>();

    static {
        // Example dummy sales dataset
        dummyData.put("sales", Arrays.asList(
                Map.of("amount", 100, "region", "North", "date", "2023-01-01"),
                Map.of("amount", 200, "region", "South", "date", "2023-01-02")
        ));

        // Another example dataset
        dummyData.put("employees", Arrays.asList(
                Map.of("name", "Alice", "id", 1, "salary", 5000),
                Map.of("name", "Bob", "id", 2, "salary", 6000)
        ));
    }

    public List<Map<String, Object>> fetchFilteredData(ProvisionRequest request) {
        List<Map<String, Object>> dataList = dummyData.getOrDefault(request.getDataset(), List.of());

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> row : dataList) {
            Map<String, Object> filteredRow = new HashMap<>();
            for (String field : request.getFields()) {
                if (row.containsKey(field)) {
                    filteredRow.put(field, row.get(field));
                }
            }
            result.add(filteredRow);
        }

        return result;
    }
}
