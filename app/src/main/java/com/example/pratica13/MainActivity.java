package com.example.pratica13;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    EditText coordinateX;
    EditText coordinateY;
    EditText coordinateZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        coordinateX = (EditText) findViewById(R.id.et1);
        coordinateY = (EditText) findViewById(R.id.et2);
        coordinateZ = (EditText) findViewById(R.id.et3);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];
            if (abs(sensorX) > 10.0
                || abs(sensorY) > 10.0
                || abs(sensorZ) > 10.0) {
                Intent i = new Intent(this, MainActivity2.class);
                startActivity(i);
            }
            else {
                coordinateX.setText(String.valueOf(sensorX));
                coordinateY.setText(String.valueOf(sensorY));
                coordinateZ.setText(String.valueOf(sensorZ));
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
}