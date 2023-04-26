package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/7.
 */

public class MoblieLoginResponse extends BaseResponesModel {

    /**
     * data : {"customers":{"createTime":1525743249000,"customerId":168,"customerPassword":"bcdb713721e9be112a128210d6717d8c","customerType":0,"customerUsername":"13333333333","delFlag":0,"ppid":168,"infoPointSum":0,"isDisable":0,"isVerificationMobile":0,"loginErrorCount":0,"loginIp":"192.168.12.1","loginKey":"a163c2b5-0434-4352-a2c2-13e45ac446d4","mobile":"13333333333","modifyTime":1525743249000,"sex":0,"userSaltVal":"090aa6732f076ed1c2eacf81c3b847e17ffffe9cfdca723b5995f35a05d47570"},"tokens":"FF936D78D116A4FB727FCBB4AB49EC9348E1EDCA5813ADDA4972D223"}
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
         * customers : {"createTime":1525743249000,"customerId":168,"customerPassword":"bcdb713721e9be112a128210d6717d8c","customerType":0,"customerUsername":"13333333333","delFlag":0,"ppid":168,"infoPointSum":0,"isDisable":0,"isVerificationMobile":0,"loginErrorCount":0,"loginIp":"192.168.12.1","loginKey":"a163c2b5-0434-4352-a2c2-13e45ac446d4","mobile":"13333333333","modifyTime":1525743249000,"sex":0,"userSaltVal":"090aa6732f076ed1c2eacf81c3b847e17ffffe9cfdca723b5995f35a05d47570"}
         * tokens : FF936D78D116A4FB727FCBB4AB49EC9348E1EDCA5813ADDA4972D223
         */

        private CustomersBean customers;
        private String tokens;

        public CustomersBean getCustomers() {
            return customers;
        }

        public void setCustomers(CustomersBean customers) {
            this.customers = customers;
        }

        public String getTokens() {
            return tokens;
        }

        public void setTokens(String tokens) {
            this.tokens = tokens;
        }

        public static class CustomersBean {
            /**
             * createTime : 1525743249000
             * customerId : 168
             * customerPassword : bcdb713721e9be112a128210d6717d8c
             * customerType : 0
             * customerUsername : 13333333333
             * delFlag : 0
             * ppid : 168
             * infoPointSum : 0
             * isDisable : 0
             * isVerificationMobile : 0
             * loginErrorCount : 0
             * loginIp : 192.168.12.1
             * loginKey : a163c2b5-0434-4352-a2c2-13e45ac446d4
             * mobile : 13333333333
             * modifyTime : 1525743249000
             * sex : 0
             * userSaltVal : 090aa6732f076ed1c2eacf81c3b847e17ffffe9cfdca723b5995f35a05d47570
             */

            private long createTime;
            private int customerId;
            private String customerPassword;
            private int customerType;
            private String customerUsername;
            private int delFlag;
            private int ppid;
            private int infoPointSum;
            private int isDisable;
            private int isVerificationMobile;
            private int loginErrorCount;
            private String loginIp;
            private String loginKey;
            private String mobile;
            private long modifyTime;
            private int sex;
            private String userSaltVal;
            private String customerImg;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public String getCustomerPassword() {
                return customerPassword;
            }

            public void setCustomerPassword(String customerPassword) {
                this.customerPassword = customerPassword;
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

            public int getIsVerificationMobile() {
                return isVerificationMobile;
            }

            public void setIsVerificationMobile(int isVerificationMobile) {
                this.isVerificationMobile = isVerificationMobile;
            }

            public int getLoginErrorCount() {
                return loginErrorCount;
            }

            public void setLoginErrorCount(int loginErrorCount) {
                this.loginErrorCount = loginErrorCount;
            }

            public String getLoginIp() {
                return loginIp;
            }

            public void setLoginIp(String loginIp) {
                this.loginIp = loginIp;
            }

            public String getLoginKey() {
                return loginKey;
            }

            public void setLoginKey(String loginKey) {
                this.loginKey = loginKey;
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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getUserSaltVal() {
                return userSaltVal;
            }

            public void setUserSaltVal(String userSaltVal) {
                this.userSaltVal = userSaltVal;
            }

            public String getCustomerImg() {
                return customerImg;
            }

            public void setCustomerImg(String customerImg) {
                this.customerImg = customerImg;
            }
        }
    }
}
