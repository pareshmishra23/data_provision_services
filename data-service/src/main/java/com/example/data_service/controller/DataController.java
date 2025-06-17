package com.example.data_service.controller;

import com.example.data_service.dto.ProvisionRequest;
import com.example.data_service.entity.SalesData;
import com.example.data_service.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/sales")
    public ResponseEntity<?> getSales(@RequestParam List<String> fields) {
        ProvisionRequest request = new ProvisionRequest("sales", fields);
        List<Map<String, Object>> data = dataService.fetchFilteredData(request);
        return ResponseEntity.ok(data);
    }


    @PostMapping("/fetch")
    public ResponseEntity<List<Map<String, Object>>> fetchData(@RequestBody ProvisionRequest request) {


        List<Map<String, Object>> data = dataService.fetchFilteredData(request);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{dataset}")
    public ResponseEntity<?> getDataset(
            @PathVariable String dataset,
            @RequestParam String fields // <== not List
    ) {
        System.out.println("Controller: " + dataset + " fields: " + fields);

        ProvisionRequest provisionRequest = new ProvisionRequest();
        provisionRequest.setDataset(dataset);
        provisionRequest.setFields(Arrays.asList(fields.split(",")));

        return ResponseEntity.ok(dataService.fetchFilteredData(provisionRequest));
    }


    @PostMapping("/save/sales")
    public SalesData save(@RequestBody SalesData data) {
        return dataService.save(data);
    }

}
