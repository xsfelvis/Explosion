package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Author: 彩笔学长
 * Time: created at 29/11/2016.
 * Description: 内部炸裂粒子效果
 */

public class ParticleInnerExplosion extends Particle {

    public ParticleInnerExplosion(int color, Rect bound, Point point) {
        super(color, bound, point);
    }

    @Override
    public void explodeAction(float factor) {
        // x轴控制粒子左右变化
        if (cx > bound.centerX()) {
            cx = cx + factor * random.nextInt(bound.width()) * (random.nextFloat());
        } else {
            cx = cx - factor * random.nextInt(bound.width()) * (random.nextFloat());
        }
        //y轴控制粒子上下变化
        if (factor <= 0.5) {
            cy = cy - factor * random.nextInt(bound.height() / 2);
        } else {
            cy = cy + factor * random.nextInt(bound.height() / 2);
        }
        radius = radius - factor * random.nextInt(2);
        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}
