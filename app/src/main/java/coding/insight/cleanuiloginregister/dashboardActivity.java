package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class dashboardActivity extends AppCompatActivity {
float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 >x2){
                Intent i = new Intent(dashboardActivity.this, Dashboard2.class);
                startActivity(i);
            }
            break;
        }
        return false;
    }


    public void addproduct(View view) {
        Intent i = new Intent(dashboardActivity.this, add_Product.class);
        startActivity(i);
    }

    public void addvend(View view) {
        Intent i = new Intent(dashboardActivity.this, addVendor.class);
        startActivity(i);
    }

    public void addcust(View view) {
        Intent i = new Intent(dashboardActivity.this, add_Customer.class);
        startActivity(i);
    }

    public void detailprod(View view) {
        Intent i = new Intent(dashboardActivity.this, ProductDetails.class);
        startActivity(i);
    }

    public void detailvend(View view) {
        Intent i = new Intent(dashboardActivity.this, Vendordetails.class);
        startActivity(i);
    }

    public void detailcust(View view) {
        Intent i = new Intent(dashboardActivity.this, custumor_details.class);
        startActivity(i);
    }
}