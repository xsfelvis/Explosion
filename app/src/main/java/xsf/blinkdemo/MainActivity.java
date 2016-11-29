package xsf.blinkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import xsf.explosion.ExplosionView;
import xsf.explosion.factory.ParticleFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvHello;
    private ImageView ivVx, ivQQ, ivQQmusic, ivQQZone, ivWb, ivFirfox;
    private View layout;
    private ExplosionView explosionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        ivVx = (ImageView) findViewById(R.id.ivVx);
        ivQQ = (ImageView) findViewById(R.id.ivQQ);
        ivQQmusic = (ImageView) findViewById(R.id.ivQQMusic);
        ivQQZone = (ImageView) findViewById(R.id.ivQQZone);
        ivWb = (ImageView) findViewById(R.id.ivWb);
        ivFirfox = (ImageView) findViewById(R.id.ivFirfox);
        layout = findViewById(R.id.layout);


        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.NORMAL));
        explosionView.addExplosionListener(tvHello);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.NORMAL));
        explosionView.addExplosionListener(ivVx);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FLY_LEFT));
        explosionView.addExplosionListener(ivQQ);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FLY_RIGHT));
        explosionView.addExplosionListener(ivQQmusic);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FALL));
        explosionView.addExplosionListener(ivQQZone);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.INNER_EXPLOSION));
        explosionView.addExplosionListener(ivWb);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FIREWORKS));
        explosionView.addExplosionListener(ivFirfox);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FIREWORKS));
        explosionView.addExplosionListener(layout);


        //explosionView.addExplosionListener(layout2);
    }
}
