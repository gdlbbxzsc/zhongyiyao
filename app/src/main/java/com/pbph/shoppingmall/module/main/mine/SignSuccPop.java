package com.pbph.shoppingmall.module.main.mine;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public class SignSuccPop extends PopupWindow {


    private LayoutInflater inflater;


    View showView;

    public SignSuccPop(Context context) {
        this(context, null);
    }

    public SignSuccPop(Context context, View showView) {
        super(context);

        inflater = LayoutInflater.from(context);

        setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体的宽
        // MainActivity.screenWidth / 4
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        View view = inflater.inflate(R.layout.pop_signsucc, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);

        view.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                dismiss();
            }
        });

        // 刷新状态
        this.update();

        this.showView = showView;

    }

    public void show() {
        show(showView);
    }

    public void show(View view) {
        this.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
    }


}
