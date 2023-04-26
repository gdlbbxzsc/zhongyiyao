package com.pbph.shoppingmall.module.shop;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreDetailResponse;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.module.shop.allcommodity.AllCommodityFragment;
import com.pbph.shoppingmall.module.shop.newtheshelves.NewTheShelvesFragment;
import com.pbph.shoppingmall.module.shop.shopindex.ShopIndexFragment;
import com.pbph.shoppingmall.module.shopdetail.ShopDetailActivity;
import com.pbph.shoppingmall.utils.GlideRoundTransform;
import com.pbph.shoppingmall.utils.ui.ShopSearchTitleBarType;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends BaseActivity<Presenter> implements Contract.View, View
        .OnClickListener {

    private TabLayout tab_layout_shop;
    private ViewPager vp_shop;
    private ShopAdapter shopAdapter;
    private List<Fragment> fragments;
    private String[] tiltes = {"店铺首页", "全部商品", "新品上架"};
    private TextView tv_shop_type;
    public int storeId;//店铺id
    private ImageView iv_shop_logo;//店铺logo
    private TextView tv_shop_name;//店铺名称
    private TextView tv_shop_position;//店铺摊位
    private TextView tv_shop_score;//店铺评分
    private TextView tv_shop_collection;//收藏
    private TextView tv_num;//数量
    private GetStoreDetailResponse.DataBean dataBean;
    private View view;

    @Override
    protected int layoutResID() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initData() {
        storeId = getIntent().getIntExtra("storeId", 0);
        fragments = new ArrayList<>();
        fragments.add(new ShopIndexFragment());
        fragments.add(AllCommodityFragment.newInstance(storeId));
        fragments.add(new NewTheShelvesFragment());
        shopAdapter = new ShopAdapter(getSupportFragmentManager());
        shopAdapter.setFragments(fragments);
        shopAdapter.setTitles(tiltes);

    }

    @Override
    protected void initView() {
        view = findViewById(R.id.view_shop_heard);
        tab_layout_shop = findViewById(R.id.tab_layout_shop);
        vp_shop = findViewById(R.id.vp_shop);
        tv_shop_type = findViewById(R.id.tv_shop_type);
        iv_shop_logo = view.findViewById(R.id.iv_shop_logo);
        tv_shop_name = view.findViewById(R.id.tv_shop_name);
        tv_shop_position = view.findViewById(R.id.tv_shop_position);
        tv_shop_score =view. findViewById(R.id.tv_shop_score);
        tv_shop_collection = view.findViewById(R.id.tv_shop_collection);
        tv_num = view.findViewById(R.id.tv_num);
        view.setOnClickListener(this);
        tv_shop_type.setOnClickListener(this);
        ShopSearchTitleBarType searchTitleBarType = new ShopSearchTitleBarType(this, "请输入搜索关键字",
                true, SearchActivity.SEARCH_TYPE_SHOPS, storeId);

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getStoreDetail(storeId);
        vp_shop.setAdapter(shopAdapter);
        tab_layout_shop.setupWithViewPager(vp_shop);
    }

    @Override
    public void setHttpData(GetStoreDetailResponse.DataBean data) {
        dataBean = data;
        Glide.with(this)
                .load(data.getStoreLogo())
                .transform(new GlideRoundTransform(this))
                .error(R.drawable.dianpu_88x88)
                .into(iv_shop_logo);
        tv_shop_name.setText(data.getStoreName());
        tv_shop_position.setText("店铺摊位:" + data.getThirdPositionNum());
        tv_shop_score.setText("店铺评分:" + String.valueOf(data.getOverallMerit()));
        tv_num.setText(String.valueOf(data.getCollectionNumber()));
        if (data.getFlag()==0){
            tv_shop_collection.setText("收藏");
        }else {
            tv_shop_collection.setText("未收藏");
            tv_shop_collection.setBackgroundResource(R.drawable.tv_shop_shouchang_bg_blue);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_shop_heard: {
                if (dataBean==null) return;
                Intent intent = new Intent();
                intent.setClass(this, ShopDetailActivity.class);
                intent.putExtra("storeId",storeId);
                intent.putExtra("dataBean", dataBean);
                startActivity(intent);
            }
            break;
        }
    }
}
