package com.pbph.shoppingmall.utils.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by longhope on 2017/5/3.
 */

public interface OnItemClickListener {

    void onItemClick(RecyclerView parent, ViewHolder viewHolder, int position);
}
