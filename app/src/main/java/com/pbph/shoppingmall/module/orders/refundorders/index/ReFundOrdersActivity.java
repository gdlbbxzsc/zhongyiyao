package com.pbph.shoppingmall.module.orders.refundorders.index;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.orders.fragment.OrdersFragment;
import com.pbph.shoppingmall.module.orders.search.OrdersSearchActivity;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

public class ReFundOrdersActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    CommonTitlebar commonTitlebar;
    ImageButton titlebar_right2;


    private RadioButton[] radioButtons = new RadioButton[2];
    View[] rbLines = new View[2];
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_refoundorders;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "退款/退货订单", true);
        titlebar_right2 = activity.findViewById(R.id.titlebar_right2);
        titlebar_right2.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                startActivity(new Intent(context, OrdersSearchActivity.class));
            }
        });
        titlebar_right2.setVisibility(View.GONE);
        titlebar_right2.setEnabled(false);

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);

        rbLines[0] = findViewById(R.id.radioButtonline1);
        rbLines[1] = findViewById(R.id.radioButtonline2);

        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }
        initViewPager();

        radioButtons[0].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == 0 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void initViewPager() {
        //        订单类型（0：全部，1：待付款，2：待收货，3：已完成，4：已取消，5：待评价，6：以评价并以晒单，7：售后申请，8：申请记录）
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(OrdersFragment.newInstance(7));
        pagerAdapter.list.add(OrdersFragment.newInstance(8));

        mViewPager.setAdapter(pagerAdapter);
    }


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
        }
    }

    private void check(int pos) {
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == pos ? View.VISIBLE : View.INVISIBLE);
        }
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


}
