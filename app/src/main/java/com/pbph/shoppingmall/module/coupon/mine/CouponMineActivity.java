package com.pbph.shoppingmall.module.coupon.mine;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SelectMyCouponCountResponse;
import com.pbph.shoppingmall.module.coupon.index.CouponIndexActivity;
import com.pbph.shoppingmall.module.coupon.mine.invalid.CouponInvalidFragment;
import com.pbph.shoppingmall.module.coupon.mine.used.CouponUsedFragment;
import com.pbph.shoppingmall.module.coupon.mine.valid.CouponValidFragment;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

public class CouponMineActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    //标题栏
    CommonTitlebar commonTitlebar;
    CheckBox titlebar_right2;

    private RadioButton[] radioButtons = new RadioButton[3];

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    TextView tv_go_index;

    LinearLayout ll_bottom;
    CheckBox cb_all;
    TextView tv_del;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_couponmine;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "优惠券", true);
        titlebar_right2 = findViewById(R.id.titlebar_right2);
        titlebar_right2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                titlebar_right2.setText("完成");
                ll_bottom.setVisibility(View.VISIBLE);
                tv_go_index.setVisibility(View.GONE);

                cb_all.setOnCheckedChangeListener(null);
                cb_all.setChecked(false);
                cb_all.setOnCheckedChangeListener(allListener);

                presenter.postRxBus4delData(OperationMsgType.Op_Start_Del);
            } else {
                titlebar_right2.setText("编辑");
                ll_bottom.setVisibility(View.GONE);
                tv_go_index.setVisibility(View.VISIBLE);

                presenter.postRxBus4delData(OperationMsgType.Op_Cancel_Del);
            }

        });
        titlebar_right2.setText("编辑");
        titlebar_right2.setChecked(false);


        mViewPager = findViewById(R.id.container);

        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);


        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }

        initViewPager();


        ll_bottom = findViewById(R.id.ll_bottom);
        cb_all = findViewById(R.id.cb_all);

        tv_del = findViewById(R.id.tv_del);
        tv_del.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                presenter.postRxBus4delData(OperationMsgType.Op_Do_Del);
            }
        });

        radioButtons[0].setChecked(true);


        tv_go_index = findViewById(R.id.tv_go_index);
        tv_go_index.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                startActivity(new Intent(context, CouponIndexActivity.class));
            }
        });

    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(new CouponValidFragment());
        pagerAdapter.list.add(new CouponUsedFragment());
        pagerAdapter.list.add(new CouponInvalidFragment());

        mViewPager.setAdapter(pagerAdapter);

    }

    CompoundButton.OnCheckedChangeListener allListener = (buttonView, isChecked) -> {
        presenter.postRxBus4delData(isChecked ? OperationMsgType.Op_Choose_All : OperationMsgType.Op_Choose_None);
    };


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        switch (buttonView.getId()) {
            case R.id.radioButton1:
                check(0);
                break;
            case R.id.radioButton2:
                check(1);
                break;
            case R.id.radioButton3:
                check(2);
                break;
        }
    }

    private void check(int pos) {
        mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageSelected(int position) {
        radioButtons[position].setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void updateNums(SelectMyCouponCountResponse.DataBean dataBean) {

        radioButtons[0].setText("未使用(" + dataBean.getUnUsedCount() + ")");
        radioButtons[1].setText("使用记录(" + dataBean.getUsedCount() + ")");
        radioButtons[2].setText("已过期(" + dataBean.getExpireCount() + ")");

    }
}
