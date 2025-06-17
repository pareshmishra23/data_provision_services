package com.example.data_service.dto;


import java.util.ArrayList;
import java.util.List;

public class ProvisionRequest {
    private String dataset;
    private List<String> fields;

    public ProvisionRequest() {
        this.fields = new ArrayList<>(); // avoids null
    }
    public ProvisionRequest(String sales, List<String> fields) {
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
