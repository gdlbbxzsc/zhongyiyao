package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SelectMyCouponCountResponse extends BaseResponesModel {

    /**
     * data : {"expireCount":0,"unUsedCount":0,"usedCount":0}
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
         * expireCount : 0
         * unUsedCount : 0
         * usedCount : 0
         */

        private int expireCount;
        private int unUsedCount;
        private int usedCount;

        public int getExpireCount() {
            return expireCount;
        }

        public void setExpireCount(int expireCount) {
            this.expireCount = expireCount;
        }

        public int getUnUsedCount() {
            return unUsedCount;
        }

        public void setUnUsedCount(int unUsedCount) {
            this.unUsedCount = unUsedCount;
        }

        public int getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(int usedCount) {
            this.usedCount = usedCount;
        }
    }
}
