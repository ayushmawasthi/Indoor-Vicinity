package com.tcs.indoorvicinity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScanQR extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r);
        codeScannerView=findViewById(R.id.scanner);
        codeScanner=new CodeScanner(this,codeScannerView);

       /* codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ScanQR.this, ""+result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        */
        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        opennew();
        return super.onTouchEvent(event);

    }

    private void opennew() {
        Intent i=new Intent(ScanQR.this,com.tcs.indoorvicinity.Tracking.class);
        i.putExtra("shop","inroute1");
        i.putExtra("start","qr1");
        startActivity(i);
    }
    //    @Override
//    protected void onResume() {
//        super.onResume();
//        codeScanner.startPreview();
//    }
    }
