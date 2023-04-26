package com.pbph.shoppingmall.module.search.shops.fragment;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.message.SearchText2ShopItemMsg;
import com.pbph.shoppingmall.model.request.SearchStoreRequest;
import com.pbph.shoppingmall.model.response.SearchStoreResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.utils.StringUtils;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;
    String searchText;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        subscribeRxBus2getSearchText2Item();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2getSearchText2Item();
    }


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

        SearchStoreRequest<SearchStoreResponse> request = new SearchStoreRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.keyword = searchText;

        switch (getBaseView().getid()) {
            case 1: {
                request.sort = "0D";
            }
            break;
            case 2: {
                request.sort = "5D";
            }
            break;
            case 3: {
                request.sort = "6D";
            }
            break;
        }

        request.request().subscribe(
                new LogoutConsumer<SearchStoreResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(SearchStoreResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }

                        List<SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean> list = null;

                        SearchStoreResponse.DataBeanX.StoreInfoBean temp = vo.getData().getStoreInfo();
                        SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean listvo = temp.getStoreInfoList();
                        if (listvo != null) list = listvo.getData();

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
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().finishSmartRefresh();
                        getBaseView().toastShort("获取失败");
                    }
                }
        );
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
        RxBusF.register0(Presenter.this, SearchText2ShopItemMsg.class, it -> {

            if (StringUtils.isEmpty(it.searchText)) {
                searchText = it.searchText;
                getBaseView().clearHttpDatas();
                return;
            }
            if (StringUtils.isEqual(it.searchText, searchText)) {
                return;
            }
            searchText = it.searchText;
            getHttpDatas(page_num = 1);
        });
    }

    @Override
    public void unSubscribeRxBus2getSearchText2Item() {
        RxBusF.removeDisposable0(Presenter.this, SearchText2ShopItemMsg.class);
    }

}
