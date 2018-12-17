package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shivam.attendanceappminor.model.AttendanceDB;
import com.example.shivam.attendanceappminor.model.AttendanceStudentModel;
import com.example.shivam.attendanceappminor.model.BatchModel;
import com.example.shivam.attendanceappminor.model.SelectSubjectModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MarkAttendanceActivity extends AppCompatActivity {
    TinyDB tinyDB ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    int count =0;
    Button button;
    LinearLayoutManager linearLayoutManager;
    MarkAttendanceAdapter markAttendanceAdapter;
    List<AttendanceStudentModel> attendanceStudentModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final Date date = new Date();
        firebaseDatabase = FirebaseDatabase.getInstance();
        attendanceStudentModels = new ArrayList<>();
        button = (Button) findViewById(R.id.submit_button_attendance);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference = firebaseDatabase.getReference();
        tinyDB = new TinyDB(MarkAttendanceActivity.this);
        //   c =  Calendar.getInstance();
        SelectSubjectModel subject = tinyDB.getObject("subject_object", SelectSubjectModel.class);
        BatchModel bm = tinyDB.getObject("batch_data", BatchModel.class);
        databaseReference.child("faculty").child(tinyDB.getString("username")).child("batches").child(bm.getBatch_id())
                .child(bm.getCurrent_sem()).child(subject.getSubject_name()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AttendanceStudentModel attendanceStudentModel = new AttendanceStudentModel();
                    attendanceStudentModel.setStudent_id(ds.getKey().toString());
                    attendanceStudentModel.setStudent_name(ds.getValue(String.class));
                    attendanceStudentModels.add(attendanceStudentModel);
                    markAttendanceAdapter = new MarkAttendanceAdapter(MarkAttendanceActivity.this, attendanceStudentModels);
                    recyclerView.setAdapter(markAttendanceAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Submitting Attendance Here
                BatchModel batchModel = tinyDB.getObject("batch_data", BatchModel.class);
                SelectSubjectModel selectSubjectModel = tinyDB.getObject("subject_object", SelectSubjectModel.class);

                final AttendanceDB attendanceDB = tinyDB.getObject("attendance", AttendanceDB.class);
                for (int i = 0; i < attendanceDB.attendanceStudentModels.size(); i++) {
                    AttendanceStudentModel a = attendanceDB.getAttendanceStudentModels().get(i);

                    databaseReference.child("attendance")
                            .child(batchModel.getBatch_id().toString())
                            .child(batchModel.getCurrent_sem().toString())
                            .child(selectSubjectModel.getSubject_name())
                            .child(simpleDateFormat.format(date).replace("-", ""))
                            .child(a.getStudent_id()).setValue(a.isPresent(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            Toast.makeText(MarkAttendanceActivity.this, "Updated ", Toast.LENGTH_SHORT).show();
                            count++;
                            if (count >= (attendanceDB.getAttendanceStudentModels().size()-1)) {

                                Toast.makeText(MarkAttendanceActivity.this,"All Data Updated ",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MarkAttendanceActivity.this,FacultyDashboardActivity.class);
                                startActivity(intent);
                            }
                        }
                    });


                }
            }
        });
    }


}
