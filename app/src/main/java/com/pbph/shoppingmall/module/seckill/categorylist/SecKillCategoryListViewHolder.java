package com.pbph.shoppingmall.module.seckill.categorylist;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class SecKillCategoryListViewHolder extends ViewHolder {

    private ImageView ivPic;

    private TextView tvName;


    private TextView tvPriceNew;
    private TextView tvPriceOld;

    private TextView tvNum;
    private ProgressBar pbNum;

    private TextView tvBtn;


    @Override
    protected int getLayout() {
        return R.layout.listview_seckill_categorylist_itemcard;
    }

    @Override
    protected void getView(View view) {

        ivPic = view.findViewById(R.id.iv_pic);
        tvName = view.findViewById(R.id.tv_name);

        tvPriceNew = view.findViewById(R.id.tv_price_new);
        tvPriceOld = view.findViewById(R.id.tv_price_old);
        tvNum = view.findViewById(R.id.tv_num);
        pbNum = view.findViewById(R.id.pb_num);
        tvBtn = view.findViewById(R.id.tv_btn);


        tvPriceOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
    }

    @Override
    protected void showView() {
    }

}
