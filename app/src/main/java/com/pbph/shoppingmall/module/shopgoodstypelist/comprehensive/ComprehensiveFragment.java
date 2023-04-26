package com.pbph.shoppingmall.module.shopgoodstypelist.comprehensive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.shopgoodstypelist.TypeGoodsListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public class ComprehensiveFragment extends BaseFragmentV4<ComprehensivePresenter> implements ComprehensiveContract.View {

    private RecyclerView comprehensive_recycler;
    private TypeGoodsListAdapter typeShopListAdapter;
    //参数名
    private static final String SORT = "sort";
    private static final String PPID = "ppid";
    private static final String STOREID = "storeId";
    private int startPage = 1;

    private SmartRefreshLayout smartRefreshLayout;
    private String sort = "0D";
    private int ppid;
    private int storeId;

    private ComprehensiveItemDecoration comprehensiveItemDecoration;
    private ComprehensiveItemLinearDecoration comprehensiveItemLinearDecoration;
    SearchProductRequest<SearchProductResponse> searchProductRequest = new SearchProductRequest<>();

    @Override
    protected int layoutResID() {
        return R.layout.fragment_comprehensive;
    }

    @Override
    protected ComprehensivePresenter createPresenter() {
        return new ComprehensivePresenter(this);
    }

    @Override
    public void initView(View view) {
        comprehensive_recycler = view.findViewById(R.id.comprehensive_recycler);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        comprehensiveItemDecoration = new ComprehensiveItemDecoration(context);
        comprehensiveItemLinearDecoration = new ComprehensiveItemLinearDecoration(context);
        comprehensive_recycler.addItemDecoration(comprehensiveItemLinearDecoration);
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            startPage++;
            getGoodsTypeListData();

        });

        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            startPage = 1;
            getGoodsTypeListData();

        });
        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        initData();
    }

    public static ComprehensiveFragment newInstance(String sort, int ppid, int storeId) {
        ComprehensiveFragment fragment = new ComprehensiveFragment();
        Bundle args = new Bundle();
        args.putString(SORT, sort);
        args.putInt(PPID, ppid);
        args.putInt(STOREID, storeId);
        fragment.setArguments(args);

        return fragment;
    }

    public void initData() {
        if (getArguments() != null) {
            sort = getArguments().getString(SORT);
            ppid = getArguments().getInt(PPID, 0);
            storeId = getArguments().getInt(STOREID, 0);

        }
        typeShopListAdapter = new TypeGoodsListAdapter(context);
        comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 1));
        comprehensive_recycler.setHasFixedSize(true);
        comprehensive_recycler.setAdapter(typeShopListAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        getGoodsTypeListData();
        typeShopListAdapter.setTransmitData(goodsId -> {
            Intent intent = new Intent(context, GoodsDetailActivity.class);
            intent.putExtra("goodsInfoId", goodsId);
            context.startActivity(intent);
        });
        adapterViewHolderType(Constant.Data.listType);
        presenter.setRxBusAdapterViewHolderType();

    }

    /**
     * @param b
     */
    public void adapterViewHolderType(boolean b) {
        if (b == true) {
            Constant.Data.listType = b;
            typeShopListAdapter.setType(0);
            comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 1));
            comprehensive_recycler.removeItemDecoration(comprehensiveItemDecoration);
            comprehensive_recycler.removeItemDecoration(comprehensiveItemLinearDecoration);
            comprehensive_recycler.addItemDecoration(comprehensiveItemLinearDecoration);
            typeShopListAdapter.notifyDataSetChanged();
        }
        if (b == false) {
            //1：设置布局类型
            Constant.Data.listType = b;
            typeShopListAdapter.setType(1);
            //2：设置对应的布局管理器
            comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 2));
            //3：刷新adapter
            comprehensive_recycler.removeItemDecoration(comprehensiveItemDecoration);
            comprehensive_recycler.removeItemDecoration(comprehensiveItemLinearDecoration);
            comprehensive_recycler.addItemDecoration(comprehensiveItemDecoration);
            typeShopListAdapter.notifyDataSetChanged();
        }
    }

    private void getGoodsTypeListData() {
        /**
         * @param keyword     搜索关键字
         * @param sort        排序
         * @param storeId     商家id
         * @param isThird     是否第三方（0boss，i店铺）
         * @param startRowNum 开始页
         * @param endRowNum   结束页
         * @param catIds      直营分类id
         * @param thirdCats   第三方分类id
         * @param brands      品牌名称
         * @param priceMin    最低价格
         * @param priceMax    最高价格
         */
        searchProductRequest.customerId = UserInfo.getInstance().getCustomerId();
        searchProductRequest.sort = sort;
        searchProductRequest.storeId = storeId;
        searchProductRequest.thirdCats = String.valueOf(ppid);
        searchProductRequest.pageNo = startPage;
        searchProductRequest.pageSize = Constant.Data.PAGE_COUNT;
        searchProductRequest.catIds = "";
        presenter.getGoodsTypeListData(searchProductRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.removeDisposable0();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> strings) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if (startPage != 1) {
            typeShopListAdapter.addStrings(strings);
            return;
        }
        if (startPage == 1) {
            typeShopListAdapter.setStrings(strings);
            return;
        }
    }

    @Override
    public void setScreenData(String brands, String priceMin, String priceMax, String params) {
        getGoodsTypeListData();
    }

    @Override
    public void httpError() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.postRxBus4setFilterDatas(true);
        presenter.unSubscribeRxBus2getFilterDatas();

    }
}
