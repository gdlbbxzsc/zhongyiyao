package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class PayMethodResponse extends BaseResponesModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ppid : 3
         * payDescribe : 推荐微信用户支付使用
         * payImage : 1
         * payName : 微信支付
         */

        private int ppid;
        private String payDescribe;
        private String payImage;
        private String payName;
        private boolean isChecked = false;
        private int payType;


        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getPayDescribe() {
            return payDescribe;
        }

        public void setPayDescribe(String payDescribe) {
            this.payDescribe = payDescribe;
        }

        public String getPayImage() {
            return payImage;
        }

        public void setPayImage(String payImage) {
            this.payImage = payImage;
        }

        public String getPayName() {
            return payName;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }
    }
}
