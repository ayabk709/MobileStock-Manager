package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.PrimitiveIterator;

public class Sales_entry extends AppCompatActivity {
   /* DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref3;
    sales S;
    Bitmap  bmp,scaledbmp;
    DateFormat dateFormat;
    int pagewidth = 1200;
   int pageHeight = 1120;
   // int pagewidth = 792;
    long invoiceNo = 0;
    DatePickerDialog.OnDateSetListener setListener;
    String[] items = {"Cash", "Checks.", "Debit cards", "Credit cards", "Mobile payments", "Electronic bank transfers"};
    //  String[] itemss =  {"Cash","Checks.","Debit cards","Credit cards","Mobile payments","Electronic bank transfers"};
    AutoCompleteTextView autoCompleteitems;

    ArrayAdapter<String> adapterItems3;
    AutoCompleteTextView autoCompleteprod;
   // TextInputEditText edit2;
   TextView edit2;
    TextInputEditText edit;
  //  TextView edit;
    TextInputEditText edit3;
  //  TextView edit3;
    sales s;
    ArrayList<String> spinnerdatallist2;
    AutoCompleteTextView autoCompletecust;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    ArrayList<String> spinnerdatallist;
    Button bnt1;
    Button btn;
    String item;
    Date obj ;*/

    DatabaseReference ref3;
    sales S;
    Bitmap  bmp,scaledbmp;
    DateFormat dateFormat;
    int pagewidth = 1200;
    int pageHeight = 1120;
    // int pagewidth = 792;

    DatePickerDialog.OnDateSetListener setListener;
    String[] items = {"Cash", "Checks", "Debit cards", "Credit cards", "Mobile payments", "Electronic bank transfers"};
    //  String[] itemss =  {"Cash","Checks.","Debit cards","Credit cards","Mobile payments","Electronic bank transfers"};
   Spinner autoCompleteitems;

    ArrayAdapter<String> adapterItems3;
    Spinner autoCompleteprod;
    // TextInputEditText edit2;
    TextView edit2;
    TextInputEditText edit;
    //  TextView edit;
    TextInputEditText edit3;
    //  TextView edit3;
    sales s;
    ArrayList<String> spinnerdatallist2;
    Spinner autoCompletecust;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    ArrayList<String> spinnerdatallist;
    Button bnt1;
    Button btn;
    Button btn1;
    String item;
    Date obj ;

    DatabaseReference dbref;
    DatabaseReference ref;
    DatabaseReference ref2;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_entry);
        autoCompleteprod = (Spinner) findViewById(R.id.podnm);
        autoCompletecust = (Spinner) findViewById(R.id.cusnm);
        autoCompleteitems = findViewById(R.id.payment);
        edit2 = findViewById(R.id.price2);
        edit = findViewById(R.id.Date2);
        edit3 = findViewById(R.id.quantity2);
        btn = findViewById(R.id.btnadd);
        btn1=findViewById(R.id.pdf);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dbref = FirebaseDatabase.getInstance().getReference();
        ref2 = FirebaseDatabase.getInstance().getReference();

       ref = FirebaseDatabase.getInstance().getReference("sales");
        fetchdata();
        spinnerdatallist= new ArrayList<String>();
        adapterItems = new ArrayAdapter<String>(Sales_entry.this, android.R.layout.simple_spinner_dropdown_item, spinnerdatallist);
        autoCompleteprod.setAdapter(adapterItems);

        fetchdata2();
        spinnerdatallist2= new ArrayList<String>();
        adapterItems2 = new ArrayAdapter<String>(Sales_entry.this, android.R.layout.simple_spinner_dropdown_item, spinnerdatallist2);
        autoCompletecust.setAdapter(adapterItems2);
        adapterItems3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        autoCompleteitems.setAdapter(adapterItems3);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Sales_entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        edit.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*try {
                    createpdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                save();
            }
        });


ActivityCompat.requestPermissions(this,new String[]{
        Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);

//createdpdf();
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            createpdf();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
});


        }
