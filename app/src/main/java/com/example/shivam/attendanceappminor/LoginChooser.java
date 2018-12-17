package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginChooser extends AppCompatActivity {
    Button student,faculty,admin;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_chooser);
        student = (Button)findViewById(R.id.student_btn);
        faculty = (Button)findViewById(R.id.faculty_btn);
        admin = (Button)findViewById(R.id.admin_btn);
        intent = new Intent(LoginChooser.this,LoginActivity.class);
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GO to faculty dashboard
                intent.putExtra("type","faculty");
                startActivity(intent);
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
intent.putExtra("type","admin");
                startActivity(intent);
                finish();
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
intent.putExtra("type","student");
                startActivity(intent);
                finish();}
        });

    }
}
