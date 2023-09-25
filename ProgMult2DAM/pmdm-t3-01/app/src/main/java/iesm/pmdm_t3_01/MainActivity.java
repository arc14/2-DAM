package iesm.pmdm_t3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        recuperarConfiguracion();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /** true -> el menú ya está visible */
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.fondo_rojo:
                //almacenarConfiguracion();
                //break;
            default:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


    private void almacenarConfiguracion(){
        int color = Color.RED;

        //Modificar el fondo de color
        LinearLayout layout = (LinearLayout) this.findViewById(R.id.container);
        layout.setBackgroundColor(color);

        //Recuperar el objeto de las preferencias
        SharedPreferences prefs = this.getSharedPreferences("misPrefs", Context.MODE_PRIVATE);

        //editar preferencias
        SharedPreferences.Editor editor = prefs.edit();

        //Almacenar de clave-valor
        editor.putString("color", String.valueOf(color));

        //Confirmar cambios
        editor.commit();
    }

    private void recuperarConfiguracion(){

        //Recuperar el objeto de las preferencias igual al anterior
        SharedPreferences prefs = this.getSharedPreferences("misPrefs", Context.MODE_PRIVATE);

        //Recuperar Valor
        String color_p = prefs.getString("color", null);

        if (color_p != null){
            //Establecer color
            LinearLayout layout = this.findViewById(R.id.container);
            layout.setBackgroundColor(Integer.valueOf(color_p));
        }

    }

}