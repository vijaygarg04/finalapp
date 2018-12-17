package com.example.shivam.attendanceappminor.model;

public class Faculty {

    String facultyname;
    String facultyid;
    String dob;
    String password;
    String gender;
    String mobilenumber;

    public Faculty(String facultyname, String facultyid, String dob, String password, String gender, String mobilenumber) {
        this.facultyname = facultyname;
        this.facultyid = facultyid;
        this.dob = dob;
        this.password = password;
        this.gender = gender;
        this.mobilenumber = mobilenumber;
    }

    public Faculty() {
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public String getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(String facultyid) {
        this.facultyid = facultyid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
}