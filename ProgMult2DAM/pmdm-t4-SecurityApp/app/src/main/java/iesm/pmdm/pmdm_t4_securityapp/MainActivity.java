package iesm.pmdm.pmdm_t4_securityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    private Button btnAcceso;
    private ImageView imgAcceso;
    private TextView txtAcceso;
    private LinearLayout layout;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcceso = findViewById(R.id.btnAcceso);
        imgAcceso = findViewById(R.id.segurata);
        txtAcceso = findViewById(R.id.txtAcceso);

        layout = findViewById(R.id.layout1);

        btnAcceso.setOnClickListener(this);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(MainActivity.this, "Error en el sensor", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Acceso correcto", Toast.LENGTH_SHORT).show();
                imgAcceso.setImageDrawable(getResources().getDrawable(R.drawable.opened));
                txtAcceso.setText("¡ACCESO PERMITIDO!");

                btnAcceso.setEnabled(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        loadAnimation(layout, R.anim.zoomacceso);
                    }
                }, 1500);

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "Error de autenticacion", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Escaner biométrico")
                .setSubtitle("Logueate con tu huella")
                .setNegativeButtonText("a")
                .build();

    }


    private void loadAnimation(View view, int recurso) {
        Animation anim = AnimationUtils.loadAnimation(view.getContext(), recurso);
        anim.setAnimationListener(this);
        view.startAnimation(anim);

    }



    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAcceso) {
            biometricPrompt.authenticate(promptInfo);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        startActivity(new Intent(this, MainActivity2.class));

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}