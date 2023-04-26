package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

/**
 * Created by 连嘉凡 on 2018/6/20.
 */

public class GetPitchOnSpecResponse extends BaseResponesModel {


    /**
     * data : {"goodsInfoId":115}
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
         * goodsInfoId : 115
         */

        private int goodsInfoId;

        public int getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(int goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }
    }
}
