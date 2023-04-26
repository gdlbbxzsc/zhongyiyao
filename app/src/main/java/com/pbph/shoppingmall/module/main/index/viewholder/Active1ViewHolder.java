package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.IndexDataAdapter;
import com.pbph.shoppingmall.utils.LongTime2HMS;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;
import com.pbph.shoppingmall.utils.ui.LinearGradientTextView;
import com.utils.DateUtils;


public class Active1ViewHolder extends ViewHolder implements IndexDataAdapter.OnTimeChangeListener {


    private LinearGradientTextView tvActiveTitle;

    private TextView tvHh;
    private TextView tvMm;
    private TextView tvSs;

    private ImageView tvActiveImg1;
    private TextView tvActivePricenow1;
    private TextView tvActivePriceold1;

    private ImageView tvActiveImg2;
    private TextView tvActivePricenow2;
    private TextView tvActivePriceold2;

    private ImageView tvActiveImg3;
    private TextView tvActivePricenow3;
    private TextView tvActivePriceold3;


    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_active1;
    }

    @Override
    protected void getView(View view) {

        IndexDataAdapter myAdapter = (IndexDataAdapter) adapter;

        tvActiveTitle = view.findViewById(R.id.tv_active_title);

        tvHh = view.findViewById(R.id.tv_hh);
        tvMm = view.findViewById(R.id.tv_mm);
        tvSs = view.findViewById(R.id.tv_ss);

        tvActiveImg1 = view.findViewById(R.id.tv_active_img1);
        tvActivePricenow1 = view.findViewById(R.id.tv_active_pricenow1);
        tvActivePriceold1 = view.findViewById(R.id.tv_active_priceold1);

        tvActiveImg2 = view.findViewById(R.id.tv_active_img2);
        tvActivePricenow2 = view.findViewById(R.id.tv_active_pricenow2);
        tvActivePriceold2 = view.findViewById(R.id.tv_active_priceold2);

        tvActiveImg3 = view.findViewById(R.id.tv_active_img3);
        tvActivePricenow3 = view.findViewById(R.id.tv_active_pricenow3);
        tvActivePriceold3 = view.findViewById(R.id.tv_active_priceold3);


        int wh = myAdapter.active_img_wh / 3;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvActiveImg1.getLayoutParams();
        params.height = params.width = wh;
        tvActiveImg1.setLayoutParams(params);
        params = (RelativeLayout.LayoutParams) tvActiveImg2.getLayoutParams();
        params.height = params.width = wh;
        tvActiveImg2.setLayoutParams(params);
        params = (RelativeLayout.LayoutParams) tvActiveImg3.getLayoutParams();
        params.height = params.width = wh;
        tvActiveImg3.setLayoutParams(params);

        myAdapter.onTimeChangeListener = this;
    }

    @Override
    protected void showView() {
    }

    final long beginTime = new DateUtils().getLong();

    @Override
    public void onTimeChange(long passTime) {
        LongTime2HMS time = LongTime2HMS.longTime2HMS(beginTime - passTime);
        tvHh.setText(time.getH());
        tvMm.setText(time.getM());
        tvSs.setText(time.getS());
    }
}
