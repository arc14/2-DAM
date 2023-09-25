package iesm.pmdm.t4_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private boolean access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Acciones del boton en un listener
        Button b = this.findViewById(R.id.boton_iniciar_sesion);

        Bundle bundle = new Bundle();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // rescatar valores del forulario login
                String email = ((TextView) findViewById(R.id.input_usuario)).getText().toString();
                String pass = ((TextView) findViewById(R.id.input_contrasena)).getText().toString();
                Snackbar.make(view, email, Snackbar.LENGTH_SHORT).show();
                Snackbar.make(view, pass, Snackbar.LENGTH_SHORT).show();


                getAccess(email, pass, bundle);

                if (access) {
                    // Contruirmos conjunto de datos a transmitir

                    bundle.putString("email", email);

                    Snackbar.make(view, "Acceso garantizado", Snackbar.LENGTH_LONG).show();


                    // Llamada del intent (la otra pantalla)
                    Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                } else {
                    // Una mejor forma de notificacion
                    Snackbar.make(view, "Error de acceso", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Devuelve cierto si se confirma que el usr y pass son correctos y se localizan en el fichero
     *
     * @param email
     * @param pass
     * @return
     */
    private void getAccess(String email, String pass, Bundle bundle) {

        Boolean aux = true;
        try {
            DataInputStream dIn = new DataInputStream(this.openFileInput("users.csv"));

            if (!email.equals("") && !pass.equals("") && pass != null && email != null) {

                while (dIn.available() > 0 && aux) {
                    String linea = dIn.readUTF();
                    if (linea.contains(email) && linea.contains(pass)) {
                        String[] datos = linea.split(":");

                        bundle.putString("email", email);
                        bundle.putString("nombre", datos[0]);
                        bundle.putString("tlfn", datos[3]);

                        access = true;

                        aux = false;
                    }
                }
            } else {
                access = false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}