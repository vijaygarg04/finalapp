package com.example.shivam.attendanceappminor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class AttendanceDB {

    public List<AttendanceStudentModel> attendanceStudentModels;

    public List<AttendanceStudentModel> getAttendanceStudentModels() {
        return attendanceStudentModels;
    }

    public void setAttendanceStudentModels(List<AttendanceStudentModel> attendanceStudentModels) {
        this.attendanceStudentModels = attendanceStudentModels;
    }

    public AttendanceDB() {

        attendanceStudentModels = new ArrayList<>();
    }
}
