package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetMyCollectionAndBrowseResponse extends BaseResponesModel {

    /**
     * data : {"browseRecordCount":0,"goodsCount":0,"storeCount":0}
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
         * browseRecordCount : 0
         * goodsCount : 0
         * storeCount : 0
         */

        private int browseRecordCount;
        private int goodsCount;
        private int storeCount;

        public int getBrowseRecordCount() {
            return browseRecordCount;
        }

        public void setBrowseRecordCount(int browseRecordCount) {
            this.browseRecordCount = browseRecordCount;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public int getStoreCount() {
            return storeCount;
        }

        public void setStoreCount(int storeCount) {
            this.storeCount = storeCount;
        }
    }
}
