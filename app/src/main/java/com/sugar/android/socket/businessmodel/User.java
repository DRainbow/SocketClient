package com.sugar.android.socket.businessmodel;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description:
 * @author: SugarT
 * @date: 16/8/8 下午3:41
 */
public class User implements Serializable {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
