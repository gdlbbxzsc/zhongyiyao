package com.pbph.shoppingmall.module.suggest;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class SuggestViewHolder extends ViewHolder {

    ImageView iv_add;

    @Override
    protected int getLayout() {
        return R.layout.gridview_suggest;
    }

    @Override
    protected void getView(View view) {
        SuggestDataAdapter myAdapter = (SuggestDataAdapter) adapter;
        iv_add = view.findViewById(R.id.iv_add);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) iv_add.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        iv_add.setLayoutParams(params);
    }

    @Override
    protected void showView() {
        String path = (String) item;
        Glide.with(adapter.context).load(path)
                .error(R.drawable.tianjiatupian)           //设置错误图片
                .placeholder(R.drawable.tianjiatupian)
                .into(iv_add);
    }

}
