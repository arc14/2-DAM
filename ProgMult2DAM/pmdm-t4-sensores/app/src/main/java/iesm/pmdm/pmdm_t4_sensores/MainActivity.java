package iesm.pmdm.pmdm_t4_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final String LOGTAG = "pmdm";
    private View layout;
    private TextView value;
    private SensorManager sensorManager; //Gestor de sensores
    private Sensor sensor; //Sensor de proximidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout = this.findViewById(R.id.layout);
        value = this.findViewById(R.id.txtView);

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //Comprueba la existencia del sensor

        if (sensor != null){


        } else {
            Toast.makeText(this, "Sensor no disponible", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Log.d(LOGTAG, String.valueOf("Medida obtenida: " + sensorEvent.values[0]));

            float proximity = sensorEvent.values[0];

            if (proximity >= 0 && proximity <= 0.4){
                value.setText("Alejate Bribón");
                layout.setBackgroundColor(Color.RED);
            } else {
                value.setText("Echa pallá");
                layout.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}