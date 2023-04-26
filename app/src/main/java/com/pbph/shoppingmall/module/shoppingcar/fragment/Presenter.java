package com.pbph.shoppingmall.module.shoppingcar.fragment;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.DelShoppingCartRequest;
import com.pbph.shoppingmall.model.request.GetShoppingCartRequest;
import com.pbph.shoppingmall.model.request.SaveGoodsFollowRequest;
import com.pbph.shoppingmall.model.request.UpdateShoppingCartRequest;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;
import com.utils.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //
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

        GetShoppingCartRequest<ShoppingCarResponse> request = new GetShoppingCartRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.shopType = 1;

        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT_MAX;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ShoppingCarResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ShoppingCarResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }
                        List<ShoppingCarResponse.DataBean.ListBean> list = vo.getData().getList();
                        if (list == null || list.isEmpty() || list.size() < Constant.Data.PAGE_COUNT_MAX) {
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


    @Override
    public void saveGoodsFollow(Map<Integer, Boolean> temps) {

        String ids = appendIds(temps);
        if (StringUtils.isEmpty(ids)) {
            getBaseView().toastShort("请选择");
            return;
        }

        SaveGoodsFollowRequest<ResultResponse> request = new SaveGoodsFollowRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.allCollectionFlag = 1;
        request.productIds = ids;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().toastShort("收藏成功");
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("收藏失败");
                    }
                }
        );
    }


    @Override
    public void updateShoppingCart(ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean retailBean) {
        UpdateShoppingCartRequest<ResultResponse> updateShoppingCartRequest = new UpdateShoppingCartRequest<>();
        updateShoppingCartRequest.customerId = MyApplication.userInfo.getCustomerId();
        updateShoppingCartRequest.mobile = MyApplication.userInfo.getMobile();

        updateShoppingCartRequest.shoppingCartId = retailBean.getPpid();
        updateShoppingCartRequest.goodsInfoId = retailBean.getGoodsInfoId();
        updateShoppingCartRequest.goodsNum = retailBean.getGoodsNum();

        updateShoppingCartRequest.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("修改购物车数量失败");
                    }
                }
        );
    }

    @Override
    public void delShoppingCart(Map<Integer, Boolean> temps) {


        String ids = appendIds(temps);
        if (StringUtils.isEmpty(ids)) {
            getBaseView().toastShort("请选择");
            return;
        }

        DelShoppingCartRequest<ResultResponse> delShoppingCartRequest = new DelShoppingCartRequest<>();
        delShoppingCartRequest.customerId = MyApplication.userInfo.getCustomerId();
        delShoppingCartRequest.mobile = MyApplication.userInfo.getMobile();

        delShoppingCartRequest.shoppingCartIds = ids;
        delShoppingCartRequest.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onDelShoppingCart(temps);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("删除失败");
                    }
                }
        );
    }


    String appendIds(Map<Integer, Boolean> temps) {
        StringBuilder sb = new StringBuilder();

        Iterator<Integer> iterator = temps.keySet().iterator();
        while (iterator.hasNext()) {
            int id = iterator.next();
            sb.append(",");
            sb.append(id);
        }
        if (sb.length() <= 0) {
            return null;
        }

        return sb.substring(1);
    }
}
