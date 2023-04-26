package com.pbph.shoppingmall.module.coupon.search;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;


public class CouponSearchViewHolder extends ViewHolder<GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean> {


    private ImageView ivCoupon;
    private TextView tvCouponName;
    private TextView tvCouponContent;
    private FrameLayout frameLayout;
    private ImageView ivCouponHad;
    private TextView tvCouponMoney;
    private TextView btnCoupon;


    @Override
    protected int getLayout() {
        return R.layout.listview_couponindex;
    }

    @Override
    protected void getView(View view) {
        ivCoupon = view.findViewById(R.id.iv_coupon);
        tvCouponName = view.findViewById(R.id.tv_coupon_name);
        tvCouponContent = view.findViewById(R.id.tv_coupon_content);
        frameLayout = view.findViewById(R.id.FrameLayout);
        ivCouponHad = view.findViewById(R.id.iv_coupon_had);
        tvCouponMoney = view.findViewById(R.id.tv_coupon_money);

        btnCoupon = view.findViewById(R.id.btn_coupon);
        btnCoupon.setOnClickListener(listener);
    }

    @Override
    protected void showView() {
        Glide.with(adapter.context).load(item.getCouponPic())
//                .error(R.drawable.tianjiatupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiatupian)
                .into(ivCoupon);

        tvCouponName.setText(item.getCouponActivityName());
        tvCouponContent.setText("满");
        tvCouponContent.append(StringUtils.moneyFen2Yuan(item.getCouponXPrice()));
        tvCouponContent.append("元可用");

        ivCouponHad.setVisibility(item.getGetStatus() == 0 ? View.INVISIBLE : View.VISIBLE);

        tvCouponMoney.setText(StringUtils.moneyFen2Yuan(item.getCouponPrice()));

        btnCoupon.setText(item.getGetStatus() == 0 ? "立即领取" : "去使用");
        btnCoupon.setBackgroundResource(item.getGetStatus() == 0 ? R.drawable.lijishiyong : R.drawable.qushiyong);
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {

//            item.setGetStatus(1);
//            btnCoupon.setText("去使用");
//            btnCoupon.setBackgroundResource(R.drawable.qushiyong);
//            ivCouponHad.setVisibility(View.VISIBLE);

            adapter.getListener().onItemViewClick(v.getId(), CouponSearchViewHolder.this);
        }
    };
}
