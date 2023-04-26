package com.pbph.shoppingmall.module.account.updatepassword;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.message.UpdatePwdMsg;
import com.pbph.shoppingmall.module.account.updatepassword.updatepwd1.UpdatePwd1Fragment;
import com.pbph.shoppingmall.module.account.updatepassword.updatepwd2.UpdatePwd2Fragment;
import com.pbph.shoppingmall.module.account.updatepassword.updatepwd3.UpdatePwd3Fragment;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

public class UpdatePwdActivity extends BaseActivity<Presenter> implements Contract.View {

    ImageButton titlebar_left;
    TextView titlebar_center;

    UpdatePwd1Fragment fragment1;
    UpdatePwd2Fragment fragment2;
    UpdatePwd3Fragment fragment3;


    boolean isForget;

    @Override
    protected int layoutResID() {
        return R.layout.activity_register;
    }


    @Override
    protected void initData() {
        isForget = getIntent().getBooleanExtra("isForget", false);
    }

    @Override
    protected void initView() {

        titlebar_left = activity.findViewById(R.id.titlebar_left);
        titlebar_left.setOnClickListener(onSingleClickListener);

        titlebar_center = activity.findViewById(R.id.titlebar_center);
        titlebar_center.setText("修改密码");

        fragment1 = new UpdatePwd1Fragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment1, fragment1.getClass().getName())
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
    public void addFragment(UpdatePwdMsg msg) {

        switch (msg.step) {
            case MsgType_Update_Pwd_Step1:
                if (fragment2 == null) fragment2 = new UpdatePwd2Fragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment2, fragment2.getClass().getName())
//                        .addToBackStack(fragment2.getClass().getName())
                        .commit();
                break;
            case MsgType_Update_Pwd_Step2:
                if (fragment3 == null) fragment3 = new UpdatePwd3Fragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment3, fragment3.getClass().getName())
//                        .addToBackStack(fragment3.getClass().getName())
                        .commit();
                break;
            case MsgType_Update_Pwd_Step3:
                finish();
                break;
        }

    }
}
