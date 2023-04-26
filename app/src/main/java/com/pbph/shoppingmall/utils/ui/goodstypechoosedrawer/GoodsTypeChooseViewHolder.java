package com.pbph.shoppingmall.utils.ui.goodstypechoosedrawer;

import android.view.View;
import android.widget.CheckBox;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class GoodsTypeChooseViewHolder extends ViewHolder {

    CheckBox tv;

    @Override
    protected int getLayout() {
        return R.layout.gridview_goodstypechoosedrawer;
    }

    @Override
    protected void getView(View view) {
        tv = view.findViewById(R.id.tv_type);
    }

    @Override
    protected void showView() {

        tv.setText((String) item);
        tv.setChecked(adapter.isChoiced(position));
    }

}
