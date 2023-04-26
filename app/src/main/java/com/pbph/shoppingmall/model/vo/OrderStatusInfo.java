package com.pbph.shoppingmall.model.vo;

/**
 * Created by Administrator on 2018/4/26.
 */

public class OrderStatusInfo {

    //    和服务器对应的 订单状态 与文字
    public Integer id;
    public String text;

    //    该订单状态下会有的操作权限 按钮名称
    public int[] option_ids;

    //该订单状态下 订单详情显示样式订单详情 只按照如下几种样式展示
    public int type_id;
    public String type_title;


}
