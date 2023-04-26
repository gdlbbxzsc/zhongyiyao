package com.pbph.shoppingmall.model.dao;

import com.pbph.mvp.base.model.BaseDaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Administrator on 2018/3/7.
 */

@Entity
public class AddressDefault extends BaseDaoModel {
    @Id(autoincrement = true)
    private Long id;//主键  自增长

    @NotNull   //不许为空
    private int addSerId;

    @NotNull   //不许为空
    private String addName;

    @NotNull   //不许为空
    private String addPhone;

    private String Province;
    private String City;
    private String District;

    private int ProvinceId;
    private int CityId;
    private int DistrictId;


    @NotNull   //不许为空
    private String addAddress;


    @Generated(hash = 1224610332)
    public AddressDefault(Long id, int addSerId, @NotNull String addName, @NotNull String addPhone,
            String Province, String City, String District, int ProvinceId, int CityId, int DistrictId,
            @NotNull String addAddress) {
        this.id = id;
        this.addSerId = addSerId;
        this.addName = addName;
        this.addPhone = addPhone;
        this.Province = Province;
        this.City = City;
        this.District = District;
        this.ProvinceId = ProvinceId;
        this.CityId = CityId;
        this.DistrictId = DistrictId;
        this.addAddress = addAddress;
    }


    @Generated(hash = 2117988109)
    public AddressDefault() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getAddSerId() {
        return this.addSerId;
    }


    public void setAddSerId(int addSerId) {
        this.addSerId = addSerId;
    }


    public String getAddName() {
        return this.addName;
    }


    public void setAddName(String addName) {
        this.addName = addName;
    }


    public String getAddPhone() {
        return this.addPhone;
    }


    public void setAddPhone(String addPhone) {
        this.addPhone = addPhone;
    }


    public String getProvince() {
        return this.Province;
    }


    public void setProvince(String Province) {
        this.Province = Province;
    }


    public String getCity() {
        return this.City;
    }


    public void setCity(String City) {
        this.City = City;
    }


    public String getDistrict() {
        return this.District;
    }


    public void setDistrict(String District) {
        this.District = District;
    }


    public String getAddAddress() {
        return this.addAddress;
    }


    public void setAddAddress(String addAddress) {
        this.addAddress = addAddress;
    }


    public int getProvinceId() {
        return this.ProvinceId;
    }


    public void setProvinceId(int ProvinceId) {
        this.ProvinceId = ProvinceId;
    }


    public int getCityId() {
        return this.CityId;
    }


    public void setCityId(int CityId) {
        this.CityId = CityId;
    }


    public int getDistrictId() {
        return this.DistrictId;
    }


    public void setDistrictId(int DistrictId) {
        this.DistrictId = DistrictId;
    }

}
