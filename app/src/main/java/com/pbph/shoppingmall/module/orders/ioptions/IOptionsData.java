package com.pbph.shoppingmall.module.orders.ioptions;

import com.pbph.shoppingmall.model.response.GetOrderResponse;

/**
 * Created by Administrator on 2018/5/25.
 */

public interface IOptionsData {

    //再次购买
    void buyAgain(GetOrderResponse.DataBean.ListBean vo);

    //删除订单
    void delOrder(GetOrderResponse.DataBean.ListBean vo);

    //去支付
    void payOrder(GetOrderResponse.DataBean.ListBean vo);

    //取消订单
    void cancelOrder(GetOrderResponse.DataBean.ListBean vo);

    //确认收货
    void confirmOrder(GetOrderResponse.DataBean.ListBean vo);

    //查看物流
    void checkLogistics(GetOrderResponse.DataBean.ListBean vo);

    //申请退款
    void applyRefundMoney(GetOrderResponse.DataBean.ListBean vo);

    //评价晒单
    void evaluateOrder(GetOrderResponse.DataBean.ListBean vo);

    //退货详情
    void refundGoodsDetail(GetOrderResponse.DataBean.ListBean vo);

    //填写物流信息
    void subLogisticsInfo(GetOrderResponse.DataBean.ListBean vo);

    //申请退货
    void applyRefundGoods(GetOrderResponse.DataBean.ListBean vo);

    //退款详情
    void refundMoneysDetail(GetOrderResponse.DataBean.ListBean vo);
}
