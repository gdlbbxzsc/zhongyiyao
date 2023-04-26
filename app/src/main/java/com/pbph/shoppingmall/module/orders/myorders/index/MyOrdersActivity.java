package com.pbph.shoppingmall.module.orders.myorders.index;

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

public class MyOrdersActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final int MESSAGE_TYPE_ALL = 0;
    public static final int MESSAGE_TYPE_NOPAY = 1;
    public static final int MESSAGE_TYPE_NOTAKE = 2;

    int message_type = MESSAGE_TYPE_ALL;

    CommonTitlebar commonTitlebar;
    ImageButton titlebar_right2;


    private RadioButton[] radioButtons = new RadioButton[5];
    View[] rbLines = new View[5];
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_myorders;
    }


    @Override
    protected void initData() {
        message_type = getIntent().getIntExtra("message_type", MESSAGE_TYPE_ALL);
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "我的订单", true);
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
        radioButtons[2] = findViewById(R.id.radioButton3);
        radioButtons[3] = findViewById(R.id.radioButton4);
        radioButtons[4] = findViewById(R.id.radioButton5);

        rbLines[0] = findViewById(R.id.radioButtonline1);
        rbLines[1] = findViewById(R.id.radioButtonline2);
        rbLines[2] = findViewById(R.id.radioButtonline3);
        rbLines[3] = findViewById(R.id.radioButtonline4);
        rbLines[4] = findViewById(R.id.radioButtonline5);

        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }
        initViewPager();

        radioButtons[message_type].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == message_type ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void initViewPager() {
        //        订单类型（0：全部，1：待付款，2：待收货，3：已完成，4：已取消，5：待评价，6：退还售后）
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(OrdersFragment.newInstance(0));
        pagerAdapter.list.add(OrdersFragment.newInstance(1));
        pagerAdapter.list.add(OrdersFragment.newInstance(2));
        pagerAdapter.list.add(OrdersFragment.newInstance(3));
        pagerAdapter.list.add(OrdersFragment.newInstance(4));

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
            case R.id.radioButton3:
                check(2);
                break;
            case R.id.radioButton4:
                check(3);
                break;
            case R.id.radioButton5:
                check(4);
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
