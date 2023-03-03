package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login2 extends AppCompatActivity {
    private static final String TAG ="FBauth" ;
    ImageView fblogo;
    // CallbackManager callbackManager;
    //LoginButton  loginButton ;
    private static final String EMAIL="email";
    EditText t1, t2;
    Button btn2;
    FirebaseAuth Auth;
    TextView forgot_passd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

       /* callbackManager =CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.fb_btn);
        loginButton.setReadPermissions("email");*/
        // If using in a fragment
        // loginButton.setFragment(this);

        // Callback registration
      /*  loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //Log.d(TAG,"onsuccess"+loginResult);
                Intent intent=new Intent(LoginActivity.this,dashboardActivity.class);
                startActivity(intent)
                ;
               // handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
               // Log.d(TAG,"onError"+exception.getMessage());

            }
        });*/
        Auth=FirebaseAuth.getInstance();
        t1=findViewById(R.id.temail);
        t2=findViewById(R.id.tpd);
        btn2 =findViewById(R.id.btn2);
        forgot_passd=findViewById(R.id.forgotpassword);

        forgot_passd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog= new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset password");
                passwordResetDialog.setMessage("Enter your email to receive Reset Link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail =resetMail.getText().toString();
                        Auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Login2.this,"Reset link sent to your Email.",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login2.this,"Error!Reset Link is not Sent"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
                    /*.addOnSuccessListener(new OnFailureListener() {
                            @Override
                            puOblic void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error!Reset Link in not sent"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        })*/
                passwordResetDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                passwordResetDialog.create().show();
            }
        });




        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = t1.getText().toString();
                String password = t2.getText().toString().trim();
                if (email.isEmpty()) {
                    t1.setError("It's empty");
                    t1.requestFocus();
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


                Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!(task.isSuccessful())) {
                            startActivity(new Intent(Login2.this,dashboardActivity.class));
                            Toast.makeText(Login2.this, "welcome", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Login2.this, "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });}

    /*private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                          //  Log.d(TAG, "signInWithCredential:success");
                         // FirebaseUser user = Auth.getCurrentUser();
                         openProfile();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
*/
    private void openProfile() {

        Intent intent=new Intent(Login2.this,dashboardActivity.class);
        startActivity(intent)
        ;    }

    //@Override
    /*protected void onStart() {
        super.onStart();
        if(Auth.getCurrentUser()!=null){
openProfile();
        }
    }*/

    public void onLoginClick(View View){
        startActivity(new Intent(this,Register2.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);


    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }*/
    }
