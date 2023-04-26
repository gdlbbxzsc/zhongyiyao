package com.pbph.shoppingmall.module.shoppingcar.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public class ShopViewHolder extends ViewHolder {

    CheckBox getIsShopChecked;
    TextView tv_shop;

    @Override

    protected int getLayout() {
        return R.layout.item_retail_parent;
    }

    @Override
    protected void getView(View view) {

        getIsShopChecked = view.findViewById(R.id.is_shop_checked);
        getIsShopChecked.setOnClickListener(listener);
        
        tv_shop = view.findViewById(R.id.tv_shop);
    }

    @Override
    protected void showView() {
        ShoppingCarResponse.DataBean.ListBean vo = (ShoppingCarResponse.DataBean.ListBean) item;

        getIsShopChecked.setChecked(vo.storeCheck);

        tv_shop.setText(vo.getStoreName());
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            adapter.getListener().onItemViewClick(v.getId(), ShopViewHolder.this);
        }
    };
}
