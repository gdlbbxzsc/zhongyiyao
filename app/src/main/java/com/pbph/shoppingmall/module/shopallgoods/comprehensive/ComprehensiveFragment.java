package com.pbph.shoppingmall.module.shopallgoods.comprehensive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.model.vo.ListOrGridBean;
import com.pbph.shoppingmall.module.typegoodslist.TypeGoodsListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public class ComprehensiveFragment extends BaseFragmentV4<ComprehensivePresenter> implements ComprehensiveContract
        .View {

    private RecyclerView comprehensive_recycler;

    private TypeGoodsListAdapter typeShopListAdapter;
    private String RxString = "com.pbph.shoppingmall.module.typeshoplist.comprehensive" +
            ".ComprehensiveFragment";
    //参数名
    private static final String ARG_PARAM1 = "workId";
    private static final String ARG_PARAM2 = "status";
    SearchProductRequest<SearchProductResponse> searchProductRequest = new SearchProductRequest<>();
    private int storeId;
    private String status;
    private int startPage = 1;
    private ComprehensiveItemDecoration comprehensiveItemDecoration;
    private ComprehensiveItemLinearDecoration comprehensiveItemLinearDecoration;
    private SmartRefreshLayout smartRefreshLayout;

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
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            startPage=1;
            getGoodsTypeListData("","","","");
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            startPage++;
            getGoodsTypeListData("","","","");
        });
        initData();
    }

    public static ComprehensiveFragment newInstance(int storeId, String status) {
        ComprehensiveFragment fragment = new ComprehensiveFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, storeId);
        args.putString(ARG_PARAM2, status);
        fragment.setArguments(args);
        return fragment;
    }


    public void initData() {
        if (getArguments() != null) {
            storeId = getArguments().getInt(ARG_PARAM1);
            status = getArguments().getString(ARG_PARAM2);
        }
        typeShopListAdapter = new TypeGoodsListAdapter(context);
        typeShopListAdapter.setTransmitData(goodsInfoId -> {
            Intent intent = new Intent();
            intent.setClass(context, GoodsDetailActivity.class);
            intent.putExtra("goodsInfoId",goodsInfoId);
            context.startActivity(intent);
        });
        comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 1));
        comprehensive_recycler.setHasFixedSize(true);
        comprehensive_recycler.setAdapter(typeShopListAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        getGoodsTypeListData("", "", "", "");
        RxBusF.register0(RxString, ListOrGridBean.class, it -> {
            ListOrGridBean listOrGridBean = it;
            if (listOrGridBean.isCbx() == true) {
                typeShopListAdapter.setType(0);
                comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 1));
                comprehensive_recycler.removeItemDecoration(comprehensiveItemDecoration);
                comprehensive_recycler.removeItemDecoration(comprehensiveItemLinearDecoration);
                comprehensive_recycler.addItemDecoration(comprehensiveItemLinearDecoration);
                typeShopListAdapter.notifyDataSetChanged();
            } else {

                //1：设置布局类型
                typeShopListAdapter.setType(1);
                //2：设置对应的布局管理器
                comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 2));
                //3：刷新adapter
                comprehensive_recycler.removeItemDecoration(comprehensiveItemLinearDecoration);
                comprehensive_recycler.removeItemDecoration(comprehensiveItemDecoration);
                comprehensive_recycler.addItemDecoration(comprehensiveItemDecoration);
                typeShopListAdapter.notifyDataSetChanged();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBusF.removeDisposable0(RxString);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> dataBeans) {
        smartRefreshLayout.finishLoadMore();
        smartRefreshLayout.finishRefresh();
        if (startPage == 1) {
            typeShopListAdapter.setStrings(dataBeans);
        } else {
            typeShopListAdapter.addStrings(dataBeans);
        }
    }

    @Override
    public void httpError() {
        smartRefreshLayout.finishLoadMore();
        smartRefreshLayout.finishRefresh();
    }

    private void getGoodsTypeListData(String brands, String priceMin, String priceMax, String
            params) {
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
//        searchProductRequest.keyword = "";
        searchProductRequest.sort = status;
        searchProductRequest.storeId = storeId;
        searchProductRequest.isThird = 1;
        searchProductRequest.pageNo = startPage;
        searchProductRequest.pageSize = Constant.Data.PAGE_COUNT;
//        searchProductRequest.catIds = cateId;
//        searchProductRequest.thirdCats = thirdCats;
        searchProductRequest.brands = brands;
        searchProductRequest.priceMin = priceMin;
        searchProductRequest.priceMax = priceMax;
        searchProductRequest.params = params;
        presenter.getGoodsTypeListData(searchProductRequest);

    }
}
