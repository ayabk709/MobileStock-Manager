package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class update_salesy extends AppCompatActivity {
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref3;

    DatePickerDialog.OnDateSetListener setListener;
    String[] items = {"Cash", "Checks", "Debit cards", "Credit cards", "Mobile payments", "Electronic bank transfers"};
    //  String[] itemss =  {"Cash","Checks.","Debit cards","Credit cards","Mobile payments","Electronic bank transfers"};
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
    purshasy p ;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_salesy);
        autoCompleteprod = findViewById(R.id.salesyprod);
        edit2 = findViewById(R.id.salesyprice);
        edit = findViewById(R.id.salesydate);
        edit3 = findViewById(R.id.salesyquantity);
        autoCompletecust = findViewById(R.id.salescust);
        ref3 = FirebaseDatabase.getInstance().getReference();
        spinnerdatallist2 = new ArrayList<>();
        retrieve2();
        adapterItems2 = new ArrayAdapter<String>(this, R.layout.list_item3, spinnerdatallist2);
        autoCompletecust.setAdapter(adapterItems2);
        autoCompletecust.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteitems = findViewById(R.id.salesytype);

        adapterItems3 = new ArrayAdapter<String>(this, R.layout.list_item3, items);
        autoCompleteitems.setAdapter(adapterItems3);
        autoCompleteitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });
        btn = findViewById(R.id.button2);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        ref = FirebaseDatabase.getInstance().getReference();

        ref2 = FirebaseDatabase.getInstance().getReference().child("sales");
        spinnerdatallist = new ArrayList<>();
        retrieve();
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item3, spinnerdatallist);
        autoCompleteprod.setAdapter(adapterItems);
        autoCompleteprod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(update_salesy.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        edit.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void save() {
        Map<String, Object> map = new HashMap<>();
        map.put("nameprd", autoCompleteprod.getText().toString());
        map.put("namevend", autoCompletecust.getText().toString());
        map.put("quantity", edit3.getText().toString());
        map.put("Date", edit.getText().toString());
        map.put("price", edit2.getText().toString());
        map.put("Type", autoCompleteitems.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("sales").child("-MumWZQ-l1OFcAeTUEw2").updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(update_salesy.this, "Data uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(update_salesy.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        /*FirebaseDatabase.getInstance().getReference().child("purshasyyy").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(update_salesy.this, "Data uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(update_salesy.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });*/

    }
    private void retrieve2() {

        ref3.child("custumor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerdatallist2.clear();
                for (DataSnapshot item2 : dataSnapshot.getChildren()) {
                    spinnerdatallist2.add(0, "choose match");
                    spinnerdatallist2.add(item2.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

     private void retrieve() {
        ref.child("Product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerdatallist.clear();
                for (DataSnapshot item2 : dataSnapshot.getChildren()) {
                    spinnerdatallist.add(0, "choose match");
                    spinnerdatallist.add(item2.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }}
