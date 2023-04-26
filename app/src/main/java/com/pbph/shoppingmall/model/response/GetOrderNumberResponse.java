package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetOrderNumberResponse extends BaseResponesModel {

    /**
     * data : {"notCollectGoodsNumber":1,"notEvaluateNumber":1,"notPayNumber":4,"retreatNumber":1}
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
         * notCollectGoodsNumber : 1
         * notEvaluateNumber : 1
         * notPayNumber : 4
         * retreatNumber : 1
         */

        private int notCollectGoodsNumber;
        private int notEvaluateNumber;
        private int notPayNumber;
        private int retreatNumber;

        public int getNotCollectGoodsNumber() {
            return notCollectGoodsNumber;
        }

        public void setNotCollectGoodsNumber(int notCollectGoodsNumber) {
            this.notCollectGoodsNumber = notCollectGoodsNumber;
        }

        public int getNotEvaluateNumber() {
            return notEvaluateNumber;
        }

        public void setNotEvaluateNumber(int notEvaluateNumber) {
            this.notEvaluateNumber = notEvaluateNumber;
        }

        public int getNotPayNumber() {
            return notPayNumber;
        }

        public void setNotPayNumber(int notPayNumber) {
            this.notPayNumber = notPayNumber;
        }

        public int getRetreatNumber() {
            return retreatNumber;
        }

        public void setRetreatNumber(int retreatNumber) {
            this.retreatNumber = retreatNumber;
        }
    }
}
