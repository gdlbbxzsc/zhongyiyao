package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetOrderResponse extends BaseResponesModel {

    /**
     * data : {"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"evaluateFlag":0,"ppid":15,"orderCode":"20180522150239732","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":111,"goodsInfoId":120,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色二尺六50*20)","goodsInfoSubtitle":"金立手机","ppid":14,"storeName":"admin"},{"goodsId":108,"goodsInfoId":108,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"旗袍1(黑色二尺六50*40)","goodsInfoSubtitle":"旗袍1","ppid":17,"storeName":"admin"}],"orderOldCode":"20180522150239732","orderPrice":120000,"orderStatus":15,"storeId":37},{"evaluateFlag":0,"ppid":16,"orderCode":"20180522150239869","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":110,"goodsInfoId":119,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525660731940.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"金立手机(黑色三尺三50*30)","goodsInfoSubtitle":"金立手机","ppid":15,"storeName":"admin"}],"orderOldCode":"20180522150239732","orderPrice":615,"orderStatus":21,"storeId":21},{"evaluateFlag":0,"ppid":17,"orderCode":"196676411300724737","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":109,"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色三尺三50*20)","goodsInfoSubtitle":"发生的发","ppid":16,"storeName":"admin"}],"orderOldCode":"196676411300724736","orderPrice":120000,"orderStatus":12,"storeId":37},{"evaluateFlag":1,"ppid":18,"orderCode":"196676411300724737","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":107,"goodsInfoId":104,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1523417518059.png","goodsInfoName":"测试商品27(黑色二尺六)","goodsInfoSubtitle":"测试商品","ppid":18,"storeName":"admin"},{"goodsId":127,"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色三尺三50*30)","goodsInfoSubtitle":"发生的发","ppid":19,"storeName":"admin"}],"orderOldCode":"196676411300724736","orderPrice":120000,"orderStatus":14,"storeId":37}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":4,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
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
         * endRowNum : 20
         * endRowNumApp : 0
         * firstPageNo : 1
         * lastPageNo : 1
         * list : [{"evaluateFlag":0,"ppid":15,"orderCode":"20180522150239732","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":111,"goodsInfoId":120,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色二尺六50*20)","goodsInfoSubtitle":"金立手机","ppid":14,"storeName":"admin"},{"goodsId":108,"goodsInfoId":108,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"旗袍1(黑色二尺六50*40)","goodsInfoSubtitle":"旗袍1","ppid":17,"storeName":"admin"}],"orderOldCode":"20180522150239732","orderPrice":120000,"orderStatus":15,"storeId":37},{"evaluateFlag":0,"ppid":16,"orderCode":"20180522150239869","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":110,"goodsInfoId":119,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525660731940.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"金立手机(黑色三尺三50*30)","goodsInfoSubtitle":"金立手机","ppid":15,"storeName":"admin"}],"orderOldCode":"20180522150239732","orderPrice":615,"orderStatus":21,"storeId":21},{"evaluateFlag":0,"ppid":17,"orderCode":"196676411300724737","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":109,"goodsInfoId":114,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色三尺三50*20)","goodsInfoSubtitle":"发生的发","ppid":16,"storeName":"admin"}],"orderOldCode":"196676411300724736","orderPrice":120000,"orderStatus":12,"storeId":37},{"evaluateFlag":1,"ppid":18,"orderCode":"196676411300724737","orderExpressType":0,"orderGoodsDtoList":[{"goodsId":107,"goodsInfoId":104,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1523417518059.png","goodsInfoName":"测试商品27(黑色二尺六)","goodsInfoSubtitle":"测试商品","ppid":18,"storeName":"admin"},{"goodsId":127,"goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色三尺三50*30)","goodsInfoSubtitle":"发生的发","ppid":19,"storeName":"admin"}],"orderOldCode":"196676411300724736","orderPrice":120000,"orderStatus":14,"storeId":37}]
         * nextPageNo : 1
         * pageNo : 1
         * pageSize : 20
         * prePageNo : 1
         * rows : 4
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
             * evaluateFlag : 0
             * ppid : 15
             * orderCode : 20180522150239732
             * orderExpressType : 0
             * orderGoodsDtoList : [{"goodsId":111,"goodsInfoId":120,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色二尺六50*20)","goodsInfoSubtitle":"金立手机","ppid":14,"storeName":"admin"},{"goodsId":108,"goodsInfoId":108,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"旗袍1(黑色二尺六50*40)","goodsInfoSubtitle":"旗袍1","ppid":17,"storeName":"admin"}]
             * orderOldCode : 20180522150239732
             * orderPrice : 120000
             * orderStatus : 15
             * storeId : 37
             */

            private int evaluateFlag;
            private int ppid;
            private String orderCode;
            private int orderExpressType;
            private String orderOldCode;
            private int orderPrice;
            private int orderStatus;
            private int storeId;
            private List<OrderGoodsDtoListBean> orderGoodsDtoList;

            public int getEvaluateFlag() {
                return evaluateFlag;
            }

            public void setEvaluateFlag(int evaluateFlag) {
                this.evaluateFlag = evaluateFlag;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getOrderExpressType() {
                return orderExpressType;
            }

            public void setOrderExpressType(int orderExpressType) {
                this.orderExpressType = orderExpressType;
            }

            public String getOrderOldCode() {
                return orderOldCode;
            }

            public void setOrderOldCode(String orderOldCode) {
                this.orderOldCode = orderOldCode;
            }

            public int getOrderPrice() {
                return orderPrice;
            }

            public void setOrderPrice(int orderPrice) {
                this.orderPrice = orderPrice;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public List<OrderGoodsDtoListBean> getOrderGoodsDtoList() {
                return orderGoodsDtoList;
            }

            public void setOrderGoodsDtoList(List<OrderGoodsDtoListBean> orderGoodsDtoList) {
                this.orderGoodsDtoList = orderGoodsDtoList;
            }

            public static class OrderGoodsDtoListBean {
                /**
                 * goodsId : 111
                 * goodsInfoId : 120
                 * goodsInfoImgUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160
                 * goodsInfoName : jin金立手机(黑色二尺六50*20)
                 * goodsInfoSubtitle : 金立手机
                 * ppid : 14
                 * storeName : admin
                 */

                private int goodsId;
                private int goodsInfoId;
                private String goodsInfoImgUrl;
                private String goodsInfoName;
                private String goodsInfoSubtitle;
                private int ppid;
                private String storeName;

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

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

                public String getGoodsInfoName() {
                    return goodsInfoName;
                }

                public void setGoodsInfoName(String goodsInfoName) {
                    this.goodsInfoName = goodsInfoName;
                }

                public String getGoodsInfoSubtitle() {
                    return goodsInfoSubtitle;
                }

                public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
                    this.goodsInfoSubtitle = goodsInfoSubtitle;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
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
