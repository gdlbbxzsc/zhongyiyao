package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class PayOrderResponse extends BaseResponesModel {

    /**
     * data : {"wechat":{"appid":"wx3e5624b6a9f3c28b","noncestr":"WYBOcWNDDz8ZsPbD","packageStr":"Sign=WXPay","partnerid":"1502524261","prepayid":"wx11135734658694cd3e6abc451033965090","sign":"2A98E8D5DFF94035598B3E14FE551355","timestamp":"1531288654"},"alipay":{"sgin":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018062960546026&biz_content=%7B%22out_trade_no%22%3A%22214421601333387264%22%2C%22passback_params%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%94%B5%E5%95%86%E5%B9%B3%E5%8F%B0-%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.1%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fshop.pbphkj.com%2Fwebservernv%2FaliPayCallback.htm&sign=LboM1bKKBFcSPYsMcmZKBUKDpM3n%2BrbVOFhb%2BOpsOjSWzpP4NhJAj%2Bb2d3bvmGr85Ku%2B3RjrZcNS9%2Fze85V0Tp%2Bs2cMA%2FKvni2A6NwkOMmb2bIuZnANopfsgbzP0nvapBd%2BlsRr46oERVFUFkuisDviPhRrQ6VdOme50G4%2BrKL50ouEj8Rfmoq8OUi7lyTlhqQl2ZqrrbpniPGAV8ItHULU%2Bf2UFbV7E0%2BdSuRcf0fkRSqUEDjQYLHBZM1fX3Cm4%2BwdusGsfa03OSIGPfdYGePS7VXbtzFHOCFgc%2BcsaFNrn8ybVtUHvx3ZxBQzJuDiOVBRiqpVrXDriWb63kw4AoQ%3D%3D&sign_type=RSA2&timestamp=2018-07-11+13%3A56%3A14&version=1.0"}}
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
         * wechat : {"appid":"wx3e5624b6a9f3c28b","noncestr":"WYBOcWNDDz8ZsPbD","packageStr":"Sign=WXPay","partnerid":"1502524261","prepayid":"wx11135734658694cd3e6abc451033965090","sign":"2A98E8D5DFF94035598B3E14FE551355","timestamp":"1531288654"}
         * alipay : {"sgin":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018062960546026&biz_content=%7B%22out_trade_no%22%3A%22214421601333387264%22%2C%22passback_params%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%94%B5%E5%95%86%E5%B9%B3%E5%8F%B0-%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.1%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fshop.pbphkj.com%2Fwebservernv%2FaliPayCallback.htm&sign=LboM1bKKBFcSPYsMcmZKBUKDpM3n%2BrbVOFhb%2BOpsOjSWzpP4NhJAj%2Bb2d3bvmGr85Ku%2B3RjrZcNS9%2Fze85V0Tp%2Bs2cMA%2FKvni2A6NwkOMmb2bIuZnANopfsgbzP0nvapBd%2BlsRr46oERVFUFkuisDviPhRrQ6VdOme50G4%2BrKL50ouEj8Rfmoq8OUi7lyTlhqQl2ZqrrbpniPGAV8ItHULU%2Bf2UFbV7E0%2BdSuRcf0fkRSqUEDjQYLHBZM1fX3Cm4%2BwdusGsfa03OSIGPfdYGePS7VXbtzFHOCFgc%2BcsaFNrn8ybVtUHvx3ZxBQzJuDiOVBRiqpVrXDriWb63kw4AoQ%3D%3D&sign_type=RSA2&timestamp=2018-07-11+13%3A56%3A14&version=1.0"}
         */

        private WechatBean wechat;
        private AlipayBean alipay;

        public WechatBean getWechat() {
            return wechat;
        }

        public void setWechat(WechatBean wechat) {
            this.wechat = wechat;
        }

        public AlipayBean getAlipay() {
            return alipay;
        }

        public void setAlipay(AlipayBean alipay) {
            this.alipay = alipay;
        }

        public static class WechatBean {
            /**
             * appid : wx3e5624b6a9f3c28b
             * noncestr : WYBOcWNDDz8ZsPbD
             * packageStr : Sign=WXPay
             * partnerid : 1502524261
             * prepayid : wx11135734658694cd3e6abc451033965090
             * sign : 2A98E8D5DFF94035598B3E14FE551355
             * timestamp : 1531288654
             */

            private String appid;
            private String noncestr;
            private String packageStr;
            private String partnerid;
            private String prepayid;
            private String sign;
            private String timestamp;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageStr() {
                return packageStr;
            }

            public void setPackageStr(String packageStr) {
                this.packageStr = packageStr;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }

        public static class AlipayBean {
            /**
             * sgin : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018062960546026&biz_content=%7B%22out_trade_no%22%3A%22214421601333387264%22%2C%22passback_params%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%94%B5%E5%95%86%E5%B9%B3%E5%8F%B0-%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.1%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fshop.pbphkj.com%2Fwebservernv%2FaliPayCallback.htm&sign=LboM1bKKBFcSPYsMcmZKBUKDpM3n%2BrbVOFhb%2BOpsOjSWzpP4NhJAj%2Bb2d3bvmGr85Ku%2B3RjrZcNS9%2Fze85V0Tp%2Bs2cMA%2FKvni2A6NwkOMmb2bIuZnANopfsgbzP0nvapBd%2BlsRr46oERVFUFkuisDviPhRrQ6VdOme50G4%2BrKL50ouEj8Rfmoq8OUi7lyTlhqQl2ZqrrbpniPGAV8ItHULU%2Bf2UFbV7E0%2BdSuRcf0fkRSqUEDjQYLHBZM1fX3Cm4%2BwdusGsfa03OSIGPfdYGePS7VXbtzFHOCFgc%2BcsaFNrn8ybVtUHvx3ZxBQzJuDiOVBRiqpVrXDriWb63kw4AoQ%3D%3D&sign_type=RSA2&timestamp=2018-07-11+13%3A56%3A14&version=1.0
             */

            private String sgin;

            public String getSgin() {
                return sgin;
            }

            public void setSgin(String sgin) {
                this.sgin = sgin;
            }
        }
    }
}
