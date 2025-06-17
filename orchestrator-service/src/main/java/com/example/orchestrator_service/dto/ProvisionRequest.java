package com.example.orchestrator_service.dto;


import java.util.List;

public class ProvisionRequest {
    private String dataset;
    private List<String> fields;

    // Getters and setters
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
