package com.pbph.shoppingmall.module.seckill.index.brand.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.salespromotion.fragment.SalesPromotionDataAdapter;
import com.pbph.shoppingmall.module.seckill.index.TimeChangeDataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.LongTime2HMS;


public class SecKillIndexBrandItemCardViewHolder extends ViewHolder implements TimeChangeDataAdapter.OnTimeChangeListener {


    private TextView tv_brand_name;

    private TextView tvHh;
    private TextView tvMm;
    private TextView tvSs;

    private ImageView iv_category_banner;
    private ImageView ivCategoryAd1;
    private ImageView ivCategoryAd2;
    private ImageView ivCategoryAd3;


    @Override
    protected int getLayout() {
        return R.layout.listview_seckillindex_branditemcard;
    }

    @Override
    protected void getView(View view) {

        tv_brand_name = view.findViewById(R.id.tv_brand_name);


        tvHh = view.findViewById(R.id.tv_hh);
        tvMm = view.findViewById(R.id.tv_mm);
        tvSs = view.findViewById(R.id.tv_ss);


        iv_category_banner = view.findViewById(R.id.iv_category_banner);
        ivCategoryAd1 = view.findViewById(R.id.iv_category_ad1);
        ivCategoryAd2 = view.findViewById(R.id.iv_category_ad2);
        ivCategoryAd3 = view.findViewById(R.id.iv_category_ad3);


        SecKillIndexBrandItemCardDataAdapter myAdapter = (SecKillIndexBrandItemCardDataAdapter) adapter;
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) iv_category_banner.getLayoutParams();
        params1.height = myAdapter.h;
        iv_category_banner.setLayoutParams(params1);


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivCategoryAd1.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivCategoryAd1.setLayoutParams(params);

        params = (LinearLayout.LayoutParams) ivCategoryAd2.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivCategoryAd2.setLayoutParams(params);

        params = (LinearLayout.LayoutParams) ivCategoryAd3.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivCategoryAd3.setLayoutParams(params);
    }

    @Override
    protected void showView() {
    }

    final long btime = 3600000;

    @Override
    public void onTimeChange(long passTime) {
        LongTime2HMS time = LongTime2HMS.longTime2HMS(btime - passTime);

        tvHh.setText(time.getH());
        tvMm.setText(time.getM());
        tvSs.setText(time.getS());
    }
}
