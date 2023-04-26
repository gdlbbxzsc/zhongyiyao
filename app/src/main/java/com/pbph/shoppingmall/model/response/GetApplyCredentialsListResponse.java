package com.pbph.shoppingmall.model.response;

import com.google.gson.annotations.SerializedName;
import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetApplyCredentialsListResponse extends BaseResponesModel {

    /**
     * data : {"applyCredentialsList":[{"code":1,"value":"有发票"},{"code":2,"value":"有质检报告"},{"code":3,"value":"没有任何凭据"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ApplyCredentialsListBean> applyCredentialsList;

        public List<ApplyCredentialsListBean> getApplyCredentialsList() {
            return applyCredentialsList;
        }

        public void setApplyCredentialsList(List<ApplyCredentialsListBean> applyCredentialsList) {
            this.applyCredentialsList = applyCredentialsList;
        }

        public static class ApplyCredentialsListBean {
            /**
             * code : 1
             * value : 有发票
             */

            @SerializedName("code")
            private int codeX;
            private String value;

            public int getCodeX() {
                return codeX;
            }

            public void setCodeX(int codeX) {
                this.codeX = codeX;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
