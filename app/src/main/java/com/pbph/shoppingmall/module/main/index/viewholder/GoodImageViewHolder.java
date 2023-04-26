package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAppDefaultTemplateResponse;
import com.pbph.shoppingmall.module.main.index.IndexDataAdapter;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;


public class GoodImageViewHolder extends ViewHolder {


    private ImageView ivIndexGoods1;


    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_goodimage;
    }

    @Override
    protected void getView(View view) {

        ivIndexGoods1 = view.findViewById(R.id.iv_index_goods1);

        IndexDataAdapter myAdapter = (IndexDataAdapter) adapter;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivIndexGoods1.getLayoutParams();
        params.width = myAdapter.good_img_w;
        params.height = myAdapter.good_img_h;
        ivIndexGoods1.setLayoutParams(params);
    }

    @Override
    protected void showView() {
        GetAppDefaultTemplateResponse.ItemData vo = (GetAppDefaultTemplateResponse.ItemData) item;
        Glide.with(adapter.context).load(vo.getImg()).into(ivIndexGoods1);
    }
}
