package com.pbph.shoppingmall.module.orders.evaluateorders.submit;

import android.view.View;

import com.pbph.shoppingmall.utils.adapter.DataAdapter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class EvaluateOrderSubmitDataAdapter extends DataAdapter {

    public int wh = 0;

    public boolean canEdit = true;


    public EvaluateOrderSubmitDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);
    }


    @Override
    public int getCount() {
        if (canEdit) {
            int c = super.getCount();
            if (c >= 3) return 3;
            return c + 1;
        } else {
            return super.getCount();
        }
    }

    @Override
    public Object getItem(int position) {
        if (position < getCount() && position < viewDataList.size())
            return super.getItem(position);

        return "";
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
        notifyDataSetChanged();
    }


}
