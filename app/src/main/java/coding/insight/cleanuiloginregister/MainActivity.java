package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref3;

    DatePickerDialog.OnDateSetListener setListener;
    String[] items =  {"Cash","Checks.","Debit cards","Credit cards","Mobile payments","Electronic bank transfers"};
    AutoCompleteTextView autoCompleteitems;

   AutoCompleteTextView autoCompleteprod;
    TextInputEditText edit2;
    TextInputEditText edit;
    TextInputEditText edit3;
    ArrayAdapter<String> adapterItems3;
    ArrayList<String> spinnerdatallist2;
    AutoCompleteTextView autoCompletecust;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    ArrayList<String> spinnerdatallist;

    Button btn;
    String  item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteprod = (AutoCompleteTextView) findViewById(R.id.auto_complete_txt);
       edit2 = findViewById(R.id.price);
        edit = findViewById(R.id.Date);
        edit3 = findViewById(R.id.quantity);
        autoCompletecust = findViewById(R.id.auto_complete_txt2);
       ref3= FirebaseDatabase.getInstance().getReference();
        spinnerdatallist2=new ArrayList<>();
        retrieve2();
        adapterItems2 = new ArrayAdapter<String>(this,R.layout.list_item,spinnerdatallist2);
        autoCompletecust.setAdapter(adapterItems2);
        autoCompletecust.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteitems = findViewById(R.id.auto_complete_txt3);

       adapterItems3 = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteitems.setAdapter(adapterItems3);
        autoCompleteitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
       btn = findViewById(R.id.button);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
    

        ref2=FirebaseDatabase.getInstance().getReference().child("purshasyyy");
        ref= FirebaseDatabase.getInstance().getReference();
        spinnerdatallist=new ArrayList<>();
        retrive();

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,spinnerdatallist);
        autoCompleteprod.setAdapter(adapterItems);
        autoCompleteprod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }

        });
        

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               save();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        edit.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    private void retrive() {
        ref.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerdatallist.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    spinnerdatallist.add(0,"choose");
                    spinnerdatallist.add(item.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




   private void retrieve2() {
        ref3.child("vendor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerdatallist2.clear();
                for (DataSnapshot item2 : dataSnapshot.getChildren()) {
                    spinnerdatallist2.add(0,"choose match");
                    spinnerdatallist2.add(item2.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }

    private void save() {
        final String name = autoCompleteprod.getText().toString().trim();
        final String name2 = autoCompletecust.getText().toString().trim();
        final String quantity = edit3.getText().toString().trim();
        final String pricey = edit2.getText().toString().trim();
        final String type = autoCompleteitems.getText().toString().trim();
        final String Date= edit.getText().toString().trim();
        if(name.equals("choose match")) {
            Toast.makeText(MainActivity.this,"noo",Toast.LENGTH_LONG).show();
        }
        if(name2.equals("choose match")) {
            Toast.makeText(MainActivity.this,"noo",Toast.LENGTH_LONG).show();
        }
        if (quantity.isEmpty()) {
            edit3.setError("It's empty");
            edit3.requestFocus();
            return;
        }
        if (pricey.isEmpty()) {
            edit2.setError("It's empty");
            edit2.requestFocus();
            return;
        } if (Date.isEmpty()) {
            edit.setError("It's empty");
            edit.requestFocus();
            return;
        }

        purshasy p2= new purshasy(name,quantity,Date,name2, pricey, type);
        ref2.push().setValue(p2);
        Toast.makeText(MainActivity.this,"inserted text",Toast.LENGTH_LONG).show();

    }



}
