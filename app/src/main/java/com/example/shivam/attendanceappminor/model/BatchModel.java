package com.example.shivam.attendanceappminor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 9/12/18.
 */

public class BatchModel {
    private String batch_id;
    private String current_sem;
    private String subject_count;
    // For Now, Doing it the simple way, that is, fetching the data all over again

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getCurrent_sem() {
        return current_sem;
    }

    public void setCurrent_sem(String current_sem) {
        this.current_sem = current_sem;
    }

    public String getSubject_count() {
        return subject_count;
    }

    public void setSubject_count(String subject_count) {
        this.subject_count = subject_count;
    }

    public BatchModel() {
        batch_id = "";
        current_sem = "0";
        subject_count =  "0";
//        studentModelBatches = new ArrayList<>();

    }

}
