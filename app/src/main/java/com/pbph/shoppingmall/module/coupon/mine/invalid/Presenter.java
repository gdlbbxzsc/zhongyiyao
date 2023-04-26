package com.pbph.shoppingmall.module.coupon.mine.invalid;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.message.CouponMineOperationMsg;
import com.pbph.shoppingmall.model.request.DeleteCouponNoRequest;
import com.pbph.shoppingmall.model.request.SelectMyCouponListRequest;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SelectMyCouponListResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;
import com.utils.StringUtils;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
        subscribeRxBus2delData();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2delData();
    }


    @Override
    public void getHttpDatas(boolean first) {
        if (first) {
            page_num = 1;
        }
        getHttpDatas(page_num);
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

        SelectMyCouponListRequest<SelectMyCouponListResponse> request = new SelectMyCouponListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.couponStatus = "4";//        2:未使用；3: 已使用；4:已过期
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<SelectMyCouponListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(SelectMyCouponListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }

                        List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list = vo.getData().getCouponList().getList();

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
    public void subscribeRxBus2delData() {
        RxBusF.register0(Presenter.this, CouponMineOperationMsg.class, it -> {

            switch (it.type) {
                case Op_Start_Del: {
                    getBaseView().delData(true);
                }
                break;
                case Op_Cancel_Del: {
                    getBaseView().delData(false);
                }
                break;
                case Op_Choose_All: {
                    getBaseView().chooseAll(true);
                }
                break;
                case Op_Choose_None: {
                    getBaseView().chooseAll(false);
                }
                break;
                case Op_Do_Del: {
                    doDel();
                }
                break;
            }
        });
    }

    @Override
    public void unSubscribeRxBus2delData() {
        RxBusF.removeDisposable0(Presenter.this, CouponMineOperationMsg.class);
    }

    @Override
    public void doDel() {

        final List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list = getBaseView().getDelIds();
        if (list == null || list.size() <= 0) return;

        StringBuilder sb = new StringBuilder();
        for (SelectMyCouponListResponse.DataBean.CouponListBean.ListBean vo : list) {
            sb.append(",");
            sb.append(vo.getCouponNoId());
        }

        String ids = sb.substring(1);

        if (StringUtils.isEmpty(ids)) return;

        DeleteCouponNoRequest<ResultResponse> request = new DeleteCouponNoRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.couponNoIds = ids;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().doDel(list);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("删除失败");
                    }
                }
        );
    }

}
