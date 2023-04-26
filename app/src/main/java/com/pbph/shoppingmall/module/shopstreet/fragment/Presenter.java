package com.pbph.shoppingmall.module.shopstreet.fragment;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.GetStoreStreetRequest;
import com.pbph.shoppingmall.model.response.GetStoreStreetResponse;
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
//        热销店铺 1
//        人气店铺 2
//        热门店铺 3
//        好评店铺 4


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

        GetStoreStreetRequest<GetStoreStreetResponse> request = new GetStoreStreetRequest<>();
        request.type = getBaseView().getTypeId();
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<GetStoreStreetResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetStoreStreetResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }

                        List<GetStoreStreetResponse.DataBean.ListBean> list = vo.getData().getList();

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
}
