package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class SaveAddressRequest<T extends BaseResponesModel> extends BaseRequest<T> {


    //    本地自用
    public int id;
    public String addressProvinceStr;
    public String addressCityStr;
    public String addressCountyStr;
    public int ppId;

    public Integer customerId;
    public String addressName;
    public Integer addressProvince;
    public Integer addressCity;
    public Integer addressCounty;
    public String addressDetail;
    public String addressMoblie;
//  N  public Integer addressPhone;
//  N  public String addressZip;
//  N  public String addressPay;
//  N  public String addressShip;
//  N  public String addressTime;
//  N  public String addressConfirm;
//  N  public String addressAlias;
//  N  public String addressStreet;
//  N  public String addressTelephone;
//  N  public String addressEmail;
    public Integer defaultVal;


    @Override
    public String getRequestPath() {
        return Constant.Path.SAVEADDRESS;
    }
}
