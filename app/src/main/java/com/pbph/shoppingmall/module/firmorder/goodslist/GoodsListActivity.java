package com.pbph.shoppingmall.module.firmorder.goodslist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;

import java.util.List;

public class GoodsListActivity extends BaseActivity<Presenter> implements Contract.View {

    private CommonTitlebar commonTitlebar;

    private RecyclerView mRecyclerView;
    private GoodsListAdapter mAdapter;

    List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean> goodsList;

    @Override
    protected int layoutResID() {
        return R.layout.activity_goods_list_detail;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initData() {
        goodsList = (List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean>) MyApplication.getDataMapData(GoodsListActivity.class.getName());
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, "商品清单", false);

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new GoodsListAdapter(this, goodsList);
        mRecyclerView.setAdapter(mAdapter);
    }


}
