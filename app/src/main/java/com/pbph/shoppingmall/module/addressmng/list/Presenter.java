package com.pbph.shoppingmall.module.addressmng.list;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.greendao.AddressDefaultDao;
import com.pbph.shoppingmall.model.dao.AddressDefault;
import com.pbph.shoppingmall.model.request.DeleteAddressRequest;
import com.pbph.shoppingmall.model.request.GetAddressListRequest;
import com.pbph.shoppingmall.model.request.SetDefaultAddressRequest;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
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

        GetAddressListRequest<GetAddressListResponse> request = new GetAddressListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetAddressListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetAddressListResponse vo) throws Exception {
                        List<GetAddressListResponse.DataBean> list = vo.getData();
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
    public void submitHttpDefaultAddress(GetAddressListResponse.DataBean dataBean) {

        SetDefaultAddressRequest<ResultResponse> request = new SetDefaultAddressRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.addressId = dataBean.getPpid();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {

                        submitDaoDefaultAddress(dataBean);
                        getBaseView().flushDefaultAddress(dataBean.getPpid());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("设置失败");
                    }
                }
        );


    }

    @Override
    public void submitDaoDefaultAddress(GetAddressListResponse.DataBean dataBean) {
        AddressDefaultDao dao = MyApplication.getInstances().getDaoSession().getAddressDefaultDao();

        dao.deleteAll();
        AddressDefault vo = data2Dao(dataBean);
        dao.insert(vo);

//        AddressDefault rVo = selectDaoDefaultAddress(dataBean.getPpid());
//
//
//        if (rVo == null) {
//
//        } else {
//            vo.setId(rVo.getId());
//            dao.update(vo);
//        }
    }

    @Override
    public void delHttpAddress(int id) {

        DeleteAddressRequest<ResultResponse> request = new DeleteAddressRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.addressId = id;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        delDaoDefaultAddress(id);
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
    public void delDaoDefaultAddress(int id) {
        AddressDefaultDao dao = MyApplication.getInstances().getDaoSession().getAddressDefaultDao();

        dao.deleteAll();

//        AddressDefault rVo = selectDaoDefaultAddress(id);
//
//        if (rVo == null)
//            return;
//        dao.delete(rVo);
    }

//    AddressDefault selectDaoDefaultAddress(int id) {
//        AddressDefaultDao dao = MyApplication.getInstances().getDaoSession().getAddressDefaultDao();
//
//        List<AddressDefault> list = dao.queryBuilder()//
//                .where(AddressDefaultDao.Properties.AddSerId.eq(id))//
//                .list();
//        if (list == null || list.size() <= 0) return null;
//
//        return list.get(0);
//    }

    AddressDefault data2Dao(GetAddressListResponse.DataBean addressRequest) {
        AddressDefault vo = new AddressDefault();


        vo.setAddSerId(addressRequest.getPpid());
        vo.setAddName(addressRequest.getAddressName());
        vo.setAddPhone(addressRequest.getAddressMoblie());
        vo.setAddAddress(addressRequest.getAddressDetail());

        vo.setProvinceId(addressRequest.getAddressProvince());
        vo.setCityId(addressRequest.getAddressCity());
        vo.setDistrictId(addressRequest.getAddressCounty());

        vo.setProvince(addressRequest.getProvinceName());
        vo.setCity(addressRequest.getCityName());
        vo.setDistrict(addressRequest.getDistrictName());


        return vo;
    }

}
