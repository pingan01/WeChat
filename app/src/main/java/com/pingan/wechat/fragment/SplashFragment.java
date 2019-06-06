package com.pingan.wechat.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pingan.wechat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private ImageView mIvContent;

    private int mResId;
    private static final String BUNDLE_KEY_RES_ID = "bundle_key_res_id";

    public static SplashFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_RES_ID, resId);
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mResId = arguments.getInt(BUNDLE_KEY_RES_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIvContent = view.findViewById(R.id.iv_content);

        mIvContent.setImageResource(mResId);

        mIvContent.setTag(mResId);
    }

}
