package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: 粒子抽象类
 */

public class Particle {

    public static final int PART_WH = 8; //默认小球宽高

    //实际的值（可变）
    public float cx; //center x of circle
    public float cy; //center y of circle
    public float radius;

    public int color;
    public float alpha;

    static Random random = new Random();

    Rect mBound;

    public static Particle generateParticle(int color, Rect bound, Point point) {
        int row = point.y; //行是高
        int column = point.x; //列是宽

        Particle particle = new Particle();
        particle.mBound = bound;
        particle.color = color;
        particle.alpha = 1f;

        particle.radius = PART_WH;
        particle.cx = bound.left + PART_WH * column;
        particle.cy = bound.top + PART_WH * row;

        return particle;
    }

    public void explodeAction(float factor) {
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cx = cx + factor * random.nextInt(mBound.height()) * (random.nextFloat() - 0.5f);
        //cy = cy + factor * random.nextInt(mBound.height() / 2);

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}