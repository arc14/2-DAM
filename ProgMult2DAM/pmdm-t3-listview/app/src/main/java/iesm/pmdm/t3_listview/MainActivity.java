package iesm.pmdm.t3_listview;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "clientes-";
    private final String extension = ".txt";
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }

    //Las busquedas y repeticiones en el adapter, no en el arrayList
    //AlertDialog para el ultimo apartado


    private void loadData() {
        // 1. Cargar datos iniciales (pueden ser arrays, list, collections, etc)
        ArrayList cadenas = new ArrayList();

        //2. Cargar datos en ListView
        addItemsInListView(cadenas);
    }

    private void addItemsInListView(ArrayList cadenas) {
        // 1. Localizar el objeto listview dentro del layout
        ListView lista = this.findViewById(R.id.listView1);

        // 2. Crear adaptador de datos y vincular los datos
        adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cadenas);
        lista.setAdapter(adaptador);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Eliminar contenido");
                builder.setMessage("¿Quieres eliminar el NIF seleccionado?");

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        adaptador.remove(adaptador.getItem(i));
                        Toast.makeText(getApplicationContext(), "Elemento eliminado", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        dialogo.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String txt = "Nº de posicion: " + i + "\nNº elementos de la lista :" + adaptador.getCount() + ("\nValor del elemento: " + adaptador.getItem(i).toString());

                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void putItem(View view) {
        // 1. Localizar el objeto edittext
        EditText text = this.findViewById(R.id.editText);

        // 2. obtener el texto e introducirlo en el adaptador

        String textIntroducido = text.getText().toString();

        if (!textIntroducido.isEmpty()) {
            if (validadorNIF(textIntroducido)){
                if(repetidoNIF(textIntroducido)) {
                    adaptador.add(text.getText().toString().toUpperCase(Locale.ROOT));

                    //Comparator ordena los datos en el arrayAdapter antes de insertarlos en la lista
                    adaptador.sort((charSequence0, charSequence1) ->
                            charSequence0.toString().compareToIgnoreCase(charSequence1.toString()));

                    text.setText("");
                    Toast.makeText(this, "Elemento añadido", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(this, "El NIF ya existe en la lista", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Formato de NIF incorrecto \n Por Ejemplo: 12345678A", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clearItems(View view) {
        //1. Volcar contenido del ListView a archivo de meoria externa
        escribirFichero();
        //2. Vaciar ListView
        adaptador.clear();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void escribirFichero() {
        // 1. Obtener ruta de almacenaje
        File dir = this.getExternalFilesDir(null);

        if (dir.canWrite()) {
            LocalDate fecha = LocalDate.now();
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("ddMMyyyy");
            String fechaS = fecha.format(formatterFecha);

            File file = new File(dir, FILENAME + fechaS + extension);
            Toast.makeText(this, "Listado guardado en: " + file.getName(), Toast.LENGTH_LONG).show();

            try {
                FileWriter fout = new FileWriter(file);
                for (int i = 0; i < adaptador.getCount(); i++) {
                    fout.write(adaptador.getItem(i).toString() + "\n");
                }

                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_LONG).show();
    }

    private boolean validadorNIF(String nif) {
        boolean bo;
        String cadenaNum = nif.substring(0, nif.length()-1);

        try {
            Integer.parseInt(cadenaNum);
            bo = true;
        } catch (NumberFormatException excepcion) {
            bo = false;
        }

        return nif.length() == 9 && Character.isLetter(nif.charAt(8)) && bo;
    }

    private boolean repetidoNIF(String nif) {
        boolean bo = true;

        for (int i = 0; i < adaptador.getCount(); i++) {
            if (adaptador.getItem(i).toString().equalsIgnoreCase(nif))
                bo = false;
        }
        return bo;
    }

}