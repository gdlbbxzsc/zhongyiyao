package com.pbph.shoppingmall.module.main.index;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.viewholder.MenuViewHolder;

/**
 * Created by Administrator on 2018/4/25.
 */

public class IndexItemDecoration extends RecyclerView.ItemDecoration {

    IndexDataAdapter adapter;

    private Paint paintGray;
    private Paint paintWhite;

    int padding = 0;
    int paddingHalf = 0;

    int moreGoodCount = 0;

    public IndexItemDecoration(Context context, IndexDataAdapter adapter) {
        paintGray = new Paint();
        paintGray.setColor(context.getResources().getColor(R.color.line_d7));

        paintWhite = new Paint();
        paintWhite.setColor(context.getResources().getColor(R.color.white));

        padding = context.getResources().getDimensionPixelSize(R.dimen.dp_16);
        paddingHalf = padding / 2;

        this.adapter = adapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

//通用设置
        outRect.set(0, 0, 0, 0);

        int pos = parent.getChildAdapterPosition(view);

        if (pos == 0) {
            moreGoodCount = 0;
        }

        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanSize = layoutParams.getSpanSize();//当前item 占用了 girdeview的 几列
        int spanIndex = layoutParams.getSpanIndex();//当前位置

        Class clz = adapter.getItemViewTypeClass(pos);

        switch (spanSize) {
//            case 4:  //menu
//                break;
            case 10:
//                if (clz == Active1ViewHolder.class) {
//                    outRect.set(0, 2, 1, 1);
//                    return;
//                }
//                if (clz == Active2ViewHolder.class) {
//                    outRect.set(1, 2, 0, 1);
//                    return;
//                }
//                if (clz == Active3ViewHolder.class) {
//                    outRect.set(0, 1, 1, 2);
//                    return;
//                }
//                if (clz == Active4ViewHolder.class) {
//                    outRect.set(1, 1, 0, 2);
//                    return;
//                }

//                if (clz == GoodMoreViewHolder.class) {
//                    moreGoodCount++;
//                    switch (spanIndex) {
//                        case 0://left
//                            if (moreGoodCount == 1)
//                                outRect.set(padding, padding, paddingHalf, paddingHalf);
//                            else
//                                outRect.set(padding, paddingHalf, paddingHalf, paddingHalf);
//                            break;
//                        case 10://right
//                            if (moreGoodCount == 2)
//                                outRect.set(paddingHalf, padding, padding, paddingHalf);
//                            else
//                                outRect.set(paddingHalf, paddingHalf, padding, paddingHalf);
//                            break;
//                    }
//                    return;
//                }
                break;
            case 5: //good

                if (clz == MenuViewHolder.class) return;

                switch (spanIndex) {
                    case 0://left
                        outRect.set(0, 2, 2, 2);
                        break;
                    case 15://right
                        outRect.set(2, 2, 0, 2);
                        break;
                    default: // 5,10
                        outRect.set(2, 2, 2, 2);
                        break;
                }
                break;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        int childCount = parent.getChildCount();
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();

//        for (int i = 0; i < childCount - 1; i++) {
//            View view = parent.getChildAt(i);
//            int pos = parent.getChildAdapterPosition(view);
//            Class clz = adapter.getItemViewTypeClass(pos);
//
//            if (clz == Active1ViewHolder.class) {
//                return;
//            }
//            if (clz == Active2ViewHolder.class) {
//                return;
//            }
//            if (clz == Active3ViewHolder.class) {
//                return;
//            }
//            if (clz == Active4ViewHolder.class) {
//                return;
//            }
//
//            if (clz == GoodViewHolder.class) {
//                return;
//            }
//            if (clz == GoodMoreViewHolder.class) {
//                return;
//            }
////            float top = view.getBottom();
////            float bottom = view.getBottom() + dividerHeight;
////            c.drawRect(left, top, right, bottom, dividerPaint);
//        }
    }

}
