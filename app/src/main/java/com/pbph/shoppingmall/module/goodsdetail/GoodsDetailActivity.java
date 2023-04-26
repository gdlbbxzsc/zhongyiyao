package com.pbph.shoppingmall.module.goodsdetail;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.GoodsDetailCommodityFragment;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailevaluate.GoodsDetailEvaluateFragment;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail.GoodsDetailDetailFragment;
import com.pbph.shoppingmall.utils.pop.CommonTitlebarRightListPop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/8.
 */

public class GoodsDetailActivity extends BaseActivity<GoodsDetailPresenter> implements
        GoodsDetailContract.View {

    private TabLayout shop_detail_tablayout;
    private ViewPager shop_detail_vp;
    private List<Fragment> fragments;
    private GoodsDetailAdapter goodsDetailAdapter;
    private String[] titles = {"商品", "详情", "评价"};
    private ImageView back_iv,more_iv;
    CommonTitlebarRightListPop pop;
    public int goodsInfoId;
    @Override
    protected int layoutResID() {
        return R.layout.activity_shopdetail;
    }

    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(this);
    }

    @Override
    protected void initData() {
        goodsInfoId = getIntent().getIntExtra("goodsInfoId",0);
        goodsDetailAdapter = new GoodsDetailAdapter(this.getSupportFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new GoodsDetailCommodityFragment());
        fragments.add(new GoodsDetailDetailFragment());
        fragments.add(new GoodsDetailEvaluateFragment());

    }

    @Override
    protected void initView() {
        back_iv= findViewById(R.id.back_iv);
        shop_detail_tablayout = findViewById(R.id.shop_detail_tablayout);
        shop_detail_vp = findViewById(R.id.shop_detail_vp);
        more_iv = findViewById(R.id.more_iv);
        goodsDetailAdapter.setFragments(fragments);
        goodsDetailAdapter.setTitles(titles);
        shop_detail_vp.setAdapter(goodsDetailAdapter);
        shop_detail_tablayout.setupWithViewPager(shop_detail_vp);
        pop = new CommonTitlebarRightListPop(activity, more_iv);
        back_iv.setOnClickListener(v -> {
            finish();
        });
        more_iv.setOnClickListener(v -> {
            if (pop != null) pop.show();
        });

    }

    public void setViewPagerPos(int i) {
        shop_detail_vp.setCurrentItem(i);
    }

}
