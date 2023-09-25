package iesm.pmdm.t4_login;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {

            TextView text = (TextView) this.findViewById(R.id.welcome);

            //se llama a obtener datos de la clase main

            text.setText("Bienvenido " + bundle.getString("nombre") + "\n"
                    + "su correo es " + bundle.getString("email")
                    + " y su telefono es " + bundle.getString("tlfn"));

        }

    }
}