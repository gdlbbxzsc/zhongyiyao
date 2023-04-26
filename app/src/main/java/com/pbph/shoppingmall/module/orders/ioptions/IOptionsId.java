package com.pbph.shoppingmall.module.orders.ioptions;

import android.content.Intent;

/**
 * Created by Administrator on 2018/5/25.
 */

public interface IOptionsId {

    //再次购买
    void buyAgain(Integer id);

    //删除订单
    void delOrder(Integer id);

    //去支付
    void payOrder(Integer id);

    //取消订单
    void cancelOrder(Integer id);

    //确认收货
    void confirmOrder(Integer id);

    //查看物流
    void checkLogistics(Integer id);

    //申请退款
    void applyRefundMoney(Integer id);

    //评价晒单
    void evaluateOrder(Integer id);

    //退货详情
    void refundGoodsDetail(Integer id);

    //填写物流信息
    void subLogisticsInfo(Integer id);

    //申请退货
    void applyRefundGoods(Integer id);

    //退款详情
    void refundMoneysDetail(Integer id);
}
