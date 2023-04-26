package com.pbph.shoppingmall.module.myscore;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.GetMyCustomerPointListRequest;
import com.pbph.shoppingmall.model.response.GetMyCustomerPointListResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
        getHttpDatas(page_num = 1);
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


        GetMyCustomerPointListRequest<GetMyCustomerPointListResponse> request = new GetMyCustomerPointListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<GetMyCustomerPointListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetMyCustomerPointListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }

                        List<GetMyCustomerPointListResponse.DataBean.ListBean> list = vo.getData().getList();

                        if (list == null || list.isEmpty()) {
                            page_num = -1;
                            getBaseView().enableSmartRefresh(true, false);
                        } else {
                            int count = 0;
                            for (GetMyCustomerPointListResponse.DataBean.ListBean temp : list) {
                                List<GetMyCustomerPointListResponse.DataBean.ListBean.PointListBean> tempList = temp.getPointList();

                                if (tempList == null) continue;

                                count+=tempList.size();

                            }
                            if (count  < Constant.Data.PAGE_COUNT){
                                page_num = -1;
                                getBaseView().enableSmartRefresh(true, false);
                            }else {
                                //加一页
                                page_num++;
                                getBaseView().enableSmartRefresh(true, true);
                            }

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
}
