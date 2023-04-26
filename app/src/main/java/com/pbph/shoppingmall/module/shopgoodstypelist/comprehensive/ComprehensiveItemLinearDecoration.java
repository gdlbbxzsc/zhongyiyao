package com.pbph.shoppingmall.module.shopgoodstypelist.comprehensive;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pbph.shoppingmall.R;

/**
 * Created by Administrator on 2018/4/25.
 */

public class ComprehensiveItemLinearDecoration extends RecyclerView.ItemDecoration {


    int padding = 0;
    int paddingHalf = 0;


    public ComprehensiveItemLinearDecoration(Context context) {

        padding = context.getResources().getDimensionPixelSize(R.dimen.dp_20);
        paddingHalf = padding / 2;


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);

//通用设置
//        outRect.set(0, 0, 0, 0);

        int pos = parent.getChildAdapterPosition(view);


        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view
                .getLayoutParams();
        int spanSize = layoutParams.getSpanSize();//当前item 占用了 girdeview的 几列


            outRect.set(0, 0, 0, 0);
//
////        if (spanSize == 1) {
////            outRect.set(0, 0, 0, 0);
////            return;
////        }
//
//
//        int spanIndex = layoutParams.getSpanIndex();//当前位置
//
//        switch (spanIndex) {
//            case 0://left
////                int left, int top, int right, int bottom
//                outRect.set(padding, paddingHalf, paddingHalf, paddingHalf);
//
//                break;
//            default://right
//
//                outRect.set(paddingHalf, paddingHalf, padding, paddingHalf);
//
//                break;
//        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
