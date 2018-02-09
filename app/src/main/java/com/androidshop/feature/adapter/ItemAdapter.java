package com.androidshop.feature.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidshop.R;
import com.androidshop.base.BaseRecyclerAdapter;
import com.androidshop.base.BaseRecyclerViewHolder;
import com.androidshop.model.ItemModel;
import com.androidshop.utils.DensityUtil;
import com.androidshop.utils.StringUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${Terry} on 2018/2/7.
 */
public class ItemAdapter extends BaseRecyclerAdapter<ItemModel.DataEntity> {

    private int w, d;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void showData(BaseRecyclerViewHolder viewHolder, int position, List<ItemModel.DataEntity> mItemDataList) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        String uri = mItemDataList.get(position).getUri();
        Log.i("print", uri);
        if (!StringUtil.isEmpty(uri)) {
            if (!StringUtil.isEmpty(uri)) {
                Picasso.with(getContext())
                        .load(uri)
                        .placeholder(R.mipmap.newlogo_square)
                        .into(holder.itemImv);
            } else {
                holder.itemImv.setImageResource(R.mipmap.newlogo_square);
            }

        }
        holder.basePrice.setText(mItemDataList.get(position).getPrice());
        holder.itemName.setText(mItemDataList.get(position).getText());
    }

    @Override
    public int getListLayoutId() {
        return R.layout.item_list;
    }

    @Override
    public BaseRecyclerViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    class ViewHolder extends BaseRecyclerViewHolder {

        ImageView itemImv;
        TextView itemName, basePrice;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImv = (ImageView) findView(R.id.item_img);
            itemName = (TextView) findView(R.id.tv_name);
            basePrice = (TextView) findView(R.id.tv_price_discount);
            w = (int) DensityUtil.getDisplayWidthDp(mContext) / 2;
            d = DensityUtil.px2dip(mContext, 16);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) itemImv.getLayoutParams();
            params.width = w;
            params.height = w;
            itemImv.setLayoutParams(params);
        }
    }

}
