package iesm.pmdm.estados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private final String tag = "PMDM";
    private TextToSpeech sintetizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear objeto sintetizador
        sintetizador = new TextToSpeech(this, this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "Se ejecuta la app");
        showNoti("Hola caracola");
        speak("Bienvenido");

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(tag, "Se ejecuta onPostResume()");
        showNoti("Esta es una app");
        speak("Esta es una app");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "La app se pausa");
        showNoti("App pausada");
        speak("App pausada");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "La app se para");
        showNoti("App parada");
        speak("App parada");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag, "La app se reinicia");
        showNoti("Reiniciado!");
        speak("Reiniciando");
    }

    @Override
    protected void onDestroy() {

        Log.v(tag, "La app se destruye");
        showNoti("App cerrada");
        speak("App finalizada");
        //liberar sintetizador para liberar memoria
        sintetizador.shutdown();

        super.onDestroy();
    }

    //Metodos

    // esto muestra notificaciones por pantalla
    private void showNoti(String a){
        //1. Crear notificacion toast
        Toast msg = Toast.makeText(this, a , Toast.LENGTH_LONG);
        //2.Mostrar notificacion
        msg.show();
    }

    //Metodo para que hable lo que le mandemos
    private void speak (String a){
        //texto, modo de cola,
        sintetizador.speak(a, TextToSpeech.QUEUE_ADD, null);
    }

    //metodo configuracion del sintetizador
    @Override
    public void onInit(int i) {
        //indicar leguaje, español no está asi que con default coge el del sistema
        sintetizador.setLanguage(Locale.getDefault());
        // Entonacion de voz
        sintetizador.setPitch(1.0f);
        //Velocidad de habla
        sintetizador.setSpeechRate(1.0f);
    }
}