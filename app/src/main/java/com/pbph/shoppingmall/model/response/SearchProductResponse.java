package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SearchProductResponse extends BaseResponesModel {

    /**
     * data : {"goodsInfo":{"brands":[],"goodsInfoList":{"data":[{"auditStatus":"2",
     * "brand":{"brandId":3,"brandName":"one piece"},"brandId":3,"catId":24,
     * "cateList":[{"catId":19,"catName":"测试一级分类","catParentId":0},{"catId":23,
     * "catName":"测试二级分类","catParentId":19},{"catId":24,"catName":"三级分类","catParentId":23}],
     * "collectionCount":0,"commcont":0,"goodsId":107,"goodsInfoAdded":"1",
     * "goodsInfoCostPrice":123,"goodsInfoId":105,"goodsInfoItemNo":"201804251126542",
     * "goodsInfoMarketPrice":3123,"goodsInfoName":"测试商品27(黑色三尺三)","goodsInfoPreferPrice":123,
     * "goodsInfoStock":50,"goodsInfoSubtitle":"测试商品","goodsInfoWeight":123,"highLightName":"<em
     * style='color:red'>测<\/em><em style='color:red'>试<\/em>商品27(黑色三尺三)",
     * "isCustomerDiscount":"0","isThird":"1","ismailbay":"0","paramList":[{"expandparamId":13,
     * "expandparamName":"属性名1111","expandparamValueId":"23","expandparamValueName":"1111",
     * "goodsId":107,"releExpandparamId":167},{"expandparamId":18,"expandparamName":"fasdfas",
     * "expandparamValueId":"37","expandparamValueName":"fasdf","goodsId":107,
     * "releExpandparamId":168}],"praise":0,"productCommentVo":{"colligate":"0","count":"0",
     * "highopinion":"0","inferior":"0","middlingopinion":"0"},"saleCount":120,"showList":"1",
     * "showMobile":"1","storeId":37,"storeName":"admin","thirdCateId":18,
     * "thirdCateList":[{"catId":16,"catName":"测试1","catParentId":0},{"catId":17,"catName":"测试2",
     * "catParentId":16},{"catId":18,"catName":"测试三","catParentId":17}],"thirdPositionNum":"",
     * "typeId":11},{"auditStatus":"2","brand":{"brandId":3,"brandName":"one piece"},"brandId":3,
     * "catId":29,"cateList":[{"catId":19,"catName":"测试一级分类","catParentId":0},{"catId":23,
     * "catName":"测试二级分类","catParentId":19},{"catId":29,"catName":"撒发生","catParentId":23}],
     * "collectionCount":0,"commcont":0,"goodsId":104,"goodsInfoAdded":"1",
     * "goodsInfoCostPrice":123,"goodsInfoId":98,"goodsInfoItemNo":"201804251039071",
     * "goodsInfoMarketPrice":1323,"goodsInfoName":"测试商品23(黑色二尺六)","goodsInfoPreferPrice":123,
     * "goodsInfoStock":50,"goodsInfoSubtitle":"啊","goodsInfoWeight":123,"highLightName":"<em
     * style='color:red'>测<\/em><em style='color:red'>试<\/em>商品23(黑色二尺六)",
     * "isCustomerDiscount":"0","isThird":"1","ismailbay":"0","paramList":[{"expandparamId":12,
     * "expandparamName":"黑属性名","expandparamValueId":"21","expandparamValueName":"手动阀",
     * "goodsId":104,"releExpandparamId":163}],"praise":0,"productCommentVo":{"colligate":"0",
     * "count":"0","highopinion":"0","inferior":"0","middlingopinion":"0"},"saleCount":201,
     * "showList":"1","showMobile":"1","storeId":165,"storeName":"admin","thirdCateId":18,
     * "thirdCateList":[{"catId":16,"catName":"测试1","catParentId":0},{"catId":17,"catName":"测试2",
     * "catParentId":16},{"catId":18,"catName":"测试三","catParentId":17}],"thirdPositionNum":"",
     * "typeId":10}],"end":1,"endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,
     * "lastPageNo":1,"nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,
     * "startNo":1,"startRowNum":0,"startRowNumApp":0,"totalPages":1},
     * "params":[{"paramName":"fasdfas","valueVoList":[{"valueName":"fasdf"}]},
     * {"paramName":"属性名1111","valueVoList":[{"valueName":"1111"}]},{"paramName":"黑属性名",
     * "valueVoList":[{"valueName":"手动阀"}]}]},"keyword":"测试","sort":"22D","storeId":0}
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
         * goodsInfo : {"brands":[],"goodsInfoList":{"data":[{"auditStatus":"2",
         * "brand":{"brandId":3,"brandName":"one piece"},"brandId":3,"catId":24,
         * "cateList":[{"catId":19,"catName":"测试一级分类","catParentId":0},{"catId":23,
         * "catName":"测试二级分类","catParentId":19},{"catId":24,"catName":"三级分类","catParentId":23}],
         * "collectionCount":0,"commcont":0,"goodsId":107,"goodsInfoAdded":"1",
         * "goodsInfoCostPrice":123,"goodsInfoId":105,"goodsInfoItemNo":"201804251126542",
         * "goodsInfoMarketPrice":3123,"goodsInfoName":"测试商品27(黑色三尺三)",
         * "goodsInfoPreferPrice":123,"goodsInfoStock":50,"goodsInfoSubtitle":"测试商品",
         * "goodsInfoWeight":123,"highLightName":"<em style='color:red'>测<\/em><em
         * style='color:red'>试<\/em>商品27(黑色三尺三)","isCustomerDiscount":"0","isThird":"1",
         * "ismailbay":"0","paramList":[{"expandparamId":13,"expandparamName":"属性名1111",
         * "expandparamValueId":"23","expandparamValueName":"1111","goodsId":107,
         * "releExpandparamId":167},{"expandparamId":18,"expandparamName":"fasdfas",
         * "expandparamValueId":"37","expandparamValueName":"fasdf","goodsId":107,
         * "releExpandparamId":168}],"praise":0,"productCommentVo":{"colligate":"0","count":"0",
         * "highopinion":"0","inferior":"0","middlingopinion":"0"},"saleCount":120,
         * "showList":"1","showMobile":"1","storeId":37,"storeName":"admin","thirdCateId":18,
         * "thirdCateList":[{"catId":16,"catName":"测试1","catParentId":0},{"catId":17,
         * "catName":"测试2","catParentId":16},{"catId":18,"catName":"测试三","catParentId":17}],
         * "thirdPositionNum":"","typeId":11},{"auditStatus":"2","brand":{"brandId":3,
         * "brandName":"one piece"},"brandId":3,"catId":29,"cateList":[{"catId":19,
         * "catName":"测试一级分类","catParentId":0},{"catId":23,"catName":"测试二级分类","catParentId":19},
         * {"catId":29,"catName":"撒发生","catParentId":23}],"collectionCount":0,"commcont":0,
         * "goodsId":104,"goodsInfoAdded":"1","goodsInfoCostPrice":123,"goodsInfoId":98,
         * "goodsInfoItemNo":"201804251039071","goodsInfoMarketPrice":1323,
         * "goodsInfoName":"测试商品23(黑色二尺六)","goodsInfoPreferPrice":123,"goodsInfoStock":50,
         * "goodsInfoSubtitle":"啊","goodsInfoWeight":123,"highLightName":"<em
         * style='color:red'>测<\/em><em style='color:red'>试<\/em>商品23(黑色二尺六)",
         * "isCustomerDiscount":"0","isThird":"1","ismailbay":"0",
         * "paramList":[{"expandparamId":12,"expandparamName":"黑属性名","expandparamValueId":"21",
         * "expandparamValueName":"手动阀","goodsId":104,"releExpandparamId":163}],"praise":0,
         * "productCommentVo":{"colligate":"0","count":"0","highopinion":"0","inferior":"0",
         * "middlingopinion":"0"},"saleCount":201,"showList":"1","showMobile":"1","storeId":165,
         * "storeName":"admin","thirdCateId":18,"thirdCateList":[{"catId":16,"catName":"测试1",
         * "catParentId":0},{"catId":17,"catName":"测试2","catParentId":16},{"catId":18,
         * "catName":"测试三","catParentId":17}],"thirdPositionNum":"","typeId":10}],"end":1,
         * "endNo":1,"endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,
         * "nextPageNo":1,"pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,"startNo":1,
         * "startRowNum":0,"startRowNumApp":0,"totalPages":1},"params":[{"paramName":"fasdfas",
         * "valueVoList":[{"valueName":"fasdf"}]},{"paramName":"属性名1111",
         * "valueVoList":[{"valueName":"1111"}]},{"paramName":"黑属性名",
         * "valueVoList":[{"valueName":"手动阀"}]}]}
         * keyword : 测试
         * sort : 22D
         * storeId : 0
         */

        private GoodsInfoBean goodsInfo;
        private String keyword;
        private String sort;
        private int storeId;

        public GoodsInfoBean getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(GoodsInfoBean goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

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

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public static class GoodsInfoBean {
            /**
             * brands : []
             * goodsInfoList : {"data":[{"auditStatus":"2","brand":{"brandId":3,"brandName":"one
             * piece"},"brandId":3,"catId":24,"cateList":[{"catId":19,"catName":"测试一级分类",
             * "catParentId":0},{"catId":23,"catName":"测试二级分类","catParentId":19},{"catId":24,
             * "catName":"三级分类","catParentId":23}],"collectionCount":0,"commcont":0,
             * "goodsId":107,"goodsInfoAdded":"1","goodsInfoCostPrice":123,"goodsInfoId":105,
             * "goodsInfoItemNo":"201804251126542","goodsInfoMarketPrice":3123,
             * "goodsInfoName":"测试商品27(黑色三尺三)","goodsInfoPreferPrice":123,"goodsInfoStock":50,
             * "goodsInfoSubtitle":"测试商品","goodsInfoWeight":123,"highLightName":"<em
             * style='color:red'>测<\/em><em style='color:red'>试<\/em>商品27(黑色三尺三)",
             * "isCustomerDiscount":"0","isThird":"1","ismailbay":"0",
             * "paramList":[{"expandparamId":13,"expandparamName":"属性名1111",
             * "expandparamValueId":"23","expandparamValueName":"1111","goodsId":107,
             * "releExpandparamId":167},{"expandparamId":18,"expandparamName":"fasdfas",
             * "expandparamValueId":"37","expandparamValueName":"fasdf","goodsId":107,
             * "releExpandparamId":168}],"praise":0,"productCommentVo":{"colligate":"0",
             * "count":"0","highopinion":"0","inferior":"0","middlingopinion":"0"},
             * "saleCount":120,"showList":"1","showMobile":"1","storeId":37,"storeName":"admin",
             * "thirdCateId":18,"thirdCateList":[{"catId":16,"catName":"测试1","catParentId":0},
             * {"catId":17,"catName":"测试2","catParentId":16},{"catId":18,"catName":"测试三",
             * "catParentId":17}],"thirdPositionNum":"","typeId":11},{"auditStatus":"2",
             * "brand":{"brandId":3,"brandName":"one piece"},"brandId":3,"catId":29,
             * "cateList":[{"catId":19,"catName":"测试一级分类","catParentId":0},{"catId":23,
             * "catName":"测试二级分类","catParentId":19},{"catId":29,"catName":"撒发生",
             * "catParentId":23}],"collectionCount":0,"commcont":0,"goodsId":104,
             * "goodsInfoAdded":"1","goodsInfoCostPrice":123,"goodsInfoId":98,
             * "goodsInfoItemNo":"201804251039071","goodsInfoMarketPrice":1323,
             * "goodsInfoName":"测试商品23(黑色二尺六)","goodsInfoPreferPrice":123,"goodsInfoStock":50,
             * "goodsInfoSubtitle":"啊","goodsInfoWeight":123,"highLightName":"<em
             * style='color:red'>测<\/em><em style='color:red'>试<\/em>商品23(黑色二尺六)",
             * "isCustomerDiscount":"0","isThird":"1","ismailbay":"0",
             * "paramList":[{"expandparamId":12,"expandparamName":"黑属性名",
             * "expandparamValueId":"21","expandparamValueName":"手动阀","goodsId":104,
             * "releExpandparamId":163}],"praise":0,"productCommentVo":{"colligate":"0",
             * "count":"0","highopinion":"0","inferior":"0","middlingopinion":"0"},
             * "saleCount":201,"showList":"1","showMobile":"1","storeId":165,"storeName":"admin",
             * "thirdCateId":18,"thirdCateList":[{"catId":16,"catName":"测试1","catParentId":0},
             * {"catId":17,"catName":"测试2","catParentId":16},{"catId":18,"catName":"测试三",
             * "catParentId":17}],"thirdPositionNum":"","typeId":10}],"end":1,"endNo":1,
             * "endRowNum":15,"endRowNumApp":0,"firstPageNo":1,"lastPageNo":1,"nextPageNo":1,
             * "pageNo":1,"pageSize":15,"prePageNo":1,"rows":2,"start":1,"startNo":1,
             * "startRowNum":0,"startRowNumApp":0,"totalPages":1}
             * params : [{"paramName":"fasdfas","valueVoList":[{"valueName":"fasdf"}]},
             * {"paramName":"属性名1111","valueVoList":[{"valueName":"1111"}]},{"paramName":"黑属性名",
             * "valueVoList":[{"valueName":"手动阀"}]}]
             */

            private GoodsInfoListBean goodsInfoList;
            private List<BrandsBean> brands;
            private List<ParamsBean> params;

            public GoodsInfoListBean getGoodsInfoList() {
                return goodsInfoList;
            }

            public void setGoodsInfoList(GoodsInfoListBean goodsInfoList) {
                this.goodsInfoList = goodsInfoList;
            }

            public List<BrandsBean> getBrands() {
                return brands;
            }

            public void setBrands(List<BrandsBean> brands) {
                this.brands = brands;
            }

            public List<ParamsBean> getParams() {
                return params;
            }

            public void setParams(List<ParamsBean> params) {
                this.params = params;
            }

            public static class GoodsInfoListBean {
                /**
                 * data : [{"auditStatus":"2","brand":{"brandId":3,"brandName":"one piece"},
                 * "brandId":3,"catId":24,"cateList":[{"catId":19,"catName":"测试一级分类",
                 * "catParentId":0},{"catId":23,"catName":"测试二级分类","catParentId":19},{"catId":24,
                 * "catName":"三级分类","catParentId":23}],"collectionCount":0,"commcont":0,
                 * "goodsId":107,"goodsInfoAdded":"1","goodsInfoCostPrice":123,"goodsInfoId":105,
                 * "goodsInfoItemNo":"201804251126542","goodsInfoMarketPrice":3123,
                 * "goodsInfoName":"测试商品27(黑色三尺三)","goodsInfoPreferPrice":123,
                 * "goodsInfoStock":50,"goodsInfoSubtitle":"测试商品","goodsInfoWeight":123,
                 * "highLightName":"<em style='color:red'>测<\/em><em
                 * style='color:red'>试<\/em>商品27(黑色三尺三)","isCustomerDiscount":"0","isThird":"1",
                 * "ismailbay":"0","paramList":[{"expandparamId":13,"expandparamName":"属性名1111",
                 * "expandparamValueId":"23","expandparamValueName":"1111","goodsId":107,
                 * "releExpandparamId":167},{"expandparamId":18,"expandparamName":"fasdfas",
                 * "expandparamValueId":"37","expandparamValueName":"fasdf","goodsId":107,
                 * "releExpandparamId":168}],"praise":0,"productCommentVo":{"colligate":"0",
                 * "count":"0","highopinion":"0","inferior":"0","middlingopinion":"0"},
                 * "saleCount":120,"showList":"1","showMobile":"1","storeId":37,
                 * "storeName":"admin","thirdCateId":18,"thirdCateList":[{"catId":16,
                 * "catName":"测试1","catParentId":0},{"catId":17,"catName":"测试2",
                 * "catParentId":16},{"catId":18,"catName":"测试三","catParentId":17}],
                 * "thirdPositionNum":"","typeId":11},{"auditStatus":"2","brand":{"brandId":3,
                 * "brandName":"one piece"},"brandId":3,"catId":29,"cateList":[{"catId":19,
                 * "catName":"测试一级分类","catParentId":0},{"catId":23,"catName":"测试二级分类",
                 * "catParentId":19},{"catId":29,"catName":"撒发生","catParentId":23}],
                 * "collectionCount":0,"commcont":0,"goodsId":104,"goodsInfoAdded":"1",
                 * "goodsInfoCostPrice":123,"goodsInfoId":98,"goodsInfoItemNo":"201804251039071",
                 * "goodsInfoMarketPrice":1323,"goodsInfoName":"测试商品23(黑色二尺六)",
                 * "goodsInfoPreferPrice":123,"goodsInfoStock":50,"goodsInfoSubtitle":"啊",
                 * "goodsInfoWeight":123,"highLightName":"<em style='color:red'>测<\/em><em
                 * style='color:red'>试<\/em>商品23(黑色二尺六)","isCustomerDiscount":"0","isThird":"1",
                 * "ismailbay":"0","paramList":[{"expandparamId":12,"expandparamName":"黑属性名",
                 * "expandparamValueId":"21","expandparamValueName":"手动阀","goodsId":104,
                 * "releExpandparamId":163}],"praise":0,"productCommentVo":{"colligate":"0",
                 * "count":"0","highopinion":"0","inferior":"0","middlingopinion":"0"},
                 * "saleCount":201,"showList":"1","showMobile":"1","storeId":165,
                 * "storeName":"admin","thirdCateId":18,"thirdCateList":[{"catId":16,
                 * "catName":"测试1","catParentId":0},{"catId":17,"catName":"测试2",
                 * "catParentId":16},{"catId":18,"catName":"测试三","catParentId":17}],
                 * "thirdPositionNum":"","typeId":10}]
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
                     * auditStatus : 2
                     * brand : {"brandId":3,"brandName":"one piece"}
                     * brandId : 3
                     * catId : 24
                     * cateList : [{"catId":19,"catName":"测试一级分类","catParentId":0},{"catId":23,
                     * "catName":"测试二级分类","catParentId":19},{"catId":24,"catName":"三级分类",
                     * "catParentId":23}]
                     * collectionCount : 0
                     * commcont : 0
                     * goodsId : 107
                     * goodsInfoAdded : 1
                     * goodsInfoCostPrice : 123
                     * goodsInfoId : 105
                     * goodsInfoItemNo : 201804251126542
                     * goodsInfoMarketPrice : 3123
                     * goodsInfoName : 测试商品27(黑色三尺三)
                     * goodsInfoPreferPrice : 123
                     * goodsInfoStock : 50
                     * goodsInfoSubtitle : 测试商品
                     * goodsInfoWeight : 123
                     * highLightName : <em style='color:red'>测</em><em
                     * style='color:red'>试</em>商品27(黑色三尺三)
                     * isCustomerDiscount : 0
                     * isThird : 1
                     * ismailbay : 0
                     * paramList : [{"expandparamId":13,"expandparamName":"属性名1111",
                     * "expandparamValueId":"23","expandparamValueName":"1111","goodsId":107,
                     * "releExpandparamId":167},{"expandparamId":18,"expandparamName":"fasdfas",
                     * "expandparamValueId":"37","expandparamValueName":"fasdf","goodsId":107,
                     * "releExpandparamId":168}]
                     * praise : 0
                     * productCommentVo : {"colligate":"0","count":"0","highopinion":"0",
                     * "inferior":"0","middlingopinion":"0"}
                     * saleCount : 120
                     * showList : 1
                     * showMobile : 1
                     * storeId : 37
                     * storeName : admin
                     * thirdCateId : 18
                     * thirdCateList : [{"catId":16,"catName":"测试1","catParentId":0},{"catId":17,
                     * "catName":"测试2","catParentId":16},{"catId":18,"catName":"测试三",
                     * "catParentId":17}]
                     * thirdPositionNum :
                     * typeId : 11
                     */

                    private String auditStatus;
                    private BrandBean brand;
                    private int brandId;
                    private int catId;
                    private int collectionCount;
                    private int commcont;
                    private int goodsId;
                    private String goodsInfoAdded;
                    private int goodsInfoCostPrice;
                    private int goodsInfoId = -1;
                    private String goodsInfoItemNo;
                    private int goodsInfoMarketPrice;
                    private String goodsInfoName;
                    private double goodsInfoPreferPrice;
                    private int goodsInfoStock;
                    private String goodsInfoSubtitle;
                    private int goodsInfoWeight;
                    private String highLightName;
                    private String isCustomerDiscount;
                    private String isThird;
                    private String ismailbay;
                    private int praise;
                    private ProductCommentVoBean productCommentVo;
                    private int saleCount;
                    private String showList;
                    private String showMobile;
                    private int storeId;
                    private String storeName;
                    private int thirdCateId;
                    private String thirdPositionNum;
                    private int typeId;
                    private List<CateListBean> cateList;
                    private List<ParamListBean> paramList;
                    private List<ThirdCateListBean> thirdCateList;
                    private List<ImgListBean> imgList;

                    public List<ImgListBean> getImgList() {
                        return imgList;
                    }

                    public void setImgList(List<ImgListBean> imgList) {
                        this.imgList = imgList;
                    }

                    public String getAuditStatus() {
                        return auditStatus;
                    }

                    public void setAuditStatus(String auditStatus) {
                        this.auditStatus = auditStatus;
                    }

                    public BrandBean getBrand() {
                        return brand;
                    }

                    public void setBrand(BrandBean brand) {
                        this.brand = brand;
                    }

                    public int getBrandId() {
                        return brandId;
                    }

                    public void setBrandId(int brandId) {
                        this.brandId = brandId;
                    }

                    public int getCatId() {
                        return catId;
                    }

                    public void setCatId(int catId) {
                        this.catId = catId;
                    }

                    public int getCollectionCount() {
                        return collectionCount;
                    }

                    public void setCollectionCount(int collectionCount) {
                        this.collectionCount = collectionCount;
                    }

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

                    public String getGoodsInfoAdded() {
                        return goodsInfoAdded;
                    }

                    public void setGoodsInfoAdded(String goodsInfoAdded) {
                        this.goodsInfoAdded = goodsInfoAdded;
                    }

                    public int getGoodsInfoCostPrice() {
                        return goodsInfoCostPrice;
                    }

                    public void setGoodsInfoCostPrice(int goodsInfoCostPrice) {
                        this.goodsInfoCostPrice = goodsInfoCostPrice;
                    }

                    public int getGoodsInfoId() {
                        return goodsInfoId;
                    }

                    public void setGoodsInfoId(int goodsInfoId) {
                        this.goodsInfoId = goodsInfoId;
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

                    public String getGoodsInfoName() {
                        return goodsInfoName;
                    }

                    public void setGoodsInfoName(String goodsInfoName) {
                        this.goodsInfoName = goodsInfoName;
                    }

                    public int getGoodsInfoPreferPrice() {

                        return (int)(goodsInfoPreferPrice)  ;
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

                    public String getGoodsInfoSubtitle() {
                        return goodsInfoSubtitle;
                    }

                    public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
                        this.goodsInfoSubtitle = goodsInfoSubtitle;
                    }

                    public int getGoodsInfoWeight() {
                        return goodsInfoWeight;
                    }

                    public void setGoodsInfoWeight(int goodsInfoWeight) {
                        this.goodsInfoWeight = goodsInfoWeight;
                    }

                    public String getHighLightName() {
                        return highLightName;
                    }

                    public void setHighLightName(String highLightName) {
                        this.highLightName = highLightName;
                    }

                    public String getIsCustomerDiscount() {
                        return isCustomerDiscount;
                    }

                    public void setIsCustomerDiscount(String isCustomerDiscount) {
                        this.isCustomerDiscount = isCustomerDiscount;
                    }

                    public String getIsThird() {
                        return isThird;
                    }

                    public void setIsThird(String isThird) {
                        this.isThird = isThird;
                    }

                    public String getIsmailbay() {
                        return ismailbay;
                    }

                    public void setIsmailbay(String ismailbay) {
                        this.ismailbay = ismailbay;
                    }

                    public int getPraise() {
                        return praise;
                    }

                    public void setPraise(int praise) {
                        this.praise = praise;
                    }

                    public ProductCommentVoBean getProductCommentVo() {
                        return productCommentVo;
                    }

                    public void setProductCommentVo(ProductCommentVoBean productCommentVo) {
                        this.productCommentVo = productCommentVo;
                    }

                    public int getSaleCount() {
                        return saleCount;
                    }

                    public void setSaleCount(int saleCount) {
                        this.saleCount = saleCount;
                    }

                    public String getShowList() {
                        return showList;
                    }

                    public void setShowList(String showList) {
                        this.showList = showList;
                    }

                    public String getShowMobile() {
                        return showMobile;
                    }

                    public void setShowMobile(String showMobile) {
                        this.showMobile = showMobile;
                    }

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

                    public int getThirdCateId() {
                        return thirdCateId;
                    }

                    public void setThirdCateId(int thirdCateId) {
                        this.thirdCateId = thirdCateId;
                    }

                    public String getThirdPositionNum() {
                        return thirdPositionNum;
                    }

                    public void setThirdPositionNum(String thirdPositionNum) {
                        this.thirdPositionNum = thirdPositionNum;
                    }

                    public int getTypeId() {
                        return typeId;
                    }

                    public void setTypeId(int typeId) {
                        this.typeId = typeId;
                    }

                    public List<CateListBean> getCateList() {
                        return cateList;
                    }

                    public void setCateList(List<CateListBean> cateList) {
                        this.cateList = cateList;
                    }

                    public List<ParamListBean> getParamList() {
                        return paramList;
                    }

                    public void setParamList(List<ParamListBean> paramList) {
                        this.paramList = paramList;
                    }

                    public List<ThirdCateListBean> getThirdCateList() {
                        return thirdCateList;
                    }

                    public void setThirdCateList(List<ThirdCateListBean> thirdCateList) {
                        this.thirdCateList = thirdCateList;
                    }

                    public static class BrandBean {
                        /**
                         * brandId : 3
                         * brandName : one piece
                         */

                        private int brandId;
                        private String brandName;

                        public int getBrandId() {
                            return brandId;
                        }

                        public void setBrandId(int brandId) {
                            this.brandId = brandId;
                        }

                        public String getBrandName() {
                            return brandName;
                        }

                        public void setBrandName(String brandName) {
                            this.brandName = brandName;
                        }
                    }

                    public static class ProductCommentVoBean {
                        /**
                         * colligate : 0
                         * count : 0
                         * highopinion : 0
                         * inferior : 0
                         * middlingopinion : 0
                         */

                        private String colligate;
                        private String count;
                        private String highopinion;
                        private String inferior;
                        private String middlingopinion;

                        public String getColligate() {
                            return colligate;
                        }

                        public void setColligate(String colligate) {
                            this.colligate = colligate;
                        }

                        public String getCount() {
                            return count;
                        }

                        public void setCount(String count) {
                            this.count = count;
                        }

                        public String getHighopinion() {
                            return highopinion;
                        }

                        public void setHighopinion(String highopinion) {
                            this.highopinion = highopinion;
                        }

                        public String getInferior() {
                            return inferior;
                        }

                        public void setInferior(String inferior) {
                            this.inferior = inferior;
                        }

                        public String getMiddlingopinion() {
                            return middlingopinion;
                        }

                        public void setMiddlingopinion(String middlingopinion) {
                            this.middlingopinion = middlingopinion;
                        }
                    }

                    public static class CateListBean {
                        /**
                         * catId : 19
                         * catName : 测试一级分类
                         * catParentId : 0
                         */

                        private int catId;
                        private String catName;
                        private int catParentId;

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

                        public int getCatParentId() {
                            return catParentId;
                        }

                        public void setCatParentId(int catParentId) {
                            this.catParentId = catParentId;
                        }
                    }

                    public static class ParamListBean {
                        /**
                         * expandparamId : 13
                         * expandparamName : 属性名1111
                         * expandparamValueId : 23
                         * expandparamValueName : 1111
                         * goodsId : 107
                         * releExpandparamId : 167
                         */

                        private int expandparamId;
                        private String expandparamName;
                        private String expandparamValueId;
                        private String expandparamValueName;
                        private int goodsId;
                        private int releExpandparamId;

                        public int getExpandparamId() {
                            return expandparamId;
                        }

                        public void setExpandparamId(int expandparamId) {
                            this.expandparamId = expandparamId;
                        }

                        public String getExpandparamName() {
                            return expandparamName;
                        }

                        public void setExpandparamName(String expandparamName) {
                            this.expandparamName = expandparamName;
                        }

                        public String getExpandparamValueId() {
                            return expandparamValueId;
                        }

                        public void setExpandparamValueId(String expandparamValueId) {
                            this.expandparamValueId = expandparamValueId;
                        }

                        public String getExpandparamValueName() {
                            return expandparamValueName;
                        }

                        public void setExpandparamValueName(String expandparamValueName) {
                            this.expandparamValueName = expandparamValueName;
                        }

                        public int getGoodsId() {
                            return goodsId;
                        }

                        public void setGoodsId(int goodsId) {
                            this.goodsId = goodsId;
                        }

                        public int getReleExpandparamId() {
                            return releExpandparamId;
                        }

                        public void setReleExpandparamId(int releExpandparamId) {
                            this.releExpandparamId = releExpandparamId;
                        }
                    }


                    public static class ImgListBean {
                        /**
                         * goodsImgDelflag : 0
                         * goodsImgId : 44
                         * goodsImgSort : 3
                         * goodsInfoId : 112
                         * imageArtworkName : [图片]http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1515728580245.png?x-oss-process=image/resize,
                         * m_fixed,h_160,w_160
                         * imageBigName : [图片]http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1515728580245.png?x-oss-process=image/resize,
                         * m_fixed,h_160,w_160?x-oss-process=image/resize,m_fixed,h_160,w_160
                         * imageInName : [图片]http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1515728580245.png?x-oss-process=image/resize,
                         * m_fixed,h_160,w_160?x-oss-process=image/resize,m_fixed,h_160,w_160
                         * imageThumName : [图片]http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
                         * .com/productimage2/3/1515728580245.png?x-oss-process=image/resize,
                         * m_fixed,h_160,w_160?x-oss-process=image/resize,m_fixed,h_56,w_56
                         */

                        private String goodsImgDelflag;
                        private int goodsImgId;
                        private int goodsImgSort;
                        private int goodsInfoId;
                        private String imageArtworkName;
                        private String imageBigName;
                        private String imageInName;
                        private String imageThumName;

                        public String getGoodsImgDelflag() {
                            return goodsImgDelflag;
                        }

                        public void setGoodsImgDelflag(String goodsImgDelflag) {
                            this.goodsImgDelflag = goodsImgDelflag;
                        }

                        public int getGoodsImgId() {
                            return goodsImgId;
                        }

                        public void setGoodsImgId(int goodsImgId) {
                            this.goodsImgId = goodsImgId;
                        }

                        public int getGoodsImgSort() {
                            return goodsImgSort;
                        }

                        public void setGoodsImgSort(int goodsImgSort) {
                            this.goodsImgSort = goodsImgSort;
                        }

                        public int getGoodsInfoId() {
                            return goodsInfoId;
                        }

                        public void setGoodsInfoId(int goodsInfoId) {
                            this.goodsInfoId = goodsInfoId;
                        }

                        public String getImageArtworkName() {
                            return imageArtworkName;
                        }

                        public void setImageArtworkName(String imageArtworkName) {
                            this.imageArtworkName = imageArtworkName;
                        }

                        public String getImageBigName() {
                            return imageBigName;
                        }

                        public void setImageBigName(String imageBigName) {
                            this.imageBigName = imageBigName;
                        }

                        public String getImageInName() {
                            return imageInName;
                        }

                        public void setImageInName(String imageInName) {
                            this.imageInName = imageInName;
                        }

                        public String getImageThumName() {
                            return imageThumName;
                        }

                        public void setImageThumName(String imageThumName) {
                            this.imageThumName = imageThumName;
                        }
                    }

                    public static class ThirdCateListBean {
                        /**
                         * catId : 16
                         * catName : 测试1
                         * catParentId : 0
                         */

                        private int catId;
                        private String catName;
                        private int catParentId;

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

                        public int getCatParentId() {
                            return catParentId;
                        }

                        public void setCatParentId(int catParentId) {
                            this.catParentId = catParentId;
                        }
                    }
                }
            }

            public static class BrandsBean {
                /**
                 * brandName : nice
                 */

                private String brandName;

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }
            }

            public static class ParamsBean {
                /**
                 * paramName : fasdfas
                 * valueVoList : [{"valueName":"fasdf"}]
                 */

                private String paramName;
                private List<ValueVoListBean> valueVoList;

                public String getParamName() {
                    return paramName;
                }

                public void setParamName(String paramName) {
                    this.paramName = paramName;
                }

                public List<ValueVoListBean> getValueVoList() {
                    return valueVoList;
                }

                public void setValueVoList(List<ValueVoListBean> valueVoList) {
                    this.valueVoList = valueVoList;
                }

                public static class ValueVoListBean {
                    /**
                     * valueName : fasdf
                     */

                    private String valueName;

                    public String getValueName() {
                        return valueName;
                    }

                    public void setValueName(String valueName) {
                        this.valueName = valueName;
                    }
                }
            }
        }
    }
}
