package iesm.pmdm.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener{

    private JuegoTresEnRaya mJuego;

    private Button mBotonesTablero[];

    private TextView mInfoTexto;
    private TextView player;
    private TextView comp;
    private TextView games;

    private char mTurno = JuegoTresEnRaya.JUGADOR;

    private boolean gameOver = false;

    //Variables contador
    private int contWin;
    private int contLose;
    private int contGames;

    //Declarar objets para las imagenes
    private Drawable fichaJug;
    private Drawable fichaMaq;

    //Declarar objets para los sonidos
    private MediaPlayer mBackgroundMusicPlayer;
    private MediaPlayer mPlayerSound;
    private MediaPlayer mMaquinaSound;
    private MediaPlayer mWinSound;
    private MediaPlayer mLoseSound;

    //Delayer de tiempo
    private Handler handler = new Handler();

    private TextToSpeech sintetizador;

    private String ttsWin;
    private String ttsLose;
    private String ttsNew;
    private String ttsCont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepararTablero();

        sintetizador = new TextToSpeech(this, this);

        mJuego = new JuegoTresEnRaya();

        fichaJug = getResources().getDrawable(R.drawable.metal_slug);
        fichaMaq = getResources().getDrawable(R.drawable.metal_enemy);

        ttsWin = (String) getText(R.string.tts_win);
        ttsLose = (String) getText(R.string.tts_lose);
        ttsNew = (String) getText(R.string.tts_new_game);
        ttsCont = (String) getText(R.string.tts_next_game);

        resetConts();
        comenzarJuego();
    }

    protected void onResume() {
        super.onResume();

        //Sonidos
        mBackgroundMusicPlayer = MediaPlayer.create(this, R.raw.bck_sound);
        mBackgroundMusicPlayer.setLooping(true);
        mBackgroundMusicPlayer.start();

        mPlayerSound = MediaPlayer.create(this, R.raw.player_coloca);
        mMaquinaSound = MediaPlayer.create(this, R.raw.maquina_coloca);
        mMaquinaSound.setVolume(85, 85);
        mWinSound = MediaPlayer.create(this, R.raw.win_sound);
        mLoseSound = MediaPlayer.create(this, R.raw.lose_sound);
    }

    protected void onPause() {
        super.onPause();

        mBackgroundMusicPlayer.release();
        mPlayerSound.release();
        mMaquinaSound.release();
        mWinSound.release();
        mLoseSound.release();
    }

    protected void onDestroy() {
        mPlayerSound.release();
        mMaquinaSound.release();
        mLoseSound.release();
        mWinSound.release();
        sintetizador.shutdown();
        super.onDestroy();
    }


    private void comenzarJuego() {
        mJuego.limpiarTablero();

        enableBotones();
        controlarTurno();

        contGames++;
        setScore();

    }

    private void controlarTurno() {
        if (mTurno == JuegoTresEnRaya.JUGADOR) {
            mInfoTexto.setText(R.string.primero_jugador);
            enableBotones();
        } else if (mTurno == JuegoTresEnRaya.MAQUINA) {
            int casilla = mJuego.getMovimientoMaquina();

            disableBotones();

            mInfoTexto.setText(R.string.turno_maquina);


            //Espera 1.5 segundos a que pueda colocar la maquina
            waitMs(new Runnable() {
                public void run() {
                    colocarFichaEnTablero(JuegoTresEnRaya.MAQUINA, casilla);

                    if (!gameOver) {
                        mTurno = JuegoTresEnRaya.JUGADOR;
                        mInfoTexto.setText(R.string.turno_jugador);

                        enableBotones();
                    }
                }
            }, 1500);
        }
    }

    private void colocarFichaEnTablero(char jugador, int casilla) {

        mJuego.moverFicha(jugador, casilla);

        mBotonesTablero[casilla].setEnabled(false);


        if (jugador == JuegoTresEnRaya.JUGADOR) {
            mBotonesTablero[casilla].setBackground(fichaJug);
            mPlayerSound.start();
        } else {
            mBotonesTablero[casilla].setBackground(fichaMaq);
            mMaquinaSound.start();
        }

        int estadoJuego = comprobarEstadoJuego();

        if (estadoJuego == 1 || estadoJuego == 2)
            gameOver();
        else if (estadoJuego == 0) {
            if (jugador == JuegoTresEnRaya.JUGADOR)
                mTurno = JuegoTresEnRaya.MAQUINA;
            else if (jugador == JuegoTresEnRaya.MAQUINA)
                mTurno = JuegoTresEnRaya.JUGADOR;

            controlarTurno();
        }
    }

    private int comprobarEstadoJuego() {
        int estado = mJuego.comprobarGanador();

        if (estado == 1) {
            mInfoTexto.setText(R.string.result_human_wins);
            mWinSound.start();
            contWin++;
            setScore();

            speak(ttsWin);
            showNoti(ttsWin);

        } else if (estado == 2) {
            mInfoTexto.setText(R.string.result_computer_wins);
            mLoseSound.start();
            contLose++;
            setScore();

            speak(ttsLose);
            showNoti(ttsLose);
        } else if (estado == 0) {
            mInfoTexto.setText(R.string.result_tie);
        }

        return estado;
    }

    private void gameOver() {
        gameOver = true;

        for (int i = 0; i < mBotonesTablero.length; i++) {
            mBotonesTablero[i].setEnabled(false);
        }

    }

    public void onClick(View boton) {
        int id = boton.getId();
        String descripcionBoton = ((Button) findViewById(id)).getContentDescription().toString();
        int casilla = Integer.parseInt(descripcionBoton) - 1;

        if (mBotonesTablero[casilla].isEnabled()) {
            colocarFichaEnTablero(JuegoTresEnRaya.JUGADOR, casilla);
        }
    }

    public void onClickContinuar(View boton) {
        mBackgroundMusicPlayer.stop();
        mBackgroundMusicPlayer = MediaPlayer.create(this, R.raw.bck_sound);
        mBackgroundMusicPlayer.setLooping(true);
        mBackgroundMusicPlayer.start();

        prepararTablero();
        comenzarJuego();

        speak(ttsCont);
        showNoti(ttsCont);
    }

    public void onClickNewGame(View boton) {
        mTurno = mTurno = JuegoTresEnRaya.JUGADOR;
        mBackgroundMusicPlayer.stop();
        mBackgroundMusicPlayer = MediaPlayer.create(this, R.raw.bck_sound);
        mBackgroundMusicPlayer.setLooping(true);
        mBackgroundMusicPlayer.start();

        resetConts();
        prepararTablero();
        comenzarJuego();

        speak(ttsNew);
        showNoti(ttsNew);
    }

    private void enableBotones() {
        for (int i = 0; i < mBotonesTablero.length; i++) {
            mBotonesTablero[i].setText("");
            mBotonesTablero[i].setEnabled(true);
        }
    }

    private void disableBotones() {
        for (int i = 0; i < mBotonesTablero.length; i++) {
            mBotonesTablero[i].setText("");
            mBotonesTablero[i].setEnabled(false);
        }
    }

    private void waitMs(Runnable a, long ms) {
        handler.postDelayed(a, ms);
    }

    private void resetConts() {
        contWin = 0;
        contLose = 0;
        contGames = -1;
    }

    private void setScore() {
        player = findViewById(R.id.player_score);
        player.setText(Integer.toString(contWin));
        comp = findViewById(R.id.computer_score);
        comp.setText(Integer.toString(contLose));
        games = findViewById(R.id.tie_score);
        games.setText(Integer.toString(contGames));
    }

    private void prepararTablero(){
        setContentView(R.layout.activity_main);

        mBotonesTablero = new Button[JuegoTresEnRaya.DIM_TABLERO];
        mBotonesTablero[0] = (Button) findViewById(R.id.one);
        mBotonesTablero[1] = (Button) findViewById(R.id.two);
        mBotonesTablero[2] = (Button) findViewById(R.id.three);
        mBotonesTablero[3] = (Button) findViewById(R.id.four);
        mBotonesTablero[4] = (Button) findViewById(R.id.five);
        mBotonesTablero[5] = (Button) findViewById(R.id.six);
        mBotonesTablero[6] = (Button) findViewById(R.id.seven);
        mBotonesTablero[7] = (Button) findViewById(R.id.eight);
        mBotonesTablero[8] = (Button) findViewById(R.id.nine);

        mInfoTexto = (TextView) findViewById(R.id.information);
    }

    private void speak(String a){
        //habla modo de cola
        sintetizador.speak(a, TextToSpeech.QUEUE_ADD, null);
    }

    @Override
    public void onInit(int i) {
        sintetizador.setLanguage(Locale.getDefault());
        sintetizador.setPitch(1.0f);
        sintetizador.setSpeechRate(1.0f);
    }

    // esto muestra notificaciones por pantalla
    private void showNoti(String a){
        Toast msg = Toast.makeText(this, a , Toast.LENGTH_LONG);
        msg.show();
    }

}