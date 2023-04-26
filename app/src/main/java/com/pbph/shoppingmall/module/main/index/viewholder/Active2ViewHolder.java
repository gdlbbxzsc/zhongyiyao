package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.IndexDataAdapter;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;
import com.pbph.shoppingmall.utils.ui.LinearGradientTextView;


public class Active2ViewHolder extends ViewHolder {


    private LinearGradientTextView tvActiveTitle;
    private TextView tvActiveContent;

    private ImageView tvActiveImg1;
    private ImageView tvActiveImg2;


    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_active2;
    }

    @Override
    protected void getView(View view) {
        tvActiveTitle = view.findViewById(R.id.tv_active_title);
        tvActiveContent = view.findViewById(R.id.tv_active_content);
        tvActiveImg1 = view.findViewById(R.id.tv_active_img1);
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
