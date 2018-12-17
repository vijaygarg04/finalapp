package com.example.shivam.attendanceappminor;

/**
 * Created by shivam on 8/12/18.
 */

public class FacultyConfigFile {
    private String faculty_name;
    private String faculty_id;
    private boolean is_logged_in;
    private String dob;
    private String gender;

    public FacultyConfigFile() {
        faculty_id = "default";
        faculty_name =  "default";
        is_logged_in = false;
        dob =  "default";
        gender = "default";
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    public boolean isIs_logged_in() {
        return is_logged_in;
    }

    public void setIs_logged_in(boolean is_logged_in) {
        this.is_logged_in = is_logged_in;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
