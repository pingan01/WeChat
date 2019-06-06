package com.pingan.wechat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pingan.wechat.fragment.SplashFragment;
import com.pingan.wechat.view.transform.ScaleTransformer;

public class SplashActivity extends AppCompatActivity {

    private ViewPager mVpSplash;

    private int[] mResIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mVpSplash = findViewById(R.id.vp_splash);

        mVpSplash.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return SplashFragment.newInstance(mResIds[i]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });

        mVpSplash.setPageTransformer(true, new ScaleTransformer());
    }
}
