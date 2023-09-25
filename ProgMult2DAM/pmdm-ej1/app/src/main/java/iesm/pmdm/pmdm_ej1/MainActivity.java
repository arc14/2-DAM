package iesm.pmdm.pmdm_ej1;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView e1, e2, e3, e4, e5, estado;
    private Drawable emoji;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView);
        emoji = getResources().getDrawable(R.drawable.boton);
        img.setImageDrawable(emoji);

        e1 = findViewById(R.id.perEst1);
        e2 = findViewById(R.id.perEst2);
        e3 = findViewById(R.id.perEst3);
        e4 = findViewById(R.id.perEst4);
        e5 = findViewById(R.id.perEst5);

        estado = findViewById(R.id.txtEstado);
        estado.setText("");

        Button capture = findViewById(R.id.buttonCapt);
        capture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonCapt) {

            Double por1 = formatearNumero(aleatorio());
            Double por2 = formatearNumero(aleatorio());
            Double por3 = formatearNumero(aleatorio());
            Double por4 = formatearNumero(aleatorio());
            Double por5 = formatearNumero(aleatorio());

            e1.setText(String.valueOf(por1));
            e2.setText(String.valueOf(por2));
            e3.setText(String.valueOf(por3));
            e4.setText(String.valueOf(por4));
            e5.setText(String.valueOf(por5));

            TextView porcentaje = findViewById(R.id.textPercent);

            int res = (int) (formatearNumero(mediaArit5Num(por1, por2, por3, por4, por5))*100);
            porcentaje.setText( res + "%");

            if (res <= 30){
                estado.setText("TRISTE");
                estado.setTextColor(Color.RED);

                emoji = getResources().getDrawable(R.drawable.triste);
            } else if (res > 30 && res <= 60){
                estado.setText("NEUTRO");
                estado.setTextColor(Color.GREEN);

                emoji = getResources().getDrawable(R.drawable.neutro);
            }else if (res > 60 && res <= 100 ) {
                estado.setText("ALTERADO");
                estado.setTextColor(Color.BLUE);

                emoji = getResources().getDrawable(R.drawable.alterado);
            }

            img.setImageDrawable(emoji);
        }
    }


    private double aleatorio(){
        return new Random().nextDouble();
    }

    private double formatearNumero(double num){
        return Math.floor(num * 100) / 100;
    }

    private double mediaArit5Num(double a, double b, double c, double d ,double e){
        return ((a + b + c + d) / 5);
    }

}