package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class GetCollectionSellerListRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public int startRowNum;
    public int endRowNum;

    public String keyword;
    public Integer cateId;
    public String sort;//1D默认排序；2D好评优先排序


    @Override
    public String getRequestPath() {
        return Constant.Path.GETCOLLECTIONSELLERLIST;
    }
}
