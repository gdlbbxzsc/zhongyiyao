package com.pbph.shoppingmall.myview.scpopwindow;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.PayMethodResponse;

import java.util.List;

public class MySCPopWindow extends PopupWindow {
    private ListView listView;
    private TextView tvTitle;
    private ImageView ivCancel;
    private SCPopDataAdapter adapter;
    private List<PayMethodResponse.DataBean> list;

    @SuppressLint("InflateParams")
    public MySCPopWindow(Activity context, String title, List<PayMethodResponse.DataBean> list, View.OnClickListener onClickListener, AdapterView.OnItemClickListener onItemClickListener) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.popupwindow_choose, null);
        // 设置SelectPicPopupWindow的View
        View bg = content.findViewById(R.id.bg);
        listView = content.findViewById(R.id.listView);
        ivCancel = content.findViewById(R.id.iv_cancel);
        bg.setOnClickListener(onClickListener);
        content.findViewById(R.id.ly).setOnClickListener(onClickListener);
        ivCancel.setOnClickListener(onClickListener);
        tvTitle = content.findViewById(R.id.title);
        tvTitle.setText(title);
//        content.findViewById(R.id.baidu_map).setOnClickListener(onClickListener);
//        content.findViewById(R.id.gaode_map).setOnClickListener(onClickListener);
        this.setContentView(content);
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(dm.widthPixels);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(dm.heightPixels + getStatusBarHeight(context));
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.AnimationPreview);
        this.setClippingEnabled(false);
        adapter = new SCPopDataAdapter(context, listView, SCPopViewHolder.class);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        this.list = list;
        adapter.addDatas(this.list);
        adapter.notifyDataSetChanged();
    }

    public void notifyData(List<PayMethodResponse.DataBean> list) {
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            //this.showAsDropDown(parent, 0, 0);
            this.showAtLocation(parent, Gravity.TOP | Gravity.START, 0, 0);
        } else {
            this.dismiss();
        }
    }

    /**
     * 获取状态通知栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
}
