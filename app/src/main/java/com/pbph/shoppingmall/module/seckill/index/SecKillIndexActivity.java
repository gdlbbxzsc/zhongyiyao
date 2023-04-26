package com.pbph.shoppingmall.module.seckill.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.seckill.goodslist.SecKillGoodsListActivity;
import com.pbph.shoppingmall.module.seckill.index.brand.SecKillIndexBrandFragment;
import com.pbph.shoppingmall.module.seckill.index.category.SecKillIndexCategoryFragment;
import com.pbph.shoppingmall.module.seckill.index.goods.SecKillIndexGoodsFragment;
import com.pbph.shoppingmall.module.seckill.pop.SecKillGoodsTypeListPop;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

public class SecKillIndexActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public static final int SECKILLI_TYPE_GOODS = 0;
    public static final int SECKILLI_TYPE_BRAND = 1;
    public static final int SECKILLI_TYPE_CATEGORY = 2;

    int seckill_type = SECKILLI_TYPE_GOODS;

    //    标题栏
    CommonTitlebar titlebar;
    ImageButton titlebar_right2;
    public SecKillGoodsTypeListPop secKillGoodsTypeListPop;

    private RadioButton[] radioButtons = new RadioButton[3];
    View[] rbLines = new View[3];

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_seckillindex;
    }


    @Override
    protected void initData() {
        seckill_type = getIntent().getIntExtra("seckill_type", SECKILLI_TYPE_GOODS);
    }

    @Override
    protected void initView() {

        titlebar = new CommonTitlebar(this, "秒杀", true);
        titlebar_right2 = activity.findViewById(R.id.titlebar_right2);
        titlebar_right2.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                secKillGoodsTypeListPop.show();
            }
        });
        secKillGoodsTypeListPop = new SecKillGoodsTypeListPop(activity, titlebar.layout);
        secKillGoodsTypeListPop.gridView.setOnItemClickListener((parent, itemView, position, id) -> {
            secKillGoodsTypeListPop.adapter.putChoiced(position);

            activity.startActivity(new Intent(activity, SecKillGoodsListActivity.class));

            secKillGoodsTypeListPop.dismiss();
        });

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);


        rbLines[0] = findViewById(R.id.radioButtonline1);
        rbLines[1] = findViewById(R.id.radioButtonline2);
        rbLines[2] = findViewById(R.id.radioButtonline3);


        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }

        initViewPager();

        radioButtons[seckill_type].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(i == seckill_type ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(new SecKillIndexGoodsFragment());
        pagerAdapter.list.add(new SecKillIndexBrandFragment());
        pagerAdapter.list.add(new SecKillIndexCategoryFragment());

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
            case R.id.radioButton3:
                check(2);
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


    @Override
    public void setHttpDatas(List<String> list) {

        secKillGoodsTypeListPop.adapter.setDatas(list);

    }
}
