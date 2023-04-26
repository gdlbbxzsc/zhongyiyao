package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/31.
 */

public class GetStoreNewGoodsForPageResponse extends BaseResponesModel {


    /**
     * data : {"end":1,"endNo":1,"endRowNum":10,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,
     * "list":[{"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1523417518059.png","goodsInfoName":"测试商品27(黑色三尺三)",
     * "goodsInfoPrice":123,"ppid":105,"praise":0},{"commcont":3,
     * "goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"旗袍1(黑色三尺三50*20)","goodsInfoPrice":123,"ppid":109,"praise":100},
     * {"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"jin金立手机(黑色二尺六50*40)","goodsInfoPrice":123,"ppid":122,"praise":0},
     * {"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"旗袍1(黑色二尺六50*20)","goodsInfoPrice":123,"ppid":106,"praise":0},
     * {"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoPrice":123,"ppid":112,"praise":0},
     * {"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"jin金立手机(黑色三尺三50*40)","goodsInfoPrice":123,"ppid":125,"praise":0},
     * {"commcont":1,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1522819041483.png","goodsInfoName":"发生的发生(黑色二尺六)",
     * "goodsInfoPrice":123,"ppid":12,"praise":100},{"commcont":4,
     * "goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"旗袍1(黑色二尺六50*40)","goodsInfoPrice":123,"ppid":108,"praise":50},
     * {"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160",
     * "goodsInfoName":"jin金立手机(黑色二尺六50*30)","goodsInfoPrice":123,"ppid":121,"praise":0}],
     * "nextPageNo":1,"pageNo":1,"pageSize":10,"prePageNo":1,"rows":9,"start":1,"startNo":1,
     * "startRowNum":0,"startRowNumApp":0,"totalPages":1}
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
         * endRowNum : 10
         * endRowNumApp : 0
         * firstPageNo : 1
         * lastPageNo : 1
         * list : [{"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1523417518059.png","goodsInfoName":"测试商品27(黑色三尺三)",
         * "goodsInfoPrice":123,"ppid":105,"praise":0},{"commcont":3,
         * "goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,
         * w_160","goodsInfoName":"旗袍1(黑色三尺三50*20)","goodsInfoPrice":123,"ppid":109,
         * "praise":100},{"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing
         * .aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,
         * h_160,w_160","goodsInfoName":"jin金立手机(黑色二尺六50*40)","goodsInfoPrice":123,"ppid":122,
         * "praise":0},{"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing
         * .aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,
         * h_160,w_160","goodsInfoName":"旗袍1(黑色二尺六50*20)","goodsInfoPrice":123,"ppid":106,
         * "praise":0},{"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing
         * .aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,
         * h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoPrice":123,"ppid":112,
         * "praise":0},{"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing
         * .aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,
         * h_160,w_160","goodsInfoName":"jin金立手机(黑色三尺三50*40)","goodsInfoPrice":123,"ppid":125,
         * "praise":0},{"commcont":1,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing
         * .aliyuncs.com/productimage2/3/1522819041483.png","goodsInfoName":"发生的发生(黑色二尺六)",
         * "goodsInfoPrice":123,"ppid":12,"praise":100},{"commcont":4,
         * "goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,
         * w_160","goodsInfoName":"旗袍1(黑色二尺六50*40)","goodsInfoPrice":123,"ppid":108,"praise":50},
         * {"commcont":0,"goodsInfoImg":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,
         * w_160","goodsInfoName":"jin金立手机(黑色二尺六50*30)","goodsInfoPrice":123,"ppid":121,"praise":0}]
         * nextPageNo : 1
         * pageNo : 1
         * pageSize : 10
         * prePageNo : 1
         * rows : 9
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
             * commcont : 0
             * goodsInfoImg : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1523417518059.png
             * goodsInfoName : 测试商品27(黑色三尺三)
             * goodsInfoPrice : 123
             * ppid : 105
             * praise : 0
             */

            private int commcont;
            private String goodsInfoImg;
            private String goodsInfoName;
            private int goodsInfoPrice;
            private int ppid;
            private int praise;

            public int getCommcont() {
                return commcont;
            }

            public void setCommcont(int commcont) {
                this.commcont = commcont;
            }

            public String getGoodsInfoImg() {
                return goodsInfoImg;
            }

            public void setGoodsInfoImg(String goodsInfoImg) {
                this.goodsInfoImg = goodsInfoImg;
            }

            public String getGoodsInfoName() {
                return goodsInfoName;
            }

            public void setGoodsInfoName(String goodsInfoName) {
                this.goodsInfoName = goodsInfoName;
            }

            public int getGoodsInfoPrice() {
                return goodsInfoPrice;
            }

            public void setGoodsInfoPrice(int goodsInfoPrice) {
                this.goodsInfoPrice = goodsInfoPrice;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }
        }
    }
}
