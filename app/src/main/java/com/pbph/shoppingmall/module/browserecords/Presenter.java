package com.pbph.shoppingmall.module.browserecords;


import android.widget.Toast;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.DelBrowseRecordRequest;
import com.pbph.shoppingmall.model.request.GetBrowseRecordListRequest;
import com.pbph.shoppingmall.model.request.SaveShoppingCartRequest;
import com.pbph.shoppingmall.model.response.GetBrowseRecordListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveShoppingCartResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
        getHttpDatasFirstPage();
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

        GetBrowseRecordListRequest<GetBrowseRecordListResponse> request = new GetBrowseRecordListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<GetBrowseRecordListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetBrowseRecordListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }

                        List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> list = vo.getData().getGoodsInfo().getList();

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
    public void doDel(String id) {

        if (id == null) return;

        DelBrowseRecordRequest<ResultResponse> request = new DelBrowseRecordRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.goodsInfoIds = id;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().doDel();
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

    @Override
    public void addShoppingCar(int goodsInfoId, int goodsNum, int shopType, String clientType) {
        SaveShoppingCartRequest<SaveShoppingCartResponse> saveShoppingCartRequest = new
                SaveShoppingCartRequest<>();
        saveShoppingCartRequest.customerId = UserInfo.getInstance().getCustomerId();
        saveShoppingCartRequest.goodsInfoId = goodsInfoId;
        saveShoppingCartRequest.goodsNum = goodsNum;
        saveShoppingCartRequest.shopType = shopType;
//        saveShoppingCartRequest.mobile = UserInfo.getInstance().getMobile();
        saveShoppingCartRequest.request().subscribe(new LogoutConsumer<SaveShoppingCartResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(SaveShoppingCartResponse vo) throws Exception {
                Toast.makeText(getBaseView().getContext(), vo.getMsg(), Toast.LENGTH_SHORT).show();

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {

            }
        });
    }




}
