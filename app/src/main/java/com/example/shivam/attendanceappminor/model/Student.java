package com.example.shivam.attendanceappminor.model;

public class Student {
    String student_name;
    String student_id;
    String student_dob;
    String password;
    String batch_id;
    String batch_start_year;
    String batch_end_year;
    String stream;
    String mobile_number;
    String gender;
    String course;

    public Student(String studentname, String studendid, String studentdob, String password, String batchid, String batchstartyear, String batchendyear, String stream, String mobilenumber, String gender, String course) {
        this.student_name = studentname;
        this.student_id = studendid;
        this.student_dob = studentdob;
        this.password = password;
        this.batch_id = batchid;
        this.batch_start_year = batchstartyear;
        this.batch_end_year = batchendyear;
        this.stream = stream;
        this.mobile_number = mobilenumber;
        this.gender = gender;
        this.course = course;
    }

    public Student() {
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_dob() {
        return student_dob;
    }

    public void setStudent_dob(String student_dob) {
        this.student_dob = student_dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}