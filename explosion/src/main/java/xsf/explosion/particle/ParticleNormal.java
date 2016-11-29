package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: 普通粒子效果
 */

public class ParticleNormal extends Particle {

    public ParticleNormal(int color, Rect bound, Point point) {
        super(color, bound, point);
    }


    /**
     * 粒子具体动作，不同的粒子动作不同
     *
     * @param factor
     */
    @Override
    public void explodeAction(float factor) {
        cx = cx + factor * random.nextInt(bound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(bound.height()) * (random.nextFloat() - 0.5f);
        radius = radius - factor * random.nextInt(2);
        alpha = (1f - factor) * (1 + random.nextFloat());
    }


}