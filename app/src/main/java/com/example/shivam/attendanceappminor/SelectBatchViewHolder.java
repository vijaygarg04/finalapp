package com.example.shivam.attendanceappminor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.shivam.attendanceappminor.model.BatchModel;

import java.util.List;

/**
 * Created by shivam on 10/12/18.
 */

public class SelectBatchViewHolder extends RecyclerView.ViewHolder {
    List<BatchModel> batchModels;
    TextView currentSem, batchId, subjectCount;
    public SelectBatchViewHolder(View itemView, List<BatchModel> batchModels) {
        super(itemView);
        this.batchModels =  batchModels;
        currentSem = (TextView) itemView.findViewById(R.id.current_semester_show);
        batchId =  (TextView) itemView.findViewById(R.id.batch_id_show);
        subjectCount = (TextView) itemView.findViewById(R.id.subject_count_show);

    }
}
