package iesmm.pmdm.pmdm_t4_simonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttons[]; // Botones
    private int colors[]; // Colores asociados
    //private TextView tv = findViewById(R.id.countScore);
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definición estática de botones y colores
        buttons = new Button[4];
        buttons[0] = this.findViewById(R.id.b1);
        buttons[1] = this.findViewById(R.id.b2);
        buttons[2] = this.findViewById(R.id.b3);
        buttons[3] = this.findViewById(R.id.b4);

        // Colores iniciales
        /*
        buttons[0].setBackgroundColor(Color.GREEN);
        buttons[1].setBackgroundColor(Color.RED);
        buttons[2].setBackgroundColor(Color.YELLOW);
        buttons[3].setBackgroundColor(Color.BLUE);
        */

        colors = new int[4];
        colors[0] = Color.GREEN;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;

        // Inicio de tarea asíncrona
        // 1. Instanciación de componentes visuales para su control
        Button go = (Button) this.findViewById(R.id.bCentral);

        // 2. Vinculamos el control del escuchador de eventos con el componente
        go.setOnClickListener(this);

        //Actualizar score con cada operacion de secuencia
        //Al pulsar el boton go comienza una nueva secuencia de colores
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bCentral:
                new TareaColores().execute();
                break;
        }
    }

    private class TareaColores extends AsyncTask<Void, Integer, Void> {
        private final int DELAY = 1000;
        @Override
        protected void onPreExecute() {
        Toast.makeText(getApplicationContext(),"Comenzando nueva secuencia",Toast.LENGTH_LONG).show();
            for ( Button b:buttons) {
                b.setBackgroundColor(Color.BLACK);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 0; i < 10; i++) {
                try {
                    int random = (int) (Math.random() * (4 - 0) + 0);
                    publishProgress(random);//Valor incremental, se le pasa a progressupdate en array
                    Thread.sleep(DELAY);
                   publishProgress(4,random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;//llama a onpostexecute

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            TextView tv = findViewById(R.id.countScore);
            score=score+1;
            tv.setText(String.valueOf(score));
            if(values[0]<4){
                buttons[values[0]].setBackgroundColor(colors[values[0]]);
                Toast.makeText(getApplicationContext(),"Boton "+values[0],Toast.LENGTH_LONG).show();

            }
            else{
                buttons[values[1]].setBackgroundColor(Color.BLACK);
            }
        }

        @Override
        protected void onPostExecute(Void param) {
            Toast.makeText(getApplicationContext(),"Tu turno",Toast.LENGTH_LONG).show();
        }
    }
}