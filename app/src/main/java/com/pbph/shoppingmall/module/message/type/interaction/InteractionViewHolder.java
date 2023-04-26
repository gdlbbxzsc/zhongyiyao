package com.pbph.shoppingmall.module.message.type.interaction;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class InteractionViewHolder extends ViewHolder {


    private ImageView ivHudongImg;
    private TextView tvHudongName;
    private TextView tvHudongDesc;
    private TextView tvHudongDate;


    @Override
    protected int getLayout() {
        return R.layout.listview_messagetype_interaction;
    }

    @Override
    protected void getView(View view) {

        ivHudongImg = view.findViewById(R.id.iv_hudong_img);
        tvHudongName = view.findViewById(R.id.tv_hudong_name);
        tvHudongDesc = view.findViewById(R.id.tv_hudong_desc);
        tvHudongDate = view.findViewById(R.id.tv_hudong_date);
    }

    @Override
    protected void showView() {
        ivHudongImg.setImageResource(R.drawable.b1banner);
    }
}
