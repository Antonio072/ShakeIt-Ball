package com.example.anton_io.shakeit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import android.widget.*;

public class PantallaPrincipal extends AppCompatActivity {
    //TODO cambiar icono
    int i=0;
    // Para detectar el shakeo
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensorDetect mShakeDetector;

    String[] respuestas=new String[]{"Si",
                                    "No",
                                    "Tal vez",
                                    "Por supuesto"};
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
                TextView txt=findViewById(R.id.Txt_Mensaje);
                int aleatorio=nAleatorio();
                try {
                    if(aleatorio==0 ||aleatorio==4 || aleatorio==8)
                        txt.setText(respuestas[0]);
                    else
                    if(aleatorio==1 || aleatorio==5 || aleatorio==9)
                        txt.setText(respuestas[1]);
                    else
                    if(aleatorio==2 || aleatorio==6)
                        txt.setText(respuestas[2]);
                    else
                    if(aleatorio==3 || aleatorio==7 )
                        txt.setText(respuestas[3]);


                } catch (Exception e) {
                }
            }
        });
    }

    int nAleatorio(){
        String respAleatoria;
        respAleatoria=""+System.nanoTime();
       String cAt=""+respAleatoria.charAt(respAleatoria.length()-2);

       return Integer.parseInt(cAt);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);

    }
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();


    }
}
