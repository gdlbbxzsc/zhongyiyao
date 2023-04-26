package com.pbph.shoppingmall.myview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by 连嘉凡 on 2018/4/9.
 */

public class ScrollLinearLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public ScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ScrollLinearLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public ScrollLinearLayoutManager(Context context, int spanCount, int orientation, boolean
            reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
