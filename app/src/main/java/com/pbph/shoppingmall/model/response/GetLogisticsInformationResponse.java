package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetLogisticsInformationResponse extends BaseResponesModel {

    /**
     * data : {"logisticsList":[{"ppid":23,"orderId":25,"orderLogKdTime":1527063157000,"orderLogPerson":"168","orderLogReason":"已发货","orderLogStatus":1,"orderLogTime":1527143681000},{"ppid":22,"orderId":25,"orderLogKdTime":1527149552000,"orderLogPerson":"168","orderLogReason":"已下单","orderLogStatus":1,"orderLogTime":1527143643000}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private String orderCode;

        private List<LogisticsListBean> logisticsList;

        public List<LogisticsListBean> getLogisticsList() {
            return logisticsList;
        }


        public void setLogisticsList(List<LogisticsListBean> logisticsList) {
            this.logisticsList = logisticsList;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public static class LogisticsListBean {
            /**
             * ppid : 23
             * orderId : 25
             * orderLogKdTime : 1527063157000
             * orderLogPerson : 168
             * orderLogReason : 已发货
             * orderLogStatus : 1
             * orderLogTime : 1527143681000
             */

            private int ppid;
            private int orderId;
            private long orderLogKdTime;
            private String orderLogPerson;
            private String orderLogReason;
            private int orderLogStatus;
            private long orderLogTime;

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public long getOrderLogKdTime() {
                return orderLogKdTime;
            }

            public void setOrderLogKdTime(long orderLogKdTime) {
                this.orderLogKdTime = orderLogKdTime;
            }

            public String getOrderLogPerson() {
                return orderLogPerson;
            }

            public void setOrderLogPerson(String orderLogPerson) {
                this.orderLogPerson = orderLogPerson;
            }

            public String getOrderLogReason() {
                return orderLogReason;
            }

            public void setOrderLogReason(String orderLogReason) {
                this.orderLogReason = orderLogReason;
            }

            public int getOrderLogStatus() {
                return orderLogStatus;
            }

            public void setOrderLogStatus(int orderLogStatus) {
                this.orderLogStatus = orderLogStatus;
            }

            public long getOrderLogTime() {
                return orderLogTime;
            }

            public void setOrderLogTime(long orderLogTime) {
                this.orderLogTime = orderLogTime;
            }
        }
    }
}
