package com.pbph.shoppingmall.myview.scpopwindow;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.PayMethodResponse;
import com.pbph.shoppingmall.module.coupon.mine.adapter.CouponListDataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public class SCPopViewHolder extends ViewHolder {

    private TextView tv;
    @Override
    protected int getLayout() {
        return R.layout.item_sc_pop_window;
    }

    @Override
    protected void getView(View view) {
        tv = view.findViewById(R.id.tv);
    }

    @Override
    protected void showView() {
        PayMethodResponse.DataBean vo = (PayMethodResponse.DataBean) item;
        tv.setText(vo.getPayName());
    }
}
