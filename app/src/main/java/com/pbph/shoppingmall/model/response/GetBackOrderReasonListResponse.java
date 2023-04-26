package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetBackOrderReasonListResponse extends BaseResponesModel {

    /**
     * data : {"resonList":[{"ppid":1,"isBack":1,"reason":"发票信息有误/发票未开"},{"ppid":2,"isBack":1,"reason":"商品质量问题"},{"ppid":3,"isBack":1,"reason":"收到的商品与描述不符"},{"ppid":4,"isBack":1,"reason":"商品需要维修"},{"ppid":5,"isBack":1,"reason":"商品发错/漏发"},{"ppid":6,"isBack":1,"reason":"收到的商品破损"},{"ppid":7,"isBack":1,"reason":"收到假货"},{"ppid":8,"isBack":1,"reason":"7天无理由退货"},{"ppid":9,"isBack":1,"reason":"其他原因"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ResonListBean> resonList;

        public List<ResonListBean> getResonList() {
            return resonList;
        }

        public void setResonList(List<ResonListBean> resonList) {
            this.resonList = resonList;
        }

        public static class ResonListBean {
            /**
             * ppid : 1
             * isBack : 1
             * reason : 发票信息有误/发票未开
             */

            private int ppid;
            private int isBack;
            private String reason;

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getIsBack() {
                return isBack;
            }

            public void setIsBack(int isBack) {
                this.isBack = isBack;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }
    }
}
