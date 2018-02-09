package com.androidshop.network.req;

import com.androidshop.constant.Constant;

/**
 * Created by ${Terry} on 2018/2/7.
 */
public class PaginationReq {
    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private int limit = Constant.PAGE_LIMIT;
}
