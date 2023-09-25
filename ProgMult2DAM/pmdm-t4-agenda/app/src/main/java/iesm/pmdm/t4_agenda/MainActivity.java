package iesm.pmdm.t4_agenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "contactos.csv";
    private ArrayAdapter adaptador;
    private ArrayList cadenas = new ArrayList();
    private ArrayList<Contacto> contactos = new ArrayList();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leerFichero();

        cadenas.sort(Comparator.naturalOrder());

        addItemsInListView(cadenas);

        contactos.sort(Contacto::compareTo);

        //Permiso para poder llamar, conviene hacerlo en el onCreate
        confirmarPermisoLlamada();
    }


    private void leerFichero() {

        File file = new File(getExternalFilesDir(null), FILENAME);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String linea = br.readLine();

            while (linea != null) {

                String[] contacto = linea.split(";");

                contactos.add(new Contacto(contacto[0], Integer.valueOf(contacto[1]), contacto[2]));
                cadenas.add(contacto[0]);

                linea = br.readLine();
            }
            br.close();

        } catch (IOException e) {
            Toast.makeText(this, "Error de lectura", Toast.LENGTH_SHORT).show();
        }
    }


    private void addItemsInListView(ArrayList arr) {

        ListView lista = this.findViewById(R.id.container);

        adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arr);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Cuadro pop
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Opciones");

                builder.setPositiveButton("Llamada", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(DialogInterface dialog, int id) {
                        llamar(contactos.get(i).getTelefono());
                    }
                });

                builder.setNegativeButton("WhatsApp", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mandarWassa();
                    }
                });

                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void llamar(Integer tlf) {
        if (confirmarPermisoLlamada()) {
            Intent i = new Intent(Intent.ACTION_CALL);
            Uri uri = Uri.parse("Telefono: " + tlf);
            i.setData(uri);
            this.startActivity(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean confirmarPermisoLlamada() {
        boolean confirmado = false;

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 0);
        } else
            confirmado = true;

        return confirmado;
    }

    private void mandarWassa() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Texto a enviar");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }
}