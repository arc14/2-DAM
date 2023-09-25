package iesm.pmdm.t3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtUsr;
    TextView txtPass;

    int auxCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        txtUsr = (TextView) findViewById(R.id.campo_email);
        txtPass = (TextView) findViewById(R.id.campo_password);

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

            case R.id.load_settings:
                recuperarConfiguracion();
                break;
            case R.id.reset_settings:
                resetConfiguracion();
                break;
            case R.id.save_settings:
                almacenarConfiguracion();
                break;
            default:
                Toast.makeText(this, /*item.getTitle().toString()*/ "Otro elemento seleccionado", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void almacenarConfiguracion(){

        String usr = String.valueOf(txtUsr.getText());

        String pass = String.valueOf(txtPass.getText());

        if (!usr.equals("") && !pass.equals("")){
            //Contruir sharedPreferences y el editor
            SharedPreferences prefs = this.getSharedPreferences("misPrefs", Context.MODE_PRIVATE);

            cambiarValores(prefs, usr, pass, 1);

            Toast.makeText(this, "Campo usuario y contraseña guardados", Toast.LENGTH_LONG).show();

            limpiarCampos(txtUsr, txtPass);

        } else {
            Toast.makeText(this, "Los campos usuario y contraseña no pueden estar vacios", Toast.LENGTH_LONG).show();
        }

    }

    private void recuperarConfiguracion(){

        //Recuperar el objeto de las preferencias igual al anterior
        SharedPreferences prefs = this.getSharedPreferences("misPrefs", Context.MODE_PRIVATE);

        //Recuperar Valor
        String usrRec = prefs.getString("usr", null);
        String passRec = prefs.getString("pass", null);

        if (usrRec != null && passRec != null) {
            //Establece valores
            txtUsr.setText(usrRec);
            txtPass.setText(passRec);
        } else {
            Toast.makeText(this, "No existen valores a recuperar", Toast.LENGTH_LONG).show();
        }
    }

    private void resetConfiguracion(){

        SharedPreferences prefs = this.getSharedPreferences("misPrefs", Context.MODE_PRIVATE);

        String usrRec = prefs.getString("usr", null);
        String passRec = prefs.getString("pass", null);

        if (usrRec != null && passRec != null) {

            cambiarValores(prefs, null, null, 0);
            limpiarCampos(txtUsr, txtPass);

        } else {
            Toast.makeText(this, "No hay valores que resetear", Toast.LENGTH_LONG).show();
        }
    }

    private void cambiarValores(SharedPreferences prefs, String usr, String pass, int cont){
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("usr", usr);
        editor.putString("pass", pass);

        if (cont == 1){
            //super.cont;
            //editor.putInt("cont", );
        } else if (cont == 0){

        }

        editor.commit();
    }

    private void limpiarCampos(TextView txtUsr, TextView txtPass){
        //Limpiar los campos en la app
        txtUsr.setText("");
        txtPass.setText("");
    }

}