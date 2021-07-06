package com.tcs.indoorvicinity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    TextView t1,t2,vendor_text,cust_text;
    Animation topanim,leftanim,rightanim;
    LinearLayout leftView,rightView;



    // slide the view from below itself to the current position
    public void slideLeft(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                -view.getWidth(),                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(2000);
        animate.setFillAfter(true);
        view.startAnimation(animate);





    }

    public void slideRight(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                view.getWidth(),                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(2000);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }


    public void centralise(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(2000);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    protected void onStart() {
        super.onStart();
        centralise(leftView);
        centralise(rightView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1=findViewById(R.id.title1);
        t2=findViewById(R.id.title2);
        vendor_text=findViewById(R.id.vendor_text);
        cust_text=findViewById(R.id.cust_text);


        leftView = findViewById(R.id.leftView);
        rightView = findViewById(R.id.rightView);

        // initialize as invisible (could also do in xml)





        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        leftanim=AnimationUtils.loadAnimation(this,R.anim.left_animation);
        rightanim=AnimationUtils.loadAnimation(this,R.anim.right_animation);

        t1.setAnimation(topanim);
        t2.setAnimation(topanim);
//        vendor_text.setAnimation(leftanim);
//        cust_text.setAnimation(rightanim);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        leftanim=AnimationUtils.loadAnimation(this,R.anim.left_animation);
        vendor_text.setAnimation(leftanim);
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        System.out.println("Left to right");
                        leftView.setVisibility(View.INVISIBLE);
                        slideLeft(leftView);

//                        Intent i =new Intent(MainActivity.this,com.tcs.indoorvicinity.Login.class);
//                        startActivity(i);
//                        leftanim=AnimationUtils.loadAnimation(this,R.anim.left_animation);

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                // this code will be executed after 2 seconds
                                callVendorPage();
                            }
                        }, 2000);

                    }

                    // Right to left swipe action
                    else
                    {
                        System.out.println("Right to left");
                        rightView.setVisibility(View.INVISIBLE);
                        slideRight(rightView);

//                        rightanim=AnimationUtils.loadAnimation(this,R.anim.right_animation);
//
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                // this code will be executed after 2 seconds
                                callCustomerPage();
                            }
                        }, 2000);

                    }




                }
                else
                {
                    // consider as something else - a screen tap for example
                    System.out.println("Something else");
                    Toast.makeText(this, "Please swipe to right for vendor login", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    public void callVendorPage(){
        Intent i =new Intent(MainActivity.this,com.tcs.indoorvicinity.Login.class);
        startActivity(i);

    }

    public void callCustomerPage(){
        Intent i =new Intent(MainActivity.this,com.tcs.indoorvicinity.UserHome.class);
        startActivity(i);

    }


}

