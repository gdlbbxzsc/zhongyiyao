package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.poupup;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/20.
 */

public class CouponPopup extends PopupWindow {
    private LayoutInflater inflater;
    private ListView list_view_coupon;
    private CouponAdapter couponAdapter;
    View showView;

    private List<CouponBean> couponBeans;


    public CouponPopup(Context context, View showView, List<CouponBean> couponBeans) {
        super(context);
        inflater = LayoutInflater.from(context);
        setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体的宽
        // MainActivity.screenWidth / 4
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        View view = inflater.inflate(R.layout.pop_list_coupon, null);
        list_view_coupon = view.findViewById(R.id.list_view_coupon);
        couponAdapter = new CouponAdapter(context);
        couponAdapter.setCouponBeanList(couponBeans);
        list_view_coupon.setAdapter(couponAdapter);
        list_view_coupon.setOnItemClickListener((parent, view1, position, id) -> {

        });
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
        this.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

}
