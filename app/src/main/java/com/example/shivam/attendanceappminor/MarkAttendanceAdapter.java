package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivam.attendanceappminor.model.AttendanceDB;
import com.example.shivam.attendanceappminor.model.AttendanceStudentModel;

import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class MarkAttendanceAdapter extends RecyclerView.Adapter<MarkAttendanceViewHolder> {
    Context context;
    List<AttendanceStudentModel> attendanceStudentModels;
    TinyDB tinyDB;
    AttendanceDB attendanceDB;
    public  MarkAttendanceAdapter(Context context, List<AttendanceStudentModel> attendanceStudentModels){

        this.context = context;
        this.attendanceStudentModels = attendanceStudentModels;
        tinyDB = new TinyDB(context);
        attendanceDB = new AttendanceDB();
    }
    @NonNull
    @Override
    public MarkAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MarkAttendanceViewHolder markAttendanceViewHolder = null;
        LayoutInflater li= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutView = li.inflate(R.layout.attendance_mark_item,parent,false);
        markAttendanceViewHolder = new MarkAttendanceViewHolder(layoutView,attendanceStudentModels);
        return markAttendanceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MarkAttendanceViewHolder holder, final int position) {
        holder.id.setText(attendanceStudentModels.get(position).getStudent_id().toString());
        holder.name.setText(attendanceStudentModels.get(position).getStudent_name().toString());

        if (attendanceStudentModels.get(position).isPresent()){
            holder.status_btn.setText("PRESENT");
        }
        else if (!attendanceStudentModels.get(position).isPresent()){
            holder.status_btn.setText("ABSENT");
        }

        holder.status_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attendanceStudentModels.get(position).isPresent()){
                    attendanceStudentModels.get(position).setPresent(false);
                    holder.status_btn.setText("ABSENT");
                    attendanceDB.setAttendanceStudentModels(attendanceStudentModels);
                    tinyDB.putObject("attendance",attendanceDB);
                }
                else {
                    attendanceStudentModels.get(position).setPresent(true);
                    holder.status_btn.setText("PRESENT");
                    attendanceDB.setAttendanceStudentModels(attendanceStudentModels);
                    tinyDB.putObject("attendance",attendanceDB);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return attendanceStudentModels.size();
    }
}
