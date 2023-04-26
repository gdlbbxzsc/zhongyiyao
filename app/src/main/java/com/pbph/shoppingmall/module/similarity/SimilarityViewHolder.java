package com.pbph.shoppingmall.module.similarity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class SimilarityViewHolder extends ViewHolder {

    private ImageView ivPic;
    private TextView tvName;

    private LinearLayout llType;
    private TextView tvZy;
    private TextView tvHd;

    private LinearLayout llMoney;
    private TextView tvPrice;
    private TextView ivCoupon;

    private TextView tvContent;

    @Override
    protected int getLayout() {
        return R.layout.gridview_similarity;
    }

    @Override
    protected void getView(View view) {
        ivPic = view.findViewById(R.id.iv_pic);
        tvName = view.findViewById(R.id.tv_name);
        llType = view.findViewById(R.id.ll_type);
        tvZy = view.findViewById(R.id.tv_zy);
        tvHd = view.findViewById(R.id.tv_hd);
        llMoney = view.findViewById(R.id.ll_money);
        tvPrice = view.findViewById(R.id.tv_price);
        ivCoupon = view.findViewById(R.id.iv_coupon);
        tvContent = view.findViewById(R.id.tv_content);


        SimilarityDataAdapter myAdapter = (SimilarityDataAdapter) adapter;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivPic.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivPic.setLayoutParams(params);
    }

    @Override
    protected void showView() {

    }

}
