package iesm.pmdm.t3_gestoracceso;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private final String LOGTAG = "PMDM";
    private final String FILENAME = "accesos.dat";

    private boolean first = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrarEstado("ENTRADA", fechaLocal(), horaLocal());
        rescatarRegistro();
    }

    // Hay un problema y es que si se cierra la app borrando el proceso no se registra
    // pero en el onDestroy no funciona
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        registrarEstado("SALIDA", fechaLocal(), horaLocal());
        super.onStop();
    }

    private void rescatarRegistro() {
        try {
            DataInputStream din = new DataInputStream(this.openFileInput(FILENAME));

            while (din.available() > 0) {
                mostrarBox(din.readUTF());
            }

        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "Fichero no encontrado");
        } catch (IOException e) {
            Log.e(LOGTAG, "Error E/S");
        } catch (Exception e) {
            Log.e(LOGTAG, "Error general");
        }
    }

    private void mostrarBox(String s) {
        TableLayout layout = this.findViewById(R.id.container);

        //Personalizar Filas
        TableRow mainRow = new TableRow(this);
        mainRow.setPadding(10, 10, 10, 10);
        mainRow.setGravity(1);

        //Personalizar cuadro de texto
        TextView box1 = new TextView(this);
        box1.setPadding(30, 0, 20, 0);
        box1.setTextSize(20);
        TextView box2 = new TextView(this);
        box2.setPadding(20, 0, 20, 0);
        box2.setTextSize(20);
        TextView box3 = new TextView(this);
        box2.setPadding(20, 0, 30, 0);
        box3.setTextSize(20);

        //Separamos la linea por ';'
        String[] datosLinea = s.split(";");

        if (first) {
            box1.setText("E/S");
            mainRow.addView(box1);
            box2.setText("FECHA");
            mainRow.addView(box2);
            box3.setText("HORA");
            mainRow.addView(box3);
            first = false;
        } else if (datosLinea[0].equalsIgnoreCase("ENTRADA")) {
            mainRow.setBackgroundColor(Color.GREEN);
            box1.setText(datosLinea[0]);
            mainRow.addView(box1);
            box2.setText(datosLinea[1]);
            mainRow.addView(box2);
            box3.setText(datosLinea[2]);
            mainRow.addView(box3);
        } else if (datosLinea[0].equalsIgnoreCase("SALIDA")) {
            mainRow.setBackgroundColor(Color.MAGENTA);
            box1.setText(datosLinea[0]);
            mainRow.addView(box1);
            box2.setText(datosLinea[1]);
            mainRow.addView(box2);
            box3.setText(datosLinea[2]);
            mainRow.addView(box3);
        }
        layout.addView(mainRow);
    }


    private void registrarEstado(String estado, String fecha, String hora) {
        Log.i(LOGTAG, "Se registra la entrada");

        try {
            // 1. Recuperar flujo de escritura
            DataOutputStream dout = new DataOutputStream(this.openFileOutput(FILENAME, Context.MODE_APPEND));

            // 2. Montar el string
            String registroCompleto = estado + "; " + fecha + "; " + hora + ";";

            // 2. Añadir al fichero
            dout.writeUTF(registroCompleto + "\n");


        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "Fichero no encontrado");
        } catch (IOException e) {
            Log.e(LOGTAG, "Error E/S");
        } catch (Exception e) {
            Log.e(LOGTAG, "Error general");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String fechaLocal() {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yy");
        String fechaS = fecha.format(formatterFecha);
        return fechaS;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String horaLocal() {
        LocalDateTime hora = LocalDateTime.now();
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("hh:mm");
        String horaS = hora.format(formatterHora);
        return horaS;
    }


}