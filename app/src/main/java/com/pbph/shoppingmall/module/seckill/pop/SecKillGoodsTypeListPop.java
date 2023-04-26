package com.pbph.shoppingmall.module.seckill.pop;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;


public class SecKillGoodsTypeListPop extends PopupWindow {

    private Activity activity;
    private Resources resources;
    private LayoutInflater inflater;

    TextView tv_pop_nums;

    public GridView gridView;
    public DataAdapter adapter;

    public View showView;

    public SecKillGoodsTypeListPop(Activity activity) {
        this(activity, null);
    }

    public SecKillGoodsTypeListPop(Activity activity, View showView) {
        super(activity);
        this.activity = activity;
        resources = activity.getResources();
        inflater = LayoutInflater.from(activity);

        setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体的宽
        // MainActivity.screenWidth / 4
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        View view = inflater.inflate(R.layout.pop_salespromotion, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);

        tv_pop_nums = view.findViewById(R.id.tv_pop_nums);

        gridView = view.findViewById(R.id.gridView);

        adapter = new DataAdapter(activity, gridView, SecKillGoodsTypeListPopViewHolder.class);
//        gridView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_SINGLE);
        gridView.setAdapter(adapter);

        // 刷新状态
        this.update();

        this.showView = showView;
    }

    public void show() {
        show(showView);
    }

    public void show(View view) {
        if (adapter.getCount() <= 0) {
            return;
        }
        tv_pop_nums.setText(String.valueOf(adapter.getCount()));

        this.showAsDropDown(view);
    }

}
