package com.pbph.shoppingmall.module.bill.submit;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.greendao.BillDefaultDao;
import com.pbph.shoppingmall.model.dao.BillDefault;
import com.pbph.shoppingmall.model.request.GetBillByIdRequest;
import com.pbph.shoppingmall.model.request.InsertBillRequest;
import com.pbph.shoppingmall.model.request.UpdateBillRequest;
import com.pbph.shoppingmall.model.response.GetBillByIdResponse;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.model.response.InsertBillResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        int id = getBaseView().getId();
        if (id == -1) return;
        getHttpBillById(id);
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }


    @Override
    public void getHttpBillById(int serId) {


        GetBillByIdRequest<GetBillByIdResponse> request = new GetBillByIdRequest<>();

        request.customerId = MyApplication.userInfo.getCustomerId();
        request.billId = serId;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetBillByIdResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetBillByIdResponse vo) throws Exception {
                        getBaseView().onGetHttpBillById(vo.getData());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("获取发票详情失败");
                    }
                }
        );


    }

    @Override
    public void submitHttpBill(InsertBillRequest<InsertBillResponse> request) {
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<InsertBillResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(InsertBillResponse vo) throws Exception {

                        request.id = vo.getData();

                        if (request.defaultVal == 1) {
                            BillDefault dVo = data2Dao(request, null);
                            submitDaoDefaultBill(dVo);
                        }

                        GetBillListResponse.DataBean res = new GetBillListResponse.DataBean();

                        res.setPpid(request.id);
                        res.setBillTitle(request.billTitle);
                        res.setBillType(String.valueOf(request.billType));
                        res.setBillParagraph(request.billParagraph);
                        res.setDefaultVal(request.defaultVal);
                        getBaseView().onSucc(res);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("插入发票详情失败");
                    }
                }
        );

    }

    @Override
    public void updateHttpBill(final UpdateBillRequest<ResultResponse> request) {


        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        if (request.defaultVal == 1) {
                            BillDefault dVo = data2Dao(null, request);
                            submitDaoDefaultBill(dVo);
                        }

                        GetBillListResponse.DataBean res = new GetBillListResponse.DataBean();
                        res.setPpid(request.id);
                        res.setBillTitle(request.billTitle);
                        res.setBillType(String.valueOf(request.billType));
                        res.setBillParagraph(request.billParagraph);
                        res.setDefaultVal(request.defaultVal);
                        getBaseView().onSucc(res);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("修改发票详情失败");
                    }
                }
        );
    }

    @Override
    public void submitDaoDefaultBill(BillDefault vo) {
        BillDefaultDao dao = MyApplication.getInstances().getDaoSession().getBillDefaultDao();
        dao.deleteAll();
        dao.insert(vo);

//        BillDefault rVo = selectDaoDefaultBill(vo);
//
//        if (rVo == null) {
//            dao.insert(vo);
//        } else {
//            vo.setId(rVo.getId());
//            dao.update(vo);
//        }
    }

//    BillDefault selectDaoDefaultBill(BillDefault request) {
//        BillDefaultDao dao = MyApplication.getInstances().getDaoSession().getBillDefaultDao();
//
//        List<BillDefault> list = dao.queryBuilder()//
//                .where(BillDefaultDao.Properties.BillSerId.eq(request.getBillSerId()))//
//                .list();
//        if (list == null || list.size() <= 0) return null;
//
//        return list.get(0);
//    }

    BillDefault data2Dao(InsertBillRequest<InsertBillResponse> ivo, UpdateBillRequest<ResultResponse> uvo) {
        BillDefault vo = new BillDefault();

        if (ivo != null) {
            vo.setBillSerId(ivo.id);
            vo.setBillTitle(ivo.billTitle);
            vo.setBillType(String.valueOf(ivo.billType));
            vo.setBillIdNum(ivo.billParagraph);
        }
        if (uvo != null) {
            vo.setBillSerId(uvo.id);
            vo.setBillTitle(uvo.billTitle);
            vo.setBillType(String.valueOf(uvo.billType));
            vo.setBillIdNum(uvo.billParagraph);
        }
        return vo;
    }
}
