package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrscann extends AppCompatActivity implements ZXingScannerView.ResultHandler  {

    int MY_PERMISSIONS_REQUEST_CAMERA=0;

    ZXingScannerView scannerView;
    private CodeScanner mCodeScanner;
    CodeScannerView scanView;
    TextView resultData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscann);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }
    @Override
    protected void onPause() {
        //mCodeScanner.releaseResources();
        super.onPause();
        scannerView.stopCamera();
    }
    @Override
    public void handleResult(Result result) {

       add_Product.resulttextview.setText(result.getText());

        onBackPressed();
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}