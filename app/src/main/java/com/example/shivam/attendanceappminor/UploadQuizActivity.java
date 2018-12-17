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

import com.example.shivam.attendanceappminor.model.Question;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class UploadQuizActivity extends AppCompatActivity {

    EditText questionfile,quizname,quiztime,batch_id_edit_text;
    Button upload ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Question> questionArrayList;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_quiz);
        questionfile=findViewById(R.id.questionfilename);
        quizname=findViewById(R.id.quizname);
        quiztime=findViewById(R.id.quiztime);
        batch_id_edit_text = (EditText)findViewById(R.id.batch_id_quiz);
        tinyDB = new TinyDB(UploadQuizActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        upload=findViewById(R.id.btnuploadquestion);
        questionArrayList=new ArrayList<>();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readExcelFile(UploadQuizActivity.this,questionfile.getText().toString(),6);

            }
        });

    }
    private void readExcelFile(Context context, String file, int size) {
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
                correctoption =  correctoption.replace(".","");
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
                inputquizdata(questionArrayList.get(i));

            }

            return null;
        }



    }

    private void inputquizdata(Question question) {
        //todo make firebase calls
        String batch_id = batch_id_edit_text.getText().toString();
        //Calendar calendar = Calendar.getInstance();
        //int year  = calendar.get(Calendar.YEAR);
        //int month = calendar.get(Calendar.MONTH);
        //String data[] =  batch_id.split("_");
        //int sem  = Utilsfunctions.findCurrentSemester(Integer.parseInt(data[0]),Integer.parseInt(data[1]),year,month);
        //databaseReference.child("quiz").child(quizname.getText().toString()).

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