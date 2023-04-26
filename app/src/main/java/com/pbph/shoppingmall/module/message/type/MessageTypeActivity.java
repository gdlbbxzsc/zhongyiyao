package com.pbph.shoppingmall.module.message.type;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.message.type.logistics.MessageTypeLogisticsFragment;
import com.pbph.shoppingmall.module.message.type.notices.MessageTypeNoticesFragment;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;

public class MessageTypeActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final int MESSAGE_TYPE_WULIU = 0;
    public static final int MESSAGE_TYPE_TONGZHI = 1;
    public static final int MESSAGE_TYPE_XIAOXI = 2;

    int message_type = MESSAGE_TYPE_WULIU;

    CommonTitlebar commonTitlebar;

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
        return R.layout.activity_messagetype;
    }


    @Override
    protected void initData() {
        message_type = getIntent().getIntExtra("message_type", MESSAGE_TYPE_WULIU);
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "消息", true);

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
//        radioButtons[2] = findViewById(R.id.radioButton3);
//

        rbLines[0] = findViewById(R.id.radioButtonline1);
        rbLines[1] = findViewById(R.id.radioButtonline2);
//        rbLines[2] = findViewById(R.id.radioButtonline3);


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
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(new MessageTypeLogisticsFragment());
        pagerAdapter.list.add(new MessageTypeNoticesFragment());
//        pagerAdapter.list.add(new MessageTypeInteractionFragment());

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
