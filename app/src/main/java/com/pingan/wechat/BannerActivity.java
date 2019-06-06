package com.pingan.wechat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.wechat.view.transform.ScaleTransformer;

public class BannerActivity extends AppCompatActivity {

    private ViewPager mVpBanner;

    private int[] mResIds = new int[]{0xffff0000, 0xff00ff00, 0xff0000ff};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mVpBanner = findViewById(R.id.vp_banner);
        mVpBanner.setOffscreenPageLimit(mResIds.length);
        mVpBanner.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {

                return mResIds.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                View view = new View(container.getContext());
                view.setBackgroundColor(mResIds[position]);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(view);
                return view;

            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);

            }
        });

        mVpBanner.setPageTransformer(true, new ScaleTransformer());
    }
}
