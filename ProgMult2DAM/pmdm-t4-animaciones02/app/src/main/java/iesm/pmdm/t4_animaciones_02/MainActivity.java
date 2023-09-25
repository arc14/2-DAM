package iesm.pmdm.t4_animaciones_02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    private ImageView image;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView);
        boton = findViewById(R.id.boton);

        image.setOnClickListener(this);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageView){

        loadAnimation(view, R.anim.rotacion);


        } else if (view.getId() == R.id.boton){
            image.setBackgroundResource(R.drawable.sprites);
            AnimationDrawable animation = (AnimationDrawable) image.getBackground();

            if (!animation.isRunning()){

                animation.start();

            } else {

                animation.stop();
            }
        }
    }

    private void loadAnimation(View view, int recurso) {

        Animation anim = AnimationUtils.loadAnimation(view.getContext(), recurso);
        anim.setAnimationListener(this);
        view.startAnimation(anim);

    }

    @Override
    public void onAnimationStart(Animation animation) {
        Toast.makeText(this, "Animacion empezada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Toast.makeText(this, "Se repite", Toast.LENGTH_SHORT).show();
    }
}