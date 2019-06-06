package com.pingan.wechat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.wechat.fragment.TabFragment;
import com.pingan.wechat.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity_Tab extends AppCompatActivity {

    private ViewPager mViewPager;

    private TabView mTabWeChat;
    private TabView mTabFriend;
    private TabView mTabFind;
    private TabView mTabMy;

    private List<String> titles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    private List<TabView> mTabs = new ArrayList<>();

    private static final String BUNDLE_KEY_POS = "bundle_key_positon";

    private int mCurrentTabPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        if (savedInstanceState != null) {
            mCurrentTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
        }

        initViews();

        initViewPagerAdapter();

        initEvents();

    }

    /**
     * 场景:用户在横竖屏切换或将应用放置后台 被系统杀死
     * 系统回收重建Activity时候--进行存储设置
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(BUNDLE_KEY_POS, mViewPager.getCurrentItem());

        super.onSaveInstanceState(outState);
    }

    private void initEvents() {

        for (int i = 0; i < mTabs.size(); i++) {
            final TabView tabView = mTabs.get(i);
            final int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(finalI, false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPagerAdapter() {
        /**
         *FragmentPagerAdapter--适用于Tab数量较少(切换的时候 fragment是没有被销毁的--同时导致Fragment一直占用内存)
         * 如果当前Fragment占内存较大 选用FragmentStatePagerAdapter
         * FragmentStatePagerAdapter---适用于tab数量较多(切换的时候 fragment被销毁)
         */

        mViewPager.setOffscreenPageLimit(titles.size());//设置ViewPager缓存
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TabFragment tabFragment = TabFragment.newInstance(titles.get(i));

                return tabFragment;
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                TabFragment tabFragment = (TabFragment) super.instantiateItem(container, position);
                mFragments.put(position, tabFragment);

                return tabFragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }

        });

        //获取0-1的回调 进行侧滑的动画拆分
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int i1) {
                //左->右 0->1   left pos,right pos+1      positionOffset 0~1
                //left progress:1~0; right progress:0~1;

                //右→左 1->0    left pos,right pos+1      positionOffet  1~0
                //left progress:0~1; right progress:1~0;

                if (positionOffset > 0) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //用户拖拽的状态
            }
        });
    }

    private void initViews() {
        mViewPager = findViewById(R.id.main_vp);

        mTabWeChat = findViewById(R.id.tab_wechat);
        mTabFriend = findViewById(R.id.tab_friend);
        mTabFind = findViewById(R.id.tab_find);
        mTabMy = findViewById(R.id.tab_my);

        mTabWeChat.setIconAndText(R.drawable.aio, R.drawable.ain, "微信");
        mTabFriend.setIconAndText(R.drawable.aim, R.drawable.ail, "通讯录");
        mTabFind.setIconAndText(R.drawable.aiq, R.drawable.aip, "发现");
        mTabMy.setIconAndText(R.drawable.ais, R.drawable.air, "我");

        mTabs.add(mTabWeChat);
        mTabs.add(mTabFriend);
        mTabs.add(mTabFind);
        mTabs.add(mTabMy);

        setCurrentTab(mCurrentTabPos);

    }

    /**
     * 选中当前Tab
     *
     * @param pos
     */
    private void setCurrentTab(int pos) {

        for (int i = 0; i < mTabs.size(); i++) {

            TabView tabView = mTabs.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            } else {
                tabView.setProgress(0);
            }
        }
    }
}
