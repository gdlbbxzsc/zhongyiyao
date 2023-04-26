package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/9.
 */

public class ProductDetailRequest<T extends BaseResponesModel> extends BaseRequest<T> {

//    public int productId;
//    public int  customerId;
//    public int browerMethod;

//    public int productId = 112;
//    public int browerMethod = 0;
//    public int startRowNum = 0;
//    public int endRowNum = 1;
//    public int bossOrStore = 0;

    public Integer customerId;
    public int  goodsInfoId;
    public int  browerMethod= 0;

    @Override
    public String getRequestPath() {
        return Constant.Path.PRODUCT_DETAIL;
    }
}
