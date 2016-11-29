package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

import xsf.explosion.Utils;

/**
 * Author: 彩笔学长
 * Time: created at 29/11/2016.
 * Description:烟花效果
 */

public class ParticleFireWorks extends Particle {
    public static final float X = Utils.dp2px(5);
    public static final float Y = Utils.dp2px(20);
    public static final float V = Utils.dp2px(2);
    public static final float W = Utils.dp2px(1);
    public static final float END_VALUE = 1.4f;

    public float tempCx;
    public float tempCy;
    public float tempRadius;
    public float top;
    public float bottom;
    public float mag;
    public float neg;
    public float life;
    public float overflow;

    public ParticleFireWorks(int color, Rect bound, Point point) {
        super(color, bound, point);
        init();
    }


    private void init() {
        radius = V;
        tempRadius = random.nextFloat() < 0.2f ? V + ((X - V) * random.nextFloat()) : W + ((V - W) * random.nextFloat());
        float nextFloat = random.nextFloat();
        top = bound.height() * ((0.18f * random.nextFloat()) + 0.2f);
        top = nextFloat < 0.2f ? top : top + ((top * 0.2f) * random.nextFloat());
        bottom = (bound.height() * (random.nextFloat() - 0.5f)) * 1.8f;
        float f = nextFloat < 0.2f ? bottom : nextFloat < 0.8f ? bottom * 0.6f : bottom * 0.3f;
        bottom = f;
        mag = 4.0f * top / bottom;
        neg = (-mag) / bottom;
        f = bound.centerX() + (Y * (random.nextFloat() - 0.5f));
        tempCx = f;
        cx = f;
        f = bound.centerY() + (Y * (random.nextFloat() - 0.5f));
        tempCy = f;
        cy = f;
        life = END_VALUE / 10 * random.nextFloat();
        overflow = 0.4f * random.nextFloat();
    }

    @Override
    public void explodeAction(float factor) {
        float f = 0f;
        float normalization = factor / END_VALUE;
        if (normalization < life || normalization > 1f - overflow) {
            alpha = 0f;
            return;
        }
        normalization = (normalization - life) / (1f - life - overflow);
        float f2 = normalization * END_VALUE;
        if (normalization >= 0.7f) {
            f = (normalization - 0.7f) / 0.3f;
        }
        alpha = 1f - f;
        f = bottom * f2;
        cx = tempCx + f;
        cy = (float) (tempCy - this.neg * Math.pow(f, 2.0)) - f * mag;
        radius = V + (tempRadius - V) * f2;

    }
}
