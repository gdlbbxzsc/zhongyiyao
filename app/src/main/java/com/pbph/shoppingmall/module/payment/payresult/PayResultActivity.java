package com.pbph.shoppingmall.module.payment.payresult;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

public class PayResultActivity extends BaseActivity<Presenter> implements Contract.View {

    private ImageView ivImg;
    private TextView tvResult;
    private TextView tvMoney;
    private TextView tvComplete;

    ///
    private boolean succ;
    private int money;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_pay_result;
    }


    @Override
    protected void initData() {
        succ = getIntent().getExtras().getBoolean("succ");
        money = getIntent().getExtras().getInt("money");
    }

    @Override
    protected void initView() {
        ivImg = (ImageView) findViewById(R.id.iv_img);
        tvResult = (TextView) findViewById(R.id.tv_result);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        tvComplete = (TextView) findViewById(R.id.tv_complete);

        tvComplete.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                finish();
            }
        });

        onResult();
    }

    void onResult() {

        tvMoney.setText("￥");
        tvMoney.append(StringUtils.moneyFen2Yuan(money));

        if (succ) {
            ivImg.setImageResource(R.drawable.chenggong);
            tvResult.setText("支付成功");
            tvResult.setTextColor(getResources().getColor(R.color.pay_success_col));
            tvComplete.setText("完成");
        } else {
            ivImg.setImageResource(R.drawable.shibia);
            tvResult.setText("支付失败");
            tvResult.setTextColor(getResources().getColor(R.color.spmse));
            tvComplete.setText("确定");
        }
    }
}
