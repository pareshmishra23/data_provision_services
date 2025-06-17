package com.example.api_generator_service.dto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
public class RouteConfig {
    private static final Logger log = LoggerFactory.getLogger(RouteConfig.class);
    private String route;
    private String dataset;
    private List<String> fields;
    // getters and setters


    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
