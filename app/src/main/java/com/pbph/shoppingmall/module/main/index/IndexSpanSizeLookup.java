package com.pbph.shoppingmall.module.main.index;

import android.support.v7.widget.GridLayoutManager;

import com.pbph.shoppingmall.module.main.index.viewholder.Active1ViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.Active2ViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.Active3ViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.Active4ViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.BannerViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.GoodImageViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.GoodMoreViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.GoodViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.ImageAdViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.ImageMoreAdViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.ImageSpeViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.MenuViewHolder;
import com.pbph.shoppingmall.utils.adapter.recyclerview.DataAdapter;

/**
 * Created by Administrator on 2018/5/14.
 */

public class IndexSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    DataAdapter adapter;

    public IndexSpanSizeLookup(DataAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getSpanSize(int position) {

        Class clz = adapter.getItemViewTypeClass(position);
        if (clz == BannerViewHolder.class) {
            return 20;
        }
        if (clz == MenuViewHolder.class) {
            return 5;
        }
        if (clz == Active1ViewHolder.class) {
            return 10;
        }
        if (clz == Active2ViewHolder.class) {
            return 10;
        }
        if (clz == Active3ViewHolder.class) {
            return 10;
        }
        if (clz == Active4ViewHolder.class) {
            return 10;
        }
        if (clz == ImageSpeViewHolder.class) {
            return 20;
        }
        if (clz == ImageAdViewHolder.class) {
            return 20;
        }
        if (clz == GoodViewHolder.class) {
            return 5;
        }
        if (clz == GoodImageViewHolder.class) {
            return 5;
        }
        if (clz == ImageMoreAdViewHolder.class) {
            return 20;
        }
        if (clz == GoodMoreViewHolder.class) {
            return 10;
        }
        return 20;
    }
}
