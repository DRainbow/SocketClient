package com.sugar.android.socket.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName: Constant
 * @Description:
 * @author: SugarT
 * @date: 16/8/10 下午6:25
 */
public class Constant {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    @IntDef({SUCCESS, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResponseCode {
    }

    public static final String HEADER_API_VERSION = "api_version";
}