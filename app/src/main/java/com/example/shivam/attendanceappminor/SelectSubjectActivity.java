package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shivam.attendanceappminor.model.BatchModel;
import com.example.shivam.attendanceappminor.model.SelectSubjectModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectSubjectActivity extends AppCompatActivity {
    BatchModel bm ;
    TinyDB tinyDB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<SelectSubjectModel> selectSubjectModels;
    RecyclerView recyclerView;
    SelectSubjectAdapter selectSubjectAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        Intent intent = getIntent();
        selectSubjectModels = new ArrayList<>();
        tinyDB = new TinyDB(SelectSubjectActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        String id  = intent.getStringExtra("batch_id");
        bm = tinyDB.getObject("batch_data",BatchModel.class);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("faculty").child(tinyDB.getString("username"))
                .child("batches").child(bm.getBatch_id()).child(bm.getCurrent_sem()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        SelectSubjectModel selectSubjectModel  = new SelectSubjectModel();
                        selectSubjectModel.setSubject_name(ds.getKey());
                        selectSubjectModels.add(selectSubjectModel);
                        selectSubjectAdapter = new SelectSubjectAdapter(SelectSubjectActivity.this,selectSubjectModels);
                        recyclerView.setAdapter(selectSubjectAdapter);

                    }
                }else {
                    Toast.makeText(SelectSubjectActivity.this, " No Subject For This Batch",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
