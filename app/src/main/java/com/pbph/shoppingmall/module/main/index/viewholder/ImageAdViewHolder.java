package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAppDefaultTemplateResponse;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;


public class ImageAdViewHolder extends ViewHolder {

    ImageView iv;


    @Override
    protected int getLayout() {
        return R.layout.listview_index_img2;
    }

    @Override
    protected void getView(View view) {
        iv = view.findViewById(R.id.iv_index_img2);
    }

    @Override
    protected void showView() {
        GetAppDefaultTemplateResponse.ItemData vo = (GetAppDefaultTemplateResponse.ItemData) item;
        Glide.with(adapter.context).load(vo.getImg()).into(iv);

//        iv.setImageResource(res);
    }
}
