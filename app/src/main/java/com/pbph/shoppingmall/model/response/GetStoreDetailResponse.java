package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/30.
 */

public class GetStoreDetailResponse extends BaseResponesModel {


    /**
     * data : {"brandList":[{"brandLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage/admin/1506407144981.jpg","ppid":1},{"brandLogo":"http://pbkjb2b2cbucket
     * .oss-cn-beijing.aliyuncs.com/productimage/admin/1506407144981.jpg","ppid":6}],
     * "categoryList":[{"catName":"三级分类","ppid":24},{"catName":"撒发生","ppid":29},
     * {"catName":"测试三级分类","ppid":21}],"collectionNumber":51,"companyAddr":"南岗区",
     * "companyContactTel":"13946140335","companyCreTime":"2018-03-30","ppid":37,
     * "overallMerit":100,"storeLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
     * .com/productimage2/37/1523861403780.jpg","storeName":"鹏博科技","thirdPositionNum":"323"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * brandList : [{"brandLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage/admin/1506407144981.jpg","ppid":1},
         * {"brandLogo":"http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage/admin/1506407144981.jpg","ppid":6}]
         * categoryList : [{"catName":"三级分类","ppid":24},{"catName":"撒发生","ppid":29},
         * {"catName":"测试三级分类","ppid":21}]
         * collectionNumber : 51
         * companyAddr : 南岗区
         * companyContactTel : 13946140335
         * companyCreTime : 2018-03-30
         * ppid : 37
         * overallMerit : 100
         * storeLogo : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs
         * .com/productimage2/37/1523861403780.jpg
         * storeName : 鹏博科技
         * thirdPositionNum : 323
         */

        private int collectionNumber;
        private String companyAddr;
        private String companyContactTel;
        private String companyCreTime;
        private int ppid;
        private int overallMerit;
        private String storeLogo;
        private String storeName;
        private String thirdPositionNum;
        private int  flag;//是否关注店铺
        private List<BrandListBean> brandList;
        private List<CategoryListBean> categoryList;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getCollectionNumber() {
            return collectionNumber;
        }

        public void setCollectionNumber(int collectionNumber) {
            this.collectionNumber = collectionNumber;
        }

        public String getCompanyAddr() {
            return companyAddr;
        }

        public void setCompanyAddr(String companyAddr) {
            this.companyAddr = companyAddr;
        }

        public String getCompanyContactTel() {
            return companyContactTel;
        }

        public void setCompanyContactTel(String companyContactTel) {
            this.companyContactTel = companyContactTel;
        }

        public String getCompanyCreTime() {
            return companyCreTime;
        }

        public void setCompanyCreTime(String companyCreTime) {
            this.companyCreTime = companyCreTime;
        }

        public int getPpid() {
            return ppid;
        }

        public void setPpid(int ppid) {
            this.ppid = ppid;
        }

        public int getOverallMerit() {
            return overallMerit;
        }

        public void setOverallMerit(int overallMerit) {
            this.overallMerit = overallMerit;
        }

        public String getStoreLogo() {
            return storeLogo;
        }

        public void setStoreLogo(String storeLogo) {
            this.storeLogo = storeLogo;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getThirdPositionNum() {
            return thirdPositionNum;
        }

        public void setThirdPositionNum(String thirdPositionNum) {
            this.thirdPositionNum = thirdPositionNum;
        }

        public List<BrandListBean> getBrandList() {
            return brandList;
        }

        public void setBrandList(List<BrandListBean> brandList) {
            this.brandList = brandList;
        }

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public static class BrandListBean implements Serializable{
            /**
             * brandLogo : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage/admin/1506407144981.jpg
             * ppid : 1
             */

            private String brandLogo;
            private int ppid;

            public String getBrandLogo() {
                return brandLogo;
            }

            public void setBrandLogo(String brandLogo) {
                this.brandLogo = brandLogo;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }
        }

        public static class CategoryListBean  implements Serializable{
            /**
             * catName : 三级分类
             * ppid : 24
             */

            private String catName;
            private int ppid;

            public String getCatName() {
                return catName;
            }

            public void setCatName(String catName) {
                this.catName = catName;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }
        }
    }
}
