package com.example.api_generator_service.service;



import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Api_Registry_Services {

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


}
