package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivam.attendanceappminor.model.BatchModel;

import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class SelectBatchAdapter extends RecyclerView.Adapter<SelectBatchViewHolder> {
    public List<BatchModel> batchModels;
    public Context context;
    TinyDB tinyDB;
    public SelectBatchAdapter(Context context, List<BatchModel> batchModels){

this.context =  context;
this.batchModels =  batchModels;
tinyDB = new TinyDB(context);
    }
    @NonNull
    @Override
    public SelectBatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SelectBatchViewHolder selectBatchViewHolder = null;
        LayoutInflater li= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutView = li.inflate(R.layout.select_batch_item,parent,false);
        selectBatchViewHolder =  new SelectBatchViewHolder(layoutView,batchModels);

        return selectBatchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectBatchViewHolder holder, final int position) {
        holder.batchId.setText("Batch Id:" +batchModels.get(position).getBatch_id().toString());
        holder.currentSem.setText("Current Semester"+batchModels.get(position).getCurrent_sem().toString());
        holder.subjectCount.setText("SubjectCount"+batchModels.get(position).getSubject_count().toString());
        holder.batchId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinyDB.putObject("batch_data",batchModels.get(position));
                Intent intent = new Intent(context,SelectSubjectActivity.class);
                intent.putExtra("batch_id", batchModels.get(position).getBatch_id().toString());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return batchModels.size();
    }
}
