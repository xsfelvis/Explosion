package xsf.explosion.particle;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description:
 */

public abstract class Particle {
    static Random random = new Random();
    public static final int DEFAULT_RADIUS = 8; //默认小球宽高
    public float cx; //center x of circle
    public float cy; //center y of circle
    public float radius;
    public Rect bound;

    public int color;
    public float alpha = 1.0f;

    public Particle(int color, Rect bound, Point point) {
        this.color = color;
        this.bound = bound;
        this.radius = DEFAULT_RADIUS;
        this.cx = bound.left + DEFAULT_RADIUS * point.x; //point.y是高
        this.cy = bound.top + DEFAULT_RADIUS * point.y;//point.x是宽
        this.alpha = 1.0f;
    }

    //不同种类的粒子具体行为交由子类实现
    public abstract void explodeAction(float factor);

}
