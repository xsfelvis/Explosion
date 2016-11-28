package xsf.explosion;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

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

    public ExplosionAnimator(View view, Bitmap bitmap, Rect bound) {
        paint = new Paint();
        containter = view;
        setDuration(DEFAULT_DURATION);
        setFloatValues(0.0f, 1.0f);
        fallingParticles = generateParticles(bitmap,bound);
    }

    private Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_Count = w / Particle.PART_WH; //横向个数
        int partH_Count = h / Particle.PART_WH; //竖向个数

        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        int bitmap_part_h = bitmap.getHeight() / partH_Count;

        Particle[][] particles = new Particle[partH_Count][partW_Count];
        Point point = null;
        for (int row = 0; row < partH_Count; row ++) { //行
            for (int column = 0; column < partW_Count; column ++) { //列
                //取得当前粒子所在位置的颜色
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);

                point = new Point(column, row); //x是列，y是行

                particles[row][column] = Particle.generateParticle(color, bound, point);
            }
        }

        return particles;
    }


    /**
     * 画粒子爆炸效果
     * @param canvas
     */
    public void draw(Canvas canvas) {
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
