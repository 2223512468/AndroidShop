package com.androidshop.model;

/**
 * Created by ${Terry} on 2018/2/1.
 */
public class RegisterModel {


    /**
     * status : 0
     * msg : 注册成功
     */

    private int status;
    private String msg;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
