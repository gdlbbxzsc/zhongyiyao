package com.pbph.shoppingmall.module.account.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pbph.mvp.BuildConfig;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.account.register.RegisterActivity;
import com.pbph.shoppingmall.module.account.updatepassword.UpdatePwdActivity;
import com.pbph.shoppingmall.utils.SpHelper;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

public class LoginActivity extends BaseActivity<Presenter> implements Contract.View {

    CommonTitlebar commonTitlebar;

    EditText account;
    EditText password;
    private ImageView ivShowPwd;

    private EditText numCode;

    private TextView tvNumCode;
    CountDownTimer timer;
    RelativeLayout rl_phone_denglu;
    RelativeLayout rl_password_denglu;
    View v_1;
    View v_2;
    TextView tv_1;
    TextView tv_2;
    LinearLayout ll_1;
    LinearLayout ll_2;

    TextView tv_use_forget;


    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "登录", false);

        ll_1 = findViewById(R.id.ll_1);
        ll_2 = findViewById(R.id.ll_2);
        v_1 = findViewById(R.id.v_1);
        v_2 = findViewById(R.id.v_2);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        rl_phone_denglu = findViewById(R.id.rl_phone_denglu);
        rl_password_denglu = findViewById(R.id.rl_password_denglu);
        rl_phone_denglu.setOnClickListener(onTitleClick);
        rl_password_denglu.setOnClickListener(onTitleClick);
        account = findViewById(R.id.edt_account);
        password = findViewById(R.id.edt_pwd);

        ivShowPwd = findViewById(R.id.iv_show_pwd);
        ivShowPwd.setOnClickListener(onTitleClick);

        type = true;
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ivShowPwd.setImageLevel(0);


        numCode = findViewById(R.id.edt_num_code);

        tvNumCode = findViewById(R.id.tv_num_code);
        tvNumCode.setOnClickListener(onTitleClick);

        findViewById(R.id.button).setOnClickListener(onTitleClick);

        findViewById(R.id.tv_use_callnum).setOnClickListener(onTitleClick);

        tv_use_forget =findViewById(R.id.tv_use_forget);
        tv_use_forget.setOnClickListener(onTitleClick);

        account.setText(SpHelper.getInstance().getAccount());
        password.setText(SpHelper.getInstance().getPassword());

        String accountStr = account.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();

        if (BuildConfig.LOG) {//测试代码
            if (StringUtils.isEmpty(accountStr)) account.setText("13333333333");
            numCode.setText("123456");
        }

        if (StringUtils.isEmpty(accountStr)) {
            return;
        }
        if (StringUtils.isEmpty(passwordStr)) {
            return;
        }
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }

    OnSingleClickListener onTitleClick = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            switch (view.getId()) {

                /*case R.id.rl_phone_denglu:{
                    tv_1.setTextColor(Color.parseColor("#1890FF"));
                    tv_2.setTextColor(getResources().getColor(R.color.gray6));
                    v_1.setVisibility(View.VISIBLE);
                    v_2.setVisibility(View.GONE);
                    ll_1.setVisibility(View.VISIBLE);
                    ll_2.setVisibility(View.GONE);
                    tv_use_forget.setVisibility(View.GONE);
                }
                    break;

                case R.id.rl_password_denglu:{
                    tv_1.setTextColor(getResources().getColor(R.color.gray6));
                    tv_2.setTextColor(Color.parseColor("#1890FF"));
                    v_1.setVisibility(View.GONE);
                    v_2.setVisibility(View.VISIBLE);
                    ll_1.setVisibility(View.GONE);
                    ll_2.setVisibility(View.VISIBLE);
                    tv_use_forget.setVisibility(View.VISIBLE);
                }
                    break;
*/
                case R.id.iv_show_pwd: {
                    setPwdMode();
                }
                break;

                case R.id.tv_num_code: {
                    String callNum = account.getText().toString().trim();
                    presenter.getNumCode(callNum);
                }
                break;

                case R.id.button: {

                    String callNum = account.getText().toString().trim();


//                    String pwd1 = password.getText().toString().trim();

                    String code = numCode.getText().toString().trim();


                    presenter.login(callNum, code);
                }
                break;
             /*   case R.id.tv_use_callnum: {//注册
                    startActivity(new Intent(context, RegisterActivity.class));
                }
                break;
                case R.id.tv_use_forget: {//忘记密码
                    startActivity(new Intent(context, UpdatePwdActivity.class)
                            .putExtra("isForget", true));
                }
                break;*/
            }
        }
    };

    volatile boolean type;

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

    @Override
    public void goMain() {
//        Intent intent = new Intent(getContext(), MainActivity.class);
//        startActivity(intent);
        finish();
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
                    resetNumcode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}
