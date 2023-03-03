package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register2 extends AppCompatActivity {
    private EditText tname;
    private EditText t1,t2,t3;
    public Button btn1;


    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        changeStatusBarColor();
        tname = findViewById(R.id.tname);
        t1 = findViewById(R.id.temail);
        t2= findViewById(R.id.tpd);
        t3 = findViewById(R.id.tpd2);
        btn1 = findViewById(R.id.btn1);
        ref= FirebaseDatabase.getInstance().getReference().child("cust");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,Login2.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
    private void registerUser() {
        final String name = tname.getText().toString().trim();
        final String email = t1.getText().toString();
        String password = t2.getText().toString().trim();
        String cpassword = t3.getText().toString().trim();
        if (email.isEmpty()) {
            t1.setError("It's empty");
            t1.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            tname.setError("It's Empty");
            tname.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            t1.setError("Not a valid emailaddress");
            t1.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            t2.setError("Its empty");
            t2.requestFocus();
            return;
        }

        if (password.length() < 6) {
            t2.setError("Less length");
            t2.requestFocus();
            return;
        }
        if (!password.equals(cpassword)) {
            t3.setError("Password Do not Match");
            t3.requestFocus();
            return;
        }
       cust u2 = new cust(name,email,password,cpassword);
        ref.push().setValue(u2);
        Toast.makeText(Register2.this,"inserted text",Toast.LENGTH_LONG).show();
    }
}