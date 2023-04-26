package com.pbph.shoppingmall.module.shop.newtheshelves;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.response.GetStoreNewGoodsForPageResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.module.typegoodslist.comprehensive.ComprehensiveItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class NewTheShelvesFragment extends BaseFragmentV4<Presenter> implements Contract.View {

    private RecyclerView recycler_new_the_shelves;
    private NewTheShelvesAdapter newTheShelvesAdapter;
    private ComprehensiveItemDecoration comprehensiveItemDecoration;
    private SmartRefreshLayout smartRefreshLayout;
    private int startPage = 1;
    private int storeId;
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_newtheshelves;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        storeId = ((ShopActivity) context).storeId;
    }

    @Override
    public void initView(View view) {

        recycler_new_the_shelves = view.findViewById(R.id.recycler_new_the_shelves);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            startPage=1;
            presenter.getGetStoreNewGoodsForPage(storeId, startPage, Constant.Data.PAGE_COUNT);
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            startPage++;
            presenter.getGetStoreNewGoodsForPage(storeId, startPage, Constant.Data.PAGE_COUNT);
        });
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getGetStoreNewGoodsForPage(storeId, startPage, Constant.Data.PAGE_COUNT);
    }

    private void initData() {

        comprehensiveItemDecoration = new ComprehensiveItemDecoration(context);
        recycler_new_the_shelves.removeItemDecoration(comprehensiveItemDecoration);
        recycler_new_the_shelves.addItemDecoration(comprehensiveItemDecoration);
        newTheShelvesAdapter = new NewTheShelvesAdapter(context);
        newTheShelvesAdapter.setSetData(i -> {
            Intent intent = new Intent(context, GoodsDetailActivity.class);
            intent.putExtra("goodsInfoId",i);
            startActivity(intent);
        });
        recycler_new_the_shelves.setLayoutManager(new GridLayoutManager(context, 2));
        recycler_new_the_shelves.setHasFixedSize(true);
        recycler_new_the_shelves.setAdapter(newTheShelvesAdapter);

    }

    @Override
    public void setGetStoreNewGoodsForPage(List<GetStoreNewGoodsForPageResponse.DataBean
            .ListBean> list) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if (startPage==1){
            newTheShelvesAdapter.setList(list);
            return;
        }
        if (startPage!=1){
            newTheShelvesAdapter.addList(list);
            return;
        }
    }

    @Override
    public void httpError() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
