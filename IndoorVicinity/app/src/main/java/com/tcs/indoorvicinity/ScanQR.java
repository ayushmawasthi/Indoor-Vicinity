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


    Button b1,b2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r);
        b1=findViewById(R.id.btnqr1);
        b2=findViewById(R.id.btnqr2);
      //  codeScannerView=findViewById(R.id.scanner);
      //  codeScanner=new CodeScanner(this,codeScannerView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opennew("qr1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opennew("qr1");
            }
        });

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


        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
*/

    }



    private void opennew(String qr) {
        Intent i=new Intent(ScanQR.this,com.tcs.indoorvicinity.Tracking.class);
        i.putExtra("shop","inroute1");
        i.putExtra("start",qr);
        startActivity(i);
    }
    //    @Override
//    protected void onResume() {
//        super.onResume();
//        codeScanner.startPreview();
//    }
    }
