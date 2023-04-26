package com.pbph.shoppingmall.model.message;

import com.pbph.mvp.base.model.BaseModel;

/**
 * Created by Administrator on 2018/4/11.
 */

public class SearchText2GoodsItemMsg extends BaseModel {

    public String searchText;

    //是文字变化 发的消息 还是 切换选项卡 发的消息。true 文字变化。false 切换选项卡。
//    但是 切换选项卡并不一定代表文字没有变。
    public boolean isSearchTextChanged;
}
