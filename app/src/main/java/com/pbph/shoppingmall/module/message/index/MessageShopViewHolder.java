package com.pbph.shoppingmall.module.message.index;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class MessageShopViewHolder extends ViewHolder {


    private ImageView ivShopmessage;
    private TextView tv_shopmessage_num;

    private TextView tvShopmessageName;
    private TextView tvShopmessageDesc;
    private TextView tvShopmessageDate;


    @Override
    protected int getLayout() {
        return R.layout.listview_messageindex_shop;
    }

    @Override
    protected void getView(View view) {
        ivShopmessage = view.findViewById(R.id.iv_shopmessage);
        tv_shopmessage_num = view.findViewById(R.id.tv_shopmessage_num);

        tvShopmessageName = view.findViewById(R.id.tv_shopmessage_name);
        tvShopmessageDesc = view.findViewById(R.id.tv_shopmessage_desc);
        tvShopmessageDate = view.findViewById(R.id.tv_shopmessage_date);
    }

    @Override
    protected void showView() {
        tv_shopmessage_num.setVisibility(position % 2 == 0 ? View.VISIBLE : View.INVISIBLE);
    }

}
