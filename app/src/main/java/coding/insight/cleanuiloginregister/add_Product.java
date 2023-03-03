package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;
import java.text.BreakIterator;

public class add_Product extends AppCompatActivity {

    Button btnadd;
    Button btnscan;
    EditText nom;
    EditText couleur;
    EditText catego;
    EditText prix;
    EditText quant;
    EditText number;


    public static EditText resulttextview;
    DatabaseReference ref;
    ImageView image;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_product);
      //  qrtext=(TextView)findViewById(R.id.qrtext);
    nom= (EditText) findViewById(R.id.name2);
    couleur= (EditText) findViewById(R.id.name3);
    catego= (EditText) findViewById(R.id.name4);
    prix= (EditText) findViewById(R.id.name5);
    quant= (EditText)findViewById(R.id.name6);
    number= (EditText) findViewById(R.id.name7);
    btnadd =(Button)findViewById(R.id.button4);
    btnscan =(Button)findViewById(R.id.button2);
        resulttextview = findViewById(R.id.name7);
        image=findViewById(R.id.profile);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        ref= FirebaseDatabase.getInstance().getReference().child("Product");





        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), qrscann.class));
            }
        });



       image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(intent,3);
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  addDataprd();
                // upload();
if(imageUri !=null){
    upload(imageUri);
}else{
    Toast.makeText(add_Product.this, "select an image", Toast.LENGTH_SHORT).show();
}

            }
        });
    }

    private void upload(Uri imageUri) {
        final  StorageReference fileref =storageReference.child(System.currentTimeMillis()+"."+getFile(imageUri));
        fileref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        final String name = nom.getText().toString().trim();
                        final String color = couleur.getText().toString();
                        String price = prix.getText().toString().trim();
                        String quantity = quant.getText().toString().trim();
                        String number_code = number.getText().toString().trim();
                        String category = catego.getText().toString().trim();
                        String itembarcode = number.getText().toString();
                        Product P = new Product(name, color, price, category, quantity, number_code,uri.toString());
                        if (itembarcode.isEmpty()) {
                            number.setError("It's Empty");
                            number.requestFocus();
                            return;
                        }
                        if (name.isEmpty()) {
                            nom.setError("It's empty");
                            nom.requestFocus();
                            return;
                        }
                        if (color.isEmpty()) {
                            couleur.setError("It's Empty");
                            couleur.requestFocus();
                            return;
                        }

                        if (price.isEmpty()) {
                            prix.setError("Its empty");
                            prix.requestFocus();
                            return;
                        }
                        if (category.isEmpty()) {
                            catego.setError("Its empty");
                            catego.requestFocus();
                            return;
                        }
                        if (quantity.isEmpty()) {
                            quant.setError("Its empty");
                            quant.requestFocus();
                            return;
                        }
                        if (number_code.isEmpty()) {
                            number.setError("Its empty");
                            number.requestFocus();
                            return;
                        }

                        String p1=ref.push().getKey();
                       ref.child(p1).setValue(P);
                        Toast.makeText(add_Product.this,"inserted",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(add_Product.this,"failed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getFile(Uri imageUri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(imageUri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode == RESULT_OK && data!=null){
            imageUri =data.getData();
            image.setImageURI(imageUri);

        }
    }

    /*
    private void upload() {
        if (imageUri != null) {

            StorageReference reference = storage.getReference().child("images/*" + UUID.randomUUID().toString());
            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(add_Product.this, "inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(add_Product.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            image.setImageURI( imageUri);
        }
    }

    private void addDataprd() {
        final String name = nom.getText().toString().trim();
        final String color = couleur.getText().toString();
        String price = prix.getText().toString().trim();
        String quantity = quant.getText().toString().trim();
        String number_code = number.getText().toString().trim();
        String category = catego.getText().toString().trim();
        String itembarcode = number.getText().toString();
        if (itembarcode.isEmpty()) {
            number.setError("It's Empty");
            number.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            nom.setError("It's empty");
            nom.requestFocus();
            return;
        }
        if (color.isEmpty()) {
            couleur.setError("It's Empty");
            couleur.requestFocus();
            return;
        }

        if (price.isEmpty()) {
            prix.setError("Its empty");
            prix.requestFocus();
            return;
        }
        if (category.isEmpty()) {
            catego.setError("Its empty");
            catego.requestFocus();
            return;
        }
        if (quantity.isEmpty()) {
            quant.setError("Its empty");
            quant.requestFocus();
            return;
        }
        if (number_code.isEmpty()) {
            number.setError("Its empty");
            number.requestFocus();
            return;
        }


        Product P = new Product(name, color, price, category, quantity, number_code);
        ref.push().setValue(P);
        Toast.makeText(add_Product.this,"inserted text",Toast.LENGTH_LONG);
    }

*/

    }