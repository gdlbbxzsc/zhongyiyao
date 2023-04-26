package com.pbph.shoppingmall.module.search.goods.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.search.goods.viewholder.SearchGoodsListViewHolder;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public class SearchGoodsListFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataAdapter adapter;


    int id = 0;

    @Override
    public int getid() {
        return id;
    }

    public static SearchGoodsListFragment newInstance(int id) {

        SearchGoodsListFragment f = new SearchGoodsListFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getInt("id") : 0;
    }


    @Override
    protected int layoutResID() {
        return R.layout.fragment_searchgoodslist;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void initView(View view) {
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());

        listView = view.findViewById(R.id.lv_shops1);

        adapter = new DataAdapter(this, listView, SearchGoodsListViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    boolean isSetUserVisibleHint;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        isSetUserVisibleHint = isVisibleToUser;

        if (isVisibleToUser && isOnResume) {
            presenter.postRxBus4setFilterDatas(true);
        } else {

        }
    }

    boolean isOnResume;

    @Override
    public void onResume() {
        super.onResume();
        isOnResume = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isOnResume = false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean vo = (SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean) adapter.getItem(position);
        startActivity(new Intent(getContext(), GoodsDetailActivity.class)
                .putExtra("goodsInfoId", vo.getGoodsInfoId())
        );
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {
        switch (rid) {
            case R.id.tv_go_shop: {//进入店铺
                SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean vo = (SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean) holder.item;
                startActivity(new Intent(getContext(), ShopActivity.class)
                        .putExtra("storeId", vo.getStoreId())
                );
            }
            break;
        }
    };

    @Override
    public void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore) {
        smartRefreshLayout.setEnableRefresh(finishRefresh);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(finishLoadMore);//是否启用上拉加载功能
    }

    @Override
    public void finishSmartRefresh() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean isVisibleFragMent() {
        return isSetUserVisibleHint;
    }
}
