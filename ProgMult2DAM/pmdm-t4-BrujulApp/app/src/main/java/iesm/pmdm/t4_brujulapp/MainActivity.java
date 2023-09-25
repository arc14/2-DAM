package iesm.pmdm.t4_brujulapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private TextView gradosText;
    private ImageView brujula;
    private SensorManager sensorManager;
    private Sensor sensor;
    private ObjectAnimator animRotacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gradosText = this.findViewById(R.id.textoRotacion);
        brujula = this.findViewById(R.id.imageView);

        //Establecer el sensor
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        else {
            Toast.makeText(this, "Error no existe sensor!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //habilitar el sensor
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == sensor.TYPE_ORIENTATION) {
            int valor = (int) quitarDecimales(sensorEvent.values[0]);

            //cambia el texto respecto al valor dado y el punto cardinal correspondiente
            if (valor < 22 || valor > 338)
                gradosText.setText(valor + " N");
            else if (valor >= 22 && valor <= 67)
                gradosText.setText(valor + " NE");
            else if (valor >= 68 && valor <= 112)
                gradosText.setText(valor + " E");
            else if (valor >= 113 && valor <= 157)
                gradosText.setText(valor + " SE");
            else if (valor >= 158 && valor <= 202)
                gradosText.setText(valor + " S");
            else if (valor >= 203 && valor <= 247)
                gradosText.setText(valor + " SW");
            else if (valor >= 248 && valor <= 292)
                gradosText.setText(valor + " W");
            else if (valor >= 248 && valor <= 338)
                gradosText.setText(valor + " NW");


            //Objeto animacion
            animRotacion = ObjectAnimator.ofFloat(brujula, "rotation", (-20f-valor))
                    .setDuration(1);
            animRotacion.start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //Elimina los decimales del valor dado

    private float quitarDecimales(float num){
        num = Float.parseFloat(new DecimalFormat("#").format(num));
        return num;
    }

}