package com.pbph.shoppingmall.module.shopstreet.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreStreetResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class ShopsViewHolder extends ViewHolder<GetStoreStreetResponse.DataBean.ListBean> {


    private ImageView ivShopstreetShops;
    private TextView tvShopstreetShopsName;

    private TextView tvShopstreetShopsDescText;

    private TextView tvShopstreetShopsEnter;


    @Override
    protected int getLayout() {
        return R.layout.listview_shopstreet_shop;
    }

    @Override
    protected void getView(View view) {
        ivShopstreetShops = view.findViewById(R.id.iv_shopstreet_shops);
        tvShopstreetShopsName = view.findViewById(R.id.tv_shopstreet_shops_name);
        tvShopstreetShopsDescText = view.findViewById(R.id.tv_shopstreet_shops_desc_text);
        tvShopstreetShopsEnter = view.findViewById(R.id.tv_shopstreet_shops_enter);


        ShopsDataAdapter myAdapter = (ShopsDataAdapter) adapter;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivShopstreetShops.getLayoutParams();
        params.height = myAdapter.h;
        ivShopstreetShops.setLayoutParams(params);
    }

    @Override
    protected void showView() {
        Glide.with(adapter.context).load(item.getStoreStreetMobliePath())
//                .error(R.drawable.tianjiatupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiatupian)
                .into(ivShopstreetShops);

        tvShopstreetShopsName.setText(item.getStoreStreetName());
        tvShopstreetShopsDescText.setText(String.valueOf(item.getGoodsNum()));
    }
}
