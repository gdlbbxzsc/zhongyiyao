package com.pbph.shoppingmall.module.main.index.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.vo.MenuVo;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;


public class MenuViewHolder extends ViewHolder {

    TextView button;


    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_menu;
    }

    @Override
    protected void getView(View view) {
        button = view.findViewById(R.id.button1);
    }

    @Override
    protected void showView() {
        MenuVo vo = (MenuVo) item;

        button.setTag(vo.id);
        button.setText(vo.text);

        Drawable d = adapter.context.getResources().getDrawable(vo.img);
        button.setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
//        button.setCompoundDrawablePadding(15);
    }
}
