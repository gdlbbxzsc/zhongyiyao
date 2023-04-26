package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by 连嘉凡 on 2018/5/14.
 */

public class SaveThirdStoreCollectResponse extends BaseResponesModel {
    /**
     * data : {"collectionVal":1}
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
         * collectionVal : 1
         */

        private int collectionVal;

        public int getCollectionVal() {
            return collectionVal;
        }

        public void setCollectionVal(int collectionVal) {
            this.collectionVal = collectionVal;
        }
    }
}
