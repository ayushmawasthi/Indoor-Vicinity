package com.tcs.indoorvicinity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class UserHome extends AppCompatActivity {
    LinearLayout l1,l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        l1=findViewById(R.id.llproduct);
        l2=findViewById(R.id.llshop);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(UserHome.this,com.tcs.indoorvicinity.Customer_View_Item.class);
                startActivity(i);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(UserHome.this,com.tcs.indoorvicinity.Customer_View_Item.class);
                startActivity(i);

            }
        });

    }


}
