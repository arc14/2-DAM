package iesm.pmdm.t4_animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = this.findViewById(R.id.image);
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim1);

        img.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.image){
            Animation animacion = AnimationUtils.loadAnimation(this, R.anim.anim4);
            img.startAnimation(animacion);

        }
    }
}