package com.androidshop.model;

/**
 * Created by ${Terry} on 2018/2/2.
 */
public class UpdateModel {

    /**
     * status : 0
     * msg : 修改密码成功
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
