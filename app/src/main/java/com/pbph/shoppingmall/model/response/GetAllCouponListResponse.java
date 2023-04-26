package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetAllCouponListResponse extends BaseResponesModel {

    /**
     * data : {"allCouponList":{"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"activeSiteType":1,"couponActivityName":"儿童节","couponActivityRemark":"和你有关系吗","couponCount":10,"couponGetNum":0,"couponGetType":2,"couponPrice":50,"couponRulesType":2,"couponXPrice":200,"createTime":1526352213000,"delFlag":0,"endTime":1526352213000,"getStatus":0,"ppid":1,"modifyTime":1526352213000,"showVal":1,"startTime":1526438613000,"storeId":20},{"activeSiteType":2,"couponActivityName":"天天领","couponActivityRemark":"天天领啥","couponCount":5,"couponGetNum":2,"couponGetType":2,"couponPrice":10,"couponRulesType":2,"couponXPrice":100,"createTime":1526352535000,"delFlag":0,"endTime":1526352535000,"getStatus":0,"ppid":2,"modifyTime":1526352535000,"showVal":1,"startTime":1526438935000,"storeId":21}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":2,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}}
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
         * allCouponList : {"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"activeSiteType":1,"couponActivityName":"儿童节","couponActivityRemark":"和你有关系吗","couponCount":10,"couponGetNum":0,"couponGetType":2,"couponPrice":50,"couponRulesType":2,"couponXPrice":200,"createTime":1526352213000,"delFlag":0,"endTime":1526352213000,"getStatus":0,"ppid":1,"modifyTime":1526352213000,"showVal":1,"startTime":1526438613000,"storeId":20},{"activeSiteType":2,"couponActivityName":"天天领","couponActivityRemark":"天天领啥","couponCount":5,"couponGetNum":2,"couponGetType":2,"couponPrice":10,"couponRulesType":2,"couponXPrice":100,"createTime":1526352535000,"delFlag":0,"endTime":1526352535000,"getStatus":0,"ppid":2,"modifyTime":1526352535000,"showVal":1,"startTime":1526438935000,"storeId":21}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":2,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
         */

        private AllCouponListBean allCouponList;

        public AllCouponListBean getAllCouponList() {
            return allCouponList;
        }

        public void setAllCouponList(AllCouponListBean allCouponList) {
            this.allCouponList = allCouponList;
        }

        public static class AllCouponListBean {
            /**
             * end : 1
             * endNo : 1
             * endRowNum : 20
             * endRowNumApp : 0
             * firstPageNo : 1
             * lastPageNo : 1
             * list : [{"activeSiteType":1,"couponActivityName":"儿童节","couponActivityRemark":"和你有关系吗","couponCount":10,"couponGetNum":0,"couponGetType":2,"couponPrice":50,"couponRulesType":2,"couponXPrice":200,"createTime":1526352213000,"delFlag":0,"endTime":1526352213000,"getStatus":0,"ppid":1,"modifyTime":1526352213000,"showVal":1,"startTime":1526438613000,"storeId":20},{"activeSiteType":2,"couponActivityName":"天天领","couponActivityRemark":"天天领啥","couponCount":5,"couponGetNum":2,"couponGetType":2,"couponPrice":10,"couponRulesType":2,"couponXPrice":100,"createTime":1526352535000,"delFlag":0,"endTime":1526352535000,"getStatus":0,"ppid":2,"modifyTime":1526352535000,"showVal":1,"startTime":1526438935000,"storeId":21}]
             * nextPageNo : 1
             * pageNo : 1
             * pageSize : 20
             * prePageNo : 1
             * rows : 2
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
                 * activeSiteType : 1
                 * couponActivityName : 儿童节
                 * couponActivityRemark : 和你有关系吗
                 * couponCount : 10
                 * couponGetNum : 0
                 * couponGetType : 2
                 * couponPrice : 50
                 * couponRulesType : 2
                 * couponXPrice : 200
                 * createTime : 1526352213000
                 * delFlag : 0
                 * endTime : 1526352213000
                 * getStatus : 0
                 * ppid : 1
                 * modifyTime : 1526352213000
                 * showVal : 1
                 * startTime : 1526438613000
                 * storeId : 20
                 */

                private int activeSiteType;
                private String couponActivityName;
                private String couponActivityRemark;
                private int couponCount;
                private int couponGetNum;
                private int couponGetType;
                private int couponPrice;
                private int couponRulesType;
                private int couponXPrice;
                private long createTime;
                private int delFlag;
                private long endTime;
                private int getStatus;
                private int ppid;
                private long modifyTime;
                private int showVal;
                private long startTime;
                private int storeId;

                private String couponPic;

                public String getCouponPic() {
                    return couponPic;
                }

                public void setCouponPic(String couponPic) {
                    this.couponPic = couponPic;
                }

                public int getActiveSiteType() {
                    return activeSiteType;
                }

                public void setActiveSiteType(int activeSiteType) {
                    this.activeSiteType = activeSiteType;
                }

                public String getCouponActivityName() {
                    return couponActivityName;
                }

                public void setCouponActivityName(String couponActivityName) {
                    this.couponActivityName = couponActivityName;
                }

                public String getCouponActivityRemark() {
                    return couponActivityRemark;
                }

                public void setCouponActivityRemark(String couponActivityRemark) {
                    this.couponActivityRemark = couponActivityRemark;
                }

                public int getCouponCount() {
                    return couponCount;
                }

                public void setCouponCount(int couponCount) {
                    this.couponCount = couponCount;
                }

                public int getCouponGetNum() {
                    return couponGetNum;
                }

                public void setCouponGetNum(int couponGetNum) {
                    this.couponGetNum = couponGetNum;
                }

                public int getCouponGetType() {
                    return couponGetType;
                }

                public void setCouponGetType(int couponGetType) {
                    this.couponGetType = couponGetType;
                }

                public int getCouponPrice() {
                    return couponPrice;
                }

                public void setCouponPrice(int couponPrice) {
                    this.couponPrice = couponPrice;
                }

                public int getCouponRulesType() {
                    return couponRulesType;
                }

                public void setCouponRulesType(int couponRulesType) {
                    this.couponRulesType = couponRulesType;
                }

                public int getCouponXPrice() {
                    return couponXPrice;
                }

                public void setCouponXPrice(int couponXPrice) {
                    this.couponXPrice = couponXPrice;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public int getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(int delFlag) {
                    this.delFlag = delFlag;
                }

                public long getEndTime() {
                    return endTime;
                }

                public void setEndTime(long endTime) {
                    this.endTime = endTime;
                }

                public int getGetStatus() {
                    return getStatus;
                }

                public void setGetStatus(int getStatus) {
                    this.getStatus = getStatus;
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

                public int getShowVal() {
                    return showVal;
                }

                public void setShowVal(int showVal) {
                    this.showVal = showVal;
                }

                public long getStartTime() {
                    return startTime;
                }

                public void setStartTime(long startTime) {
                    this.startTime = startTime;
                }

                public int getStoreId() {
                    return storeId;
                }

                public void setStoreId(int storeId) {
                    this.storeId = storeId;
                }
            }
        }
    }
}
