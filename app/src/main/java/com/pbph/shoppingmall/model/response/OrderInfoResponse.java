package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class OrderInfoResponse extends BaseResponesModel {

    /**
     * data : {"createTime":1527598053000,"customerRemark":"11111111111","expressPrice":0,"ppid":38,"invoiceTitle":"公司天天","invoiceType":1,"orderCode":"198942495189942272","orderGoods":[{"evaluateFlag":0,"goodsCouponPrice":0,"goodsId":109,"goodsInfoId":112,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoNum":5,"goodsInfoOldPrice":123,"goodsInfoPrice":123,"goodsInfoSubtitle":"发生的发","goodsInfoSumPrice":0},{"evaluateFlag":0,"goodsCouponPrice":0,"goodsId":108,"goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"旗袍1(黑色三尺三50*20)","goodsInfoNum":53,"goodsInfoOldPrice":123,"goodsInfoPrice":123,"goodsInfoSubtitle":"旗袍1","goodsInfoSumPrice":0},{"evaluateFlag":0,"goodsCouponPrice":0,"goodsId":109,"goodsInfoId":122,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色二尺六50*40)","goodsInfoNum":5,"goodsInfoOldPrice":123,"goodsInfoPrice":123,"goodsInfoSubtitle":"金立手机","goodsInfoSumPrice":0}],"orderLinePay":1,"orderLog":[{"orderLogReason":"您提交了订单，请等待系统确认","orderLogTime":"2018-05-29 20:47:33"},{"orderLogReason":"您提交了订单，请等待系统确认","orderLogTime":"2018-05-29 20:47:33"}],"orderOldCode":"198942492878880768","orderOldPrice":7134,"orderPrePrice":0,"orderPrice":7134,"orderStatus":10,"shippingAddress":"%E4%B8%AA%E5%A5%BD%E8%A7%89%E9%82%A3%E4%B8%AA","shippingCity":"哈尔滨市","shippingCounty":"南岗区","shippingMobile":"13946054492","shippingPerson":"gdl","shippingPhone":"13946054492","shippingProvince":"黑龙江","storeId":37,"storeName":"admin"}
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
         * createTime : 1527598053000
         * customerRemark : 11111111111
         * expressPrice : 0
         * ppid : 38
         * invoiceTitle : 公司天天
         * invoiceType : 1
         * orderCode : 198942495189942272
         * orderGoods : [{"evaluateFlag":0,"goodsCouponPrice":0,"goodsId":109,"goodsInfoId":112,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"上岛咖啡还看书(黑色二尺六50*20)","goodsInfoNum":5,"goodsInfoOldPrice":123,"goodsInfoPrice":123,"goodsInfoSubtitle":"发生的发","goodsInfoSumPrice":0},{"evaluateFlag":0,"goodsCouponPrice":0,"goodsId":108,"goodsInfoId":109,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525397730866.jpg?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"旗袍1(黑色三尺三50*20)","goodsInfoNum":53,"goodsInfoOldPrice":123,"goodsInfoPrice":123,"goodsInfoSubtitle":"旗袍1","goodsInfoSumPrice":0},{"evaluateFlag":0,"goodsCouponPrice":0,"goodsId":109,"goodsInfoId":122,"goodsInfoImgUrl":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399118678.png?x-oss-process=image/resize,m_fixed,h_160,w_160","goodsInfoName":"jin金立手机(黑色二尺六50*40)","goodsInfoNum":5,"goodsInfoOldPrice":123,"goodsInfoPrice":123,"goodsInfoSubtitle":"金立手机","goodsInfoSumPrice":0}]
         * orderLinePay : 1
         * orderLog : [{"orderLogReason":"您提交了订单，请等待系统确认","orderLogTime":"2018-05-29 20:47:33"},{"orderLogReason":"您提交了订单，请等待系统确认","orderLogTime":"2018-05-29 20:47:33"}]
         * orderOldCode : 198942492878880768
         * orderOldPrice : 7134
         * orderPrePrice : 0
         * orderPrice : 7134
         * orderStatus : 10
         * shippingAddress : %E4%B8%AA%E5%A5%BD%E8%A7%89%E9%82%A3%E4%B8%AA
         * shippingCity : 哈尔滨市
         * shippingCounty : 南岗区
         * shippingMobile : 13946054492
         * shippingPerson : gdl
         * shippingPhone : 13946054492
         * shippingProvince : 黑龙江
         * storeId : 37
         * storeName : admin
         */
        private long createTime;
        private String customerRemark;
        private int expressPrice;
        private int ppid;
        private String invoiceTitle;
        private int invoiceType;
        private String invoiceCustomerContent;
        private String orderCode;
        private int orderLinePay;
        private String orderOldCode;
        private int orderOldPrice;
        private int orderPrePrice;
        private int orderPrice;
        private int orderStatus;
        private String shippingAddress;
        private String shippingCity;
        private String shippingCounty;
        private String shippingMobile;
        private String shippingPerson;
        private String shippingPhone;
        private String shippingProvince;
        private int storeId;
        private String storeName;
        private List<OrderGoodsBean> orderGoods;
        private List<OrderLogBean> orderLog;

        private BackOrderInfoBean backOrderInfo;

        private String orderExpressName;


        private int evaluateFlag;

        public int getEvaluateFlag() {
            return evaluateFlag;
        }

        public void setEvaluateFlag(int evaluateFlag) {
            this.evaluateFlag = evaluateFlag;
        }

        public BackOrderInfoBean getBackOrderInfo() {
            return backOrderInfo;
        }

        public void setBackOrderInfo(BackOrderInfoBean backOrderInfo) {
            this.backOrderInfo = backOrderInfo;
        }

        public String getInvoiceCustomerContent() {
            return invoiceCustomerContent;
        }

        public void setInvoiceCustomerContent(String invoiceCustomerContent) {
            this.invoiceCustomerContent = invoiceCustomerContent;
        }

        public String getOrderExpressName() {
            return orderExpressName;
        }

        public void setOrderExpressName(String orderExpressName) {
            this.orderExpressName = orderExpressName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCustomerRemark() {
            return customerRemark;
        }

        public void setCustomerRemark(String customerRemark) {
            this.customerRemark = customerRemark;
        }

        public int getExpressPrice() {
            return expressPrice;
        }

        public void setExpressPrice(int expressPrice) {
            this.expressPrice = expressPrice;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getOrderLinePay() {
            return orderLinePay;
        }

        public void setOrderLinePay(int orderLinePay) {
            this.orderLinePay = orderLinePay;
        }

        public String getOrderOldCode() {
            return orderOldCode;
        }

        public void setOrderOldCode(String orderOldCode) {
            this.orderOldCode = orderOldCode;
        }

        public int getOrderOldPrice() {
            return orderOldPrice;
        }

        public void setOrderOldPrice(int orderOldPrice) {
            this.orderOldPrice = orderOldPrice;
        }

        public int getOrderPrePrice() {
            return orderPrePrice;
        }

        public void setOrderPrePrice(int orderPrePrice) {
            this.orderPrePrice = orderPrePrice;
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

        public String getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public String getShippingCity() {
            return shippingCity;
        }

        public void setShippingCity(String shippingCity) {
            this.shippingCity = shippingCity;
        }

        public String getShippingCounty() {
            return shippingCounty;
        }

        public void setShippingCounty(String shippingCounty) {
            this.shippingCounty = shippingCounty;
        }

        public String getShippingMobile() {
            return shippingMobile;
        }

        public void setShippingMobile(String shippingMobile) {
            this.shippingMobile = shippingMobile;
        }

        public String getShippingPerson() {
            return shippingPerson;
        }

        public void setShippingPerson(String shippingPerson) {
            this.shippingPerson = shippingPerson;
        }

        public String getShippingPhone() {
            return shippingPhone;
        }

        public void setShippingPhone(String shippingPhone) {
            this.shippingPhone = shippingPhone;
        }

        public String getShippingProvince() {
            return shippingProvince;
        }

        public void setShippingProvince(String shippingProvince) {
            this.shippingProvince = shippingProvince;
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

        public List<OrderGoodsBean> getOrderGoods() {
            return orderGoods;
        }

        public void setOrderGoods(List<OrderGoodsBean> orderGoods) {
            this.orderGoods = orderGoods;
        }

        public List<OrderLogBean> getOrderLog() {
            return orderLog;
        }

        public void setOrderLog(List<OrderLogBean> orderLog) {
            this.orderLog = orderLog;
        }

        public static class OrderGoodsBean {
            /**
             * evaluateFlag : 0
             * goodsCouponPrice : 0
             * goodsId : 109
             * goodsInfoId : 112
             * goodsInfoImgUrl : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1525399207505.png?x-oss-process=image/resize,m_fixed,h_160,w_160
             * goodsInfoName : 上岛咖啡还看书(黑色二尺六50*20)
             * goodsInfoNum : 5
             * goodsInfoOldPrice : 123
             * goodsInfoPrice : 123
             * goodsInfoSubtitle : 发生的发
             * goodsInfoSumPrice : 0
             */

            private int evaluateFlag;
            private int goodsCouponPrice;
            private int goodsId;
            private int goodsInfoId;
            private String goodsInfoImgUrl;
            private String goodsInfoName;
            private int goodsInfoNum;
            private int goodsInfoOldPrice;
            private int goodsInfoPrice;
            private String goodsInfoSubtitle;
            private int goodsInfoSumPrice;

            public int getEvaluateFlag() {
                return evaluateFlag;
            }

            public void setEvaluateFlag(int evaluateFlag) {
                this.evaluateFlag = evaluateFlag;
            }

            public int getGoodsCouponPrice() {
                return goodsCouponPrice;
            }

            public void setGoodsCouponPrice(int goodsCouponPrice) {
                this.goodsCouponPrice = goodsCouponPrice;
            }

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

            public int getGoodsInfoNum() {
                return goodsInfoNum;
            }

            public void setGoodsInfoNum(int goodsInfoNum) {
                this.goodsInfoNum = goodsInfoNum;
            }

            public int getGoodsInfoOldPrice() {
                return goodsInfoOldPrice;
            }

            public void setGoodsInfoOldPrice(int goodsInfoOldPrice) {
                this.goodsInfoOldPrice = goodsInfoOldPrice;
            }

            public int getGoodsInfoPrice() {
                return goodsInfoPrice;
            }

            public void setGoodsInfoPrice(int goodsInfoPrice) {
                this.goodsInfoPrice = goodsInfoPrice;
            }

            public String getGoodsInfoSubtitle() {
                return goodsInfoSubtitle;
            }

            public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
                this.goodsInfoSubtitle = goodsInfoSubtitle;
            }

            public int getGoodsInfoSumPrice() {
                return goodsInfoSumPrice;
            }

            public void setGoodsInfoSumPrice(int goodsInfoSumPrice) {
                this.goodsInfoSumPrice = goodsInfoSumPrice;
            }
        }

        public static class OrderLogBean {
            /**
             * orderLogReason : 您提交了订单，请等待系统确认
             * orderLogTime : 2018-05-29 20:47:33
             */

            private String orderLogReason;
            private String orderLogTime;

            public String getOrderLogReason() {
                return orderLogReason;
            }

            public void setOrderLogReason(String orderLogReason) {
                this.orderLogReason = orderLogReason;
            }

            public String getOrderLogTime() {
                return orderLogTime;
            }

            public void setOrderLogTime(String orderLogTime) {
                this.orderLogTime = orderLogTime;
            }
        }

        public static class BackOrderInfoBean {

            /**
             * applyCredentials : 有质检报告
             * backPrice : 1230
             * backReason : 商品质量问题
             * backRemark : 哈哈哈哈哈哈
             * backWay : 0
             * isCollect : 1
             * orderExpressName :
             * orderExpressNo :
             */

            private String applyCredentials;
            private int backPrice;
            private String backReason;
            private String backRemark;
            private int backWay;
            private int isCollect;
            private String orderExpressName;
            private String orderExpressNo;
            private String uploadDocuments;
            private String storeAddress;

            private Integer isBack;

            public Integer getIsBack() {
                return isBack;
            }

            public void setIsBack(Integer isBack) {
                this.isBack = isBack;
            }

            public String getUploadDocuments() {
                return uploadDocuments;
            }

            public void setUploadDocuments(String uploadDocuments) {
                this.uploadDocuments = uploadDocuments;
            }

            public String getApplyCredentials() {
                return applyCredentials;
            }

            public void setApplyCredentials(String applyCredentials) {
                this.applyCredentials = applyCredentials;
            }

            public int getBackPrice() {
                return backPrice;
            }

            public void setBackPrice(int backPrice) {
                this.backPrice = backPrice;
            }

            public String getBackReason() {
                return backReason;
            }

            public void setBackReason(String backReason) {
                this.backReason = backReason;
            }

            public String getBackRemark() {
                return backRemark;
            }

            public void setBackRemark(String backRemark) {
                this.backRemark = backRemark;
            }

            public int getBackWay() {
                return backWay;
            }

            public void setBackWay(int backWay) {
                this.backWay = backWay;
            }

            public int getIsCollect() {
                return isCollect;
            }

            public void setIsCollect(int isCollect) {
                this.isCollect = isCollect;
            }

            public String getOrderExpressName() {
                return orderExpressName;
            }

            public void setOrderExpressName(String orderExpressName) {
                this.orderExpressName = orderExpressName;
            }

            public String getOrderExpressNo() {
                return orderExpressNo;
            }

            public void setOrderExpressNo(String orderExpressNo) {
                this.orderExpressNo = orderExpressNo;
            }

            public String getStoreAddress() {
                return storeAddress;
            }

            public void setStoreAddress(String storeAddress) {
                this.storeAddress = storeAddress;
            }
        }
    }


}
