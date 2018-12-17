package com.example.shivam.attendanceappminor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shivam on 8/12/18.
 */

public class StudentConfigFile {
    //This object will be serialized and stored in the android file so that it becomes easily accessible everywhere
    //TODO : use tinyDB to store this info after login to make calls everytime
    private String student_name;
    private boolean logged_in;
    private String student_id;
    private String student_gender;
    private String student_dob;
    private String batch_id;//This is same as batch name
    private String batch_start_year;
    private String batch_end_year;
   // private Map<String,SemesterObjUtil> semester_list;

    public StudentConfigFile() {
        student_dob =  "default";
        student_name =  "default";
        student_gender = "default";
        batch_id = "default";
        batch_start_year = "0";
        logged_in = false;
        batch_end_year = "0";
        //semester_list = new HashMap<>();

    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    public String getStudent_dob() {
        return student_dob;
    }

    public void setStudent_dob(String student_dob) {
        this.student_dob = student_dob;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_start_year() {
        return batch_start_year;
    }

    public void setBatch_start_year(String batch_start_year) {
        this.batch_start_year = batch_start_year;
    }

    public String getBatch_end_year() {
        return batch_end_year;
    }

    public void setBatch_end_year(String batch_end_year) {
        this.batch_end_year = batch_end_year;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
