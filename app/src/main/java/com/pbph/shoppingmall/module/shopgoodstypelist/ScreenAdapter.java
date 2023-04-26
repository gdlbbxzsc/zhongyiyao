package com.pbph.shoppingmall.module.shopgoodstypelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.myview.MyGridView;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/26.
 */

public class ScreenAdapter extends BaseAdapter {
    private Context context;
    private List<List<String>> lists;

    public ScreenAdapter(Context context) {
        this.context = context;
    }

    public void setLists(List<List<String>> lists) {
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScreenViewHolder screenViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_screen, null);
            screenViewHolder = new ScreenViewHolder(convertView);
            convertView.setTag(screenViewHolder);
        } else {
            screenViewHolder = (ScreenViewHolder) convertView.getTag();
        }
        ScreenGridAdapter screenGridAdapter = new ScreenGridAdapter(context);

        screenViewHolder.check_all.setOnCheckedChangeListener((buttonView, isChecked) -> {
            screenGridAdapter.setB(!isChecked);
        });
        screenGridAdapter.setStringList(lists.get(position));
        screenViewHolder.gv_screen.setAdapter(screenGridAdapter);
        return convertView;
    }

    class ScreenViewHolder {
        CheckBox check_all;
        MyGridView gv_screen;

        public ScreenViewHolder(View view) {
            check_all = view.findViewById(R.id.check_all);
            gv_screen = view.findViewById(R.id.gv_screen);
        }
    }
}
