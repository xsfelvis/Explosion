package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Author: 彩笔学长
 * Time: created at 29/11/2016.
 * Description: 向右上方飞出粒子效果
 */

public class ParticleFlyRight extends Particle {

    public ParticleFlyRight(int color, Rect bound, Point point) {
        super(color, bound, point);
    }

    @Override
    public void explodeAction(float factor) {
        cx = cx + factor * random.nextInt(bound.width()) * (random.nextFloat());
        cy = cy - factor * random.nextInt(bound.width()) * (random.nextFloat());
        radius = radius - factor * random.nextInt(2);
        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
