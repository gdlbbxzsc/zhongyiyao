package com.pbph.shoppingmall.module.collect.pop;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;


public class CollectTypeListPop extends PopupWindow {

    private BaseFragmentV4 fragmentV4;
    private Resources resources;
    private LayoutInflater inflater;


    public GridView gridView;
    public DataAdapter adapter;

    public View showView;


    public CollectTypeListPop(BaseFragmentV4 fragmentV4, View showView, Class clz) {
        super(fragmentV4.getContext());
        this.fragmentV4 = fragmentV4;
        resources = fragmentV4.getContext().getResources();
        inflater = LayoutInflater.from(fragmentV4.getContext());

        setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体的宽
        // MainActivity.screenWidth / 4
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        View view = inflater.inflate(R.layout.pop_collecttype, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);


        gridView = view.findViewById(R.id.gridView);

        adapter = new DataAdapter(fragmentV4, gridView, clz);
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
        this.showAsDropDown(view);
    }
}
