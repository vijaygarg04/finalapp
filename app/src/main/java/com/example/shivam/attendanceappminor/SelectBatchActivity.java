package com.example.shivam.attendanceappminor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shivam.attendanceappminor.model.BatchModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectBatchActivity extends AppCompatActivity {
    //TODO : Download the whole faculty object
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TinyDB tinyDB;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SelectBatchAdapter selectBatchAdapter;
    List<BatchModel> batchModels;
    Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_batch);
        tinyDB = new TinyDB(SelectBatchActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        batchModels = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference =  firebaseDatabase.getReference();
        databaseReference.child("faculty").child(tinyDB.getString("username")).child("batches").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    for(DataSnapshot ds  :  dataSnapshot.getChildren()){
                        int year=c.get(Calendar.YEAR);
                        int month=c.get(Calendar.MONTH);
                        int sub_count = 0;
                        String key[]  = ds.getKey().toString().split("_");
                     int current_sem = Utilsfunctions.findCurrentSemester(Integer.parseInt(key[0]),Integer.parseInt(key[1]),year,month);
                     if (ds.child(String.valueOf(current_sem)).exists()){
                         sub_count  = (int)(ds.child(String.valueOf(current_sem)).getChildrenCount());
                     }
                     BatchModel batchModel = new BatchModel();
                     batchModel.setBatch_id(ds.getKey());
                     batchModel.setCurrent_sem(String.valueOf(current_sem));
                     batchModel.setSubject_count(String.valueOf(sub_count));
                     if (sub_count>0){
                         batchModels.add(batchModel);
                        selectBatchAdapter = new SelectBatchAdapter(SelectBatchActivity.this,batchModels);
                        recyclerView.setAdapter(selectBatchAdapter);
                     }

                    }

                }
                else {
                    Toast.makeText(SelectBatchActivity.this,"You Don't Have Any Active Batches",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
