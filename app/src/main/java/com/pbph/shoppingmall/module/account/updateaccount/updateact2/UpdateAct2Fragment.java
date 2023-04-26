package com.pbph.shoppingmall.module.account.updateaccount.updateact2;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

/**
 * Created by Administrator on 2018/1/19.
 */

public class UpdateAct2Fragment extends BaseFragmentV4<Presenter> implements Contract.View {

    private EditText edtAccount;

    private EditText editText;

    private TextView tvNumCode;
    CountDownTimer timer;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_updateact2;
    }

    @Override
    public void initView(View view) {

        edtAccount = view.findViewById(R.id.edt_account);

        editText = view.findViewById(R.id.edt_num_code);

        tvNumCode = view.findViewById(R.id.tv_num_code);
        tvNumCode.setOnClickListener(onTitleClick);

        view.findViewById(R.id.button).setOnClickListener(onTitleClick);

    }

    OnSingleClickListener onTitleClick = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            switch (view.getId()) {

                case R.id.tv_num_code: {
                    presenter.getNumCode(edtAccount.getText().toString().trim());
                }
                break;
                case R.id.button: {
                    presenter.submitAccountCode(edtAccount.getText().toString().trim(), editText.getText().toString().trim());
                }
                break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroyView();
    }


    @Override
    public void resetNumcode() {
        tvNumCode.setEnabled(true);
        tvNumCode.setText("点击获取验证码");
    }

    @Override
    public void waitNumCode() {

        tvNumCode.setEnabled(false);

        timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    tvNumCode.setText("重新发送(");
                    tvNumCode.append(String.valueOf(millisUntilFinished / 1000));
                    tvNumCode.append(")");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                try {
                    tvNumCode.setEnabled(true);
                    tvNumCode.setText("点击获取验证码");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}
