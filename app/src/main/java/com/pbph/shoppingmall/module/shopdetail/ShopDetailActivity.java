package com.pbph.shoppingmall.module.shopdetail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreDetailResponse;
import com.pbph.shoppingmall.module.shopallgoods.AllWithNewGoodsActivity;
import com.pbph.shoppingmall.module.shopnewgoods.NewGoodsGoShelfActivity;
import com.pbph.shoppingmall.utils.GlideRoundTransform;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

public class ShopDetailActivity extends BaseActivity<Presenter> implements Contract.View, View
        .OnClickListener {

    private TextView tv_all_goods;
    private GetStoreDetailResponse.DataBean dataBean;
    private View view;
    private ImageView iv_shop_logo;//店铺logo
    private TextView tv_shop_name;//店铺名称
    private TextView tv_shop_position;//店铺摊位
    private TextView tv_shop_score;//店铺评分
    private TextView tv_shop_collection;//收藏
    private TextView tv_num;//数量
    private TextView tv_contact_buy;
    private int storeId;//店铺id
    private TextView tv_new_goods;

    private ListView lv_shop_detail;
    private RecyclerView recyclerBrand;
    private ShopDetailAdapter shopDetailAdapter;
    private ShopDetailBrandAdapter shopDetailBrandAdapter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        storeId = getIntent().getIntExtra("storeId", -1);
        dataBean = (GetStoreDetailResponse.DataBean) getIntent().getSerializableExtra("dataBean");
        shopDetailAdapter = new ShopDetailAdapter(this);
        shopDetailAdapter.setCategoryListBeans(dataBean.getCategoryList());
        shopDetailBrandAdapter = new ShopDetailBrandAdapter(this);
        shopDetailBrandAdapter.setBrandListBeans(dataBean.getBrandList());


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {
        CommonTitlebar commonTitlebar = new CommonTitlebar(this, "店铺详情", true);
        tv_all_goods = findViewById(R.id.tv_all_goods);
        view = findViewById(R.id.view_shop_heard);
        iv_shop_logo = view.findViewById(R.id.iv_shop_logo);
        tv_shop_name = view.findViewById(R.id.tv_shop_name);
        tv_shop_position = view.findViewById(R.id.tv_shop_position);
        tv_shop_score = view.findViewById(R.id.tv_shop_score);
        tv_shop_collection = view.findViewById(R.id.tv_shop_collection);
        tv_num = view.findViewById(R.id.tv_num);
        tv_contact_buy = findViewById(R.id.tv_contact_buy);
        lv_shop_detail = findViewById(R.id.lv_shop_detail);
        recyclerBrand = findViewById(R.id.recycler_brand);
        tv_new_goods = findViewById(R.id.tv_new_goods);
        tv_new_goods.setOnClickListener(this);
        tv_all_goods.setOnClickListener(this);
        tv_contact_buy.setOnClickListener(this);
        Glide.with(this)
                .load(dataBean.getStoreLogo())
                .transform(new GlideRoundTransform(this))
                .error(R.drawable.dianpu_88x88)
                .into(iv_shop_logo);
        tv_shop_name.setText(dataBean.getStoreName());
        tv_shop_position.setText("店铺摊位:");
        tv_shop_position.append(String.valueOf(dataBean.getThirdPositionNum()));
        tv_shop_score.setText("店铺评分:");
        tv_shop_score.append(String.valueOf(dataBean.getOverallMerit()));
        tv_num.setText(String.valueOf(dataBean.getCollectionNumber()));
        tv_contact_buy.setText("联系卖家:");
        tv_contact_buy.append(dataBean.getCompanyContactTel());
        lv_shop_detail.setAdapter(shopDetailAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerBrand.setLayoutManager(linearLayoutManager);
        recyclerBrand.setAdapter(shopDetailBrandAdapter);

        if (dataBean.getFlag() == 0) {
            tv_shop_collection.setText("收藏");
        } else {
            tv_shop_collection.setText("未收藏");
            tv_shop_collection.setBackgroundResource(R.drawable.tv_shop_shouchang_bg_blue);
        }
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shop_type: {

            }
            break;
            case R.id.tv_all_goods: {
                if (storeId == -1) return;
                Intent intent = new Intent();
                intent.setClass(ShopDetailActivity.this, AllWithNewGoodsActivity.class);
                intent.putExtra("storeId", storeId);
                startActivity(intent);
            }
            break;
            case R.id.tv_new_goods: {
                if (storeId == -1) return;
                Intent intent = new Intent();
                intent.setClass(ShopDetailActivity.this, NewGoodsGoShelfActivity.class);
                intent.putExtra("storeId", storeId);
                startActivity(intent);

            }
            break;

            case R.id.tv_contact_buy: {
                //跳转到拨号界面，同时传递电话号码
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + dataBean.getCompanyContactTel()));
                startActivity(dialIntent);
            }
            break;

            default: {

            }
            break;
        }
    }















}
