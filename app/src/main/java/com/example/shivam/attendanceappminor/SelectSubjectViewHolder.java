package com.example.shivam.attendanceappminor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.shivam.attendanceappminor.model.SelectSubjectModel;

import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class SelectSubjectViewHolder extends RecyclerView.ViewHolder{
    List<SelectSubjectModel> selectSubjectModels;
    TextView textView;
    public SelectSubjectViewHolder(View itemView, List<SelectSubjectModel> selectSubjectModels) {
        super(itemView);
        this.selectSubjectModels = selectSubjectModels;
        textView = (TextView)itemView.findViewById(R.id.subject_code_show);
    }
}
