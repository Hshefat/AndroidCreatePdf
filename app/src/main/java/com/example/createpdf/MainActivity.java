package com.example.createpdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button pbtn;
    String [] informationArray = new String[]{"Name:", "Company Name:", "Address:", " Phone Number: ", "Email:"};


    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pbtn = findViewById(R.id.pdf_button);
      /*  pbtn = this.findViewById(R.id.pdf_button);*/

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPDF();
        /*pbtn = findViewById(R.id.pdf_button);*/
    }

    private void createPDF() {
        pbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Toast.makeText(, "Please input medicine name", Toast.LENGTH_SHORT).show();*/
                showToast(" Created a folder and pdf file");

                PdfDocument mypdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                L.i("Click");

                PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(600, 900, 1).create();
                PdfDocument.Page myPage = mypdfDocument.startPage(myPageInfo1);
                L.i("Pdf Document");

                Canvas canvas = myPage.getCanvas();
               /* canvas.drawText(" Hello Shefat", 40, 50, myPaint);
                mypdfDocument.finishPage(myPage);*/

                /*myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(12.0f);
                canvas.drawText("Heading Text", myPageInfo1.getPageWidth() / 2, 30, myPaint);

                myPaint.setTextSize(8.0f);
                myPaint.setColor(Color.rgb(100, 50, 0));
                canvas.drawText("Road No: 15, Dhaka, Bangladesh", myPageInfo1.getPageWidth() / 2, 40, myPaint);
                L.i("Heading");*/


                /*myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(50, 50, 50));
                canvas.drawText("Customer Information", 20, 70, myPaint);*/


                /*Name LIst*/
                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(12.0f);
                myPaint.setColor(Color.BLACK);
                int startXPosition = 40;
                int endXPosition = myPageInfo1.getPageWidth() - 300;
                int startYPosition = 40;
                for (int i = 0; i < 5; i++)
                {
                    canvas.drawText(informationArray[i], startXPosition, startYPosition, myPaint);
                    //canvas.drawLine(startXPosition, startYPosition+3, endXPosition, startYPosition+3, myPaint);
                    //  canvas.drawLine(10, 190, myPageInfo1.getPageWidth()-10, 190, myPaint);// line bellow Email
                    startYPosition+=20;
                }


             /*   Photo Box */
                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(2);
                canvas.drawRect(20, 20, myPageInfo1.getPageWidth()-30,130, myPaint);// box hight
                /*colum into box */
                /*canvas.drawLine(100, 100, 100, 200, myPaint);
                canvas.drawLine(200, 100, 200, 200, myPaint);*/
                myPaint.setStrokeWidth(0);
                myPaint.setStyle(Paint.Style.FILL);
                L.i("Draw Line ");
                /*canvas.drawText("Photo", 35, 150,myPaint);
                canvas.drawText("Photo", 150, 150,myPaint);
                canvas.drawText("Photo", 220, 150,myPaint);*/
                L.i("Photo Box");




              // canvas.drawLine(10, 390, myPageInfo1.getPageWidth()-10, 390, myPaint);    // single line draw up name





                /*For Bold Border Left*/
                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(2);
                /*For Bold Border left*/
                canvas.drawLine(20, 120, 20, 700, myPaint);// name cloum
                /*For Bold Border meddile*/
                canvas.drawLine(170, 130, 170, 700, myPaint);// name cloum
                L.i("Name List ");
                /*For Bold Border right*/
                canvas.drawLine(570, 120, 570, 700, myPaint);// name cloum
                /*For Bold Border Bottom*/
                canvas.drawLine(20, 700, myPageInfo1.getPageWidth()-30, 700, myPaint);// name row




                /*Photo BOX*/
               /* myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(2);
                canvas.drawRect(10, 250, myPageInfo1.getPageWidth()-10,350, myPaint);// box hight

                *//*colum into box *//*
                canvas.drawLine(85, 200, 85, 300, myPaint);
                canvas.drawLine(200, 200, 200, 300, myPaint);

                myPaint.setStrokeWidth(0);
                myPaint.setStyle(Paint.Style.FILL);
                L.i("Draw Line ");

                canvas.drawText("Photo", 35, 250,myPaint);
                canvas.drawText("Photo", 150, 250,myPaint);
                canvas.drawText("Photo", 190, 250,myPaint);
                L.i("Photo Box");
*/



               /* Note comment */
               /* canvas.drawText("Note: ", 10, 320, myPaint);
                canvas.drawLine(35, 325, myPageInfo1.getPageWidth()-10, 325, myPaint);
                canvas.drawLine(10, 345, myPageInfo1.getPageWidth()-10, 345, myPaint);
                canvas.drawLine(10, 365, myPageInfo1.getPageWidth()-10, 365, myPaint);
                L.i("Note ");*/

                mypdfDocument.finishPage(myPage);

                L.i("myPage ");
                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Telo/";
                File file = new File(directory_path);

                if (!file.exists()) {
                    file.mkdirs();
                }

                String fileName = "Prescription_"+ Calendar.getInstance().getTime().toString()+".pdf";
                String targetPdf = directory_path+fileName;
                File filePath = new File(targetPdf);
                L.i("File");

                try {
                    mypdfDocument.writeTo(new FileOutputStream(filePath));

                    L.i("Try");

                } catch (IOException e) {
                    e.printStackTrace();
                    L.i("Catch");
                }
                mypdfDocument.close();
                L.i("Close");
            }

        });

    }
}