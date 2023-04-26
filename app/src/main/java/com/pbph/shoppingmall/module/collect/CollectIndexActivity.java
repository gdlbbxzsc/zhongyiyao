package com.pbph.shoppingmall.module.collect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.collect.goods.CollectGoodsTypeFragment;
import com.pbph.shoppingmall.module.collect.search.goods.MyCollectGoodsSearchActivity;
import com.pbph.shoppingmall.module.collect.search.shops.MyCollectShopsSearchActivity;
import com.pbph.shoppingmall.module.collect.shops.CollectShopsTypeFragment;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

public class CollectIndexActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final int COLLECT_TYPE_GOODS = 0;
    public static final int COLLECT_TYPE_SHOPS = 1;

    int collect_type = COLLECT_TYPE_GOODS;

    ImageButton titlebar_left;
    ImageButton titlebar_right;
    CheckBox titlebar_right2;

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
        return R.layout.activity_collectindex;
    }


    @Override
    protected void initData() {
        collect_type = getIntent().getIntExtra("collect_type", COLLECT_TYPE_GOODS);
    }

    @Override
    protected void initView() {
        titlebar_left = activity.findViewById(R.id.titlebar_left);
        titlebar_left.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                finish();
            }
        });


        titlebar_right = activity.findViewById(R.id.titlebar_right);
        titlebar_right.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                if (radioButtons[0].isChecked()) {
                    startActivity(new Intent(context, MyCollectGoodsSearchActivity.class));
                } else {
                    startActivity(new Intent(context, MyCollectShopsSearchActivity.class));
                }
            }
        });


        titlebar_right2 = findViewById(R.id.titlebar_right2);
        titlebar_right2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                titlebar_right2.setText("完成");
            } else {
                titlebar_right2.setText("编辑");
            }
            presenter.postRxBus4changeEditState(isChecked);
        });
        titlebar_right2.setText("编辑");
        titlebar_right2.setChecked(false);


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

        radioButtons[collect_type].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == collect_type ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(new CollectGoodsTypeFragment());
        pagerAdapter.list.add(new CollectShopsTypeFragment());

        mViewPager.setAdapter(pagerAdapter);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        switch (buttonView.getId()) {
            case R.id.radioButton1:
                check(0);
                titlebar_right2.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton2:
                check(1);
                titlebar_right2.setVisibility(View.GONE);
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
