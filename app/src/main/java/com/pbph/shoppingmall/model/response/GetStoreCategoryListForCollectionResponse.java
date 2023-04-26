package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetStoreCategoryListForCollectionResponse extends BaseResponesModel {

    /**
     * data : {"storeCategory":[{"catName":"外星人三代","cateId":20,"collectionCounts":"1"},{"catName":"拯救者","cateId":21,"collectionCounts":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<StoreCategoryBean> storeCategory;

        public List<StoreCategoryBean> getStoreCategory() {
            return storeCategory;
        }

        public void setStoreCategory(List<StoreCategoryBean> storeCategory) {
            this.storeCategory = storeCategory;
        }

        public static class StoreCategoryBean {
            /**
             * catName : 外星人三代
             * cateId : 20
             * collectionCounts : 1
             */

            private String catName;
            private int cateId;
            private String collectionCounts;

            public String getCatName() {
                return catName;
            }

            public void setCatName(String catName) {
                this.catName = catName;
            }

            public int getCateId() {
                return cateId;
            }

            public void setCateId(int cateId) {
                this.cateId = cateId;
            }

            public String getCollectionCounts() {
                return collectionCounts;
            }

            public void setCollectionCounts(String collectionCounts) {
                this.collectionCounts = collectionCounts;
            }
        }
    }
}
