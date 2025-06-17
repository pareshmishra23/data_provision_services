package com.example.orchestrator_service.service;



import com.example.orchestrator_service.dto.ProvisionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrchestratorService {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean validateWithMetadataService(ProvisionRequest request) {
        String url = "http://localhost:8082/metadata/validate";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }


    public boolean validateWithdataService(ProvisionRequest request) {
        String dataUrl = "http://localhost:8084/data/fetch";
        ResponseEntity<List> dataResponse = restTemplate.postForEntity(dataUrl, request, List.class);

        if (dataResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(dataResponse.getBody()).hasBody();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data service failed.").hasBody();
        }
    }


}
