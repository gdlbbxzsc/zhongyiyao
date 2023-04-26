package com.pbph.shoppingmall.model.response;

import com.pbph.mvp.base.model.BaseResponesModel;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class GetAppDefaultTemplateResponse extends BaseResponesModel {

    /**
     * data : {"adverts":[],"floors":[],"sliders":[]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ItemData> adverts;
        private List<ItemData> floors;
        private List<ItemData> sliders;

        public List<ItemData> getAdverts() {
            return adverts;
        }

        public void setAdverts(List<ItemData> adverts) {
            this.adverts = adverts;
        }

        public List<ItemData> getFloors() {
            return floors;
        }

        public void setFloors(List<ItemData> floors) {
            this.floors = floors;
        }

        public List<ItemData> getSliders() {
            return sliders;
        }

        public void setSliders(List<ItemData> sliders) {
            this.sliders = sliders;
        }
    }


    public static class ItemData {

        /**
         * action : GoodsList
         * actionParam : {"goodsInfoId":"1","searchParam":{"brands":["路飞"],"cates":["end分类"]}}
         * banners : []
         * adverts : []
         * img : http://pbkjb2b2cbucket.oss-cn-beijing.aliyuncs.com/productimage2/3/1519895338339.jpg
         * ordering : 2
         * price : 0
         * text : 0.827433628318584
         */

        private String action;
        private ActionParamBean actionParam;
        private String img;
        private int ordering;
        private int price;
        private String text;
        private List<ItemData> banners;
        private List<ItemData> adverts;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public ActionParamBean getActionParam() {
            return actionParam;
        }

        public void setActionParam(ActionParamBean actionParam) {
            this.actionParam = actionParam;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getOrdering() {
            return ordering;
        }

        public void setOrdering(int ordering) {
            this.ordering = ordering;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<ItemData> getBanners() {
            return banners;
        }

        public void setBanners(List<ItemData> banners) {
            this.banners = banners;
        }

        public List<ItemData> getAdverts() {
            return adverts;
        }

        public void setAdverts(List<ItemData> adverts) {
            this.adverts = adverts;
        }

        public static class ActionParamBean {
            /**
             * goodsInfoId : 1
             * searchParam : {"brands":["路飞"],"cates":["end分类"]}
             */

            private String goodsInfoId;
            private SearchParamBean searchParam;

            public String getGoodsInfoId() {
                return goodsInfoId;
            }

            public void setGoodsInfoId(String goodsInfoId) {
                this.goodsInfoId = goodsInfoId;
            }

            public SearchParamBean getSearchParam() {
                return searchParam;
            }

            public void setSearchParam(SearchParamBean searchParam) {
                this.searchParam = searchParam;
            }

            public static class SearchParamBean {
                private List<String> brands;
                private List<String> cates;

                public List<String> getBrands() {
                    return brands;
                }

                public void setBrands(List<String> brands) {
                    this.brands = brands;
                }

                public List<String> getCates() {
                    return cates;
                }

                public void setCates(List<String> cates) {
                    this.cates = cates;
                }
            }
        }
    }
}
