package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Orderscust extends AppCompatActivity {
    orderadapter adapter;
    private ListView recy;
    DatabaseReference database;
    ArrayList<sales> list;
    Button btnupdate;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderscust);

        recy = findViewById(R.id.listy);
list=new ArrayList<>();
database = FirebaseDatabase.getInstance().getReference("sales");
database.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        list.clear();
        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
            sales s = dataSnapshot.getValue(sales.class);
            list.add(s);
        }
        adapter=new orderadapter(Orderscust.this,list);
        recy.setAdapter(adapter);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

recy.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        sales s = list.get(position);
        showupdate(s.getDate(),s.getQuantity(),s.getPrice());


        return false;
    }
});
    }
    private void showupdate( String Date, String Quantity,String Price){
        AlertDialog.Builder mDialog =new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_sales,null);
        mDialog.setView(mDialogView);
        Spinner prod = findViewById(R.id.podnm);
        Spinner cust = findViewById(R.id.cusnm);
        EditText date = findViewById(R.id.Date2);
        EditText quantity = findViewById(R.id.quantity2);
        EditText price = findViewById(R.id.price2);
        Spinner payment = findViewById(R.id.payment);
        btnupdate=findViewById(R.id.btnupdate);
        mDialog.setTitle("Updating"+Date+"record");
        mDialog.setTitle("Updating"+Quantity+"record");
        mDialog.setTitle("Updating"+Price+"record");
mDialog.show();
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("5+");

String name= prod.getSelectedItem().toString();
String name2= cust.getSelectedItem().toString();
String name3= date.getText().toString();
String name4= quantity.getText().toString();
String name5= price.getText().toString();
String name6= payment.getSelectedItem().toString();
update(name,name2,name3,name4,name5,name6);
                Toast.makeText(Orderscust.this,"updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void update(String name,String cust , String date , String quantity , String price, String type){

        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference("sales");
        sales s = new sales(name, quantity,  date,cust, price, type);
        DBref.setValue(s);








    }}