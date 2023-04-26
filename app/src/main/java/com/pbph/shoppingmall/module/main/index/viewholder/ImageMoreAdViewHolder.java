package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;


public class ImageMoreAdViewHolder extends ViewHolder {

    ImageView iv;


    @Override
    protected int getLayout() {
        return R.layout.listview_index_img3;
    }

    @Override
    protected void getView(View view) {
        iv = view.findViewById(R.id.iv_index_img3);
    }

    @Override
    protected void showView() {
//        Glide.with(adapter.context).load("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg").into(iv);
        int res = (int) item;
        iv.setImageResource(res);
    }
}
