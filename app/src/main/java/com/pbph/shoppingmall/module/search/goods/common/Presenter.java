package com.pbph.shoppingmall.module.search.goods.common;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.message.FilterDatasMsg;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;
import com.pbph.shoppingmall.model.message.SearchText2GoodsItemMsg;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.utils.StringUtils;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;
    String searchText;

    String startPriceStr;
    String endPriceStr;

    String brandStr;

    String paramsStr;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        subscribeRxBus2getSearchText2Item();
        subscribeRxBus2getFilterDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2getSearchText2Item();
        unSubscribeRxBus2getFilterDatas();
    }


    List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean> params;
    List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands;

    @Override
    public void getHttpDatas(int page) {


        if (page != page_num) {
            return;
        }
        if (page == -1) {
            getBaseView().enableSmartRefresh(true, false);
            return;
        }
        if (page > 1) {
            getBaseView().enableSmartRefresh(true, true);
        }
        SearchProductRequest<SearchProductResponse> request = new SearchProductRequest<>();
        request.customerId = UserInfo.getInstance().getCustomerId();
        request.keyword = searchText;
        switch (getBaseView().getid()) {
            case 1:
                request.sort = "0D";
                break;
            case 2:
                request.sort = "22D";
                break;
        }
        request.pageNo = page;
        request.pageSize = Constant.Data.PAGE_COUNT;


        request.priceMin = startPriceStr;
        request.priceMax = endPriceStr;
        request.brands = brandStr;
        request.params = paramsStr;


        request.request().subscribe(new LogoutConsumer<SearchProductResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(SearchProductResponse vo) throws Exception {


                getBaseView().finishSmartRefresh();

                if (vo.getCode() != 200) {
                    getBaseView().toastShort(vo.getMsg());
                    return;
                }

                //如果 等于零 代表第一页 要清除数据
                if (page == page_num && page_num == 1) {
                    getBaseView().clearHttpDatas();
                }
                SearchProductResponse.DataBeanX.GoodsInfoBean info = vo.getData().getGoodsInfo();

                params = info.getParams();
                brands = info.getBrands();

                postRxBus4setFilterDatas(false);


                List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> list = info.getGoodsInfoList().getData();

                if (list == null || list.isEmpty() || list.size() < Constant.Data.PAGE_COUNT) {
                    page_num = -1;
                    getBaseView().enableSmartRefresh(true, false);
                } else {
                    //加一页
                    page_num++;
                    getBaseView().enableSmartRefresh(true, true);
                }
                ////向listview中加载数据
                getBaseView().setHttpDatas(list);
            }

        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                getBaseView().finishSmartRefresh();
                getBaseView().toastShort("获取失败");
            }
        });
    }

    @Override
    public void getHttpDatasFirstPage() {
        getHttpDatas(page_num = 1);
    }

    @Override
    public void getHttpDatasNextPage() {
        getHttpDatas(page_num);
    }

    @Override
    public void subscribeRxBus2getSearchText2Item() {
        RxBusF.register0(Presenter.this, SearchText2GoodsItemMsg.class, it -> {

            searchText = it.searchText;

            startPriceStr = null;
            endPriceStr = null;

            brandStr = null;

            paramsStr = null;

            if (StringUtils.isEmpty(it.searchText)) {
                getBaseView().clearHttpDatas();
                return;
            }
            getHttpDatas(page_num = 1);
        });
    }

    @Override
    public void unSubscribeRxBus2getSearchText2Item() {
        RxBusF.removeDisposable0(Presenter.this, SearchText2GoodsItemMsg.class);
    }

    FilterDatasMsg filterDatasMsg = new FilterDatasMsg();

    @Override
    public void postRxBus4setFilterDatas(boolean isClearData) {

        if (!getBaseView().isVisibleFragMent()) return;

        filterDatasMsg.isClearData = isClearData;

        if (isClearData) {
            filterDatasMsg.brands = brands = null;
            filterDatasMsg.params = params = null;
        } else {
            filterDatasMsg.brands = brands;
            filterDatasMsg.params = params;
        }

        RxBusF.post0(filterDatasMsg);
    }

    @Override
    public void subscribeRxBus2getFilterDatas() {
        RxBusF.register0(Presenter.this, FilterDatasResultMsg.class, it -> {
            if (!getBaseView().isVisibleFragMent()) return;

            startPriceStr = it.startPrice;
            endPriceStr = it.endPrice;

            brandStr = it.brand;

            paramsStr = it.params;

            getHttpDatas(page_num = 1);
        });
    }

    @Override
    public void unSubscribeRxBus2getFilterDatas() {
        RxBusF.removeDisposable0(Presenter.this, FilterDatasResultMsg.class);
    }

}
