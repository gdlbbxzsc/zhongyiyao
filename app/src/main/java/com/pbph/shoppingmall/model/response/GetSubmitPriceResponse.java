package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetSubmitPriceResponse extends BaseResponesModel {

    /**
     * data : {"expressPrice":0,"orderPrice":1107,"totolPrice":1107}
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
         * expressPrice : 0
         * orderPrice : 1107
         * totolPrice : 1107
         */

        private int expressPrice;
        private int orderPrice;
        private int totolPrice;

        public int getExpressPrice() {
            return expressPrice;
        }

        public void setExpressPrice(int expressPrice) {
            this.expressPrice = expressPrice;
        }

        public int getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(int orderPrice) {
            this.orderPrice = orderPrice;
        }

        public int getTotolPrice() {
            return totolPrice;
        }

        public void setTotolPrice(int totolPrice) {
            this.totolPrice = totolPrice;
        }
    }
}
