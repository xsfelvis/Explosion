package xsf.explosion.factory;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import xsf.explosion.particle.Particle;
import xsf.explosion.particle.ParticleFalling;
import xsf.explosion.particle.ParticleFireWorks;
import xsf.explosion.particle.ParticleFlyLeft;
import xsf.explosion.particle.ParticleFlyRight;
import xsf.explosion.particle.ParticleInnerExplosion;
import xsf.explosion.particle.ParticleNormal;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: 粒子数组生成类
 */

public class ParticleFactory {
    public static final int NORMAL = 0;//普通 类型
    public static final int FLY_RIGHT = 1;//向右上方飞出类型
    public static final int FLY_LEFT = 2;//向左上方飞出类型
    public static final int FALL = 3;//向下破碎
    public static final int INNER_EXPLOSION = 4;//从内部炸开粒子效果
    public static final int FIREWORKS = 5;//从内部炸开粒子效果
    private int particleType;

    public ParticleFactory(@ParticleType int type) {
        particleType = type;
    }

    @IntDef({NORMAL, FLY_RIGHT, FLY_LEFT, FALL, INNER_EXPLOSION, FIREWORKS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ParticleType {
    }

    /**
     * 产生具体粒子数组
     *
     * @param bitmap
     * @param bound
     * @return
     */
    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_Count = w / ParticleNormal.DEFAULT_RADIUS; //横向个数
        int partH_Count = h / ParticleNormal.DEFAULT_RADIUS; //竖向个数

        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        int bitmap_part_h = bitmap.getHeight() / partH_Count;

        Particle[][] particles = new Particle[partH_Count][partW_Count];
        Point point = null;
        for (int row = 0; row < partH_Count; row++) { //行
            for (int column = 0; column < partW_Count; column++) { //列
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);
                point = new Point(column, row); //x是列，y是行
                //particles[row][column] = ParticleNormal.generateParticle(color, bound, point);
                particles[row][column] = getParticles(particleType, color, bound, point);
            }
        }

        return particles;
    }

    private Particle getParticles(int particleType, int color, Rect bound, Point point) {

        switch (particleType) {
            case NORMAL:
                return new ParticleNormal(color, bound, point);
            case FLY_RIGHT:
                return new ParticleFlyRight(color, bound, point);
            case FLY_LEFT:
                return new ParticleFlyLeft(color, bound, point);
            case FALL:
                return new ParticleFalling(color, bound, point);
            case INNER_EXPLOSION:
                return new ParticleInnerExplosion(color, bound, point);
            case FIREWORKS:
                return new ParticleFireWorks(color, bound, point);
            default:
                break;
        }
        return null;
    }

}
