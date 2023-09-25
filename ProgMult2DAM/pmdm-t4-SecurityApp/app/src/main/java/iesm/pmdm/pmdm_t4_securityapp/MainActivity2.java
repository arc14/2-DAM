package iesm.pmdm.pmdm_t4_securityapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener {

    private TextView txtPuntuacion;
    private ImageView candado;
    private ImageView llave;
    private RelativeLayout layout;

    private int lyWidth;
    private Float posX;

    private SensorManager sensorManager;
    private Sensor sensor;

    private ObjectAnimator obAnim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame);

        txtPuntuacion = findViewById(R.id.txtPuntuacion);
        candado = findViewById(R.id.candado);
        layout = findViewById(R.id.relativeLayout);

        llave = findViewById(R.id.llave);

        posX = candado.getX();

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        } else {
            Toast.makeText(this, "Error no hay sensor!", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        lyWidth = layout.getWidth();

        movCandado(lyWidth, event);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void movCandado(int lyWidth, SensorEvent event) {
        if (event.sensor.getType() == sensor.TYPE_ORIENTATION) {
            int giroMovil = (int) event.values[2];

            //movimiento
            if (giroMovil < -5 && posX < lyWidth - 180) {

                posX = posX + 5;
                obAnim = ObjectAnimator.ofFloat(candado, "X", posX)
                        .setDuration(1);
                obAnim.start();

            } else if (giroMovil > 5 && posX >= 0) {
                posX = posX - 5;
                obAnim = ObjectAnimator.ofFloat(candado, "X", posX)
                        .setDuration(1);
                obAnim.start();
            }
        }
    }


    //la idea era que llovieran llaves y recogerlas con el candado
    private void llaves(){

        int aux = 0;

            llave = findViewById(R.id.llave);

            float numero = (float)(Math.random()*(850-0+1)+0);

            llave.setX(numero);

            obAnim = ObjectAnimator.ofFloat(candado, "Y", 750)
                    .setDuration(4000);



            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // yourMethod();
                }
            }, 1200);



    }
}
