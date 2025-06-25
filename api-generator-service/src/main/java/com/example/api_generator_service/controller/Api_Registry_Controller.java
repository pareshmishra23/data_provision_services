package com.example.api_generator_service.controller;


import com.example.api_generator_service.dto.RouteConfig;
import com.example.api_generator_service.service.Api_Registry_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Api_Registry_Controller {

    @Autowired
    private Api_Registry_Services Api_Registry_Service;
    private final Map<String, RouteConfig> routeRegistry = new ConcurrentHashMap<>();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerRoute(@RequestBody RouteConfig config) {
        // Save config to local map
        if (routeRegistry.containsKey(config.getRoute())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Route already exists.");
        }else{
            routeRegistry.put(config.getRoute(), config);
        }

        return ResponseEntity.ok("Route registered: " + config.getRoute());
    }

    @GetMapping("/routes")
    public ResponseEntity<Map<String, RouteConfig>> getRoutes() {
        return ResponseEntity.ok(routeRegistry);
    }

    @GetMapping("/api/{dataset}")
    public ResponseEntity<String> handleDynamicRoute(@PathVariable String dataset, @RequestParam List<String> fields) {
        if (fields == null || fields.isEmpty()) {
            return ResponseEntity.badRequest().body("Fields query param is required");
        }

        RouteConfig config = routeRegistry.get("/api/" + dataset);
        if (config == null) {
            return ResponseEntity.badRequest().body("Route not found for dataset: " + dataset);
        }

        String dataServiceUrl = "http://localhost:8084/data/" + dataset + "?fields=" + String.join(",", fields);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataServiceUrl, String.class);
        return ResponseEntity.ok(response.getBody());
    }

   /* @GetMapping("/api/{dataset}")
    public ResponseEntity<String> handleDynamicRoute(@PathVariable String dataset, @RequestParam List<String> fields) {
        // Look for the matching route in routeRegistry
        if (fields == null || fields.isEmpty()) {
            return ResponseEntity.badRequest().body("api- registry : Fields must not be null or empty");
        }

        RouteConfig config = routeRegistry.get("/api/" + dataset);

        if (config == null) {
            return ResponseEntity.badRequest().body("Route not found for dataset: " + dataset);
        }



        // Construct target URL to DataService
        String dataServiceUrl = "http://localhost:8084/data/" + dataset + "?fields=" + String.join(",", fields);

        RestTemplate restTemplate = restTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dataServiceUrl, String.class);
        return ResponseEntity.ok(response.getBody());
    }
*/

}
