package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSManager;
    Sensor mSensor;

    TextView txtX,txtY,txtZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mSManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txtX = findViewById(R.id.textX);
        txtY = findViewById(R.id.textY);
        txtZ = findViewById(R.id.textZ);
        String x,y,z;
        x = String.valueOf(event.values[0]);
        y = String.valueOf(event.values[1]);
        z = String.valueOf(event.values[2]);

        txtX.setText(x);
        txtY.setText(y);
        txtZ.setText(z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}