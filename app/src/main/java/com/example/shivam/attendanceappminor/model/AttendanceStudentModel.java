package com.example.shivam.attendanceappminor.model;

/**
 * Created by shivam on 10/12/18.
 */

public class AttendanceStudentModel {
    private boolean present;
    private String student_id;
    private String student_name;

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public AttendanceStudentModel() {
    }
}
