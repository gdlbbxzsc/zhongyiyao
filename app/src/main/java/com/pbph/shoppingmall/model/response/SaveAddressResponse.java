package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SaveAddressResponse extends BaseResponesModel {


    /**
     * data : {"ppid":82}
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
         * ppid : 82
         */

        private int ppid;

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }
    }
}
