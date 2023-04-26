package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetDefaultBillResponse extends BaseResponesModel {

    /**
     * data : {"billEmail":"","billTelephone":"","billTitle":"吞吞吐吐","billType":"0","createTime":1525867778000,"customerId":168,"defaultVal":1,"delFlag":0,"ppid":173}
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
         * billEmail :
         * billTelephone :
         * billTitle : 吞吞吐吐
         * billType : 0
         * createTime : 1525867778000
         * customerId : 168
         * defaultVal : 1
         * delFlag : 0
         * ppid : 173
         */

        private String billEmail;
        private String billTelephone;
        private String billTitle;
        private String billType;
        private String createTime;
        private int customerId;
        private int defaultVal;
        private int delFlag;
        private int ppid;

        private String billContent;
        private String modifyTime;
        private String billParagraph;

        private String billUnit;
        private String billBankName;
        private String billBankAccount;

        public String getBillEmail() {
            return billEmail;
        }

        public void setBillEmail(String billEmail) {
            this.billEmail = billEmail;
        }

        public String getBillTelephone() {
            return billTelephone;
        }

        public void setBillTelephone(String billTelephone) {
            this.billTelephone = billTelephone;
        }

        public String getBillTitle() {
            return billTitle;
        }

        public void setBillTitle(String billTitle) {
            this.billTitle = billTitle;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getDefaultVal() {
            return defaultVal;
        }

        public void setDefaultVal(int defaultVal) {
            this.defaultVal = defaultVal;
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

        public String getBillContent() {
            return billContent;
        }

        public void setBillContent(String billContent) {
            this.billContent = billContent;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getBillParagraph() {
            return billParagraph;
        }

        public void setBillParagraph(String billParagraph) {
            this.billParagraph = billParagraph;
        }

        public String getBillUnit() {
            return billUnit;
        }

        public void setBillUnit(String billUnit) {
            this.billUnit = billUnit;
        }

        public String getBillBankName() {
            return billBankName;
        }

        public void setBillBankName(String billBankName) {
            this.billBankName = billBankName;
        }

        public String getBillBankAccount() {
            return billBankAccount;
        }

        public void setBillBankAccount(String billBankAccount) {
            this.billBankAccount = billBankAccount;
        }
    }
}
