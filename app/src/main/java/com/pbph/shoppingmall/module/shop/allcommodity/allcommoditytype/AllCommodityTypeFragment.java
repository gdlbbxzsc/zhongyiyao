package com.pbph.shoppingmall.module.shop.allcommodity.allcommoditytype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

public class AllCommodityTypeFragment extends BaseFragmentV4<ComprehensivePresenter> implements
        ComprehensiveContract.View {

    SearchProductRequest<SearchProductResponse> searchProductRequest = new SearchProductRequest<>();
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView comprehensive_recycler;
    private TypeGoodsListAdapter typeGoodsListAdapter;

    //参数名
    private static final String ARG_PARAM1 = "storeId";
    private static final String ARG_PARAM2 = "status";
    private int storeId;//店铺id
    private String status;//状态
    int startPage = 1;//分页


    @Override
    protected int layoutResID() {
        return R.layout.fragment_comprehensive;
    }

    @Override
    protected ComprehensivePresenter createPresenter() {
        return new ComprehensivePresenter(this);
    }


    public static AllCommodityTypeFragment newInstance(int storeId, String status) {
        AllCommodityTypeFragment fragment = new AllCommodityTypeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, storeId);
        args.putString(ARG_PARAM2, status);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initView(View view) {
        comprehensive_recycler = view.findViewById(R.id.comprehensive_recycler);
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            startPage=1;
            getGoodsTypeListData("", "", "", "");
            refreshLayout.getLayout().postDelayed(() -> {
                refreshLayout.finishRefresh();
            }, 1000);
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            startPage++;
            getGoodsTypeListData("", "", "", "");
            refreshLayout.getLayout().postDelayed(() -> {
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        initData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initData() {
        typeGoodsListAdapter = new TypeGoodsListAdapter(context);
        typeGoodsListAdapter.setType(0);
        typeGoodsListAdapter.setTransmitData(goodsId -> {
            context.startActivity(new Intent(context, GoodsDetailActivity.class).putExtra
                    ("goodsInfoId", goodsId));
        });
        comprehensive_recycler.setLayoutManager(new LinearLayoutManager(context));
        comprehensive_recycler.setHasFixedSize(true);
        comprehensive_recycler.setAdapter(typeGoodsListAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            storeId = getArguments().getInt(ARG_PARAM1);
            status = getArguments().getString(ARG_PARAM2);
        }
        getGoodsTypeListData("", "", "", "");
        adapterViewHolderType(Constant.Data.listType);
        presenter.setRxBusAdapterViewHolderType();

    }



    private void getGoodsTypeListData(String brands, String priceMin, String priceMax, String params) {
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
        searchProductRequest.sort = status;
        searchProductRequest.storeId = storeId;
        searchProductRequest.pageNo = startPage;
        searchProductRequest.pageSize = Constant.Data.PAGE_COUNT;
        searchProductRequest.catIds = "";



        presenter.getGoodsTypeListData(searchProductRequest);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBusF.removeDisposable0(ListOrGridBean.RxString);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean
            .DataBean> strings) {
        smartRefreshLayout.finishLoadMore();
        smartRefreshLayout.finishRefresh();
        if (startPage==1){
            typeGoodsListAdapter.setStrings(strings);
       return;
        }
        if (startPage!= 1){
            typeGoodsListAdapter.addStrings(strings);
            return;
        }
    }

    @Override
    public void httpError() {
        smartRefreshLayout.finishLoadMore();
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void adapterViewHolderType(boolean b) {
        if (b== true) {
            Constant.Data.listType = b;
            typeGoodsListAdapter.setType(0);
            comprehensive_recycler.setLayoutManager(new LinearLayoutManager(context));
            typeGoodsListAdapter.notifyDataSetChanged();
        } else {
            Constant.Data.listType = b;
            //1：设置布局类型
            typeGoodsListAdapter.setType(1);
            //2：设置对应的布局管理器
            comprehensive_recycler.setLayoutManager(new GridLayoutManager(context, 2));
            //3：刷新adapter
            typeGoodsListAdapter.notifyDataSetChanged();
        }
    }
}
