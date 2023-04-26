package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SelectSysAddressListResponse extends BaseResponesModel {

    /**
     * data : {"provinceList":[{"cityList":[{"cityName":"北京市","ppid":1,"districtList":[{"districtName":"东城区","ppid":1}]}],"ppid":1,"provinceName":"北京"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ProvinceListBean> provinceList;

        public List<ProvinceListBean> getProvinceList() {
            return provinceList;
        }

        public void setProvinceList(List<ProvinceListBean> provinceList) {
            this.provinceList = provinceList;
        }

        public static class ProvinceListBean {
            /**
             * cityList : [{"cityName":"北京市","ppid":1,"districtList":[{"districtName":"东城区","ppid":1}]}]
             * ppid : 1
             * provinceName : 北京
             */

            private int ppid;
            private String provinceName;
            private List<CityListBean> cityList;

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

            public List<CityListBean> getCityList() {
                return cityList;
            }

            public void setCityList(List<CityListBean> cityList) {
                this.cityList = cityList;
            }

            public static class CityListBean {
                /**
                 * cityName : 北京市
                 * ppid : 1
                 * districtList : [{"districtName":"东城区","ppid":1}]
                 */

                private String cityName;
                private int ppid;
                private List<DistrictListBean> districtList;

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
                }

                public List<DistrictListBean> getDistrictList() {
                    return districtList;
                }

                public void setDistrictList(List<DistrictListBean> districtList) {
                    this.districtList = districtList;
                }

                public static class DistrictListBean {
                    /**
                     * districtName : 东城区
                     * ppid : 1
                     */

                    private String districtName;
                    private int ppid;

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
                }
            }
        }
    }
}
