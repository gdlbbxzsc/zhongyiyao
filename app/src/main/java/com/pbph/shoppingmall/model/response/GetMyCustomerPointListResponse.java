package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetMyCustomerPointListResponse extends BaseResponesModel {

    /**
     * data : {"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"month":"本月","pointList":[{"createTime":1544413154000,"customerId":283,"delFlag":0,"ppid":198,"modifyTime":1544413154000,"pointDetail":"商家端注册","pointScore":2,"pointType":1,"sourceVal":1},{"createTime":1544490921000,"customerId":283,"delFlag":0,"ppid":205,"modifyTime":1544490921000,"pointDetail":"登录","pointScore":1,"pointType":1,"sourceVal":1},{"createTime":1544492225000,"customerId":283,"delFlag":0,"ppid":208,"modifyTime":1544492225000,"pointDetail":"登录","pointScore":1,"pointType":1,"sourceVal":1}]},{"month":"11月","pointList":[{"createTime":1543368920000,"customerId":283,"delFlag":0,"ppid":207,"modifyTime":1543455320000,"pointDetail":"注册","pointScore":2,"pointType":1,"sourceVal":1},{"createTime":1543455763000,"customerId":283,"delFlag":0,"ppid":209,"modifyTime":1544492563000,"pointDetail":"注册","pointScore":2,"pointType":1,"sourceVal":1}]}],"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":5,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
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
         * end : 1
         * endNo : 1
         * endRowNum : 15
         * endRowNumApp : 0
         * firstPageNo : 1
         * lastPageNo : 1
         * list : [{"month":"本月","pointList":[{"createTime":1544413154000,"customerId":283,"delFlag":0,"ppid":198,"modifyTime":1544413154000,"pointDetail":"商家端注册","pointScore":2,"pointType":1,"sourceVal":1},{"createTime":1544490921000,"customerId":283,"delFlag":0,"ppid":205,"modifyTime":1544490921000,"pointDetail":"登录","pointScore":1,"pointType":1,"sourceVal":1},{"createTime":1544492225000,"customerId":283,"delFlag":0,"ppid":208,"modifyTime":1544492225000,"pointDetail":"登录","pointScore":1,"pointType":1,"sourceVal":1}]},{"month":"11月","pointList":[{"createTime":1543368920000,"customerId":283,"delFlag":0,"ppid":207,"modifyTime":1543455320000,"pointDetail":"注册","pointScore":2,"pointType":1,"sourceVal":1},{"createTime":1543455763000,"customerId":283,"delFlag":0,"ppid":209,"modifyTime":1544492563000,"pointDetail":"注册","pointScore":2,"pointType":1,"sourceVal":1}]}]
         * nextPageNo : 1
         * pageNo : 1
         * pageSize : 15
         * prePageNo : 1
         * rows : 5
         * start : 1
         * startNo : 1
         * startRowNum : 0
         * startRowNumApp : 0
         * totalPages : 1
         */

        private int end;
        private int endNo;
        private int endRowNum;
        private int endRowNumApp;
        private int firstPageNo;
        private int lastPageNo;
        private int nextPageNo;
        private int pageNo;
        private int pageSize;
        private int prePageNo;
        private int rows;
        private int start;
        private int startNo;
        private int startRowNum;
        private int startRowNumApp;
        private int totalPages;
        private List<ListBean> list;

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getEndNo() {
            return endNo;
        }

        public void setEndNo(int endNo) {
            this.endNo = endNo;
        }

        public int getEndRowNum() {
            return endRowNum;
        }

        public void setEndRowNum(int endRowNum) {
            this.endRowNum = endRowNum;
        }

        public int getEndRowNumApp() {
            return endRowNumApp;
        }

        public void setEndRowNumApp(int endRowNumApp) {
            this.endRowNumApp = endRowNumApp;
        }

        public int getFirstPageNo() {
            return firstPageNo;
        }

        public void setFirstPageNo(int firstPageNo) {
            this.firstPageNo = firstPageNo;
        }

        public int getLastPageNo() {
            return lastPageNo;
        }

        public void setLastPageNo(int lastPageNo) {
            this.lastPageNo = lastPageNo;
        }

        public int getNextPageNo() {
            return nextPageNo;
        }

        public void setNextPageNo(int nextPageNo) {
            this.nextPageNo = nextPageNo;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPrePageNo() {
            return prePageNo;
        }

        public void setPrePageNo(int prePageNo) {
            this.prePageNo = prePageNo;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getStartNo() {
            return startNo;
        }

        public void setStartNo(int startNo) {
            this.startNo = startNo;
        }

        public int getStartRowNum() {
            return startRowNum;
        }

        public void setStartRowNum(int startRowNum) {
            this.startRowNum = startRowNum;
        }

        public int getStartRowNumApp() {
            return startRowNumApp;
        }

        public void setStartRowNumApp(int startRowNumApp) {
            this.startRowNumApp = startRowNumApp;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * month : 本月
             * pointList : [{"createTime":1544413154000,"customerId":283,"delFlag":0,"ppid":198,"modifyTime":1544413154000,"pointDetail":"商家端注册","pointScore":2,"pointType":1,"sourceVal":1},{"createTime":1544490921000,"customerId":283,"delFlag":0,"ppid":205,"modifyTime":1544490921000,"pointDetail":"登录","pointScore":1,"pointType":1,"sourceVal":1},{"createTime":1544492225000,"customerId":283,"delFlag":0,"ppid":208,"modifyTime":1544492225000,"pointDetail":"登录","pointScore":1,"pointType":1,"sourceVal":1}]
             */

            private String month;
            private List<PointListBean> pointList;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public List<PointListBean> getPointList() {
                return pointList;
            }

            public void setPointList(List<PointListBean> pointList) {
                this.pointList = pointList;
            }

            public static class PointListBean {
                /**
                 * createTime : 1544413154000
                 * customerId : 283
                 * delFlag : 0
                 * ppid : 198
                 * modifyTime : 1544413154000
                 * pointDetail : 商家端注册
                 * pointScore : 2
                 * pointType : 1
                 * sourceVal : 1
                 */

                private long createTime;
                private int customerId;
                private int delFlag;
                private int ppid;
                private long modifyTime;
                private String pointDetail;
                private int pointScore;
                private int pointType;
                private int sourceVal;

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(int customerId) {
                    this.customerId = customerId;
                }

                public int getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(int delFlag) {
                    this.delFlag = delFlag;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
                }

                public long getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(long modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public String getPointDetail() {
                    return pointDetail;
                }

                public void setPointDetail(String pointDetail) {
                    this.pointDetail = pointDetail;
                }

                public int getPointScore() {
                    return pointScore;
                }

                public void setPointScore(int pointScore) {
                    this.pointScore = pointScore;
                }

                public int getPointType() {
                    return pointType;
                }

                public void setPointType(int pointType) {
                    this.pointType = pointType;
                }

                public int getSourceVal() {
                    return sourceVal;
                }

                public void setSourceVal(int sourceVal) {
                    this.sourceVal = sourceVal;
                }
            }
        }
    }
}
