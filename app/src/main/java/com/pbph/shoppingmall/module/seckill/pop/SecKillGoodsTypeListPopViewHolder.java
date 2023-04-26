package com.pbph.shoppingmall.module.seckill.pop;

import android.view.View;
import android.widget.CheckBox;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class SecKillGoodsTypeListPopViewHolder extends ViewHolder {

    CheckBox tv;

    @Override
    protected int getLayout() {
        return R.layout.gridview_salespromotion_pop;
    }

    @Override
    protected void getView(View view) {
        tv = view.findViewById(R.id.tv_type);
    }

    @Override
    protected void showView() {
        tv.setChecked(adapter.isChoiced(position));
    }

}
