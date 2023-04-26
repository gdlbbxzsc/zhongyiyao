package com.pbph.shoppingmall.module.payment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.PayMethodResponse;
import com.pbph.shoppingmall.module.orders.myorders.index.MyOrdersActivity;
import com.pbph.shoppingmall.module.payment.payresult.PayResultActivity;
import com.pbph.shoppingmall.utils.dialog.PaymentYesNoDialog;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends BaseActivity<Presenter> implements Contract.View, PayMethodAdapter.OnMethodItemClickListener {

    //标题栏
    CommonTitlebar commonTitlebar;

    private TextView tvPay, tvTotolPrice;
    private RecyclerView rvPayMethod;
    private PayMethodAdapter payMethodAdapter;
    List<PayMethodResponse.DataBean> payMethodBeans;
    private int payId, orderType;
    private int payType;
    private String orderId;
    private String orderCode;
    private int totolPrice;

    @Override
    protected int layoutResID() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initData() {
        totolPrice = getIntent().getExtras().getInt("totolPrice");
        orderId = getIntent().getExtras().getString("orderId");
        orderCode = getIntent().getExtras().getString("orderCode");
        orderType = getIntent().getExtras().getInt("orderType");
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "支付中心", false);
        commonTitlebar.titlebar_left.setOnClickListener(view -> {
            onBackPressed();
        });
        tvPay = findViewById(R.id.tv_pay);
        tvPay.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
//                    Intent intent = new Intent(PaymentActivity.this, PayResultActivity.class);
//                    intent.putExtra("result",1);//0 成功  1失败
//                    startActivity(intent);


//                1支付宝，2银联，3公众账号 ，5微信
//                类型（1：支付宝，2：微信）
                presenter.payOrder(orderCode, orderType, payId, payType);
 

            }
        });

        rvPayMethod = findViewById(R.id.rv_pay_method);
        tvTotolPrice = findViewById(R.id.tv_totol_price);
        tvTotolPrice.setText(StringUtils.moneyFen2Yuan(totolPrice));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPayMethod.setLayoutManager(layoutManager);
        payMethodBeans = new ArrayList<>();
        payMethodAdapter = new PayMethodAdapter(this, payMethodBeans);
        payMethodAdapter.setOnMethodItemClickListener(this);
        rvPayMethod.setAdapter(payMethodAdapter);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        PaymentYesNoDialog.show(context, "确定离开?", "您的订单在23小时59分钟内未支付将被取消,请尽快完成支付", 0, position -> finish());
    }


    @Override
    public void initPayMethod(List<PayMethodResponse.DataBean> payMethodBeans) {
        for (PayMethodResponse.DataBean bean : payMethodBeans) {
            bean.setChecked(false);
        }
        payMethodBeans.get(0).setChecked(true);
        payId = payMethodBeans.get(0).getPpid();//默认支付方式
        payType = payMethodBeans.get(0).getPayType();
        this.payMethodBeans.clear();
        this.payMethodBeans.addAll(payMethodBeans);
        payMethodAdapter.notifyDataSetChanged();
    }

    @Override
    public void payResult(boolean payResult) {

        if (payResult) {
            tvPay.setClickable(false);

            startActivity(new Intent(getContext(), MyOrdersActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtra("message_type", MyOrdersActivity.MESSAGE_TYPE_ALL));

            finish();
        } else {
            startActivity(new Intent(context, PayResultActivity.class)
                    .putExtra("succ", payResult)
                    .putExtra("money", totolPrice)
            );
        }
    }

    @Override
    public void onPayMethodItemClick(View view, int position) {
        if (payMethodBeans == null || payMethodBeans.size() == 0) {
            return;
        }
        for (PayMethodResponse.DataBean bean : payMethodBeans) {
            bean.setChecked(false);
        }
        payMethodBeans.get(position).setChecked(true);
        payId = payMethodBeans.get(position).getPpid();
        payType = payMethodBeans.get(position).getPayType();
        payMethodAdapter.notifyDataSetChanged();
    }
}
