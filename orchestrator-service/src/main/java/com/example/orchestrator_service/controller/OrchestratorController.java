package com.example.orchestrator_service.controller;



import com.example.orchestrator_service.dto.ProvisionRequest;
import com.example.orchestrator_service.dto.RouteConfig;
import com.example.orchestrator_service.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orchestrator")
public class OrchestratorController {

    @Autowired
    private OrchestratorService orchestratorService;

    @PostMapping("/provision")
    public ResponseEntity<String> provisionMetaData(@RequestBody ProvisionRequest request) {
        boolean valid = orchestratorService.validateWithMetadataService(request);
        if (valid) {
            return ResponseEntity.ok("Metadata valid. Proceeding to data service...");
        } else {
            return ResponseEntity.badRequest().body("Invalid dataset or fields");
        }




    }

    @PostMapping("/register")
    public ResponseEntity<String> genrate(@RequestBody RouteConfig config) {
        // Assuming api-registry runs on port 8082
        String apiRegistryUrl = "http://localhost:8083/register";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(apiRegistryUrl, config, String.class);
        return ResponseEntity.ok("Submitted to API Registry: " + response.getBody());
    }

    // Call Data Service
    @PostMapping("/data_provision")
    public ResponseEntity<String> provisionData(@RequestBody ProvisionRequest request) {
        boolean valid = orchestratorService.validateWithdataService(request);
        if (valid) {
            return ResponseEntity.ok("data valid. Proceeding to data service...");
        } else {
            return ResponseEntity.badRequest().body("Invalid dataset or fields");
        }

    }
}
