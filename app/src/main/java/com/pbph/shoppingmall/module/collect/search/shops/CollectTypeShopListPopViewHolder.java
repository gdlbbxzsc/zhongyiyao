package com.pbph.shoppingmall.module.collect.search.shops;

import android.view.View;
import android.widget.CheckBox;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetGoodsCategoryListForFollowResponse;
import com.pbph.shoppingmall.model.response.GetStoreCategoryListForCollectionResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class CollectTypeShopListPopViewHolder extends ViewHolder<GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean> {

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
        tv.setText(item.getCatName());
        tv.setChecked(adapter.isChoiced(position));
    }

}
