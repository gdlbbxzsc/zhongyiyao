package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetMyCustomerResponse extends BaseResponesModel {

    /**
     * data : {"customer":{"createTime":1525743249000,"customerAddress":null,"customerCardId":null,"customerId":168,"customerImg":null,"customerInterest":null,"customerNickname":null,"customerPassword":null,"customerRealname":null,"customerType":0,"customerUsername":"13333333333","delFlag":0,"delTime":null,"email":null,"ppid":168,"infoPointSum":0,"isDisable":0,"isVerificationEmail":null,"isVerificationMobile":0,"jpushId":null,"loginErrorCount":null,"loginIp":null,"loginKey":null,"loginLockTime":null,"loginTime":null,"mobile":"13333333333","modifyTime":1525743249000,"npCoupon":null,"npOrder":null,"os":null,"pointLevelName":null,"sex":0,"storeId":null,"userSaltVal":null},"customerInfo":{"customerAddress":null,"customerBirthday":null,"customerCardId":null,"customerCity":null,"customerCounty":null,"customerId":168,"customerImg":null,"customerInterest":null,"customerMaritalType":0,"customerPassword":null,"customerPhone":null,"customerPointSum":246248,"customerProvince":null,"customerQq":null,"customerRealname":null,"customerStreet":null,"customerUsername":null,"email":null,"ppid":11,"mobile":null,"modifyTime":null,"pointLevelId":14,"pointLevelName":"aaaa","sex":0},"pointLevelNameImg":""}
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
         * customer : {"createTime":1525743249000,"customerAddress":null,"customerCardId":null,"customerId":168,"customerImg":null,"customerInterest":null,"customerNickname":null,"customerPassword":null,"customerRealname":null,"customerType":0,"customerUsername":"13333333333","delFlag":0,"delTime":null,"email":null,"ppid":168,"infoPointSum":0,"isDisable":0,"isVerificationEmail":null,"isVerificationMobile":0,"jpushId":null,"loginErrorCount":null,"loginIp":null,"loginKey":null,"loginLockTime":null,"loginTime":null,"mobile":"13333333333","modifyTime":1525743249000,"npCoupon":null,"npOrder":null,"os":null,"pointLevelName":null,"sex":0,"storeId":null,"userSaltVal":null}
         * customerInfo : {"customerAddress":null,"customerBirthday":null,"customerCardId":null,"customerCity":null,"customerCounty":null,"customerId":168,"customerImg":null,"customerInterest":null,"customerMaritalType":0,"customerPassword":null,"customerPhone":null,"customerPointSum":246248,"customerProvince":null,"customerQq":null,"customerRealname":null,"customerStreet":null,"customerUsername":null,"email":null,"ppid":11,"mobile":null,"modifyTime":null,"pointLevelId":14,"pointLevelName":"aaaa","sex":0}
         * pointLevelNameImg :
         */

        private CustomerBean customer;
        private CustomerInfoBean customerInfo;
        private String pointLevelNameImg;

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public CustomerInfoBean getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(CustomerInfoBean customerInfo) {
            this.customerInfo = customerInfo;
        }

        public String getPointLevelNameImg() {
            return pointLevelNameImg;
        }

        public void setPointLevelNameImg(String pointLevelNameImg) {
            this.pointLevelNameImg = pointLevelNameImg;
        }

        public static class CustomerBean {
            /**
             * createTime : 1525743249000
             * customerAddress : null
             * customerCardId : null
             * customerId : 168
             * customerImg : null
             * customerInterest : null
             * customerNickname : null
             * customerPassword : null
             * customerRealname : null
             * customerType : 0
             * customerUsername : 13333333333
             * delFlag : 0
             * delTime : null
             * email : null
             * ppid : 168
             * infoPointSum : 0
             * isDisable : 0
             * isVerificationEmail : null
             * isVerificationMobile : 0
             * jpushId : null
             * loginErrorCount : null
             * loginIp : null
             * loginKey : null
             * loginLockTime : null
             * loginTime : null
             * mobile : 13333333333
             * modifyTime : 1525743249000
             * npCoupon : null
             * npOrder : null
             * os : null
             * pointLevelName : null
             * sex : 0
             * storeId : null
             * userSaltVal : null
             */

            private long createTime;
            private Object customerAddress;
            private Object customerCardId;
            private int customerId;
            private String customerImg;
            private Object customerInterest;
            private String customerNickname;
            private Object customerPassword;
            private Object customerRealname;
            private int customerType;
            private String customerUsername;
            private int delFlag;
            private Object delTime;
            private Object email;
            private int ppid;
            private int infoPointSum;
            private int isDisable;
            private Object isVerificationEmail;
            private int isVerificationMobile;
            private Object jpushId;
            private Object loginErrorCount;
            private Object loginIp;
            private Object loginKey;
            private Object loginLockTime;
            private Object loginTime;
            private String mobile;
            private long modifyTime;
            private Object npCoupon;
            private Object npOrder;
            private Object os;
            private Object pointLevelName;
            private int sex;// 0保密1男2女
            private Object storeId;
            private Object userSaltVal;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getCustomerAddress() {
                return customerAddress;
            }

            public void setCustomerAddress(Object customerAddress) {
                this.customerAddress = customerAddress;
            }

            public Object getCustomerCardId() {
                return customerCardId;
            }

            public void setCustomerCardId(Object customerCardId) {
                this.customerCardId = customerCardId;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }

            public Object getCustomerInterest() {
                return customerInterest;
            }

            public void setCustomerInterest(Object customerInterest) {
                this.customerInterest = customerInterest;
            }

            public String getCustomerNickname() {
                return customerNickname;
            }

            public void setCustomerNickname(String customerNickname) {
                this.customerNickname = customerNickname;
            }

            public Object getCustomerPassword() {
                return customerPassword;
            }

            public void setCustomerPassword(Object customerPassword) {
                this.customerPassword = customerPassword;
            }

            public Object getCustomerRealname() {
                return customerRealname;
            }

            public void setCustomerRealname(Object customerRealname) {
                this.customerRealname = customerRealname;
            }

            public int getCustomerType() {
                return customerType;
            }

            public void setCustomerType(int customerType) {
                this.customerType = customerType;
            }

            public String getCustomerUsername() {
                return customerUsername;
            }

            public void setCustomerUsername(String customerUsername) {
                this.customerUsername = customerUsername;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public Object getDelTime() {
                return delTime;
            }

            public void setDelTime(Object delTime) {
                this.delTime = delTime;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public int getInfoPointSum() {
                return infoPointSum;
            }

            public void setInfoPointSum(int infoPointSum) {
                this.infoPointSum = infoPointSum;
            }

            public int getIsDisable() {
                return isDisable;
            }

            public void setIsDisable(int isDisable) {
                this.isDisable = isDisable;
            }

            public Object getIsVerificationEmail() {
                return isVerificationEmail;
            }

            public void setIsVerificationEmail(Object isVerificationEmail) {
                this.isVerificationEmail = isVerificationEmail;
            }

            public int getIsVerificationMobile() {
                return isVerificationMobile;
            }

            public void setIsVerificationMobile(int isVerificationMobile) {
                this.isVerificationMobile = isVerificationMobile;
            }

            public Object getJpushId() {
                return jpushId;
            }

            public void setJpushId(Object jpushId) {
                this.jpushId = jpushId;
            }

            public Object getLoginErrorCount() {
                return loginErrorCount;
            }

            public void setLoginErrorCount(Object loginErrorCount) {
                this.loginErrorCount = loginErrorCount;
            }

            public Object getLoginIp() {
                return loginIp;
            }

            public void setLoginIp(Object loginIp) {
                this.loginIp = loginIp;
            }

            public Object getLoginKey() {
                return loginKey;
            }

            public void setLoginKey(Object loginKey) {
                this.loginKey = loginKey;
            }

            public Object getLoginLockTime() {
                return loginLockTime;
            }

            public void setLoginLockTime(Object loginLockTime) {
                this.loginLockTime = loginLockTime;
            }

            public Object getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(Object loginTime) {
                this.loginTime = loginTime;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public Object getNpCoupon() {
                return npCoupon;
            }

            public void setNpCoupon(Object npCoupon) {
                this.npCoupon = npCoupon;
            }

            public Object getNpOrder() {
                return npOrder;
            }

            public void setNpOrder(Object npOrder) {
                this.npOrder = npOrder;
            }

            public Object getOs() {
                return os;
            }

            public void setOs(Object os) {
                this.os = os;
            }

            public Object getPointLevelName() {
                return pointLevelName;
            }

            public void setPointLevelName(Object pointLevelName) {
                this.pointLevelName = pointLevelName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getStoreId() {
                return storeId;
            }

            public void setStoreId(Object storeId) {
                this.storeId = storeId;
            }

            public Object getUserSaltVal() {
                return userSaltVal;
            }

            public void setUserSaltVal(Object userSaltVal) {
                this.userSaltVal = userSaltVal;
            }
        }

        public static class CustomerInfoBean {
            /**
             * customerAddress : null
             * customerBirthday : null
             * customerCardId : null
             * customerCity : null
             * customerCounty : null
             * customerId : 168
             * customerImg : null
             * customerInterest : null
             * customerMaritalType : 0
             * customerPassword : null
             * customerPhone : null
             * customerPointSum : 246248
             * customerProvince : null
             * customerQq : null
             * customerRealname : null
             * customerStreet : null
             * customerUsername : null
             * email : null
             * ppid : 11
             * mobile : null
             * modifyTime : null
             * pointLevelId : 14
             * pointLevelName : aaaa
             * sex : 0
             */

            private Object customerAddress;
            private String customerBirthday;
            private Object customerCardId;
            private Object customerCity;
            private Object customerCounty;
            private int customerId;
            private Object customerImg;
            private Object customerInterest;
            private int customerMaritalType;
            private Object customerPassword;
            private Object customerPhone;
            private int customerPointSum;
            private Object customerProvince;
            private Object customerQq;
            private Object customerRealname;
            private Object customerStreet;
            private Object customerUsername;
            private Object email;
            private int ppid;
            private Object mobile;
            private Object modifyTime;
            private int pointLevelId;
            private String pointLevelName;
            private int sex;

            public Object getCustomerAddress() {
                return customerAddress;
            }

            public void setCustomerAddress(Object customerAddress) {
                this.customerAddress = customerAddress;
            }

            public String getCustomerBirthday() {
                return customerBirthday;
            }

            public void setCustomerBirthday(String customerBirthday) {
                this.customerBirthday = customerBirthday;
            }

            public Object getCustomerCardId() {
                return customerCardId;
            }

            public void setCustomerCardId(Object customerCardId) {
                this.customerCardId = customerCardId;
            }

            public Object getCustomerCity() {
                return customerCity;
            }

            public void setCustomerCity(Object customerCity) {
                this.customerCity = customerCity;
            }

            public Object getCustomerCounty() {
                return customerCounty;
            }

            public void setCustomerCounty(Object customerCounty) {
                this.customerCounty = customerCounty;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public Object getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(Object customerImg) {
                this.customerImg = customerImg;
            }

            public Object getCustomerInterest() {
                return customerInterest;
            }

            public void setCustomerInterest(Object customerInterest) {
                this.customerInterest = customerInterest;
            }

            public int getCustomerMaritalType() {
                return customerMaritalType;
            }

            public void setCustomerMaritalType(int customerMaritalType) {
                this.customerMaritalType = customerMaritalType;
            }

            public Object getCustomerPassword() {
                return customerPassword;
            }

            public void setCustomerPassword(Object customerPassword) {
                this.customerPassword = customerPassword;
            }

            public Object getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(Object customerPhone) {
                this.customerPhone = customerPhone;
            }

            public int getCustomerPointSum() {
                return customerPointSum;
            }

            public void setCustomerPointSum(int customerPointSum) {
                this.customerPointSum = customerPointSum;
            }

            public Object getCustomerProvince() {
                return customerProvince;
            }

            public void setCustomerProvince(Object customerProvince) {
                this.customerProvince = customerProvince;
            }

            public Object getCustomerQq() {
                return customerQq;
            }

            public void setCustomerQq(Object customerQq) {
                this.customerQq = customerQq;
            }

            public Object getCustomerRealname() {
                return customerRealname;
            }

            public void setCustomerRealname(Object customerRealname) {
                this.customerRealname = customerRealname;
            }

            public Object getCustomerStreet() {
                return customerStreet;
            }

            public void setCustomerStreet(Object customerStreet) {
                this.customerStreet = customerStreet;
            }

            public Object getCustomerUsername() {
                return customerUsername;
            }

            public void setCustomerUsername(Object customerUsername) {
                this.customerUsername = customerUsername;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getPointLevelId() {
                return pointLevelId;
            }

            public void setPointLevelId(int pointLevelId) {
                this.pointLevelId = pointLevelId;
            }

            public String getPointLevelName() {
                return pointLevelName;
            }

            public void setPointLevelName(String pointLevelName) {
                this.pointLevelName = pointLevelName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }
    }
}
