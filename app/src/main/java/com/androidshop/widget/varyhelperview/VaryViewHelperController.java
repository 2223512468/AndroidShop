/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidshop.widget.varyhelperview;

import android.view.View;
import android.widget.TextView;

import com.androidshop.R;
import com.androidshop.utils.StringUtil;


public class VaryViewHelperController {

    private final String EMPTY_MESSAGE = "不好意思，未找到相关数据";
    private final String ERROR_MESSAGE = "网络出小差了";
    private IVaryViewHelper helper;

    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message);
        TextView textView = (TextView) layout.findViewById(R.id.message);
        if (!StringUtil.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        } else {
            textView.setText(R.string.data_error);
        }
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    /**
     * 内容为空的时候
     *
     * @param emptyMsg                   ：内容为空的提示语
     * @param onClickListener：监听点击加载为空界面
     */
    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message);
        TextView textView = (TextView) layout.findViewById(R.id.message);
        if (!StringUtil.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText(EMPTY_MESSAGE);
        }
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    /**
     * 内容加载时用于显示加载过程
     *
     * @param msg ：显示的文字
     */
    public void showLoading(String msg) {
        View layout = helper.inflate(R.layout.loading);
        if (!StringUtil.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(R.id.loading_msg);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }


    public void restore() {
        helper.restoreView();
    }
}
