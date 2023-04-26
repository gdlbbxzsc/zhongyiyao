package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetCouponCategoryListResponse extends BaseResponesModel {

    /**
     * data : {"couponCategoryList":[{"catGrade":1,"catName":"电脑、办公","catRate":0,"catSeoDesc":"","catSeoKeyword":"","catSeoTitle":"","catSort":1,"createTime":1516278345000,"delFlag":0,"ppid":1,"parentId":0,"createUserId":1,"modifyUserId":1},{"catGrade":1,"catName":"测试一级分类","catRate":0,"catSeoDesc":"测试一级分类","catSeoKeyword":"测试一级分类","catSeoTitle":"测试一级分类","catSort":2,"createTime":1522388431000,"createUserId":1,"delFlag":0,"ppid":19,"parentId":0},{"catGrade":1,"catName":"一级分类","catRate":0,"catSeoDesc":"一级分类","catSeoKeyword":"一级分类","catSeoTitle":"一级分类","catSort":3,"createTime":1522640145000,"createUserId":1,"delFlag":0,"ppid":22,"parentId":0},{"catGrade":1,"catName":"一级分类111","catRate":0,"catSeoDesc":"一级分类111","catSeoKeyword":"一级分类111","catSeoTitle":"一级分类111","catSort":4,"createTime":1522641143000,"createUserId":1,"delFlag":0,"ppid":25,"parentId":0},{"catGrade":1,"catName":"end分类","catRate":0,"catSeoDesc":"end分类","catSeoKeyword":"end分类","catSeoTitle":"end分类","catSort":5,"createTime":1523858561000,"createUserId":1,"delFlag":0,"ppid":30,"parentId":0},{"catGrade":1,"catName":"1F服装总汇","catRate":0,"catSeoDesc":"1F服装总汇","catSeoKeyword":"1F服装总汇","catSeoTitle":"1F服装总汇","catSort":2,"createTime":1525329831000,"createUserId":1,"delFlag":0,"ppid":34,"parentId":0},{"catGrade":1,"catName":"2F手机数码","catRate":0,"catSeoDesc":"手机数码","catSeoKeyword":"手机数码","catSeoTitle":"手机数码","catSort":1,"createTime":1525781308000,"createUserId":1,"delFlag":0,"ppid":40,"modifyUserId":1,"parentId":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CouponCategoryListBean> couponCategoryList;

        public List<CouponCategoryListBean> getCouponCategoryList() {
            return couponCategoryList;
        }

        public void setCouponCategoryList(List<CouponCategoryListBean> couponCategoryList) {
            this.couponCategoryList = couponCategoryList;
        }

        public static class CouponCategoryListBean {
            /**
             * catGrade : 1
             * catName : 电脑、办公
             * catRate : 0.0
             * catSeoDesc :
             * catSeoKeyword :
             * catSeoTitle :
             * catSort : 1
             * createTime : 1516278345000
             * delFlag : 0
             * ppid : 1
             * parentId : 0
             * createUserId : 1
             * modifyUserId : 1
             */

            private int catGrade;
            private String catName;
            private double catRate;
            private String catSeoDesc;
            private String catSeoKeyword;
            private String catSeoTitle;
            private int catSort;
            private long createTime;
            private int delFlag;
            private int ppid;
            private int parentId;
            private int createUserId;
            private int modifyUserId;

            public int getCatGrade() {
                return catGrade;
            }

            public void setCatGrade(int catGrade) {
                this.catGrade = catGrade;
            }

            public String getCatName() {
                return catName;
            }

            public void setCatName(String catName) {
                this.catName = catName;
            }

            public double getCatRate() {
                return catRate;
            }

            public void setCatRate(double catRate) {
                this.catRate = catRate;
            }

            public String getCatSeoDesc() {
                return catSeoDesc;
            }

            public void setCatSeoDesc(String catSeoDesc) {
                this.catSeoDesc = catSeoDesc;
            }

            public String getCatSeoKeyword() {
                return catSeoKeyword;
            }

            public void setCatSeoKeyword(String catSeoKeyword) {
                this.catSeoKeyword = catSeoKeyword;
            }

            public String getCatSeoTitle() {
                return catSeoTitle;
            }

            public void setCatSeoTitle(String catSeoTitle) {
                this.catSeoTitle = catSeoTitle;
            }

            public int getCatSort() {
                return catSort;
            }

            public void setCatSort(int catSort) {
                this.catSort = catSort;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public int getModifyUserId() {
                return modifyUserId;
            }

            public void setModifyUserId(int modifyUserId) {
                this.modifyUserId = modifyUserId;
            }
        }
    }
}
