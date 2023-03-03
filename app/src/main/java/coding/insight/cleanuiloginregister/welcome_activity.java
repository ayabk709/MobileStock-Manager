package coding.insight.cleanuiloginregister;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class welcome_activity extends AppCompatActivity {
Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
/*img=(ImageView)findViewById(R.id.imageView2);
Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
   img.setAnimation(animation);*/
spn=findViewById(R.id.spino);
       ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).equals("User")) {
           Intent intent = new Intent(welcome_activity.this, Login2.class);
            startActivity(intent);

        } else if (parent.getItemAtPosition(position).equals("Admin")) {

            Intent intent = new Intent(welcome_activity.this,LoginActivity.class);
            startActivity(intent);

        }
        else if(parent.getItemAtPosition(position).equals("Choisissez l'une des elements")) {
            Toast.makeText(welcome_activity.this, "non", Toast.LENGTH_SHORT).show();
    }}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
    }

    /*public void fef(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }*/
}