package xsf.explosion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: 爆炸类的场地
 */

public class ExplosionView extends View {
    public static final String TAG = "Explosion";
    public static final Canvas canvas = new Canvas();
    private ArrayList<ExplosionAnimator> explosionAnimators;
    private View.OnClickListener onClickListener;

    public ExplosionView(Context context) {
        super(context);
        init();
    }

    public ExplosionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExplosionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        explosionAnimators = new ArrayList<ExplosionAnimator>();
        attachActivity((Activity) getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(ExplosionAnimator animator : explosionAnimators){
            animator.draw(canvas);
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
                    ExplosionView.this.explode(view);
                    view.setOnClickListener(null);
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
    private void explode(final View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);//得到view相对于整个屏幕的坐标
        rect.offset(0, -Utils.dp2px(75));//去掉状态栏高度
        final ExplosionAnimator animator = new ExplosionAnimator(this, createBitmapFromView(view), rect);
        explosionAnimators.add(animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.animate().alpha(0f).setDuration(150).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                explosionAnimators.remove(animator);
                animation = null;

            }
        });
        animator.start();
    }

    private Bitmap createBitmapFromView(View view) {
        /*
         * 为什么屏蔽以下代码段？
         * 如果ImageView直接得到位图，那么当它设置背景（backgroud)时，不会读取到背景颜色
         */
//        if (view instanceof ImageView) {
//            Drawable drawable = ((ImageView)view).getDrawable();
//            if (drawable != null && drawable instanceof BitmapDrawable) {
//                return ((BitmapDrawable) drawable).getBitmap();
//            }
//        }

        //view.clearFocus(); //不同焦点状态显示的可能不同——（azz:不同就不同有什么关系？）

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        if (bitmap != null) {
            synchronized (canvas) {
                canvas.setBitmap(bitmap);
                view.draw(canvas);
                canvas.setBitmap(null); //清除引用
            }
        }
        return bitmap;
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
