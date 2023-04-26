package com.pbph.shoppingmall.model.message;

import com.pbph.mvp.base.model.BaseModel;
import com.pbph.shoppingmall.model.response.SearchProductResponse;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class FilterDatasMsg extends BaseModel {

    public List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean> params;
    public List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands;

    public  boolean isClearData;

}
