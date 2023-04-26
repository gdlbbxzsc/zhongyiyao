package com.pbph.shoppingmall.module.coupon.mine.invalid;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SelectMyCouponListResponse;
import com.pbph.shoppingmall.module.coupon.mine.adapter.CouponListDataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.DateUtils;
import com.utils.StringUtils;


public class CouponInvalidViewHolder extends ViewHolder {

    private CheckBox cb_coupon;
    private View ViewRight;
    RelativeLayout.LayoutParams params;

    private Button btnCoupon;

    private TextView tvCouponPrice;
    private TextView tvCouponPriceOld;
    private TextView tvCouponName;
    private TextView tvCouponContent;

    @Override
    protected int getLayout() {
        return R.layout.listview_couponinvalid1;
    }

    @Override
    protected void getView(View view) {

        cb_coupon = view.findViewById(R.id.cb_coupon);
        ViewRight = view.findViewById(R.id.ViewRight);
        params = (RelativeLayout.LayoutParams) ViewRight.getLayoutParams();


        tvCouponPrice = (TextView) view.findViewById(R.id.tv_coupon_price);
        tvCouponPriceOld = (TextView) view.findViewById(R.id.tv_coupon_price_old);


        tvCouponName = view.findViewById(R.id.tv_coupon_name);
        tvCouponContent = view.findViewById(R.id.tv_coupon_content);


        btnCoupon = view.findViewById(R.id.btn_coupon);
        btnCoupon.setOnClickListener(listener);
    }

    @Override
    protected void showView() {
        SelectMyCouponListResponse.DataBean.CouponListBean.ListBean vo = (SelectMyCouponListResponse.DataBean.CouponListBean.ListBean) item;

        CouponListDataAdapter cadapter = (CouponListDataAdapter) adapter;
        if (cadapter.isEdit()) {
            cb_coupon.setVisibility(View.VISIBLE);

            params.rightMargin = cadapter.marginRight;
            ViewRight.setLayoutParams(params);

            cb_coupon.setChecked(adapter.isChoiced(position));
        } else {
            cb_coupon.setVisibility(View.GONE);

            params.rightMargin = 0;
            ViewRight.setLayoutParams(params);
        }


        tvCouponName.setText(vo.getCouponName());

        tvCouponPrice.setText(StringUtils.moneyFen2Yuan(vo.getCouponPrice()));

        tvCouponPriceOld.setText("满");
        tvCouponPriceOld.append(StringUtils.moneyFen2Yuan(vo.getCouponXPrice()));
        tvCouponPriceOld.append("元可用");

        DateUtils dateUtilsStart=new DateUtils(vo.getCouponStartTime());
        DateUtils dateUtilsEnd=new DateUtils(vo.getCouponEndTime());
        tvCouponContent.setText("有效期");
        tvCouponContent.append(dateUtilsStart.getString(DateUtils.PATTERN_3));
        tvCouponContent.append("-");
        tvCouponContent.append(dateUtilsEnd.getString(DateUtils.PATTERN_3));



    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            Toast.makeText(adapter.context, "invalid", Toast.LENGTH_SHORT).show();
        }
    };
}
