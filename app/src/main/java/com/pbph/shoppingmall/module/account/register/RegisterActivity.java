package com.pbph.shoppingmall.module.account.register;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.message.RegisterMsg;
import com.pbph.shoppingmall.module.account.register.register1.Register1Fragment;
import com.pbph.shoppingmall.module.account.register.register2.Register2Fragment;
import com.pbph.shoppingmall.module.account.register.register3.Register3Fragment;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

public class RegisterActivity extends BaseActivity<Presenter> implements Contract.View {

    ImageButton titlebar_left;
    TextView titlebar_center;

    Register1Fragment register1Fragment;
    Register2Fragment register2Fragment;
    Register3Fragment register3Fragment;

    @Override
    protected int layoutResID() {
        return R.layout.activity_register;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

        titlebar_left = activity.findViewById(R.id.titlebar_left);
        titlebar_left.setOnClickListener(onSingleClickListener);

        titlebar_center = activity.findViewById(R.id.titlebar_center);
        titlebar_center.setText("手机快速注册");

        register1Fragment = new Register1Fragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, register1Fragment, register1Fragment.getClass().getName())
                .commit();


    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.titlebar_left: {
//                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                        getSupportFragmentManager().popBackStack();
//                    } else {
                    finish();
//                    }
                }
                break;
            }
        }
    };


    @Override
    public void addFragment(RegisterMsg msg) {

        switch (msg.step) {
            case MsgType_Register_Step1:
                if (register2Fragment == null) register2Fragment = new Register2Fragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, register2Fragment, register2Fragment.getClass().getName())
//                        .addToBackStack(register2Fragment.getClass().getName())
                        .commit();
                break;
            case MsgType_Register_Step2:
                if (register3Fragment == null) register3Fragment = new Register3Fragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, register3Fragment, register3Fragment.getClass().getName())
//                        .addToBackStack(register3Fragment.getClass().getName())
                        .commit();
                break;
            case MsgType_Register_Step3:
                finish();
                break;
        }

    }
}
