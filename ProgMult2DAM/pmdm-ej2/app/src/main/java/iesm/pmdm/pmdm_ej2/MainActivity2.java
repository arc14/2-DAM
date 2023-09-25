package iesm.pmdm.pmdm_ej2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            DataInputStream dIn = new DataInputStream(this.openFileInput("numCuentaAtras.csv"));
            if(dIn.available() > 0){
                cont = dIn.readInt();
            }
            dIn.close();

            cont++;

            //DataOutputStream dOut = new DataOutputStream(this.openFileOutput("numCuentaAtras.csv"))

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {

            //TODO
            //Cuenta atras e imagen

        }


    }
}
