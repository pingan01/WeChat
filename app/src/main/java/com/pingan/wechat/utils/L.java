package com.pingan.wechat.utils;

import android.util.Log;

import com.pingan.wechat.BuildConfig;

/**
 * Created time : 2019/6/5 17:57.
 *
 * @author LKKJ
 */
public class L {

    private static final String TAG = "LKKJ";

    private static boolean isDeBug = BuildConfig.DEBUG;

    public static void d(String msg, Object... args) {

        if (!isDeBug) {
            return;
        }

        Log.d(TAG, String.format(msg, args));
    }
}
