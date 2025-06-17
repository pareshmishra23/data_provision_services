package com.example.metadata_service.services;



import com.example.metadata_service.dto.ProvisionRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MetadataService {

    // Sample metadata structure (can be fetched from DB/config in real apps)
    private static final Map<String, List<String>> datasetMetadata = new HashMap<>();

    static {
        datasetMetadata.put("sales", Arrays.asList("amount", "region", "date", "product"));
        datasetMetadata.put("employees", Arrays.asList("name", "id", "department", "salary"));
    }

    public boolean validate(ProvisionRequest request) {
        List<String> availableFields = datasetMetadata.get(request.getDataset());

        if (availableFields == null) {
            return false;
        }

        for (String field : request.getFields()) {
            if (!availableFields.contains(field)) {
                return false;
            }
        }
        return true;
    }
}
