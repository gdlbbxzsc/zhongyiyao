package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class GetAllCouponListRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public String categoryId;
    public int startRowNum;
    public int endRowNum;

    public String keyword;

    @Override
    public String getRequestPath() {
        return Constant.Path.GETALLCOUPONLIST;
    }
}
