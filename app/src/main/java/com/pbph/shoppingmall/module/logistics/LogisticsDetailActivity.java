package com.pbph.shoppingmall.module.logistics;

import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetLogisticsInformationResponse;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

import java.util.List;

public class LogisticsDetailActivity extends BaseActivity<Presenter> implements Contract.View {


    CommonTitlebar commonTitlebar;
    private DataAdapter adapter;
    private TextView tv_order_code;
    String orderId;
    String orderCode;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_logisticsdetail;
    }


    @Override
    protected void initData() {
        orderId = getIntent().getStringExtra("orderId");
        orderCode = getIntent().getStringExtra("orderCode");
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "物流详情", true);

        ListView listView = findViewById(R.id.lv_logistics);
        tv_order_code = findViewById(R.id.tv_order_code);
        tv_order_code.setText(orderCode);
        adapter = new DataAdapter(this, listView, LogisticsDetailViewHolder.class);
        listView.setAdapter(adapter);
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void setHttpDatas(List<GetLogisticsInformationResponse.DataBean.LogisticsListBean> list, String orderCode) {

        if (!TextUtils.isEmpty(orderCode)) {
            tv_order_code.setText(orderCode);
        }

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

}
