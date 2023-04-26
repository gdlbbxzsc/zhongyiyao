package com.pbph.shoppingmall.module.account.register.register3;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Register3Fragment extends BaseFragmentV4<Presenter> implements Contract.View {

    EditText password;
    private ImageView ivShowPwd;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_register3;
    }

    @Override
    public void initView(View view) {
        password = view.findViewById(R.id.edt_pwd);

        ivShowPwd = view.findViewById(R.id.iv_show_pwd);
        ivShowPwd.setOnClickListener(onTitleClick);

        type = true;
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ivShowPwd.setImageLevel(0);

        view.findViewById(R.id.button).setOnClickListener(onTitleClick);


    }

    OnSingleClickListener onTitleClick = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            switch (view.getId()) {

                case R.id.iv_show_pwd: {
                    setPwdMode();
                }
                break;
                case R.id.button: {

                    String pwd1 = password.getText().toString().trim();


                    if (StringUtils.isEmpty(pwd1)) {
                        toastShort("请输入密码");
                        return;
                    }
                    presenter.submitNumCode();
                }
                break;
            }
        }
    };


    volatile boolean type = true;

    private void setPwdMode() {
        type = !type;
        if (type) {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            ivShowPwd.setImageLevel(0);
        } else {
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            ivShowPwd.setImageLevel(1);
        }
    }

}
