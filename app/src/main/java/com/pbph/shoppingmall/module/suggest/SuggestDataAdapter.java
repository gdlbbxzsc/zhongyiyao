package com.pbph.shoppingmall.module.suggest;

import android.view.View;

import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;

/**
 * Created by Administrator on 2018/4/12.
 */

public class SuggestDataAdapter extends DataAdapter {

    public int wh = 0;


    public SuggestDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);
    }

    public SuggestDataAdapter(Object activity, View view, int count) {
        super(activity, view, count);
    }


    @Override
    public int getCount() {
        int c = super.getCount();
        if (c >= 3) return 3;
        return c + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position < getCount() && position < viewDataList.size())
            return super.getItem(position);

        return "";
    }

}
