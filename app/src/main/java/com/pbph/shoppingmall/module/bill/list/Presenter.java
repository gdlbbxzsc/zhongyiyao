package com.pbph.shoppingmall.module.bill.list;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.greendao.BillDefaultDao;
import com.pbph.shoppingmall.model.dao.BillDefault;
import com.pbph.shoppingmall.model.request.DeleteBillRequest;
import com.pbph.shoppingmall.model.request.GetBillListRequest;
import com.pbph.shoppingmall.model.request.SetDefaultBillByIdRequest;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
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
    public void getHttpDatasFirstPage() {
        getHttpDatas(page_num = 1);
    }

    @Override
    public void getHttpDatas(int page) {

        GetBillListRequest<GetBillListResponse> request = new GetBillListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetBillListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetBillListResponse vo) throws Exception {
                        List<GetBillListResponse.DataBean> list = vo.getData();
                        getBaseView().setHttpDatas(list);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("获取列表失败");
                    }
                }
        );
    }

    @Override
    public void submitHttpDefaultBill(final GetBillListResponse.DataBean dataBean) {
        SetDefaultBillByIdRequest<ResultResponse> request = new SetDefaultBillByIdRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.billId = dataBean.getPpid();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {

                        submitDaoDefaultBill(dataBean);
                        getBaseView().flushDefaultBill(dataBean.getPpid());
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
    public void submitDaoDefaultBill(GetBillListResponse.DataBean request) {
        BillDefaultDao dao = MyApplication.getInstances().getDaoSession().getBillDefaultDao();

//        BillDefault rVo = selectDaoDefaultBill(request.getPpid());

        dao.deleteAll();
        BillDefault vo = data2Dao(request);
        dao.insert(vo);

//        if (rVo == null) {
//
//        } else {
//            vo.setId(rVo.getId());
//            dao.update(vo);
//        }
    }

    @Override
    public void delHttpBill(final int id) {
        DeleteBillRequest<ResultResponse> request = new DeleteBillRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.billId = id;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        delDaoDefaultBill(id);
                        getBaseView().flushDatas4Del(id);
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
    public void delDaoDefaultBill(int id) {
        BillDefaultDao dao = MyApplication.getInstances().getDaoSession().getBillDefaultDao();
        dao.deleteAll();

//        BillDefault rVo = selectDaoDefaultBill(id);
//
//        if (rVo == null)
//            return;
//        dao.delete(rVo);
    }

//    BillDefault selectDaoDefaultBill(int id) {
//        BillDefaultDao dao = MyApplication.getInstances().getDaoSession().getBillDefaultDao();
//
//        List<BillDefault> list = dao.queryBuilder()//
//                .where(BillDefaultDao.Properties.BillSerId.eq(id))//
//                .list();
//        if (list == null || list.size() <= 0) return null;
//
//        return list.get(0);
//    }

    BillDefault data2Dao(GetBillListResponse.DataBean request) {
        BillDefault vo = new BillDefault();
        vo.setBillSerId(request.getPpid());
        vo.setBillTitle(request.getBillTitle());
        vo.setBillType(request.getBillType());
        vo.setBillIdNum(request.getBillParagraph());

        return vo;
    }

}
