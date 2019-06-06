package com.pingan.wechat.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pingan.wechat.R;
import com.pingan.wechat.utils.L;

public class TabFragment extends Fragment {

    private TextView mTextView;

    private String mTitle;

    public static final String BUNDLE_KEY_TITLE = "key_title";

    public static interface OnTitleClickListener {
        void onTictleClick(String title);
    }

    private OnTitleClickListener mOnTitleClickListener;

    /**
     * 对外接口公布的方法
     *
     * @param onTitleClickListener
     */
    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {

        mOnTitleClickListener = onTitleClickListener;
    }


    public static TabFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE, title);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) ;
        {
            mTitle = arguments.getString(BUNDLE_KEY_TITLE, "");
        }

        L.d("onCreate,title=" + mTitle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        L.d("onCreateView,title=" + mTitle);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = view.findViewById(R.id.txt_title);
        mTextView.setText(mTitle);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //被点击--用户点击了 Antivity监听知道
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onTictleClick("微信tab改变");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("onDestroyView,title=" + mTitle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d("onDestroy,title=" + mTitle);
    }

    /**
     * Activity调用Fragment中方法
     *
     * @param title
     */
    public void changeTitle(String title) {

        if (!isAdded()) {
            return;
        }
        mTextView.setText(title);

    }
}
