package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class ShoppingCarResponse extends BaseResponesModel {

    /**
     * data : {"end":2,"endNo":2,"endRowNum":2,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":2,"list":[{"shoppingCartDTOList":[{"customerId":168,"goodsInfo":{"goodsId":111,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色三尺三50*40)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":9,"specDetailName":"三尺三","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"三尺三"},{"specDetailId":13,"specDetailName":"50*40","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*40"}],"goodsInfoSubtitle":"金立手机","ppid":125,"marketingList":[]},"goodsInfoId":125,"goodsJoinPrice":123,"goodsNum":5,"goodsPreferentialPrice":0,"ppid":70,"shopType":1},{"customerId":168,"goodsInfo":{"goodsId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":2,"specDetailName":"二尺六","specDetailNickname":"二尺六","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"二尺六"},{"specDetailId":11,"specDetailName":"50*20","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*20"}],"goodsInfoSubtitle":"发生的发","ppid":112,"marketingList":[]},"goodsInfoId":112,"goodsJoinPrice":123,"goodsNum":5,"goodsPreferentialPrice":0,"ppid":76,"shopType":1}],"storeId":1,"storeName":"null"}],"nextPageNo":2,"pageNo":1,"pageSize":2,"prePageNo":1,"rows":4,"start":1,"startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":2}
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
         * end : 2
         * endNo : 2
         * endRowNum : 2
         * endRowNumApp : 0
         * firstPageNo : 1
         * lastPageNo : 2
         * list : [{"shoppingCartDTOList":[{"customerId":168,"goodsInfo":{"goodsId":111,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色三尺三50*40)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":9,"specDetailName":"三尺三","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"三尺三"},{"specDetailId":13,"specDetailName":"50*40","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*40"}],"goodsInfoSubtitle":"金立手机","ppid":125,"marketingList":[]},"goodsInfoId":125,"goodsJoinPrice":123,"goodsNum":5,"goodsPreferentialPrice":0,"ppid":70,"shopType":1},{"customerId":168,"goodsInfo":{"goodsId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":2,"specDetailName":"二尺六","specDetailNickname":"二尺六","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"二尺六"},{"specDetailId":11,"specDetailName":"50*20","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*20"}],"goodsInfoSubtitle":"发生的发","ppid":112,"marketingList":[]},"goodsInfoId":112,"goodsJoinPrice":123,"goodsNum":5,"goodsPreferentialPrice":0,"ppid":76,"shopType":1}],"storeId":1,"storeName":"null"}]
         * nextPageNo : 2
         * pageNo : 1
         * pageSize : 2
         * prePageNo : 1
         * rows : 4
         * start : 1
         * startNo : 1
         * startRowNum : 0
         * startRowNumApp : 0
         * totalPages : 2
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
             * shoppingCartDTOList : [{"customerId":168,"goodsInfo":{"goodsId":111,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色三尺三50*40)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":9,"specDetailName":"三尺三","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"三尺三"},{"specDetailId":13,"specDetailName":"50*40","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*40"}],"goodsInfoSubtitle":"金立手机","ppid":125,"marketingList":[]},"goodsInfoId":125,"goodsJoinPrice":123,"goodsNum":5,"goodsPreferentialPrice":0,"ppid":70,"shopType":1},{"customerId":168,"goodsInfo":{"goodsId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":2,"specDetailName":"二尺六","specDetailNickname":"二尺六","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"二尺六"},{"specDetailId":11,"specDetailName":"50*20","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*20"}],"goodsInfoSubtitle":"发生的发","ppid":112,"marketingList":[]},"goodsInfoId":112,"goodsJoinPrice":123,"goodsNum":5,"goodsPreferentialPrice":0,"ppid":76,"shopType":1}]
             * storeId : 1
             * storeName : null
             */

            public boolean storeCheck = false;

            private int storeId;
            private String storeName;
            private List<ShoppingCartDTOListBean> shoppingCartDTOList;

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public List<ShoppingCartDTOListBean> getShoppingCartDTOList() {
                return shoppingCartDTOList;
            }

            public void setShoppingCartDTOList(List<ShoppingCartDTOListBean> shoppingCartDTOList) {
                this.shoppingCartDTOList = shoppingCartDTOList;
            }

            public static class ShoppingCartDTOListBean {
                /**
                 * customerId : 168
                 * goodsInfo : {"goodsId":111,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色三尺三50*40)","goodsInfoSpecList":[{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":9,"specDetailName":"三尺三","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"三尺三"},{"specDetailId":13,"specDetailName":"50*40","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*40"}],"goodsInfoSubtitle":"金立手机","ppid":125,"marketingList":[]}
                 * goodsInfoId : 125
                 * goodsJoinPrice : 123
                 * goodsNum : 5
                 * goodsPreferentialPrice : 0
                 * ppid : 70
                 * shopType : 1
                 */

                public boolean goodsCheck = false;

                private int customerId;
                private GoodsInfoBean goodsInfo;
                private int goodsInfoId;
                private int goodsJoinPrice;
                private int goodsNum;
                private int goodsPreferentialPrice;
                private int ppid;
                private int shopType;

                public int getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(int customerId) {
                    this.customerId = customerId;
                }

                public GoodsInfoBean getGoodsInfo() {
                    return goodsInfo;
                }

                public void setGoodsInfo(GoodsInfoBean goodsInfo) {
                    this.goodsInfo = goodsInfo;
                }

                public int getGoodsInfoId() {
                    return goodsInfoId;
                }

                public void setGoodsInfoId(int goodsInfoId) {
                    this.goodsInfoId = goodsInfoId;
                }

                public int getGoodsJoinPrice() {
                    return goodsJoinPrice;
                }

                public void setGoodsJoinPrice(int goodsJoinPrice) {
                    this.goodsJoinPrice = goodsJoinPrice;
                }

                public int getGoodsNum() {
                    return goodsNum;
                }

                public void setGoodsNum(int goodsNum) {
                    this.goodsNum = goodsNum;
                }

                public int getGoodsPreferentialPrice() {
                    return goodsPreferentialPrice;
                }

                public void setGoodsPreferentialPrice(int goodsPreferentialPrice) {
                    this.goodsPreferentialPrice = goodsPreferentialPrice;
                }

                public int getPpid() {
                    return ppid;
                }

                public void setPpid(int ppid) {
                    this.ppid = ppid;
                }

                public int getShopType() {
                    return shopType;
                }

                public void setShopType(int shopType) {
                    this.shopType = shopType;
                }

                public static class GoodsInfoBean {
                    /**
                     * goodsId : 111
                     * goodsInfoImgUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160
                     * goodsInfoName : jin金立手机(黑色三尺三50*40)
                     * goodsInfoSpecList : [{"specDetailId":1,"specDetailName":"黑色","specDetailNickname":"黑色","specId":1,"specName":"颜色","specNickname":"颜色","specRemark":"颜色","specValueRemark":"黑色"},{"specDetailId":9,"specDetailName":"三尺三","specId":2,"specName":"尺寸","specNickname":"尺寸","specRemark":"尺寸","specValueRemark":"三尺三"},{"specDetailId":13,"specDetailName":"50*40","specId":7,"specName":"宽高","specNickname":"宽高","specRemark":"宽高","specValueRemark":"50*40"}]
                     * goodsInfoSubtitle : 金立手机
                     * ppid : 125
                     * marketingList : []
                     */

                    private int goodsId;
                    private String goodsInfoImgUrl;
                    private String goodsInfoName;
                    private String goodsInfoSubtitle;
                    private int ppid;
                    private List<GoodsInfoSpecListBean> goodsInfoSpecList;
                    private List<?> marketingList;

                    public int getGoodsId() {
                        return goodsId;
                    }

                    public void setGoodsId(int goodsId) {
                        this.goodsId = goodsId;
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

                    public List<GoodsInfoSpecListBean> getGoodsInfoSpecList() {
                        return goodsInfoSpecList;
                    }

                    public void setGoodsInfoSpecList(List<GoodsInfoSpecListBean> goodsInfoSpecList) {
                        this.goodsInfoSpecList = goodsInfoSpecList;
                    }

                    public List<?> getMarketingList() {
                        return marketingList;
                    }

                    public void setMarketingList(List<?> marketingList) {
                        this.marketingList = marketingList;
                    }

                    public static class GoodsInfoSpecListBean {
                        /**
                         * specDetailId : 1
                         * specDetailName : 黑色
                         * specDetailNickname : 黑色
                         * specId : 1
                         * specName : 颜色
                         * specNickname : 颜色
                         * specRemark : 颜色
                         * specValueRemark : 黑色
                         */

                        private int specDetailId;
                        private String specDetailName;
                        private String specDetailNickname;
                        private int specId;
                        private String specName;
                        private String specNickname;
                        private String specRemark;
                        private String specValueRemark;

                        public int getSpecDetailId() {
                            return specDetailId;
                        }

                        public void setSpecDetailId(int specDetailId) {
                            this.specDetailId = specDetailId;
                        }

                        public String getSpecDetailName() {
                            return specDetailName;
                        }

                        public void setSpecDetailName(String specDetailName) {
                            this.specDetailName = specDetailName;
                        }

                        public String getSpecDetailNickname() {
                            return specDetailNickname;
                        }

                        public void setSpecDetailNickname(String specDetailNickname) {
                            this.specDetailNickname = specDetailNickname;
                        }

                        public int getSpecId() {
                            return specId;
                        }

                        public void setSpecId(int specId) {
                            this.specId = specId;
                        }

                        public String getSpecName() {
                            return specName;
                        }

                        public void setSpecName(String specName) {
                            this.specName = specName;
                        }

                        public String getSpecNickname() {
                            return specNickname;
                        }

                        public void setSpecNickname(String specNickname) {
                            this.specNickname = specNickname;
                        }

                        public String getSpecRemark() {
                            return specRemark;
                        }

                        public void setSpecRemark(String specRemark) {
                            this.specRemark = specRemark;
                        }

                        public String getSpecValueRemark() {
                            return specValueRemark;
                        }

                        public void setSpecValueRemark(String specValueRemark) {
                            this.specValueRemark = specValueRemark;
                        }
                    }
                }
            }
        }
    }
}
