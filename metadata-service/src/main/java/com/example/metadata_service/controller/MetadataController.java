package com.example.metadata_service.controller;

import com.example.metadata_service.dto.ProvisionRequest;
import com.example.metadata_service.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateMetadata(@RequestBody ProvisionRequest request) {
        boolean isValid = metadataService.validate(request);
        return ResponseEntity.ok(isValid);
    }
}
