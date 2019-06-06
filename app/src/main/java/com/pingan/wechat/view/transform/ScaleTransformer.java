package com.pingan.wechat.view.transform;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pingan.wechat.utils.L;

/**
 * Created time : 2019/6/6 15:45.
 *
 * @author LKKJ   ViewPager切换时动画的绘制
 */
public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;//放大缩小变化

    private static final float MIN_ALPHA = 0.5f;//透明度变化

    @Override
    public void transformPage(@NonNull View page, float position) {

        L.d(page.getTag() + ", pos==" + position);
        //第一页到第二页滑动  第一页positon->0~-1  第二页Position->1~0
        //第二页到第一页滑动  第一页position->-1~0     第二页position->0~1

        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);

        } else if (position <= 1) {
            if (position < 0) {
                //左边页面->右边页面 position：0~-1  -----> [1,0.75f]
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);//将position值转换为Scale的值
                page.setScaleX(scaleA);
                page.setScaleY(scaleA);

                //右边页面->左边页面 position：-1~0  -----> [0.75,1] --->MIN_SCALE + (1 - MIN_SCALE) * (1 + position);从左到右 从右到左换算公式一致

                //透明度变化--[1-0.5f]
                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
                page.setAlpha(alphaA);

            } else {
                //右边页面
                //左边到右边 position 1~0 ------>[0.75f,1]
                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
                page.setScaleX(scaleB);
                page.setScaleY(scaleB);

                //透明度变化---[0.5f-1]
                float alphaB = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
                page.setAlpha(alphaB);
            }
        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        }
    }
}
