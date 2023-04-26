package com.pbph.shoppingmall.module.message.type.logistics;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.GetMyMessageListRequest;
import com.pbph.shoppingmall.model.request.GetOrderRequest;
import com.pbph.shoppingmall.model.response.GetMyMessageListResponse;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import java.util.ArrayList;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

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
        GetMyMessageListRequest<GetMyMessageListResponse> request = new GetMyMessageListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;
        request.messageType = 1;
        request.request().subscribe(
                new LogoutConsumer<GetMyMessageListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetMyMessageListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }
                        List<GetMyMessageListResponse.DataBean.MessageListBean.ListBean> list = vo.getData().getMessageList().getList();

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
}
