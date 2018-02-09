package com.androidshop.feature.frag;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.androidshop.R;
import com.androidshop.base.BaseFragment;
import com.androidshop.constant.Constant;
import com.androidshop.feature.adapter.ItemAdapter;
import com.androidshop.model.FilterModel;
import com.androidshop.model.ItemModel;
import com.androidshop.network.ApiImp;
import com.androidshop.network.req.PaginationReq;
import com.androidshop.utils.FilterSpUtil;
import com.androidshop.widget.recyclerview.MultiRecycleView;

import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2018/2/7.
 */
public class ItemFrag extends BaseFragment implements MultiRecycleView.OnMutilRecyclerViewListener {

    @BindView(R.id.recyclerView)
    MultiRecycleView recycleView;

    private boolean isFirst = true;//是否为第一次加载
    private ItemAdapter adapter;
    private int offset = 1;


    @Override
    protected int getViewId() {
        return R.layout.layout_item_frag;
    }

    @Override
    protected void init() {

        initViewController(recycleView);
        adapter = new ItemAdapter();
        recycleView.setAdapter(adapter);
        recycleView.setGridLayout(2);
        showLoading(true, "");
        recycleView.setOnMutilRecyclerViewListener(this);
        getConfig();
        getList();

    }

    private void getList() {
        PaginationReq req = new PaginationReq();
        req.setOffset(offset);
        req.setLimit(Constant.PAGE_LIMIT);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSONArray.toJSONString(req));
        ApiImp.get().config(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemModel>() {
                    @Override
                    public void onCompleted() {
                        if (isFirst) {
                            showLoading(false, "");
                            isFirst = false;
                        }
                        recycleView.stopLoadingMore();
                        recycleView.stopRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLoading(false, "");
                    }

                    @Override
                    public void onNext(ItemModel model) {
                        Log.i("print", JSONArray.toJSONString(model));
                        if (model.getStatus() == 0) {
                            if (offset == 1) {
                                if (isFirst) {
                                    showLoading(false, "");
                                    isFirst = false;
                                }
                                List<ItemModel.DataEntity> list = model.getData();
                                if (list == null || list.size() == 0) {
                                    showEmpty(true, "未找到相关单品", null);
                                    adapter.clear();
                                } else {
                                    adapter.resetItems(list);
                                }
                            } else {
                                adapter.addItems(model.getData());
                            }
                        }
                    }
                });
    }


    private void getConfig() {
        /**
         * 网络获取
         */
        Observable<FilterModel> obNet = ApiImp.get().filter();
        /**
         * 本地获取
         */
        Observable<FilterModel> obShare = Observable.create(new Observable.OnSubscribe<FilterModel>() {
            @Override
            public void call(Subscriber<? super FilterModel> subscriber) {
                subscriber.onNext(FilterSpUtil.getInfo(mContext));
                subscriber.onCompleted();
            }
        });

        //设置本地取不到 网络取
        Observable<FilterModel> observer = Observable.concat(obShare, obNet).first(new Func1<FilterModel, Boolean>() {
            @Override
            public Boolean call(FilterModel configModel) {
                return configModel != null;
            }
        });

        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FilterModel filterModel) {

                    }
                });
    }


    @Override
    public void onRefresh() {
        offset = 1;
        getList();
    }

    @Override
    public void onLoadMore() {
        offset = offset + 1;
        getList();
    }
}
