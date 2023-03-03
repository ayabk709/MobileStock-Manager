package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_Customer extends AppCompatActivity {
    private EditText nameV;
    private EditText txt1,txt2,txt3,txt4;
    public Button btn1;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        nameV= findViewById(R.id.namecust);
        txt1= findViewById(R.id.companycust);
        txt2= findViewById(R.id.adrrscust);
        txt3 = findViewById(R.id.mobilcust);
        txt4 = findViewById(R.id.emailcust);

        btn1 = findViewById(R.id.btn55);
        ref= FirebaseDatabase.getInstance().getReference().child("custumor");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerVendor();
            }
        });
    }

    private void registerVendor() {
        final String custname = nameV.getText().toString().trim();
        final String company = txt1.getText().toString();
        String adress= txt2.getText().toString().trim();
        String mobil= txt3.getText().toString().trim();
        String email = txt4.getText().toString().trim();

        if (email.isEmpty()) {
            txt4.setError("It's empty");
            txt4.requestFocus();
            return;
        }
        if (custname.isEmpty()) {
            nameV.setError("It's Empty");
            nameV.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt4.setError("Not a valid emailaddress");
            txt4.requestFocus();
            return;
        }

        if (company.isEmpty()) {
            txt1.setError("Its empty");
            txt1.requestFocus();
            return;
        }
        if (adress.isEmpty()) {
            txt2.setError("Its empty");
            txt2.requestFocus();
            return;
        }
        if (mobil.isEmpty()) {
            txt3.setError("Its empty");
            txt3.requestFocus();
            return;
        }




        vendor u2 = new vendor(custname,adress, mobil, email, company);
        ref.push().setValue(u2);
        Toast.makeText(add_Customer.this,"inserted text",Toast.LENGTH_SHORT).show();

    }
}