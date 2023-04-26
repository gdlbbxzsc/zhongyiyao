package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/30.
 */

public class GetStoreCategoryByStoreIdResponse extends BaseResponesModel {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ppid : 18
         * storeCategoryName : 测试三
         */

        private int ppid;
        private String storeCategoryName;

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getStoreCategoryName() {
            return storeCategoryName;
        }

        public void setStoreCategoryName(String storeCategoryName) {
            this.storeCategoryName = storeCategoryName;
        }
    }
}
