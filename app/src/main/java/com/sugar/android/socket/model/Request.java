package com.sugar.android.socket.model;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Request
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午4:31
 */
public class Request implements Serializable {

    private String method;

    private List<Object> params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}
