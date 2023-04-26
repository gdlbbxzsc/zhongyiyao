package com.pbph.shoppingmall.module.shopnewgoods;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.response.GetStoreNewGoodsForPageResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.module.typegoodslist.comprehensive.ComprehensiveItemDecoration;
import com.pbph.shoppingmall.utils.ui.ShopSearchTitleBarType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/7/2.
 */

public class NewGoodsGoShelfActivity extends BaseActivity<Presenter> implements Contract.View {

    SmartRefreshLayout smartRefreshLayout ;
    RecyclerView recycler_new_the_shelves;
    NewTheShelvesAdapter newTheShelvesAdapter ;
    private ComprehensiveItemDecoration comprehensiveItemDecoration;
    private int startPage = 1;
    private int storeId;
    @Override
    protected int layoutResID() {
        return R.layout.activity_newgoodsgoshelf;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initData() {
        newTheShelvesAdapter = new NewTheShelvesAdapter(this);
        newTheShelvesAdapter.setSetData(i -> {
            Intent intent = new Intent(this, GoodsDetailActivity.class);
            intent.putExtra("goodsInfoId",i);
            startActivity(intent);
        });
        storeId = getIntent().getIntExtra("storeId",0);


    }

    @Override
    protected void initView() {
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            startPage=1;
            presenter.getGetStoreNewGoodsForPage(storeId, startPage, Constant.Data.PAGE_COUNT);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            startPage++;
            presenter.getGetStoreNewGoodsForPage(storeId, startPage, Constant.Data.PAGE_COUNT);
        });

        recycler_new_the_shelves = findViewById(R.id.recycler_new_the_shelves);
        comprehensiveItemDecoration = new ComprehensiveItemDecoration(context);
        recycler_new_the_shelves.removeItemDecoration(comprehensiveItemDecoration);
        recycler_new_the_shelves.addItemDecoration(comprehensiveItemDecoration);
        recycler_new_the_shelves.setLayoutManager(new GridLayoutManager(context, 2));
        recycler_new_the_shelves.setHasFixedSize(true);
        recycler_new_the_shelves.setAdapter(newTheShelvesAdapter);
        ShopSearchTitleBarType shopSearchTitleBarType = new ShopSearchTitleBarType(this, "请输入搜索关键字", true,
                SearchActivity.SEARCH_TYPE_SHOPS,storeId);

    }

    @Override
    public void setGetStoreNewGoodsForPage(List<GetStoreNewGoodsForPageResponse.DataBean
            .ListBean> listBeans) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if (startPage==1){
            newTheShelvesAdapter.setList(listBeans);
            return;
        }
        if (startPage!=1){
            newTheShelvesAdapter.addList(listBeans);
            return;
        }
    }

    @Override
    public void httpError() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getGetStoreNewGoodsForPage(storeId, startPage, Constant.Data.PAGE_COUNT);
    }
}
