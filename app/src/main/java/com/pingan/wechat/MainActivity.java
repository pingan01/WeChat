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
import android.widget.Button;

import com.pingan.wechat.fragment.TabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private Button mBtnWeChat;
    private Button mBtnFriend;
    private Button mBtnFind;
    private Button mBtnMy;

    private List<String> titles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initViewPagerAdapter();
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
                if (i == 0) {
                    tabFragment.setOnTitleClickListener(new TabFragment.OnTitleClickListener() {
                        @Override
                        public void onTictleClick(String title) {
                            changeWeChatTab(title);
                        }
                    });

                }
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
        mBtnWeChat = findViewById(R.id.btn_wechat);
        mBtnFriend = findViewById(R.id.btn_friend);
        mBtnFind = findViewById(R.id.btn_find);
        mBtnMy = findViewById(R.id.btn_my);
        mBtnWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabFragment tabFragment = mFragments.get(0);
                if (tabFragment != null) {
                    tabFragment.changeTitle("这是修改的标题");
                }
            }
        });
    }

    /**
     * Fragment调用Anctivity方法--Fragment暴露对外接口 Activity监听
     *
     * @param title
     */
    public void changeWeChatTab(String title) {

        mBtnWeChat.setText(title);
    }
}
