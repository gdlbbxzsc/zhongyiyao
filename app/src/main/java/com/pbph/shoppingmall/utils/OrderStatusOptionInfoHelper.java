package com.pbph.shoppingmall.utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.vo.OrderOptionInfo;
import com.pbph.shoppingmall.model.vo.OrderStatusInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/31.
 */

public class OrderStatusOptionInfoHelper {

    Context context;

    public Map<Integer, OrderStatusInfo> statusMap;
    public Map<Integer, OrderOptionInfo> optionMap;

    public OrderStatusOptionInfoHelper(Context context) {

        this.context = context;

        initOrderStatus();
        initOrderOptions();
    }


    void initOrderStatus() {
        int[] ids = context.getResources().getIntArray(R.array.order_status_ids);
        String[] titles = context.getResources().getStringArray(R.array.order_status_texts);

        int[] type_ids = context.getResources().getIntArray(R.array.order_status_detail_type_ids);
        String[] type_titles = context.getResources().getStringArray(R.array.order_status_detail_type_texts);


        TypedArray ar = context.getResources().obtainTypedArray(R.array.order_status_option_types);
        int len = ar.length();
        int[] option_types = new int[len];
        for (int i = 0; i < len; i++)
            option_types[i] = ar.getResourceId(i, 0);
        ar.recycle();

        statusMap = new HashMap<>(ids.length);
        for (int i = 0; i < ids.length; i++) {

            OrderStatusInfo vo = new OrderStatusInfo();

            vo.id = ids[i];
            vo.text = titles[i];

            vo.option_ids = context.getResources().getIntArray(option_types[i]);

            vo.type_id = type_ids[i];
            vo.type_title = type_titles[i];

            statusMap.put(vo.id, vo);
        }
    }


    void initOrderOptions() {
        int[] ids = context.getResources().getIntArray(R.array.order_status_option_ids);
        String[] titles = context.getResources().getStringArray(R.array.order_status_option_texts);
        String[] methods = context.getResources().getStringArray(R.array.order_status_option_methods);


        TypedArray ar = context.getResources().obtainTypedArray(R.array.order_status_option_text_colors);
        int len = ar.length();
        int[] text_colors = new int[len];
        for (int i = 0; i < len; i++)
            text_colors[i] = ar.getResourceId(i, 0);
        ar.recycle();

        ar = context.getResources().obtainTypedArray(R.array.order_status_option_text_bgs);
        len = ar.length();
        int[] text_bgs = new int[len];
        for (int i = 0; i < len; i++)
            text_bgs[i] = ar.getResourceId(i, 0);
        ar.recycle();


        optionMap = new HashMap<>(ids.length);
        for (int i = 0; i < ids.length; i++) {
            OrderOptionInfo vo = new OrderOptionInfo();
            vo.id = ids[i];
            vo.text = titles[i];

            vo.text_color = context.getResources().getColor(text_colors[i]);
            vo.text_bg = text_bgs[i];

            vo.method_name = methods[i];

            optionMap.put(vo.id, vo);
        }
    }


    public OrderStatusInfo getOrderStatusInfo(int order_status) {
        return statusMap.get(order_status);
    }

    public OrderOptionInfo getOrderOptionInfo(int option_id) {
        return optionMap.get(option_id);
    }

}
