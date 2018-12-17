package com.example.shivam.attendanceappminor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class SelectBatchModel {

    public List<BatchModel> batchModels;

    public SelectBatchModel() {
        batchModels = new ArrayList<>();
    }

    public List<BatchModel> getBatchModels() {
        return batchModels;
    }

    public void setBatchModels(List<BatchModel> batchModels) {
        this.batchModels = batchModels;
    }
}
