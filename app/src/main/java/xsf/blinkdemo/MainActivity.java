package xsf.blinkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import xsf.explosion.ExplosionView;

public class MainActivity extends AppCompatActivity {
    private TextView tvHello;
    private ExplosionView explosionView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        explosionView = new ExplosionView(this);
        tvHello = (TextView) findViewById(R.id.tvHello);
        imageView = (ImageView) findViewById(R.id.ivVx);
        explosionView.addExplosionListener(tvHello);
        explosionView.addExplosionListener(imageView);
        explosionView.addExplosionListener(findViewById(R.id.layout2));

    }
}
