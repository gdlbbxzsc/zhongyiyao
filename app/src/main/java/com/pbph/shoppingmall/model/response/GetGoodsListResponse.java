package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/8.
 */

public class GetGoodsListResponse extends BaseResponesModel {


    /**
     * data : {"catId":36,"catName":"旗袍","goodsInfoList":{"end":0,"endNo":0,"endRowNum":15,
     * "endRowNumApp":2,"firstPageNo":1,"lastPageNo":0,"list":[],"nextPageNo":0,"pageNo":1,
     * "pageSize":15,"prePageNo":1,"rows":0,"start":1,"startNo":1,"startRowNum":0,
     * "startRowNumApp":0,"totalPages":0}}
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
         * catId : 36
         * catName : 旗袍
         * goodsInfoList : {"end":0,"endNo":0,"endRowNum":15,"endRowNumApp":2,"firstPageNo":1,
         * "lastPageNo":0,"list":[],"nextPageNo":0,"pageNo":1,"pageSize":15,"prePageNo":1,
         * "rows":0,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":0}
         */

        private int catId;
        private String catName;
        private GoodsInfoListBean goodsInfoList;

        public int getCatId() {
            return catId;
        }

        public void setCatId(int catId) {
            this.catId = catId;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public GoodsInfoListBean getGoodsInfoList() {
            return goodsInfoList;
        }

        public void setGoodsInfoList(GoodsInfoListBean goodsInfoList) {
            this.goodsInfoList = goodsInfoList;
        }

        public static class GoodsInfoListBean {
            /**
             * end : 0
             * endNo : 0
             * endRowNum : 15
             * endRowNumApp : 2
             * firstPageNo : 1
             * lastPageNo : 0
             * list : []
             * nextPageNo : 0
             * pageNo : 1
             * pageSize : 15
             * prePageNo : 1
             * rows : 0
             * start : 1
             * startNo : 1
             * startRowNum : 0
             * startRowNumApp : 0
             * totalPages : 0
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
            private List<?> list;

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

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }
        }
    }
}
