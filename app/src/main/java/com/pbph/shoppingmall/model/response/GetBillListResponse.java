package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetBillListResponse extends BaseResponesModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * billContent : 哈哈哈哈
         * billEmail : frefre
         * billTelephone : 13121301200
         * billTitle : 测试123
         * billType : 1
         * customerId : 168
         * defaultVal : 0
         * delFlag : 0
         * ppid : 124
         */

//        private int id;//数据库id

//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }

        private String billContent;
        private String billEmail;
        private String billTelephone;
        private String billTitle;
        private String billType;
        private int customerId;
        private int defaultVal;
        private int delFlag;
        private int ppid;
        private String billParagraph;

        private String billUnit;
        private String billBankName;
        private String billBankAccount;

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

        public String getBillParagraph() {
            return billParagraph;
        }

        public void setBillParagraph(String billParagraph) {
            this.billParagraph = billParagraph;
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
    }
}
