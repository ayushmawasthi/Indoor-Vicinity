package com.tcs.indoorvicinity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Tracking extends AppCompatActivity implements SensorEventListener {
    Sensor steps;
    private SensorManager sensorManager;
    private static final String TAG = "Tracking";
    TextView t1;
    int stepDetect=0;
    String accx;
    String start="s1",shop="e1";
    HashMap <String,String> hs;


    TextView t2;
    ImageView img;
    Sensor accelerometer,magnetometer;
    private float[] lastacc=new float[3];
    private float[] lastmag=new float[3];
    private float[] rotationmatrix = new float[9];
    private float[] orientation = new float[3];
    boolean islastacccompied=false;
    boolean islastmagcopied=false;

    long lastUpdatedTime=0;
    float currentDegree=0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        t1=findViewById(R.id.tvsteps);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Intent i=new Intent();
        start=i.getStringExtra("start");
        shop=i.getStringExtra("shop");
        hs=new HashMap<>();
        hs.put(start+shop,"130_13 50_25");

        t2=findViewById(R.id.direc);
        img=findViewById(R.id.imgv);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(accelerometer !=null)
        {
            sensorManager.registerListener(Tracking.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);


        }
        else
        {
            Toast.makeText(this, "Accelerometer Not supported", Toast.LENGTH_SHORT).show();

        }
        if(magnetometer !=null)
        {
            sensorManager.registerListener(Tracking.this,magnetometer,SensorManager.SENSOR_DELAY_NORMAL);


        }
        else
        {
            Toast.makeText(this, "Magnometer Not supported", Toast.LENGTH_SHORT).show();

        }



        steps=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if(steps !=null)
        {
            sensorManager.registerListener(Tracking.this,steps,SensorManager.SENSOR_DELAY_FASTEST);
            Log.d(TAG, "onCreate: Step Detector Sensor Registered");

        }
        else
        {
            Toast.makeText(this, "Step Detector sensor Not supported", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor=event.sensor;
        if(sensor.getType()==Sensor.TYPE_STEP_DETECTOR)
        {
            accx=""+event.values[0];

            stepDetect= (int) (stepDetect+event.values[0]);
            t1.setText(String.valueOf(stepDetect));
        }
        if(event.sensor==accelerometer)
        {
            System.arraycopy(event.values,0,lastacc,0,event.values.length);
            islastacccompied=true;
        }
        else if(event.sensor==magnetometer)
        {
            System.arraycopy(event.values,0,lastmag,0,event.values.length);
            islastmagcopied=true;
        }
        if(islastacccompied && islastmagcopied && System.currentTimeMillis()-lastUpdatedTime>250)
        {
            SensorManager.getRotationMatrix(rotationmatrix,null,lastacc,lastmag);
            SensorManager.getOrientation(rotationmatrix,orientation);


            float azimuthIRadians=orientation[0];
            float azimuthInDegree= (float) Math.toDegrees(azimuthIRadians);


            RotateAnimation rotateAnimation=new RotateAnimation(currentDegree,-azimuthInDegree, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);
            img.startAnimation(rotateAnimation);
            currentDegree=-azimuthInDegree;
            lastUpdatedTime=System.currentTimeMillis();
            t2.setText(azimuthInDegree+"deg");

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}