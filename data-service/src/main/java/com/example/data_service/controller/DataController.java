package com.example.data_service.controller;

import com.example.data_service.dto.ProvisionRequest;
import com.example.data_service.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/fetch")
    public ResponseEntity<List<Map<String, Object>>> fetchData(@RequestBody ProvisionRequest request) {
        List<Map<String, Object>> data = dataService.fetchFilteredData(request);
        return ResponseEntity.ok(data);
    }
}
