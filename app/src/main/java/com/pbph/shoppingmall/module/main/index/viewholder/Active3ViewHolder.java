package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.IndexDataAdapter;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;
import com.pbph.shoppingmall.utils.ui.LinearGradientTextView;


public class Active3ViewHolder extends ViewHolder {

    private LinearGradientTextView tvActiveTitle1;
    private TextView tvActiveContent1;
    private ImageView tvActiveImg1;
    private LinearGradientTextView tvActiveTitle2;
    private TextView tvActiveContent2;
    private ImageView tvActiveImg2;


    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_active3;
    }

    @Override
    protected void getView(View view) {

        tvActiveTitle1 = view.findViewById(R.id.tv_active_title1);
        tvActiveContent1 = view.findViewById(R.id.tv_active_content1);
        tvActiveImg1 = view.findViewById(R.id.tv_active_img1);
        tvActiveTitle2 = view.findViewById(R.id.tv_active_title2);
        tvActiveContent2 = view.findViewById(R.id.tv_active_content2);
        tvActiveImg2 = view.findViewById(R.id.tv_active_img2);


        IndexDataAdapter myAdapter = (IndexDataAdapter) adapter;
        int wh = myAdapter.active_img_wh / 2;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvActiveImg1.getLayoutParams();
        params.height = params.width = wh;
        tvActiveImg1.setLayoutParams(params);
        params = (LinearLayout.LayoutParams) tvActiveImg2.getLayoutParams();
        params.height = params.width = wh;
        tvActiveImg2.setLayoutParams(params);
    }

    @Override
    protected void showView() {
    }
}
