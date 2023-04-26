package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SelectMyCouponListResponse extends BaseResponesModel {

    /**
     * data : {"couponList":{"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"activeSiteType":1,"codeNo":"123654546","couponCount":17,"couponEndTime":1529639100000,"couponGetNum":1,"couponGetType":0,"couponId":23,"couponName":"哈哈","couponNoId":2,"couponPic":null,"couponPrice":11,"couponRulesType":1,"couponStartTime":1514943900000,"couponXPrice":null,"storeId":21},{"activeSiteType":1,"codeNo":"122246545","couponCount":15,"couponEndTime":1527821220000,"couponGetNum":1,"couponGetType":0,"couponId":20,"couponName":"测试测试","couponNoId":1,"couponPic":null,"couponPrice":12,"couponRulesType":1,"couponStartTime":1514237100000,"couponXPrice":null,"storeId":21}],"nextPageNo":1,"objectBean":null,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1,"url":null}}
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
         * couponList : {"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"activeSiteType":1,"codeNo":"123654546","couponCount":17,"couponEndTime":1529639100000,"couponGetNum":1,"couponGetType":0,"couponId":23,"couponName":"哈哈","couponNoId":2,"couponPic":null,"couponPrice":11,"couponRulesType":1,"couponStartTime":1514943900000,"couponXPrice":null,"storeId":21},{"activeSiteType":1,"codeNo":"122246545","couponCount":15,"couponEndTime":1527821220000,"couponGetNum":1,"couponGetType":0,"couponId":20,"couponName":"测试测试","couponNoId":1,"couponPic":null,"couponPrice":12,"couponRulesType":1,"couponStartTime":1514237100000,"couponXPrice":null,"storeId":21}],"nextPageNo":1,"objectBean":null,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1,"url":null}
         */

        private CouponListBean couponList;

        public CouponListBean getCouponList() {
            return couponList;
        }

        public void setCouponList(CouponListBean couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean {
            /**
             * end : 1
             * endNo : 1
             * endRowNum : 15
             * endRowNumApp : 0
             * firstPageNo : 1
             * lastPageNo : 1
             * list : [{"activeSiteType":1,"codeNo":"123654546","couponCount":17,"couponEndTime":1529639100000,"couponGetNum":1,"couponGetType":0,"couponId":23,"couponName":"哈哈","couponNoId":2,"couponPic":null,"couponPrice":11,"couponRulesType":1,"couponStartTime":1514943900000,"couponXPrice":null,"storeId":21},{"activeSiteType":1,"codeNo":"122246545","couponCount":15,"couponEndTime":1527821220000,"couponGetNum":1,"couponGetType":0,"couponId":20,"couponName":"测试测试","couponNoId":1,"couponPic":null,"couponPrice":12,"couponRulesType":1,"couponStartTime":1514237100000,"couponXPrice":null,"storeId":21}]
             * nextPageNo : 1
             * objectBean : null
             * pageNo : 1
             * pageSize : 15
             * prePageNo : 1
             * rows : 2
             * start : 1
             * startNo : 1
             * startRowNum : 0
             * startRowNumApp : 0
             * totalPages : 1
             * url : null
             */

            private int end;
            private int endNo;
            private int endRowNum;
            private int endRowNumApp;
            private int firstPageNo;
            private int lastPageNo;
            private int nextPageNo;
            private Object objectBean;
            private int pageNo;
            private int pageSize;
            private int prePageNo;
            private int rows;
            private int start;
            private int startNo;
            private int startRowNum;
            private int startRowNumApp;
            private int totalPages;
            private Object url;
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

            public Object getObjectBean() {
                return objectBean;
            }

            public void setObjectBean(Object objectBean) {
                this.objectBean = objectBean;
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

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
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
                 * codeNo : 123654546
                 * couponCount : 17
                 * couponEndTime : 1529639100000
                 * couponGetNum : 1
                 * couponGetType : 0
                 * couponId : 23
                 * couponName : 哈哈
                 * couponNoId : 2
                 * couponPic : null
                 * couponPrice : 11
                 * couponRulesType : 1
                 * couponStartTime : 1514943900000
                 * couponXPrice : null
                 * storeId : 21
                 */

                private int activeSiteType;
                private String codeNo;
                private int couponCount;
                private long couponEndTime;
                private int couponGetNum;
                private int couponGetType;
                private int couponId;
                private String couponName;
                private int couponNoId;
                private Object couponPic;
                private int couponPrice;
                private int couponRulesType;
                private long couponStartTime;
                private int couponXPrice;
                private int storeId;

                public int getActiveSiteType() {
                    return activeSiteType;
                }

                public void setActiveSiteType(int activeSiteType) {
                    this.activeSiteType = activeSiteType;
                }

                public String getCodeNo() {
                    return codeNo;
                }

                public void setCodeNo(String codeNo) {
                    this.codeNo = codeNo;
                }

                public int getCouponCount() {
                    return couponCount;
                }

                public void setCouponCount(int couponCount) {
                    this.couponCount = couponCount;
                }

                public long getCouponEndTime() {
                    return couponEndTime;
                }

                public void setCouponEndTime(long couponEndTime) {
                    this.couponEndTime = couponEndTime;
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

                public int getCouponId() {
                    return couponId;
                }

                public void setCouponId(int couponId) {
                    this.couponId = couponId;
                }

                public String getCouponName() {
                    return couponName;
                }

                public void setCouponName(String couponName) {
                    this.couponName = couponName;
                }

                public int getCouponNoId() {
                    return couponNoId;
                }

                public void setCouponNoId(int couponNoId) {
                    this.couponNoId = couponNoId;
                }

                public Object getCouponPic() {
                    return couponPic;
                }

                public void setCouponPic(Object couponPic) {
                    this.couponPic = couponPic;
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

                public long getCouponStartTime() {
                    return couponStartTime;
                }

                public void setCouponStartTime(long couponStartTime) {
                    this.couponStartTime = couponStartTime;
                }

                public int getCouponXPrice() {
                    return couponXPrice;
                }

                public void setCouponXPrice(int couponXPrice) {
                    this.couponXPrice = couponXPrice;
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
