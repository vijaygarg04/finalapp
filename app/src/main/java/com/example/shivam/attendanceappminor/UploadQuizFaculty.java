package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.attendanceappminor.model.Faculty;
import com.example.shivam.attendanceappminor.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class UploadQuizFaculty extends AppCompatActivity {
EditText quizname, quiztime,filename;
Button uploadbtn;
    ArrayList<Question> questionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_quiz_faculty);
        quizname=findViewById(R.id.quizname);
        quiztime=findViewById(R.id.quiztime);
        filename=findViewById(R.id.filename);
        uploadbtn=findViewById(R.id.uploadbtn);
        questionArrayList=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("quiz");
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String squizname=quizname.getText().toString();
                int squiztime=Integer.parseInt(quiztime.getText().toString());

                String sfilename=filename.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("quiz").child(squizname).child("time").setValue(squiztime);
                readExcelFile(UploadQuizFaculty.this,sfilename,6);

            }
        });
    }
    private void readExcelFile(Context context, String file,int size) {
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.w("FileUtils", "Storage not available or read only");
            return;
        }
        try{
            // Creating Input Stream
            File file1 = new File(context.getExternalFilesDir(null), file);
            FileInputStream myInput = new FileInputStream(file1);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIter = mySheet.rowIterator();
            rowIter.next();
            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                String values[]=new String[size];
                int i=0;
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    values[i]=myCell.toString();
                    i++;
                    Log.w("FileUtils", "Cell Value: " +  myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
                String correctoption  = values[5];

                correctoption =  correctoption.replace(".0","");
                correctoption  = correctoption.replace("E10","");
                Question question=new Question(values[0],values[1],values[2],values[3],values[4],correctoption);
                questionArrayList.add(question);


            }
            MyTask myTask=new MyTask();
            myTask.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        return;
    }
    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ddMMyyyy");
            String sdate=simpleDateFormat.format(date).toString().trim();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected Void doInBackground(Void  ...aVoid) {

            for (int i = 0; i < questionArrayList.size(); i++) {
                inputquizdata(questionArrayList.get(i),i+1);

            }

            return null;
        }



    }

    private void inputquizdata(Question question,int questionnumber) {
        //todo make firebase calls
        String squizname=quizname.getText().toString();
        FirebaseDatabase.getInstance().getReference().child("quiz").child(squizname).child("question").child("question"+questionnumber).setValue(question);

    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
