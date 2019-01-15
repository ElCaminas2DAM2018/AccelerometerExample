package org.ieselcaminas.pmdm.accelerometerexample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;

    private SensorManager mSensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        textViewZ = (TextView) findViewById(R.id.textViewZ);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textViewX.setText(String.format("X: %.2f",event.values[0]));
        textViewY.setText(String.format("Y: %.2f",event.values[1]));
        textViewZ.setText(String.format("Z: %.2f",event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}