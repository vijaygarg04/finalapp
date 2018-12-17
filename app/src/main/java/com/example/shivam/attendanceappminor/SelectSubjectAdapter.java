package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivam.attendanceappminor.model.SelectSubjectModel;

import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class SelectSubjectAdapter extends RecyclerView.Adapter<SelectSubjectViewHolder> {
    List<SelectSubjectModel> selectSubjectModels;
    Context context;
    TinyDB tinyDB;
    public SelectSubjectAdapter(Context context, List<SelectSubjectModel> selectSubjectModels){
        this.context =  context;
        this.selectSubjectModels =  selectSubjectModels;
        tinyDB = new TinyDB(context);
    }

    @NonNull
    @Override
    public SelectSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SelectSubjectViewHolder selectSubjectViewHolder = null;
        LayoutInflater li= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutView = li.inflate(R.layout.select_subject_item,parent,false);
        selectSubjectViewHolder = new SelectSubjectViewHolder(layoutView,selectSubjectModels);
        return selectSubjectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SelectSubjectViewHolder holder, final int position) {
        holder.textView.setText(selectSubjectModels.get(position).getSubject_name().toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MarkAttendanceActivity.class);
                intent.putExtra("subject_code",selectSubjectModels.get(position).getSubject_name().toString());
                tinyDB.putObject("subject_object",selectSubjectModels.get(position));
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return selectSubjectModels.size();
    }
}
