package xsf.explosion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.Random;

import xsf.explosion.factory.ParticleFactory;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: 爆炸类的场地
 */

public class ExplosionView extends View {
    public static final String TAG = "Explosion";
    public static final Canvas canvas = new Canvas();
    public static final int SHAKE_DURATION = 200;
    private ArrayList<ExplosionAnimator> explosionAnimators;
    private View.OnClickListener onClickListener;
    private ParticleFactory factory;
    private AnimatorSet animatorSet;

    public ExplosionView(Context context, ParticleFactory factory) {
        super(context);
        init(factory);
    }

    public ExplosionView(Context context, AttributeSet attrs, ParticleFactory factory) {
        super(context, attrs);
        init(factory);
    }

    public ExplosionView(Context context, AttributeSet attrs, int defStyleAttr, ParticleFactory factory) {
        super(context, attrs, defStyleAttr);
        init(factory);
    }

    private void init(ParticleFactory factory) {
        this.factory = factory;
        explosionAnimators = new ArrayList<ExplosionAnimator>();
        attachActivity((Activity) getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ExplosionAnimator animator : explosionAnimators) {
            animator.drawExplosion(canvas);
        }
    }

    /**
     * 全屏附着到Actvity
     *
     * @param activity
     */
    private void attachActivity(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }

    private OnClickListener getOnClickListerner() {
        if (null == onClickListener) {
            onClickListener = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ExplosionView.this.explode(view);
                        view.setOnClickListener(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return onClickListener;
    }

    /**
     * 使得view出现粒子效果
     *
     * @param view
     */
    private void explode(final View view) throws Exception {
        final Random random = new Random();
        if (view.getVisibility() != VISIBLE || view.getAlpha() == 0) {
            throw new Exception("当前控件状态为不可见！");
        }
        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);//得到view相对于整个屏幕的坐标
        int contentTop = ((ViewGroup) getParent()).getTop();
        Rect frame = new Rect();
        ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        rect.offset(0, -contentTop - statusBarHeight);//去掉状态栏高度
        if (rect.width() == 0 || rect.height() == 0) {
            throw new Exception("获取高度为0！");
        }
        //震动动画
        ValueAnimator shakeaAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(SHAKE_DURATION);
        shakeaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setTranslationX((random.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
                view.setTranslationY((random.nextFloat() - 0.5f) * view.getHeight() * 0.05f);
            }
        });
        final ExplosionAnimator explosionAnimator = new ExplosionAnimator(this, Utils.createBitmapFromView(view, canvas), rect, factory);
        explosionAnimators.add(explosionAnimator);
        explosionAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                view.animate().setDuration(150).scaleX(0f).scaleY(0f).alpha(0f).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                explosionAnimators.remove(explosionAnimator);
            }
        });

        animatorSet = new AnimatorSet();
        animatorSet.play(shakeaAnimator).before(explosionAnimator);
        animatorSet.start();
    }

    //=========对外提供的功能函数===========

    /**
     * 给view添加爆炸效果监听
     *
     * @param view
     */
    public void addExplosionListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                addExplosionListener(viewGroup.getChildAt(i));
            }
        } else {
            view.setClickable(true);
            view.setOnClickListener(getOnClickListerner());
        }

    }


}
