package com.pbph.shoppingmall.module.sendbackinfo;


import android.view.View;
import android.widget.EditText;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

public class SendBackInfoActivity extends BaseActivity<Presenter> implements Contract.View {

    EditText edtSendbackCom;
    EditText edtSendbackCode;

    String orderId;

    @Override
    protected int layoutResID() {
        return R.layout.activity_sendbackinfo;
    }


    @Override
    protected void initData() {
        orderId = getIntent().getStringExtra("orderId");
    }

    @Override
    protected void initView() {
        CommonTitlebar commonTitlebar = new CommonTitlebar(this, "填写物流信息", true);

        edtSendbackCom = findViewById(R.id.edt_sendback_com);
        edtSendbackCode = findViewById(R.id.edt_sendback_code);
        findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                String com = edtSendbackCom.getText().toString().trim();
                if (StringUtils.isEmpty(com)) {
                    toastShort("请填写物流公司");
                    return;
                }
                String code = edtSendbackCode.getText().toString().trim();
                if (StringUtils.isEmpty(code)) {
                    toastShort("请填写物流单号");
                    return;
                }
                presenter.submit(com, code);
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public void onSubmit() {
        finish();
    }
}
