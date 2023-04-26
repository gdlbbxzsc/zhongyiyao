package com.pbph.shoppingmall.module.coupon.search;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.greendao.CouponSearchRecordDao;
import com.pbph.shoppingmall.model.dao.CouponSearchRecord;
import com.pbph.shoppingmall.model.request.GetAllCouponListRequest;
import com.pbph.shoppingmall.model.request.ReceiveCouponRequest;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;
import com.utils.StringUtils;

import java.util.Date;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        selectSearchRecords();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    String search_text;

    @Override
    public void doSearch(String str) {

        if (StringUtils.isEmpty(str)) {
            search_text = str;
            return;
        }

        CouponSearchRecordDao dao = MyApplication.getInstances().getDaoSession().getCouponSearchRecordDao();

        List<CouponSearchRecord> list = dao.queryBuilder()//
                .where(CouponSearchRecordDao.Properties.SearchText.eq(str))//
                .list();

        if (list == null || list.size() <= 0) {
            CouponSearchRecord vo = new CouponSearchRecord();
            vo.setSearchText(str);
            vo.setCreateTime(new Date());
            dao.insert(vo);
        } else {
            CouponSearchRecord vo = list.get(0);
            vo.setCreateTime(new Date());
            dao.update(vo);
        }

        selectSearchRecords();

        if (StringUtils.isEqual(str, search_text)) {
            return;
        }
        search_text = str;

        page_num = 1;
        getHttpDatas(page_num);
    }

    @Override
    public void selectSearchRecords() {
        List<CouponSearchRecord> list = MyApplication.getInstances().getDaoSession().getCouponSearchRecordDao()//
                .queryBuilder()//
//                .where(SearchRecordDao.Properties.SearchType.eq(1))//
                .orderDesc(CouponSearchRecordDao.Properties.CreateTime)//
                .limit(10)//
                .list();
        getBaseView().initSearchRecords(list);
    }

    @Override
    public void deleteSearchRecords() {
        MyApplication.getInstances().getDaoSession().getCouponSearchRecordDao()//
                .deleteAll();
        getBaseView().initSearchRecords(null);
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

        GetAllCouponListRequest<GetAllCouponListResponse> request = new GetAllCouponListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.keyword = search_text;
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<GetAllCouponListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetAllCouponListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }
                        List<GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean> list = vo.getData().getAllCouponList().getList();

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
    public void receiveCouponRequest(int id) {

        ReceiveCouponRequest<ResultResponse> request = new ReceiveCouponRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.couponActivityId = id;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().receiveCouponRequest(id);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("领取失败");
                    }
                }
        );
    }
}
