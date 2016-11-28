package xsf.explosion;

import android.content.res.Resources;

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


}
