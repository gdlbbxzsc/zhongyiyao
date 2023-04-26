package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SubmitOrderResponse extends BaseResponesModel {

    /**
     * data : {"orderId":"199939883327053824","orderType":0}
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
         * orderId : 199939883327053824
         * orderType : 0
         */

        private String orderCode;

        private int orderType;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }
    }
}