/*
    private void createpdf() {
        obj=new Date();
        // String prod = autoCompleteprod.getText().toString();
        //String cust = autoCompletecust.getText().toString();
        //String item3= autoCompleteitems.getText().toString();
        if ( edit.getText().toString().length() == 0|| edit2.getText().toString().length() == 0 || edit3.getText().toString().length() == 0 ) {
            Toast.makeText(Sales_entry.this, "field empty", Toast.LENGTH_LONG).show();
        } else {
            PdfDocument mypdf = new PdfDocument();
            Paint paint = new Paint();
            Paint titlePaint =new Paint();
            PdfDocument.PageInfo myPageinfo1 = new PdfDocument.PageInfo.Builder(250,400,1).create();
            PdfDocument.Page myPage = mypdf.startPage(myPageinfo1);
            Canvas canvas =myPage.getCanvas();

            titlePaint.setTextAlign(Paint.Align.CENTER);
            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlePaint.setTextSize(70);
            canvas.drawText("Magic invent",pagewidth/2,270,titlePaint);
            paint.setColor(Color.rgb(0,113,188));
            paint.setTextSize(30f);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Call 0666806199",1160,40,paint);
            canvas.drawText(" 0666806199",1160,80,paint);
            titlePaint.setTextAlign(Paint.Align.CENTER);
            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
            paint.setTextSize(70);
            canvas.drawText("invoices",pagewidth/2,500,titlePaint);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize(35f);
            paint.setColor(Color.BLACK);
            canvas.drawText("Custumor name :"+autoCompletecust.getSelectedItem().toString(),20,590,paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Invoice No:"+"232425",pagewidth-20,590,paint);
            dateFormat = new SimpleDateFormat("dd/mm/yy");
            canvas.drawText("Date:"+dateFormat.format(obj),pagewidth-20,640,paint);

            dateFormat=new SimpleDateFormat("HH:mm:ss");
            canvas.drawText("Time:"+dateFormat.format(obj),pagewidth-20,690,paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            canvas.drawRect(20,780,pagewidth-20,860,paint);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText("Si.No.",40,830,paint);
            canvas.drawText("Product name",200,830,paint);
            canvas.drawText("Price",700,830,paint);
            canvas.drawText("Qty",900,830,paint);
            canvas.drawText("Total",1050,830,paint);



            canvas.drawLine(180,790,180,840,paint);
            canvas.drawLine(680,790,680,840,paint);
            canvas.drawLine(880,790,880,840,paint);
            canvas.drawLine(1030,790,1030,840,paint);
            float total=0;
            if(autoCompleteprod.getSelectedItemPosition()!=0){

                canvas.drawText("1",40,950,paint);
                canvas.drawText(autoCompleteprod.getSelectedItem().toString(),200,950,paint);
                canvas.drawText(edit3.getText().toString(), 220,950,paint);
                canvas.drawText(edit2.getText().toString(), 700,950,paint);
                total=Float.parseFloat(edit2.getText().toString())*(Float.parseFloat(edit3.getText().toString()));
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(String.valueOf(total),pagewidth-40,950,paint);
                paint.setTextAlign(Paint.Align.LEFT);
            }







            mypdf.finishPage(myPage);
            File myFile = new File( Environment.getExternalStorageDirectory(),"/Hell.pdf");
            try {
                mypdf.writeTo(new FileOutputStream(myFile));
            }
            catch (Exception e){
                e.printStackTrace();

            }




        }
        }
 */





  /*
       bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createpdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

         //   createpdf();

    }

    private void createpdf() throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "mypdf.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file);
        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDocument);
        Paragraph paragraph = new Paragraph("Helloe how are u ");
        document.add(paragraph);
        document.close();
        Toast.makeText(this, "pddf created", Toast.LENGTH_LONG).show();

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(400, 600, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
        Canvas canvas = myPage.getCanvas();
        Paint myPaint = new Paint();
        Paint title = new Paint();

        canvas.drawText("welcome", 40, 50, myPaint);
        title.setTextAlign(Paint.Align.CENTER);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(70);
        final String name = autoCompleteprod.getText().toString().trim();

        title.setTextAlign(Paint.Align.RIGHT);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(70);

        autoCompletecust.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String name2 = autoCompletecust.getOnItemClickListener().toString();
                int g= 60, r = 140;
                myPage.getCanvas().drawText("cust:" + name2, g, r, myPaint);
            }
        });
        final String quantity = edit3.getText().toString().trim();

        final String pricey = edit2.getText().toString().trim();
        final String type = autoCompleteitems.getText().toString().trim();
        final String Date = edit.getText().toString().trim();
        float total = 0;
        int x = 60, y = 80;
        title.setTextAlign(Paint.Align.RIGHT);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(70);
        myPage.getCanvas().drawText("quantity:" + quantity, x, y, myPaint);
        int f = 60, j = 100;
        title.setTextAlign(Paint.Align.RIGHT);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(70);
        myPage.getCanvas().drawText("price:" + pricey, f, j, myPaint);
        int b = 60, c = 120;
        title.setTextAlign(Paint.Align.RIGHT);
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(70);
        total = Float.parseFloat(quantity) * (Float.parseFloat(pricey));
        ;
        myPage.getCanvas().drawText("total:" + total, b, c, myPaint);

        myPdfDocument.finishPage(myPage);
        File myFile = new File(Environment.getExternalStorageDirectory(), "/Hell.pdf");
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
  private void createpdf() throws FileNotFoundException {
      String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
      File file = new File(pdfPath, "mypdf.pdf");
      OutputStream outputStream = new FileOutputStream(file);
      PdfWriter writer = new PdfWriter(file);
      com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
      Document document = new Document(pdfDocument);
      Paragraph paragraph = new Paragraph("Helloe how are u ");
      document.add(paragraph);
      document.close();
      Toast.makeText(this, "pddf created", Toast.LENGTH_LONG).show();

      PdfDocument myPdfDocument = new PdfDocument();
      PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(400, 600, 1).create();
      PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
      Canvas canvas = myPage.getCanvas();
      Paint myPaint = new Paint();
      Paint title = new Paint();

      canvas.drawText("welcome", 300, 60, myPaint);
      title.setTextAlign(Paint.Align.CENTER);
     // title.setColor(Color.ye);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
     /* float[] width={100f,100f};
      Table table= new Table(width);
      table.setHorizontalAlignment(HorizontalAlignment.CENTER);
      table.addCell(new Cell().add(new Paragraph("date")));
      table.addCell(new Cell().add(new Paragraph("")));

      table.addCell(new Cell().add(new Paragraph("Prod")));
      table.addCell(new Cell().add(new Paragraph(autoCompleteprod.getSelectedItem().toString())));

      table.addCell(new Cell().add(new Paragraph("quantity")));
      table.addCell(new Cell().add(new Paragraph("")));

      table.addCell(new Cell().add(new Paragraph("price")));
      table.addCell(new Cell().add(new Paragraph("")));

      table.addCell(new Cell().add(new Paragraph("total")));
      table.addCell(new Cell().add(new Paragraph("")));


*/    final String name = autoCompleteprod.getSelectedItem().toString();
      final String name2 = autoCompletecust.getSelectedItem().toString();
      final String quantity = edit3.getText().toString().trim();
      final String pricey = edit2.getText().toString().trim();
      final String type = autoCompleteitems.getSelectedItem().toString();
      final String Date = edit.getText().toString().trim();
      canvas.drawText("Date"+Date, 40, 80, myPaint);
      title.setTextAlign(Paint.Align.CENTER);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);


  // String month,day,year;
      float total = 0;
      int x = 60, y = 300;
      title.setTextAlign(Paint.Align.RIGHT);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
      myPage.getCanvas().drawText("Product:  " + name, x, y, myPaint);
      int f = 60, j = 320;
      title.setTextAlign(Paint.Align.RIGHT);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
      myPage.getCanvas().drawText("cust:  " + name2, f, j, myPaint);
      int b = 60, c = 340;
      title.setTextAlign(Paint.Align.RIGHT);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
      myPage.getCanvas().drawText("quantity:  " + quantity, b, c, myPaint);
      int d= 60, m = 360;
      title.setTextAlign(Paint.Align.RIGHT);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
      myPage.getCanvas().drawText("price:  " + pricey, d, m, myPaint);
      int z = 60, t= 380;
      title.setTextAlign(Paint.Align.RIGHT);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
      myPage.getCanvas().drawText("Type of payment:  " + autoCompleteitems.getSelectedItem().toString(), z, t, myPaint);
      int g = 60, k = 400;
      title.setTextAlign(Paint.Align.RIGHT);
      title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
      title.setTextSize(70);
      total = Float.parseFloat(quantity) * (Float.parseFloat(pricey));
      ;
      myPage.getCanvas().drawText("total:" + total, g, k, myPaint);



     /* month = findViewById(R.id.textView10);
      year = findViewById(R.id.textView11);
      day = findViewById(R.id.textView12);

      Date current = Calendar.getInstance().getTime();
      String formattedDate= DateFormat.getDateInstance(DateFormat.FULL).format(current);
      String[] split= formattedDate.split(",");
      Log.d("MLOG",current.toString());
      Log.d("MLOG",formattedDate);
      month.setText(split[1]);
      day.setText(split[0]);
      year.setText(split[2]);
      Log.d("MLOG",split[0].trim());
      Log.d("MLOG",split[1].trim());
      Log.d("MLOG",split[2].trim());
*/
      myPdfDocument.finishPage(myPage);
      File myFile = new File(Environment.getExternalStorageDirectory(), "/Hell.pdf");
      try {
          myPdfDocument.writeTo(new FileOutputStream(myFile));
      } catch (Exception e) {
          e.printStackTrace();
      }}








   /* public void create(View view) {

        S = new sales();
        if (autoCompleteprod.getText().toString().length() == 0 || autoCompletecust.getText().toString().length() == 0 || edit.getText().toString().length() == 0 || edit.getText().toString().length() == 0 || edit2.getText().toString().length() == 0 || edit3.getText().toString().length() == 0 || autoCompleteitems.getText().toString().length() == 0) {
            Toast.makeText(Sales_entry.this, "field empty", Toast.LENGTH_LONG).show();
        } else {
            PdfDocument myPdfDocument = new PdfDocument();

            Paint title = new Paint();
            PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300, 400, 1).create();
            PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
            Paint myPaint = new Paint();
            final String name = autoCompleteprod.getText().toString().trim();
            final String name2 = autoCompletecust.getText().toString().trim();
            final String quantity = edit3.getText().toString().trim();
            final String pricey = edit2.getText().toString().trim();
            final String type = autoCompleteitems.getText().toString().trim();
            final String Date = edit.getText().toString().trim();
            int x = 10, y = 25;
             myPage.getCanvas().drawText(quantity,x,y,myPaint);
            myPdfDocument.finishPage(myPage);

            String myFilePath = Environment.getExternalStorageDirectory().getPath() +"/Hello.pdf";
            File myFile = new File(myFilePath);
            try {
                myPdfDocument.writeTo(new FileOutputStream(myFile));
            }
            catch (Exception e){
                e.printStackTrace();

            }

            myPdfDocument.close();
        }
       PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(400, 600, 1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

        Paint myPaint = new Paint();
        Paint title =new Paint();
        Canvas canvas =myPage.getCanvas();
        canvas.drawText("welcome",40,50,myPaint);
        title.setTextAlign(Paint.Align.CENTER);
        title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        title.setTextSize(70);*/
       /* canvas.drawText("Magic invent",pagewidth/2,270,title);
        myPaint.setColor(Color.rgb(0,113,188));
        myPaint.setTextSize(30f);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Call 0666806199",1160,40,myPaint);
        canvas.drawText(" 0666806199",1160,80,myPaint);
        title.setTextAlign(Paint.Align.CENTER);
        title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
        myPaint.setTextSize(70);
        canvas.drawText("invoices",pagewidth/2,500,title);
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(35f);
        myPaint.setColor(Color.BLACK);
        canvas.drawText("Custumor name :"+autoCompletecust.getText(),20,590,myPaint);
        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Invoice No:"+"232425",pagewidth-20,590,myPaint);
       // dateFormat = new SimpleDateFormat("dd/mm/yy");
        canvas.drawText("Date:"+dateFormat.format(S),pagewidth-20,640,myPaint);

       // dateFormat=new SimpleDateFormat("HH:mm:ss");
        //canvas.drawText("Time:"+dateFormat.format(S),pagewidth-20,690,myPaint);
        /*myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20,760,pagewidth-20,860,myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("Si.No.",40,830,myPaint);
        canvas.drawText("Product name",200,830,myPaint);
        canvas.drawText("Price",700,830,myPaint);
        canvas.drawText("Qty",900,830,myPaint);
        canvas.drawText("Total",1050,830,myPaint);
        float total = 0;
        canvas.drawLine(180,790,180,840,myPaint);
        canvas.drawLine(680,790,680,840,myPaint);
        canvas.drawLine(880,790,880,840,myPaint);
        canvas.drawLine(1030,790,1030,840,myPaint);
        canvas.drawText("1",40,950,myPaint);
canvas.drawText(autoCompleteprod.getText().toString(),220,950,myPaint);
canvas.drawText(edit2.getText().toString(),700,950,myPaint);
canvas.drawText(edit3.getText().toString(),900,950,myPaint);

total=Float.parseFloat(edit3.getText().toString())*(Float.parseFloat(edit2.getText().toString()));
myPaint.setTextAlign(Paint.Align.RIGHT);
canvas.drawText(String.valueOf(total),pagewidth-40,950,myPaint);
myPaint.setTextAlign(Paint.Align.LEFT);*/
/*
canvas.drawLine(680,1200,700,1250,myPaint);
myPaint.setTextAlign(Paint.Align.LEFT);
myPaint.setColor(Color.rgb(247,147,30));
canvas.drawRect(680,1350,pagewidth-20,1450,myPaint);
myPaint.setColor(Color.BLACK);
myPaint.setTextSize(50f);
myPaint.setTextAlign(Paint.Align.LEFT);
canvas.drawText("total",700,1415,myPaint);
myPaint.setTextAlign(Paint.Align.RIGHT);


myPdfDocument.finishPage(myPage);
        File myFile = new File( Environment.getExternalStorageDirectory(),"/Hell.pdf");
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }*/


    private void fetchdata2() {
        ref2.child("custumor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerdatallist2.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    spinnerdatallist2.add(0,"choose match");
                    spinnerdatallist2.add(item.child("name").getValue().toString());
                }
                adapterItems2.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    private void save() {
        String  item=autoCompleteprod.getSelectedItem().toString();
        String  cust=autoCompletecust.getSelectedItem().toString();
        String  quantity=edit3.getText().toString();
        String  date=edit.getText().toString();
        String price=edit2.getText().toString();
        String  payment=autoCompleteitems.getSelectedItem().toString();
        if(item.equals("choose match")) {
            Toast.makeText(Sales_entry.this,"noo",Toast.LENGTH_LONG).show();
        }
        if(cust.equals("choose match")) {
            Toast.makeText(Sales_entry.this,"noo",Toast.LENGTH_LONG).show();
        }
        if (quantity.isEmpty()) {
            edit3.setError("It's empty");
            edit3.requestFocus();
            return;
        }
        if (price.isEmpty()) {
            edit2.setError("It's empty");
            edit2.requestFocus();
            return;
        } if (date.isEmpty()) {
            edit.setError("It's empty");
            edit.requestFocus();
            return;
        }
      sales p2= new sales(item,quantity,date,cust,price,payment);
        ref.push().setValue(p2);
        Toast.makeText(Sales_entry.this,"inserted text",Toast.LENGTH_LONG).show();
    }

    private void fetchdata() {
        dbref.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerdatallist.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    spinnerdatallist.add(0,"choose match");
                    spinnerdatallist.add(item.child("name").getValue().toString());

                }
                adapterItems.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}



