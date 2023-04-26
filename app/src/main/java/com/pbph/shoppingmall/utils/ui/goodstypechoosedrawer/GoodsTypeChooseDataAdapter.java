package com.pbph.shoppingmall.utils.ui.goodstypechoosedrawer;

import android.view.View;

import com.pbph.shoppingmall.utils.adapter.DataAdapter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class GoodsTypeChooseDataAdapter extends DataAdapter {

    public boolean spread = false;

    public GoodsTypeChooseDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);
    }

    public GoodsTypeChooseDataAdapter(Object activity, View view, int count) {
        super(activity, view, count);
    }


    @Override
    public int getCount() {
        int count = super.getCount();

        if (!spread) {
            if (count < 3) return count;
            return 3;
        }

        return count;
    }


}
