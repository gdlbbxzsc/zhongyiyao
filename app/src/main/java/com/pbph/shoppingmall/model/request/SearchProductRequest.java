package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class SearchProductRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;//用户id
    public String keyword;             //搜索关键字
    public String sort;//排序
    public Integer storeId;//商家id
    public Integer isThird;//是否第三方（0boss，i店铺）
    public Integer pageNo;//开始页
    public Integer pageSize;//结束页
    public String catIds;//直营分类id
    public String thirdCats;//第三方分类id
    public String brands;//品牌名称
    public String priceMin;//最低价格
    public String priceMax;//最高价格
    public String params;//属性

    @Override
    public String getRequestPath() {
        return Constant.Path.SEARCHPRODUCT;
    }
}
