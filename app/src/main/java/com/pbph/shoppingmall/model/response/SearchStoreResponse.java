package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SearchStoreResponse extends BaseResponesModel {

    /**
     * data : {"keyword":"富贵人家","sort":"5D","storeInfo":{"storeInfoList":{"data":[{"goodsInfoList":[{"goodsInfoId":113,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"}],"saleCount":800,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"}],"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":1,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}}}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * keyword : 富贵人家
         * sort : 5D
         * storeInfo : {"storeInfoList":{"data":[{"goodsInfoList":[{"goodsInfoId":113,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"}],"saleCount":800,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"}],"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":1,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}}
         */

        private String keyword;
        private String sort;
        private StoreInfoBean storeInfo;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public StoreInfoBean getStoreInfo() {
            return storeInfo;
        }

        public void setStoreInfo(StoreInfoBean storeInfo) {
            this.storeInfo = storeInfo;
        }

        public static class StoreInfoBean {
            /**
             * storeInfoList : {"data":[{"goodsInfoList":[{"goodsInfoId":113,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"}],"saleCount":800,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"}],"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":1,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
             */

            private StoreInfoListBean storeInfoList;

            public StoreInfoListBean getStoreInfoList() {
                return storeInfoList;
            }

            public void setStoreInfoList(StoreInfoListBean storeInfoList) {
                this.storeInfoList = storeInfoList;
            }

            public static class StoreInfoListBean {
                /**
                 * data : [{"goodsInfoList":[{"goodsInfoId":113,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"}],"saleCount":800,"storeId":21,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg","storeName":"富贵人家"}]
                 * end : 1
                 * endNo : 1
                 * endRowNum : 15
                 * endRowNumApp : 0
                 * firstPageNo : 1
                 * lastPageNo : 1
                 * nextPageNo : 1
                 * pageNo : 1
                 * pageSize : 15
                 * prePageNo : 1
                 * rows : 1
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
                private List<DataBean> data;

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

                public List<DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBean> data) {
                    this.data = data;
                }

                public static class DataBean {
                    /**
                     * goodsInfoList : [{"goodsInfoId":113,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"},{"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160"}]
                     * saleCount : 800
                     * storeId : 21
                     * storeLogo : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/21/1520237155062.jpg
                     * storeName : 富贵人家
                     */

                    private int saleCount;
                    private int storeId;
                    private String storeLogo;
                    private String storeName;
                    private List<GoodsInfoListBean> goodsInfoList;

                    public int getSaleCount() {
                        return saleCount;
                    }

                    public void setSaleCount(int saleCount) {
                        this.saleCount = saleCount;
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

                    public List<GoodsInfoListBean> getGoodsInfoList() {
                        return goodsInfoList;
                    }

                    public void setGoodsInfoList(List<GoodsInfoListBean> goodsInfoList) {
                        this.goodsInfoList = goodsInfoList;
                    }

                    public static class GoodsInfoListBean {
                        /**
                         * goodsInfoId : 113
                         * goodsInfoImgUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160
                         */

                        private int goodsInfoId;
                        private String goodsInfoImgUrl;

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getGoodsInfoImgUrl() {
                            return goodsInfoImgUrl;
                        }

                        public void setGoodsInfoImgUrl(String goodsInfoImgUrl) {
                            this.goodsInfoImgUrl = goodsInfoImgUrl;
                        }
                    }
                }
            }
        }
    }
}
