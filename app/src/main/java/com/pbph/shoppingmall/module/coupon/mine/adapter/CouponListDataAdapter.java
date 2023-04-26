package com.pbph.shoppingmall.module.coupon.mine.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class CouponListDataAdapter extends DataAdapter {

    private boolean edit = false;

    public int marginRight = 0;

    public CouponListDataAdapter(Object activity, View view, Class viewholder) {
        super(activity, view, viewholder);

        marginRight = 0 - context.getResources().getDimensionPixelSize(R.dimen.dp_6);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.xuanzhong);
        marginRight -= bitmap.getWidth();
        bitmap.recycle();
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        notifyDataSetChanged();
    }

}
