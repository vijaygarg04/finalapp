package com.example.shivam.attendanceappminor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    TinyDB tinyDB;
    boolean b;
    Intent intent2;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Admin will upload batch wise data of all the students before the usage of application.
        // Redirect to LoginScreen where the option for choosing the type is given.(Check for tinyDB values)
        //If logged_in = true, what kind of login is it. and then redirect directly to the dashboard of respective user.
        tinyDB = new TinyDB(SplashScreen.this);
        try{

           b   = tinyDB.getBoolean("logged_in");
           type = tinyDB.getString("login_type");

           if (b){
               Toast.makeText(SplashScreen.this,"welcome",Toast.LENGTH_LONG).show();
               if (type.equals("admin")){
                    intent2= new Intent(SplashScreen.this,AdminDashboardActivity.class);
               }else if(type.equals("faculty")){
                   intent2 = new Intent(SplashScreen.this,FacultyDashboardActivity.class);

               }
               else if(type.equals("student")){
                   intent2 = new Intent(SplashScreen.this,StudentDashboardActivity.class);
               }
              //startActivity(intent2);
           }else{
               intent2 = new Intent(SplashScreen.this,LoginChooser.class);

           }
           startActivity(intent2);
           finish();

        }catch (Exception e){
            Intent intent = new Intent(SplashScreen.this,LoginChooser.class);
            startActivity(intent);
            Toast.makeText(SplashScreen.this, "First Time Login",Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
