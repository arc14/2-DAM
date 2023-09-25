package iesmm.pmdm.pmdm_t4_05;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progress;

    /*
    Ejercicio de práctica
    N1 - Se incrementa en 10 cada x segundos
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Instanciación de componentes visuales para su control
        Button start = (Button) this.findViewById(R.id.button);

        // 2. Vinculamos el control del escuchador de eventos con el componente
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:

                new TareaProgressBar().execute();


                break;
        }
    }

    /* Muestra un cuadro de diálogo con barra de progreso */
    private void showProgress() {
        // OTRA OPCIÓN: Método estático de inicio: ProgressDialog.show(this, titulo, mensaje);
        progress = new ProgressDialog(this);
        progress.setTitle("Descarga simulada"); // Titulo
        progress.setMessage("Cargando"); // Contenido

        // Propiedades de configuración
        progress.setMax(100); // Valor máximo de la barra de progreso
        progress.setCancelable(true); // Permitir que se cancele por el usuario

        // Tipo de barra de progreso: ProgressDialog.STYLE_SPINNER / STYLE_HORIZONTAL
        // progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // Mostrar cuadro de diálogo
        progress.show();

        // Incrementar valor del progreso en n. unidades
        // progress.incrementProgressBy(1); // Aumenta en 1 unidad

        // Obtener valor de la barra de progreso
        //progress.getProgress();

        // Obtener valor máximo de la barra de progreso
        // progress.getMax();

        // Finalización del cuadro de diálogo
        // progress.cancel();
    }

    private class TareaProgressBar extends AsyncTask<Void, Integer, Void> {

        /*
        onPreExecute:
        Método llamado antes de empezar el procesamiento en segundo plano de doInBackground.
        */
        @Override
        protected void onPreExecute() {
            showProgress();
        }

        /*
        doInBackground:
        este método se ejecuta en un thread separado, lo que le permite ejecutar un tratamiento pesado en una tarea de segundo plano.
        Recibe como parámetros los declarados al llamar al método execute(Params).
        */
        @Override
        protected Void doInBackground(Void... voids) {

            while (progress.getProgress() < progress.getMax()){
                //Estoy envia al onProgressUpdate que es donde se debe ejecutar
                publishProgress(10);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null; //Cuando acaba este metodo, va al onPostExecute para acabar
        }

        /*
        onProgressUpdate:
        es llamado por publishProgress(), dentro de doInBackground(Params) (su uso es muy común para por ejemplo actualizar el porcentaje de un componente ProgressBar).
        */
        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.incrementProgressBy(values[0]);
        }

        /*
        Este método es llamado tras finalizar doInBackground(Params).
        Recibe como parámetro el resultado devuelto por doInBackground(Params).
        */
        @Override
        protected void onPostExecute(Void param) {
            progress.cancel();
        }
    }
}