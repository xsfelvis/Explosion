package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Author: 彩笔学长
 * Time: created at 29/11/2016.
 * Description:
 */

public class ParticleFlyLeft extends Particle{
    public ParticleFlyLeft(int color, Rect bound, Point point) {
        super(color, bound, point);
    }

    @Override
    public void explodeAction(float factor) {
        cx = cx - factor * random.nextInt(bound.width()) * (random.nextFloat());
        cy = cy - factor * random.nextInt(bound.width()) * (random.nextFloat());
        radius = radius - factor * random.nextInt(2);
        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
