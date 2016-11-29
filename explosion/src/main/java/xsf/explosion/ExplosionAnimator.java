package xsf.explosion;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import xsf.explosion.factory.ParticleFactory;
import xsf.explosion.particle.Particle;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: 粒子动画类
 */

public class ExplosionAnimator extends ValueAnimator {
    public static final int DEFAULT_DURATION = 1500;
    private Particle[][] fallingParticles;
    private Paint paint;
    private View containter;


    public ExplosionAnimator(View view, Bitmap bitmap, Rect bound, ParticleFactory factory) {
        paint = new Paint();
        containter = view;
        setDuration(DEFAULT_DURATION);
        setFloatValues(0.0f, 1.0f);
        fallingParticles = factory.generateParticles(bitmap, bound);
    }

    /**
     * 画粒子爆炸效果
     *
     * @param canvas
     */
    public void drawExplosion(Canvas canvas) {
        if (!isStarted()) {
            return;
        }
        for (Particle[] fallingParticle : fallingParticles) {
            for (Particle p : fallingParticle) {
                p.explodeAction((Float) getAnimatedValue());
                paint.setColor(p.color);
                paint.setAlpha((int) (Color.alpha(p.color) * p.alpha));//透明色不是黑色
                canvas.drawCircle(p.cx, p.cy, p.radius, paint);
            }
        }
        containter.invalidate();
    }

    @Override
    public void start() {
        super.start();
        containter.invalidate();
    }
}
