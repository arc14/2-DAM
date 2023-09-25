package iesm.pmdm.pmdm_t2_xilofono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

/**
 * autor: Alejandro Romero
 */

public class MainActivity extends AppCompatActivity {

    private TextToSpeech sint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sint = new TextToSpeech(this, this::onInit);

    }

    /**
     * Metodo que dependiendo del boton pulsado llama a emitirSonido() con una variable distinta
     * @param v
     */

    public void pulsaBoton(View v){
        switch (v.getId()) {
            case R.id.button:
                emitirSonido(0.2f, "DO");
                break;
            case R.id.button2:
                emitirSonido(0.4f,"RE");
                break;
            case R.id.button3:
                emitirSonido(0.6f, "MI");
                break;
            case R.id.button4:
                emitirSonido(0.8f, "FA");
                break;
            case R.id.button5:
                emitirSonido(1f, "SOL");
                break;
            case R.id.button6:
                emitirSonido(1.2f, "LA");
                break;
            case R.id.button7:
                emitirSonido(1.4f, "SI");
                break;
            case R.id.button8:
                emitirSonido(1.6f, "DO");
                break;
        }
    }

    /**
     * Metodo que emite un sonido llamando a la funcion speak()
     * Tambien cambia la caracteristica Pitch de valor con el valor de entrada
     * @param pitchValue
     */

    private void emitirSonido(Float pitchValue, String txt){
        sint.setPitch(pitchValue);
        sint.speak(txt, TextToSpeech.QUEUE_FLUSH,null);
    }

    /**
     * Metodo donde definir valores para TextToSpeech
     * @param i
     */

    private void onInit(int i) {
        sint.setLanguage(Locale.getDefault());

        // Por algún motivo si no se aplica un valor al setSpeechRate() no funcion
        // el metodo setPitch() declarado en emitirSonido(), suenan todos los botones
        // igual.
        sint.setSpeechRate(8f);
    }
}