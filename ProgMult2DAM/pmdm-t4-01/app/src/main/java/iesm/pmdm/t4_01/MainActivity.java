package iesm.pmdm.t4_01;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;

import iesm.pmdm.t4_01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements GestorApp {

    private AppBarConfiguration appBarConfiguration;
    private iesm.pmdm.t4_01.databinding.ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.create_action_navegador_web:
                cargarWeb("http://www.marca.es");
                break;
            case R.id.create_action_marcador_llamada:
                abrirMarcadorLlamada();
                break;
            case R.id.create_action_llamada_numero:
                marcarLlamada("123");
                break;
            case R.id.create_action_llamada:
                realizarLlamada("663858796");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void cargarWeb(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        i.setData(uri);

        this.startActivity(i);
    }

    @Override
    public void abrirMarcadorLlamada() {
        this.startActivity(new Intent(Intent.ACTION_DIAL));
    }

    @Override
    public void marcarLlamada(String telefono) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel: " + telefono);
        i.setData(uri);

        this.startActivity(i);
    }

    @Override
    public void realizarLlamada(String telefono) {
        //Permisos llamada
        confirmarPermisoLlamada();
        // Igual que el metodo anterior pero en 1 linea
        this.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel: " + telefono)));
    }

    @Override
    public void mandarSms(String contenido) {

    }

    @Override
    public void mandarSms(String telefono, String contenido) {

    }

    private boolean confirmarPermisoLlamada() {
        boolean confirmado = false;

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            this.requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 0);
        }
        else
            confirmado = true;

        return confirmado;
    }
}