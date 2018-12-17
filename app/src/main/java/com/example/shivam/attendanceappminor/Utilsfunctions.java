package com.example.shivam.attendanceappminor;

/**
 * Created by shivam on 8/12/18.
 */

public class Utilsfunctions {

    public static int findCurrentSemester(int start_year, int end_year, int current_year, int current_month){
        int r=0;
        if (current_year<start_year){
            return 12;
        }
        if(current_year>end_year){
            return 11;
        }

        else if(start_year<current_year){

            switch (current_year-start_year){

                case 0:
                    r = (current_month/7);
                break;
                case 1:
                    r = (2+(current_month)/7);
                break;
                case 2:
                    r = (4+(current_month)/7);
                break;
                case 3:
                    r = (6+(current_month)/7);

                break;
                case 4:
                    r = (8+(current_month)/7);
                break;

            }
        }
        return r;

    }
    public static String codeResult(int code){
        //returns the string corresponding a particular code according to the application
        String response =  "default";
        switch (code){
            case 11:
                response = "You Have Already Graduated, Thus Current Semester Can't Be Found Out";
                break;
            case 12:
                response =  "Your Data Is Invalid ";
                break;
            case 0:
                response = "Your Batch Hasn't Started Yet";
        }

        return response;
    }
}
