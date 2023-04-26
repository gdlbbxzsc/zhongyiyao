package com.pbph.shoppingmall.module.shopgoodstypelist.comprehensive;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.model.message.FilterDatasMsg;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.model.vo.ShopGoodsListOrGridBean;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public class ComprehensivePresenter<T extends ComprehensiveContract.View> extends
        BasePresenter<T> implements ComprehensiveContract.Presenter {

    List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean> params;
    List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands;
    FilterDatasMsg filterDatasMsg = new FilterDatasMsg();

    public ComprehensivePresenter(T baseView) {
        super(baseView);

    }

    @Override
    public void subscribe() {
        subscribeRxBus2getFilterDatas();

    }

    @Override
    public void unsubscribe() {
        unSubscribeRxBus2getFilterDatas();

    }


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
    @Override
    public void getHttpData(String keyword, String sort, Integer storeId, Integer isThird,
                            Integer startRowNum, Integer endRowNum, String catIds, String
                                        thirdCats, String brands, String priceMin, String
                                        priceMax) {
        SearchProductRequest<SearchProductResponse> searchProductRequest = new
                SearchProductRequest<>();
        searchProductRequest.customerId = UserInfo.getInstance().getCustomerId();
        searchProductRequest.keyword = keyword;
        searchProductRequest.sort = sort;
        searchProductRequest.storeId = storeId;
        searchProductRequest.isThird = isThird;
        searchProductRequest.pageNo = startRowNum;
        searchProductRequest.pageSize = endRowNum;
        searchProductRequest.catIds = catIds;
        searchProductRequest.thirdCats = thirdCats;
        searchProductRequest.brands = brands;
        searchProductRequest.priceMin = priceMin;
        searchProductRequest.priceMax = priceMax;
        searchProductRequest.request().subscribe(new LogoutConsumer<SearchProductResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(SearchProductResponse vo) throws Exception {
                getBaseView().setHttpData(vo.getData().getGoodsInfo().getGoodsInfoList().getData());

            }

        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();

            }
        });

    }

    @Override
    public void getGoodsTypeListData(SearchProductRequest<SearchProductResponse>
                                                 searchProductRequest) {
        searchProductRequest.request().subscribe(new LogoutConsumer<SearchProductResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(SearchProductResponse vo) throws Exception {
                getBaseView().setHttpData(vo.getData().getGoodsInfo().getGoodsInfoList().getData());
                params = vo.getData().getGoodsInfo().getParams();
                brands = vo.getData().getGoodsInfo().getBrands();
                postRxBus4setFilterDatas(false);

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                getBaseView().httpError();
            }
        });
    }

    @Override
    public void postRxBus4setFilterDatas(boolean isClearData) {
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
        RxBusF.register0(ComprehensivePresenter.this, FilterDatasResultMsg.class, it -> {
            getBaseView().setScreenData(it.brand, it.startPrice, it.endPrice, it.params);

        });
    }

    @Override
    public void unSubscribeRxBus2getFilterDatas() {
        RxBusF.removeDisposable0(ComprehensivePresenter.this, FilterDatasResultMsg.class);

    }

    @Override
    public void setRxBusAdapterViewHolderType() {
        RxBusF.register0(ShopGoodsListOrGridBean.RxString, ShopGoodsListOrGridBean.class, it -> {
            ShopGoodsListOrGridBean listOrGridBean = it;
           getBaseView().adapterViewHolderType(listOrGridBean.isCbx());

        });
    }

    @Override
    public void removeDisposable0() {
        RxBusF.removeDisposable0(ShopGoodsListOrGridBean.RxString);

    }
}
