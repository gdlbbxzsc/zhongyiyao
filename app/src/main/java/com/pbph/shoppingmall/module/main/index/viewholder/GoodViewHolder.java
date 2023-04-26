package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.IndexDataAdapter;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;


public class GoodViewHolder extends ViewHolder {


    private TextView tvGoodTitle1;
    private TextView tvGoodTitle2;
    private ImageView ivIndexGoods1;


    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_good;
    }

    @Override
    protected void getView(View view) {
        tvGoodTitle1 = view.findViewById(R.id.tv_good_title1);
        tvGoodTitle2 = view.findViewById(R.id.tv_good_title2);
        ivIndexGoods1 = view.findViewById(R.id.iv_index_goods1);

        IndexDataAdapter myAdapter = (IndexDataAdapter) adapter;


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvGoodTitle1.getLayoutParams();
        params.width = myAdapter.good_img_wh;
        tvGoodTitle1.setLayoutParams(params);
        params = (LinearLayout.LayoutParams) tvGoodTitle2.getLayoutParams();
        params.width = myAdapter.good_img_wh;
        tvGoodTitle2.setLayoutParams(params);


        params = (LinearLayout.LayoutParams) ivIndexGoods1.getLayoutParams();
        params.width = myAdapter.good_img_wh;
        params.height = myAdapter.good_img_wh;
        ivIndexGoods1.setLayoutParams(params);
    }

    @Override
    protected void showView() {
        Glide.with(adapter.context).load("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg").into(ivIndexGoods1);

    }
}
