package com.pbph.shoppingmall.module.addressmng.submit;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.greendao.AddressDefaultDao;
import com.pbph.shoppingmall.model.dao.AddressDefault;
import com.pbph.shoppingmall.model.request.GetAddressRequest;
import com.pbph.shoppingmall.model.request.SaveAddressRequest;
import com.pbph.shoppingmall.model.request.SelectSysAddressListRequest;
import com.pbph.shoppingmall.model.request.UpdateAddressRequest;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.model.response.GetAddressResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveAddressResponse;
import com.pbph.shoppingmall.model.response.SelectSysAddressListResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        selectSysAddressList();
        int id = getBaseView().getId();
        if (id == -1) return;
        getHttpAddressById(id);
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }

    @Override
    public void selectSysAddressList() {

        SelectSysAddressListRequest<SelectSysAddressListResponse> request = new SelectSysAddressListRequest<>();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<SelectSysAddressListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(SelectSysAddressListResponse vo) throws Exception {
                        getBaseView().setSysAddressList(vo.getData().getProvinceList());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("获取省市区列表失败");
                    }
                }
        );
    }

    @Override
    public void getHttpAddressById(int serId) {

        GetAddressRequest<GetAddressResponse> request = new GetAddressRequest<>();

        request.customerId = MyApplication.userInfo.getCustomerId();
        request.addressId = serId;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetAddressResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetAddressResponse vo) throws Exception {

                        getBaseView().onGetHttpAddressById(vo.getData());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("获取地址详情失败");
                    }
                }
        );
    }

    @Override
    public void submitHttpAddress(SaveAddressRequest<SaveAddressResponse> request) {

        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<SaveAddressResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(SaveAddressResponse vo) throws Exception {

                        request.id = vo.getData().getPpid();

                        if (request.defaultVal == 1) {
                            AddressDefault dVo = data2Dao(request, null);
                            submitDaoDefaultAddress(dVo);
                        }

                        GetAddressListResponse.DataBean res = new GetAddressListResponse.DataBean();

                        res.setPpid(request.id);
                        res.setAddressName(request.addressName);
                        res.setAddressMoblie(request.addressMoblie);

                        res.setAddressProvince(request.addressProvince);
                        res.setAddressCity(request.addressCity);
                        res.setAddressCounty(request.addressCounty);

                        res.setProvinceName(request.addressProvinceStr);
                        res.setCityName(request.addressCityStr);
                        res.setDistrictName(request.addressCountyStr);

                        res.setAddressDetail(request.addressDetail);
                        res.setDefaultVal(request.defaultVal);
                        getBaseView().onSucc(res);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("插入地址详情失败");
                    }
                }
        );
    }

    @Override
    public void updateHttpAddress(UpdateAddressRequest<ResultResponse> request) {

        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        if (request.defaultVal == 1) {
                            AddressDefault dVo = data2Dao(null, request);
                            submitDaoDefaultAddress(dVo);
                        }

                        GetAddressListResponse.DataBean res = new GetAddressListResponse.DataBean();

                        res.setPpid(request.id);
                        res.setAddressName(request.addressName);
                        res.setAddressMoblie(request.addressMoblie);

                        res.setAddressProvince(request.addressProvince);
                        res.setAddressCity(request.addressCity);
                        res.setAddressCounty(request.addressCounty);

                        res.setProvinceName(request.addressProvinceStr);
                        res.setCityName(request.addressCityStr);
                        res.setDistrictName(request.addressCountyStr);

                        res.setAddressDetail(request.addressDetail);
                        res.setDefaultVal(request.defaultVal);
                        getBaseView().onSucc(res);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("修改地址详情失败");
                    }
                }
        );
    }

    @Override
    public void submitDaoDefaultAddress(AddressDefault vo) {
        AddressDefaultDao dao = MyApplication.getInstances().getDaoSession().getAddressDefaultDao();
        dao.deleteAll();
        dao.insert(vo);
//        AddressDefault rVo = selectDaoDefaultAddress(vo);
//
//        if (rVo == null) {
//            dao.insert(vo);
//        } else {
//            vo.setId(rVo.getId());
//            dao.update(vo);
//        }
    }

//    AddressDefault selectDaoDefaultAddress(AddressDefault vo) {
//        AddressDefaultDao dao = MyApplication.getInstances().getDaoSession().getAddressDefaultDao();
//
//        List<AddressDefault> list = dao.queryBuilder()//
//                .where(AddressDefaultDao.Properties.AddSerId.eq(vo.getAddSerId()))//
//                .list();
//        if (list == null || list.size() <= 0) return null;
//
//        return list.get(0);
//    }

    private AddressDefault data2Dao(SaveAddressRequest<SaveAddressResponse> ivo, UpdateAddressRequest<ResultResponse> uvo) {
        AddressDefault vo = new AddressDefault();

        if (ivo != null) {
            vo.setAddSerId(ivo.id);
            vo.setAddName(ivo.addressName);
            vo.setAddPhone(ivo.addressMoblie);
            vo.setAddAddress(ivo.addressDetail);

            vo.setProvinceId(ivo.addressProvince);
            vo.setCityId(ivo.addressCity);
            vo.setDistrictId(ivo.addressCounty);

            vo.setProvince(ivo.addressProvinceStr);
            vo.setCity(ivo.addressCityStr);
            vo.setDistrict(ivo.addressCountyStr);
        }
        if (uvo != null) {
            vo.setAddSerId(uvo.id);
            vo.setAddName(uvo.addressName);
            vo.setAddPhone(uvo.addressMoblie);
            vo.setAddAddress(uvo.addressDetail);

            vo.setProvinceId(uvo.addressProvince);
            vo.setCityId(uvo.addressCity);
            vo.setDistrictId(uvo.addressCounty);

            vo.setProvince(uvo.addressProvinceStr);
            vo.setCity(uvo.addressCityStr);
            vo.setDistrict(uvo.addressCountyStr);
        }
        return vo;
    }
}
