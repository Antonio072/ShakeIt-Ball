package com.example.anton_io.shakeit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class PantallaPrincipal extends AppCompatActivity {


    // Para detectar el shakeo
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensorDetect mShakeDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new SensorDetect();
        mShakeDetector.setOnShakeListener(new SensorDetect.OnShakeListener() {

            @Override
            public void onShake(int count) {
                Toast.makeText(PantallaPrincipal.this, "Se movio alv", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        Toast.makeText(PantallaPrincipal.this, "Se movio alv", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();

    }
}
