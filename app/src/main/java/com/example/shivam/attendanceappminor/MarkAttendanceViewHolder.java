package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shivam.attendanceappminor.model.AttendanceStudentModel;

import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class MarkAttendanceViewHolder extends RecyclerView.ViewHolder {
    List<AttendanceStudentModel> attendanceStudentModels;
    Button status_btn;
    TextView name,id;
    public MarkAttendanceViewHolder(View itemView, List<AttendanceStudentModel> attendanceStudentModels) {
        super(itemView);
        status_btn = (Button)itemView.findViewById(R.id.status_button);
        name= (TextView)itemView.findViewById(R.id.student_name_show);
        id = (TextView)itemView.findViewById(R.id.student_id_show);
        this.attendanceStudentModels = attendanceStudentModels;
    }
}
