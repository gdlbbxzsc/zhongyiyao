package com.pbph.shoppingmall.module.seckill.index;

import android.view.View;

import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public class TimeChangeDataAdapter extends DataAdapter {

    public TimeChangeDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);
    }


    public List<OnTimeChangeListener> interfaceList = new ArrayList<>();

    @Override
    public void onGetView(ViewHolder holder) {
        super.onGetView(holder);
        interfaceList.add((OnTimeChangeListener) holder);
    }

    public interface OnTimeChangeListener {
        void onTimeChange(long passTime);
    }
}
