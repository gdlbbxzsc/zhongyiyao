package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetGoodsCategoryListForFollowResponse extends BaseResponesModel {

    /**
     * data : {"goodsCategory":[{"catId":29,"catName":"撒发生","followCounts":1},{"catId":36,"catName":"旗袍","followCounts":1}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<GoodsCategoryBean> goodsCategory;

        public List<GoodsCategoryBean> getGoodsCategory() {
            return goodsCategory;
        }

        public void setGoodsCategory(List<GoodsCategoryBean> goodsCategory) {
            this.goodsCategory = goodsCategory;
        }

        public static class GoodsCategoryBean {
            /**
             * catId : 29
             * catName : 撒发生
             * followCounts : 1
             */

            private int catId;
            private String catName;
            private int followCounts;

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

            public int getFollowCounts() {
                return followCounts;
            }

            public void setFollowCounts(int followCounts) {
                this.followCounts = followCounts;
            }
        }
    }
}
