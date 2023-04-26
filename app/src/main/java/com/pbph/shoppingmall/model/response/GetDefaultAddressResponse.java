package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetDefaultAddressResponse extends BaseResponesModel {

    /**
     * data : {"addressCity":3,"addressCounty":2,"addressDetail":"那次","addressMoblie":1312130201,"addressName":"1","addressProvince":2,"cityName":"石家庄市","defaultVal":1,"districtName":"西城区","ppid":4,"provinceName":"天津"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addressCity : 3
         * addressCounty : 2
         * addressDetail : 那次
         * addressMoblie : 1312130201
         * addressName : 1
         * addressProvince : 2
         * cityName : 石家庄市
         * defaultVal : 1
         * districtName : 西城区
         * ppid : 4
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
