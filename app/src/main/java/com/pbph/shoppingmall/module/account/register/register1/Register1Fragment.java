package com.pbph.shoppingmall.module.account.register.register1;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

/**
 * Created by Administrator on 2018/1/19.
 */

public class Register1Fragment extends BaseFragmentV4<Presenter> implements Contract.View {

    EditText edt_account;
    EditText edt_img_code;
    private ImageView ivImgCode;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_register1;
    }

    @Override
    public void initView(View view) {
        edt_account = view.findViewById(R.id.edt_account);
        edt_img_code = view.findViewById(R.id.edt_img_code);

        ivImgCode = view.findViewById(R.id.iv_img_code);
        ivImgCode.setOnClickListener(onTitleClick);

        view.findViewById(R.id.button).setOnClickListener(onTitleClick);


    }

    OnSingleClickListener onTitleClick = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            switch (view.getId()) {

                case R.id.iv_img_code: {
                    presenter.getImgCode();
                }
                break;
                case R.id.button: {
                    presenter.submitAccountImgCode();
                }
                break;
            }
        }
    };

    @Override
    public void showImgCode(String url) {

    }

}
