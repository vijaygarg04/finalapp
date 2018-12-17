package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.shivam.attendanceappminor.model.AddBatchModel;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class EnterBatchDetails extends AppCompatActivity {
    MaterialDialog materialDialog;
    EditText filenameet,batch_course,batch_start_year,batch_end_year,batch_stream,subject;
    Button uploadbtn,submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TinyDB tinyDB;
    Calendar c = Calendar.getInstance();
    ArrayList<AddBatchModel> studentidlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_batch_details);
        //Based on details entered and file uploaded, make update in the database
        filenameet=findViewById(R.id.upload_file_edit_text);
        uploadbtn=findViewById(R.id.btnupload);
        studentidlist=new ArrayList<>();
        tinyDB = new TinyDB(EnterBatchDetails.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile();
            }
        });
        //materialDialog = new MaterialDialog.Builder(EnterBatchDetails.this)
        batch_course =  (EditText)findViewById(R.id.batch_course_name);
        batch_end_year = (EditText)findViewById(R.id.end_year);
        batch_start_year = (EditText)findViewById(R.id.start_year);
        batch_stream = (EditText)findViewById(R.id.select_stream);
        subject = (EditText)findViewById(R.id.subject_taught);
        submit = (Button)findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadfile();
                int year = c.get(Calendar.YEAR);
                int month =  c.get(Calendar.MONTH);
                for(int i = 0;  i< studentidlist.size() ; i++){

                    databaseReference.child("faculty")
                            .child(tinyDB.getString("username"))
                            .child("batches")
                            .child(batch_start_year.getText().toString().trim()+"_"+batch_end_year.getText().toString().trim()+"_"+batch_course.getText().toString().trim()+"_"+batch_stream.getText().toString().trim())
                            .child(String.valueOf(Utilsfunctions.findCurrentSemester(Integer.valueOf(batch_start_year.getText().toString())
                                    ,Integer.parseInt(batch_end_year.getText().toString()),year,month))).child(subject.getText().toString())
                            .child(studentidlist.get(i).getStudent_id()).setValue(studentidlist.get(i).getStudent_name());
                    databaseReference.child("student").child(studentidlist.get(i).getStudent_id().toString()).child("semester").child(String.valueOf(Utilsfunctions.findCurrentSemester(Integer.valueOf(batch_start_year.getText().toString())
                            ,Integer.parseInt(batch_end_year.getText().toString()),year,month))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ).child(subject.getText().toString()).setValue(tinyDB.getString("username"));
                    if (i==(studentidlist.size()-2)){
                        Toast.makeText(EnterBatchDetails.this,"Data Updated, Press Back",Toast.LENGTH_LONG).show();
                    }


                }


            }
        });


    }

    private void uploadfile() {
        String filename=filenameet.getText().toString().trim();
        readExcelFile(EnterBatchDetails.this,filename);


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

    private void readExcelFile(Context context, String file) {
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
                int i=0;
                String value[]= new String[2];
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    value[i]=myCell.toString();
                    i++;
                    Log.w("FileUtils", "Cell Value: " +  myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }

                value[0] =  value[0].replace(".","");
                value[0]  = value[0].replace("E10","");
                AddBatchModel addBatchModel = new AddBatchModel(value[0],value[1]);
                studentidlist.add(addBatchModel);
            }

            //do firebase operation here
        }catch (Exception e){
            e.printStackTrace();
        }

        return;
    }
}
