package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetBillByIdResponse extends BaseResponesModel {

    /**
     * data : {"billBankAccount":null,"billBankName":null,"billContent":"哈哈哈哈","billEmail":"frefre","billParagraph":null,"billTelephone":"13121301200","billTitle":"测试123","billType":"1","billUnit":null,"createTime":null,"customerId":168,"defaultVal":0,"delFlag":0,"delTime":null,"ppid":124,"modifyTime":null}
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
         * billBankAccount : null
         * billBankName : null
         * billContent : 哈哈哈哈
         * billEmail : frefre
         * billParagraph : null
         * billTelephone : 13121301200
         * billTitle : 测试123
         * billType : 1
         * billUnit : null
         * createTime : null
         * customerId : 168
         * defaultVal : 0
         * delFlag : 0
         * delTime : null
         * ppid : 124
         * modifyTime : null
         */

        private String billBankAccount;
        private String billBankName;
        private String billContent;
        private String billEmail;
        private String billParagraph;
        private String billTelephone;
        private String billTitle;
        private String billType;
        private String billUnit;
        private Object createTime;
        private int customerId;
        private int defaultVal;
        private int delFlag;
        private Object delTime;
        private int ppid;
        private Object modifyTime;

        public String getBillBankAccount() {
            return billBankAccount;
        }

        public void setBillBankAccount(String billBankAccount) {
            this.billBankAccount = billBankAccount;
        }

        public String getBillBankName() {
            return billBankName;
        }

        public void setBillBankName(String billBankName) {
            this.billBankName = billBankName;
        }

        public String getBillContent() {
            return billContent;
        }

        public void setBillContent(String billContent) {
            this.billContent = billContent;
        }

        public String getBillEmail() {
            return billEmail;
        }

        public void setBillEmail(String billEmail) {
            this.billEmail = billEmail;
        }

        public String getBillParagraph() {
            return billParagraph;
        }

        public void setBillParagraph(String billParagraph) {
            this.billParagraph = billParagraph;
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

        public String getBillUnit() {
            return billUnit;
        }

        public void setBillUnit(String billUnit) {
            this.billUnit = billUnit;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public Object getDelTime() {
            return delTime;
        }

        public void setDelTime(Object delTime) {
            this.delTime = delTime;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
