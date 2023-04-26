package com.pbph.shoppingmall.module.collect.shops.type;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.message.CollectEditMsg;
import com.pbph.shoppingmall.model.message.CollectShopTypeMsg;
import com.pbph.shoppingmall.model.request.GetCollectionSellerListRequest;
import com.pbph.shoppingmall.model.response.GetCollectionSellerListResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int type_id = -1;
    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
//        subscribeRxBus2changeEditState();
        subscribeRxBus2setTypeId();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2changeEditState();
        unSubscribeRxBus2setTypeId();
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
        GetCollectionSellerListRequest<GetCollectionSellerListResponse> request = new GetCollectionSellerListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.cateId = type_id;

        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<GetCollectionSellerListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetCollectionSellerListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }
                        List<GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean> list = vo.getData().getCollectionSellerList().getList();

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
    public void subscribeRxBus2changeEditState() {
        RxBusF.register0(Presenter.this, CollectEditMsg.class, it -> getBaseView().changeEditState(it.isCheck));
    }

    @Override
    public void unSubscribeRxBus2changeEditState() {
        RxBusF.removeDisposable0(Presenter.this, CollectEditMsg.class);
    }

    @Override
    public void subscribeRxBus2setTypeId() {
        RxBusF.register0(Presenter.this, CollectShopTypeMsg.class, it -> {
                    if (type_id == it.type_id) {
                        return;
                    }
                    type_id = it.type_id;
                    getHttpDatas(page_num = 1);
                }
        );
    }

    @Override
    public void unSubscribeRxBus2setTypeId() {
        RxBusF.removeDisposable0(Presenter.this, CollectShopTypeMsg.class);
    }

}
