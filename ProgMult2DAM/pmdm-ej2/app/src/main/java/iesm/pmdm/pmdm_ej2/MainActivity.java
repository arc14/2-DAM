package iesm.pmdm.pmdm_ej2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText numSeg;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Cronometro B.O.M. - Máximo 10 segundos" , Toast.LENGTH_LONG).show();

        Button start = findViewById(R.id.button);
        start.setOnClickListener(this);

        numSeg = findViewById(R.id.numero);

        bundle = new Bundle();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            String a = String.valueOf(numSeg.getText());
            int segs = Integer.parseInt(a);
            minMaxSegs(segs);
        }
    }

    private void minMaxSegs(int segs){
        if (segs < 1){
            Toast.makeText(getApplicationContext(), "El tiempo no puede ser inferior a 1" , Toast.LENGTH_LONG).show();
        } else if (segs > 10){
            Toast.makeText(getApplicationContext(), "El tiempo no puede ser superior a 10" , Toast.LENGTH_LONG).show();
        } else {
            bundle.putInt("segundos", segs);
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}