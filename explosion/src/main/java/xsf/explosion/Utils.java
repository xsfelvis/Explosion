package xsf.explosion;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Author: 彩笔学长
 * Time: created at 28/11/2016.
 * Description: view保存为bitmap的工具
 */

public class Utils {
    /**
     * 密度
     */
    public static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(int dp) {
        return Math.round(dp * DENSITY);
    }

    /**
     * view 生成bitmap
     * @param view
     * @param canvas
     * @return
     */
    public static Bitmap createBitmapFromView(View view, Canvas canvas) {
        Bitmap bitmap = createBitmapSafety(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888,1);

        if (bitmap != null) {
            synchronized (canvas) {
                canvas.setBitmap(bitmap);
                view.draw(canvas);
                canvas.setBitmap(null); //清除引用
            }
        }
        return bitmap;
    }

    private static Bitmap createBitmapSafety(int width, int height, Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafety(width, height, config, retryCount - 1);
            }
            return null;
        }
    }



}
