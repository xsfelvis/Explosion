package xsf.blinkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import xsf.explosion.ExplosionView;
import xsf.explosion.factory.ParticleFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvFireWorks, tvNormal, tvFlyLeft, tvFlyRight, tvFall, tvInnerExplosion, tvFrieWorks2;
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
        tvFireWorks = (TextView) findViewById(R.id.tvFireWorks);
        layout = findViewById(R.id.layout);
        tvNormal = (TextView) findViewById(R.id.tvNormal);
        ivVx = (ImageView) findViewById(R.id.ivVx);
        tvFlyLeft = (TextView) findViewById(R.id.tvFlyLeft);
        ivQQ = (ImageView) findViewById(R.id.ivQQ);
        tvFlyRight = (TextView) findViewById(R.id.tvFlyRight);
        ivQQmusic = (ImageView) findViewById(R.id.ivQQMusic);
        tvFall = (TextView) findViewById(R.id.tvFall);
        ivQQZone = (ImageView) findViewById(R.id.ivQQZone);
        tvInnerExplosion = (TextView) findViewById(R.id.tvInnerExplosion);
        ivWb = (ImageView) findViewById(R.id.ivWb);
        tvFrieWorks2 = (TextView) findViewById(R.id.tvFireWorks2);
        ivFirfox = (ImageView) findViewById(R.id.ivFirfox);


        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FIREWORKS));
        explosionView.addExplosionListener(layout);
        explosionView.addExplosionListener(tvFireWorks);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.NORMAL));
        explosionView.addExplosionListener(tvNormal);
        explosionView.addExplosionListener(ivVx);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FLY_LEFT));
        explosionView.addExplosionListener(tvFlyLeft);
        explosionView.addExplosionListener(ivQQ);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FLY_RIGHT));
        explosionView.addExplosionListener(tvFlyRight);
        explosionView.addExplosionListener(ivQQmusic);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FALL));
        explosionView.addExplosionListener(tvFall);
        explosionView.addExplosionListener(ivQQZone);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.INNER_EXPLOSION));
        explosionView.addExplosionListener(tvInnerExplosion);
        explosionView.addExplosionListener(ivWb);
        explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.FIREWORKS));
        explosionView.addExplosionListener(tvFrieWorks2);
        explosionView.addExplosionListener(ivFirfox);


        //explosionView.addExplosionListener(layout2);
    }
}
