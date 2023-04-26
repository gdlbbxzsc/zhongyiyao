package com.pbph.shoppingmall.module.account.updateaccount;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.message.UpdateActMsg;
import com.pbph.shoppingmall.model.message.UpdatePwdMsg;
import com.pbph.shoppingmall.module.account.updateaccount.updateact1.UpdateAct1Fragment;
import com.pbph.shoppingmall.module.account.updateaccount.updateact2.UpdateAct2Fragment;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

public class UpdateActActivity extends BaseActivity<Presenter> implements Contract.View {

    ImageButton titlebar_left;
    TextView titlebar_center;

    UpdateAct1Fragment fragment1;
    UpdateAct2Fragment fragment2;


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
        titlebar_center.setText("修改账号");

        fragment1 = new UpdateAct1Fragment();

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
    public void addFragment(UpdateActMsg msg) {

        switch (msg.step) {
            case MsgType_Update_Act_Step1:
                if (fragment2 == null) fragment2 = new UpdateAct2Fragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment2, fragment2.getClass().getName())
//                        .addToBackStack(fragment2.getClass().getName())
                        .commit();
                break;
            case MsgType_Update_Act_Step2:
                finish();
                break;
        }

    }
}
