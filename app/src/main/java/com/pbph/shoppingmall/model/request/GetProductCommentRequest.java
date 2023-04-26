package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/9.
 */

public class GetProductCommentRequest <T extends BaseResponesModel> extends BaseRequest<T> {

    public int startRowNum;
    public int endRowNum;
    public int goodsId;
    public int type;
//    public String title;
    @Override
    public String getRequestPath() {
        return Constant.Path.GET_PRODUCT_COMMENT;
    }
}
