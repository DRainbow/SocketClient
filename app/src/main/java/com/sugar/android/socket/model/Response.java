package com.sugar.android.socket.model;

import java.io.Serializable;

/**
 * @ClassName: Response
 * @Description:
 * @author: SugarT
 * @date: 16/8/9 下午1:34
 */
public class Response implements Serializable {

    public int code;

    public String message;

    public String data;
}
