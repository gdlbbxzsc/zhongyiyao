package com.pbph.shoppingmall.module.collect.shops.adapter;

import android.view.View;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.collect.shops.viewholder.CollectShopsListViewHolder;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class CollectShopsListDataAdapter extends DataAdapter {

    private boolean edit = false;

    public int marginRight = 0;


    public CollectShopsListDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);

        marginRight = 0 - context.getResources().getDimensionPixelSize(R.dimen.dp_50);
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        notifyDataSetChanged();
    }

}
