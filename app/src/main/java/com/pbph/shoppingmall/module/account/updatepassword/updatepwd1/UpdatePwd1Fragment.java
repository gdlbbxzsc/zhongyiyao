package com.pbph.shoppingmall.module.account.updatepassword.updatepwd1;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.AMUtils;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

/**
 * Created by Administrator on 2018/1/19.
 */

public class UpdatePwd1Fragment extends BaseFragmentV4<Presenter> implements Contract.View {


    EditText edt_account;
    TextView tv_account;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_updatepwd1;
    }

    @Override
    public void initView(View view) {

        edt_account = view.findViewById(R.id.edt_account);
        tv_account = view.findViewById(R.id.tv_account);


        view.findViewById(R.id.button).setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                String acc = edt_account.getText().toString();
                if (!validateAccount(acc)) return;
                presenter.submit2ValidateAccount(acc);
            }
        });
    }

    boolean validateAccount(String acc) {
        if (TextUtils.isEmpty(acc)) {
            Toast.makeText(context, R.string.phone_number_is_null, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!AMUtils.isMobile(acc)) {
            Toast.makeText(context, R.string.Illegal_phone_number, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
