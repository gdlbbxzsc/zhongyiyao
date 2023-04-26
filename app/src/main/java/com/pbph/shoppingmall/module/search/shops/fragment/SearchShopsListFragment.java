package com.pbph.shoppingmall.module.search.shops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchStoreResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public class SearchShopsListFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private SearchShopsListDataAdapter adapter;
    int img_wh;

    int id = 0;

    @Override
    public int getid() {
        return id;
    }

    public static SearchShopsListFragment newInstance(int id) {

        SearchShopsListFragment f = new SearchShopsListFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments() != null ? getArguments().getInt("id") : 0;
        scaleWH();
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_searchshopslist;
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

        adapter = new SearchShopsListDataAdapter(this, listView, SearchShopsListViewHolder.class);
        adapter.wh = img_wh;

        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean vo = (SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean) adapter.getItem(position);
        startActivity(new Intent(getContext(), ShopActivity.class)
                .putExtra("storeId", vo.getStoreId())
        );
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {
        SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean vo = (SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean) holder.item;
        switch (rid) {
            case R.id.tv_go_shop: {//进入店铺
                startActivity(new Intent(getContext(), ShopActivity.class)
                        .putExtra("storeId", vo.getStoreId())
                );
            }
            break;
            case R.id.iv_good1: {
                startActivity(new Intent(getContext(), GoodsDetailActivity.class)
                        .putExtra("goodsInfoId", vo.getGoodsInfoList().get(0).getGoodsInfoId())
                );
            }
            break;
            case R.id.iv_good2: {
                startActivity(new Intent(getContext(), GoodsDetailActivity.class)
                        .putExtra("goodsInfoId", vo.getGoodsInfoList().get(1).getGoodsInfoId())
                );
            }
            break;
            case R.id.iv_good3: {
                startActivity(new Intent(getContext(), GoodsDetailActivity.class)
                        .putExtra("goodsInfoId", vo.getGoodsInfoList().get(2).getGoodsInfoId())
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
    public void setHttpDatas(List<SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    private void scaleWH() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        img_wh = outMetrics.widthPixels;
        int wh = context.getResources().getDimensionPixelSize(R.dimen.dp_16) * (3);
        img_wh -= wh;
        img_wh /= 3;
    }
}
