package com.pbph.shoppingmall.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 动态获取GridView高度
 * Created by 连嘉凡 on 2018/3/5.
 */

public class GetHeightGridView extends GridView {
    public GetHeightGridView(Context context) {
        super(context);
    }

    public GetHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GetHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
