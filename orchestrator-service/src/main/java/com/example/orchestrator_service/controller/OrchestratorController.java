package com.example.orchestrator_service.controller;



import com.example.orchestrator_service.dto.ProvisionRequest;
import com.example.orchestrator_service.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
