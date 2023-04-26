package com.pbph.shoppingmall.module.typegoodslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/26.
 */

public class ScreenGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;
    boolean b = true;
    public ScreenGridAdapter(Context context) {
        this.context = context;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    public void setB(boolean b) {
        this.b = b;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (b) {
            return 3;
        } else {
            return stringList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScreenGridViewHolder screenGridViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_screen_grid, null);
            screenGridViewHolder = new ScreenGridViewHolder(convertView);
            convertView.setTag(screenGridViewHolder);
        } else {
            screenGridViewHolder = (ScreenGridViewHolder) convertView.getTag();
        }
        screenGridViewHolder.check_screen.setText(stringList.get(position));
        return convertView;
    }

    class ScreenGridViewHolder {
        CheckBox check_screen;

        public ScreenGridViewHolder(View view) {
            check_screen = view.findViewById(R.id.check_screen);
        }
    }
}
