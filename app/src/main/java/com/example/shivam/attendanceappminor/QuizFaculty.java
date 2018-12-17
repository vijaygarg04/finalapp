package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizFaculty extends AppCompatActivity {
Button upload, generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_faculty);
        upload = (Button)findViewById(R.id.upload_quiz);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(QuizFaculty.this,UploadQuizFaculty.class);
                startActivity(i);
            }
        });
    }
}
