package com.pbph.shoppingmall.module.orders.adapter;

import android.view.View;

import com.pbph.shoppingmall.utils.OrderStatusOptionInfoHelper;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class MyOrdersDataAdapter extends DataAdapter {

    public OrderStatusOptionInfoHelper orderHelper;

    public MyOrdersDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);
        orderHelper = new OrderStatusOptionInfoHelper(context);
    }

    public MyOrdersDataAdapter(Object activity, View view, int count) {
        super(activity, view, count);
        orderHelper = new OrderStatusOptionInfoHelper(context);
    }
}
