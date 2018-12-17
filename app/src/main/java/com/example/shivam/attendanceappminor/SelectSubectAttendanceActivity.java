package com.example.shivam.attendanceappminor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class SelectSubectAttendanceActivity extends AppCompatActivity {
    TinyDB tinyDB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        int y  = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        setContentView(R.layout.activity_select_subect_attendance);
        tinyDB = new TinyDB(SelectSubectAttendanceActivity.this);
        String id = tinyDB.getString("student_id");
        String batch_id[] = tinyDB.getString("batch_id").split("_");
        int sem = Utilsfunctions.findCurrentSemester(Integer.parseInt(batch_id[0]),Integer.parseInt(batch_id[1]),y,m);
        databaseReference.child("student").child(id).child(String.valueOf(sem)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
