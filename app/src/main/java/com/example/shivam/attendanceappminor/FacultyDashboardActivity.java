package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shivam.attendanceappminor.model.Faculty;

public class FacultyDashboardActivity extends AppCompatActivity {
    Button add_batch,mark_attendance,conduct_quiz_btn,logout;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        mark_attendance = (Button)findViewById(R.id.mark_attendance_button);
        tinyDB=new TinyDB(this);
        add_batch =(Button)findViewById(R.id.add_batch_button);
        conduct_quiz_btn = (Button)findViewById(R.id.conduct_quiz_button);
        conduct_quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(FacultyDashboardActivity.this, QuizFaculty.class);
                startActivity(intent);
            }
        });
        add_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyDashboardActivity.this, EnterBatchDetails.class);
                startActivity(intent);
            }
        });

        logout= (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinyDB.clear();
                Intent intent = new Intent(FacultyDashboardActivity.this, LoginChooser.class);
                startActivity(intent);
                finish();
            }
        });
        mark_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyDashboardActivity.this, SelectBatchActivity.class);
                startActivity(intent);
            }
        });
         }
}
