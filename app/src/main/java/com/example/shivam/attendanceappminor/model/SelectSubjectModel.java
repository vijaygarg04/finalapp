package com.example.shivam.attendanceappminor.model;

/**
 * Created by shivam on 10/12/18.
 */

public class SelectSubjectModel {
    private String subject_name;

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public SelectSubjectModel() {

        subject_name = "";
    }
}
