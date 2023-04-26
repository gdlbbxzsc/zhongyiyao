package com.pbph.shoppingmall.module.main.index;

import android.support.v7.widget.RecyclerView;

import com.pbph.shoppingmall.utils.adapter.recyclerview.DataAdapter;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class IndexDataAdapter extends DataAdapter {


    public OnBannerListener listener;

    public int banner_height;

    public int good_img_wh = 0;

    public int good_img_w = 0;
    public int good_img_h = 0;

    public int more_good_img_wh = 0;
    public int active_img_wh;

    public OnTimeChangeListener onTimeChangeListener;
    public OnBannerChangeListener onBannerChangeListener;

    public IndexDataAdapter(Object activity, RecyclerView view, int view_type_count) {
        super(activity, view, view_type_count);
    }

    public interface OnTimeChangeListener {
        void onTimeChange(long passTime);
    }

    public interface OnBannerChangeListener {
        void onUpdate(List list);

        void onRelease();

    }
}
