package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetCollectionSellerListResponse extends BaseResponesModel {

    /**
     * data : {"collectionSellerList":{"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"collectionNumber":0,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"},{"collectionNumber":0,"storeId":20,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage/admin/1506407144981.jpg","storeName":"鹏博科技"}],"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}}
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
         * collectionSellerList : {"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"collectionNumber":0,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"},{"collectionNumber":0,"storeId":20,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage/admin/1506407144981.jpg","storeName":"鹏博科技"}],"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
         */

        private CollectionSellerListBean collectionSellerList;

        public CollectionSellerListBean getCollectionSellerList() {
            return collectionSellerList;
        }

        public void setCollectionSellerList(CollectionSellerListBean collectionSellerList) {
            this.collectionSellerList = collectionSellerList;
        }

        public static class CollectionSellerListBean {
            /**
             * end : 1
             * endNo : 1
             * endRowNum : 15
             * endRowNumApp : 0
             * firstPageNo : 1
             * lastPageNo : 1
             * list : [{"collectionNumber":0,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"},{"collectionNumber":0,"storeId":20,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage/admin/1506407144981.jpg","storeName":"鹏博科技"}]
             * nextPageNo : 1
             * pageNo : 1
             * pageSize : 15
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
                 * collectionNumber : 0
                 * storeId : 21
                 * storeLogo : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg
                 * storeName : 富贵人家
                 */

                private int collectionNumber;
                private int storeId;
                private String storeLogo;
                private String storeName;
                private String overallMerit;

                public String getOverallMerit() {
                    return overallMerit;
                }

                public void setOverallMerit(String overallMerit) {
                    this.overallMerit = overallMerit;
                }

                public int getCollectionNumber() {
                    return collectionNumber;
                }

                public void setCollectionNumber(int collectionNumber) {
                    this.collectionNumber = collectionNumber;
                }

                public int getStoreId() {
                    return storeId;
                }

                public void setStoreId(int storeId) {
                    this.storeId = storeId;
                }

                public String getStoreLogo() {
                    return storeLogo;
                }

                public void setStoreLogo(String storeLogo) {
                    this.storeLogo = storeLogo;
                }

                public String getStoreName() {
                    return storeName;
                }

                public void setStoreName(String storeName) {
                    this.storeName = storeName;
                }
            }
        }
    }
}
