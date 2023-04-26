package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetAddressListResponse extends BaseResponesModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * addressCity : 2
         * addressCounty : 2
         * addressDetail : 阿萨
         * addressMoblie : 1321220254
         * addressName : 2
         * addressProvince : 2
         * cityName : 天津市
         * defaultVal : 1
         * districtName : 西城区
         * ppid : 7
         * provinceName : 天津
         */

        private int addressCity;
        private int addressCounty;
        private String addressDetail;
        private String addressMoblie;
        private String addressName;
        private int addressProvince;
        private String cityName;
        private int defaultVal;
        private String districtName;
        private int ppid;
        private String provinceName;

        public int getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(int addressCity) {
            this.addressCity = addressCity;
        }

        public int getAddressCounty() {
            return addressCounty;
        }

        public void setAddressCounty(int addressCounty) {
            this.addressCounty = addressCounty;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getAddressMoblie() {
            return addressMoblie;
        }

        public void setAddressMoblie(String addressMoblie) {
            this.addressMoblie = addressMoblie;
        }

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public int getAddressProvince() {
            return addressProvince;
        }

        public void setAddressProvince(int addressProvince) {
            this.addressProvince = addressProvince;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getDefaultVal() {
            return defaultVal;
        }

        public void setDefaultVal(int defaultVal) {
            this.defaultVal = defaultVal;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }
    }
}
