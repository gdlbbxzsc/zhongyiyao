package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetCustomerFollowListResponse extends BaseResponesModel {

    /**
     * data : {"goodsInfo":{"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"commcont":1,"goodsId":108,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041307294","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"旗袍1(黑色三尺三50*20)","subtitle":"旗袍1","thirdVal":0,"warePrice":123},{"commcont":0,"goodsId":109,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":112,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041309281","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"上岛咖啡还看书(黑色二尺六50*20)","subtitle":"发生的发","thirdVal":0,"warePrice":123},{"commcont":0,"goodsId":109,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041309284","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"上岛咖啡还看书(黑色三尺三50*30)","subtitle":"发生的发","thirdVal":0,"warePrice":123}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":3,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}}
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
         * goodsInfo : {"end":1,"endNo":1,"endRowNum":20,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"list":[{"commcont":1,"goodsId":108,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041307294","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"旗袍1(黑色三尺三50*20)","subtitle":"旗袍1","thirdVal":0,"warePrice":123},{"commcont":0,"goodsId":109,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":112,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041309281","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"上岛咖啡还看书(黑色二尺六50*20)","subtitle":"发生的发","thirdVal":0,"warePrice":123},{"commcont":0,"goodsId":109,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041309284","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"上岛咖啡还看书(黑色三尺三50*30)","subtitle":"发生的发","thirdVal":0,"warePrice":123}],"nextPageNo":1,"pageNo":1,"pageSize":20,"prePageNo":1,"rows":3,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1}
         */

        private GoodsInfoBean goodsInfo;

        public GoodsInfoBean getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(GoodsInfoBean goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public static class GoodsInfoBean {
            /**
             * end : 1
             * endNo : 1
             * endRowNum : 20
             * endRowNumApp : 0
             * firstPageNo : 1
             * lastPageNo : 1
             * list : [{"commcont":1,"goodsId":108,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041307294","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"旗袍1(黑色三尺三50*20)","subtitle":"旗袍1","thirdVal":0,"warePrice":123},{"commcont":0,"goodsId":109,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":112,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041309281","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"上岛咖啡还看书(黑色二尺六50*20)","subtitle":"发生的发","thirdVal":0,"warePrice":123},{"commcont":0,"goodsId":109,"goodsInfoAddedVal":"1","goodsInfoCostPrice":123,"goodsInfoDelflag":"0","goodsInfoId":115,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoItemNo":"201805041309284","goodsInfoMarketPrice":123,"goodsInfoPreferPrice":123,"goodsInfoStock":0,"goodsInfoWeight":123,"praise":0,"productName":"上岛咖啡还看书(黑色三尺三50*30)","subtitle":"发生的发","thirdVal":0,"warePrice":123}]
             * nextPageNo : 1
             * pageNo : 1
             * pageSize : 20
             * prePageNo : 1
             * rows : 3
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
                 * commcont : 1
                 * goodsId : 108
                 * goodsInfoAddedVal : 1
                 * goodsInfoCostPrice : 123
                 * goodsInfoDelflag : 0
                 * goodsInfoId : 109
                 * goodsInfoImgUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160
                 * goodsInfoItemNo : 201805041307294
                 * goodsInfoMarketPrice : 123
                 * goodsInfoPreferPrice : 123
                 * goodsInfoStock : 0
                 * goodsInfoWeight : 123
                 * praise : 0
                 * productName : 旗袍1(黑色三尺三50*20)
                 * subtitle : 旗袍1
                 * thirdVal : 0
                 * warePrice : 123
                 */

                private int commcont;
                private int goodsId;
                private String goodsInfoAddedVal;
                private int goodsInfoCostPrice;
                private String goodsInfoDelflag;
                private int goodsInfoId;
                private String goodsInfoImgUrl;
                private String goodsInfoItemNo;
                private int goodsInfoMarketPrice;
                private int goodsInfoPreferPrice;
                private int goodsInfoStock;
                private int goodsInfoWeight;
                private int praise;
                private String productName;
                private String subtitle;
                private int thirdVal;
                private int warePrice;

                public int getCommcont() {
                    return commcont;
                }

                public void setCommcont(int commcont) {
                    this.commcont = commcont;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public String getGoodsInfoAddedVal() {
                    return goodsInfoAddedVal;
                }

                public void setGoodsInfoAddedVal(String goodsInfoAddedVal) {
                    this.goodsInfoAddedVal = goodsInfoAddedVal;
                }

                public int getGoodsInfoCostPrice() {
                    return goodsInfoCostPrice;
                }

                public void setGoodsInfoCostPrice(int goodsInfoCostPrice) {
                    this.goodsInfoCostPrice = goodsInfoCostPrice;
                }

                public String getGoodsInfoDelflag() {
                    return goodsInfoDelflag;
                }

                public void setGoodsInfoDelflag(String goodsInfoDelflag) {
                    this.goodsInfoDelflag = goodsInfoDelflag;
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

                public String getGoodsInfoItemNo() {
                    return goodsInfoItemNo;
                }

                public void setGoodsInfoItemNo(String goodsInfoItemNo) {
                    this.goodsInfoItemNo = goodsInfoItemNo;
                }

                public int getGoodsInfoMarketPrice() {
                    return goodsInfoMarketPrice;
                }

                public void setGoodsInfoMarketPrice(int goodsInfoMarketPrice) {
                    this.goodsInfoMarketPrice = goodsInfoMarketPrice;
                }

                public int getGoodsInfoPreferPrice() {
                    return goodsInfoPreferPrice;
                }

                public void setGoodsInfoPreferPrice(int goodsInfoPreferPrice) {
                    this.goodsInfoPreferPrice = goodsInfoPreferPrice;
                }

                public int getGoodsInfoStock() {
                    return goodsInfoStock;
                }

                public void setGoodsInfoStock(int goodsInfoStock) {
                    this.goodsInfoStock = goodsInfoStock;
                }

                public int getGoodsInfoWeight() {
                    return goodsInfoWeight;
                }

                public void setGoodsInfoWeight(int goodsInfoWeight) {
                    this.goodsInfoWeight = goodsInfoWeight;
                }

                public int getPraise() {
                    return praise;
                }

                public void setPraise(int praise) {
                    this.praise = praise;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public int getThirdVal() {
                    return thirdVal;
                }

                public void setThirdVal(int thirdVal) {
                    this.thirdVal = thirdVal;
                }

                public int getWarePrice() {
                    return warePrice;
                }

                public void setWarePrice(int warePrice) {
                    this.warePrice = warePrice;
                }
            }
        }
    }
}
