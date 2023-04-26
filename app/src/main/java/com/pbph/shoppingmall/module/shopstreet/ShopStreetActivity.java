package com.pbph.shoppingmall.module.shopstreet;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.shopstreet.fragment.ShopsFragment;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;

public class ShopStreetActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    CommonTitlebar commonTitlebar;


    private RadioButton[] radioButtons = new RadioButton[4];
    View[] rbLines = new View[4];
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_shopstreet;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "店铺街", false);

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);
        radioButtons[3] = findViewById(R.id.radioButton4);

        rbLines[0] = findViewById(R.id.radioButtonline1);
        rbLines[1] = findViewById(R.id.radioButtonline2);
        rbLines[2] = findViewById(R.id.radioButtonline3);
        rbLines[3] = findViewById(R.id.radioButtonline4);

        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }
        initViewPager();
    }

    private void initViewPager() {

//        热销店铺 1
//        人气店铺 2
//        热门店铺 3
//        好评店铺 4

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(ShopsFragment.newInstance(1));
        pagerAdapter.list.add(ShopsFragment.newInstance(2));
        pagerAdapter.list.add(ShopsFragment.newInstance(3));
        pagerAdapter.list.add(ShopsFragment.newInstance(4));


        mViewPager.setAdapter(pagerAdapter);

        radioButtons[0].setChecked(true);
        rbLines[0].setVisibility(View.VISIBLE);
        rbLines[1].setVisibility(View.INVISIBLE);
        rbLines[2].setVisibility(View.INVISIBLE);
        rbLines[3].setVisibility(View.INVISIBLE);
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
