package coding.insight.cleanuiloginregister;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private EditText tname;
    private EditText t1,t2,t3;
    public Button btn1;


    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         changeStatusBarColor();
        tname = findViewById(R.id.tname);
       t1 = findViewById(R.id.temail);
        t2= findViewById(R.id.tpd);
       t3 = findViewById(R.id.tpd2);
        btn1 = findViewById(R.id.btn1);
        ref=FirebaseDatabase.getInstance().getReference().child("user");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registerUser();

            }
        });


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
        user u2 = new user(name,email,password,cpassword);
        ref.push().setValue(u2);
        Toast.makeText(RegisterActivity.this,"inserted text",Toast.LENGTH_LONG).show();
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
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }


   /* private void registerUser() {
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
        user u2 = new user(name,email,password,cpassword);
        ref.push().setValue(u2);


    }*/
}