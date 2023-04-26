package com.pbph.shoppingmall.module.input.choose;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;


public class ChooseViewHolder extends ViewHolder {


    private TextView tvChooseText;
    private ImageView ivChooseState;


    @Override
    protected int getLayout() {
        return R.layout.listview_input_choose;
    }

    @Override
    protected void getView(View view) {
        tvChooseText = view.findViewById(R.id.tv_choose_text);
        ivChooseState = view.findViewById(R.id.iv_choose_state);
    }

    @Override
    protected void showView() {
        ChooseDataVo vo = (ChooseDataVo) item;

        tvChooseText.setText(vo.text);
        ivChooseState.setVisibility(adapter.isChoiced(position) ? View.VISIBLE : View.INVISIBLE);
    }

}
