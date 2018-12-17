package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.attendanceappminor.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText user_name,password,batch_id;
    Button button;
    FirebaseDatabase firebaseDatabase;
    TinyDB tinyDB;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tinyDB = new TinyDB(LoginActivity.this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        user_name = (EditText) findViewById(R.id.user_name_edit);
        password = (EditText)findViewById(R.id.password_edit);
        button = (Button)findViewById(R.id.submit_btn);
        final Intent intent = getIntent();

        final String type  = intent.getStringExtra("type");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Do validations
                //username will be student_id(enrollment number)

        if(type.equals( "admin")){
            databaseReference.child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String id  = dataSnapshot.child("adminId").getValue().toString();
                    String pass  = dataSnapshot.child("adminPassword").getValue().toString();
                    if (id.equals(user_name.getText().toString().trim()) && pass.equals(password.getText().toString())){
                        tinyDB.putString("login_type","admin");
                        tinyDB.putBoolean("logged_in",true);
                        tinyDB.putString("username",user_name.getText().toString());
                        Intent intent1 = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                        startActivity(intent1);
                        finish();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else if (type.equals("faculty")){
            databaseReference.child("faculty").child(user_name.getText().toString()).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot!=null){
                        if (dataSnapshot.getValue(String.class).equals(password.getText().toString())){
                        Intent intent1 = new Intent(LoginActivity.this,FacultyDashboardActivity.class);
                        tinyDB.putString("login_type","faculty");
                        tinyDB.putBoolean("logged_in",true);
                        tinyDB.putString("username",user_name.getText().toString());
                        startActivity(intent1);
                        finish();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Wrong Credentials",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if(type.equals("student")){

            databaseReference.child("student").child(user_name.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String password_text = dataSnapshot.child("password").getValue(String.class);
                   if(password.getText().toString().equals(password_text)){

                       //Valid Student

                       Intent intent2 = new Intent(LoginActivity.this,StudentDashboardActivity.class);
                       tinyDB.putString("student_id",user_name.getText().toString());
                       tinyDB.putBoolean("logged_in",true);
                       tinyDB.putString("batch_id",dataSnapshot.child("batch_id").getValue(String.class));
                       tinyDB.putString("login_type","student");
                       startActivity(intent2);
                       finish();
                   }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


            }
        });

    }
}
