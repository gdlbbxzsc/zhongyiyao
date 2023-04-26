package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/8.
 */

public class GetGoodsListRequest <T extends BaseResponesModel> extends BaseRequest<T> {

    public int cateId;
    public int showStatus;
    public int startRowNum;
    public int endRowNum;
    public int bossOrStore;
    public Integer customerId;
    public String sort;
    @Override
    public String getRequestPath() {
        return Constant.Path.GET_GOODS_LIST;
    }
}
