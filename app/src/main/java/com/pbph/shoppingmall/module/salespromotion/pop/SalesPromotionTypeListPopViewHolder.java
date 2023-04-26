package com.pbph.shoppingmall.module.salespromotion.pop;

import android.view.View;
import android.widget.CheckBox;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class SalesPromotionTypeListPopViewHolder extends ViewHolder {

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
        String vo = (String) item;
        tv.setText(vo);
        tv.setChecked(adapter.isChoiced(position));
    }

}
