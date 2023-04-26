package com.pbph.shoppingmall.module.goodstype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreCategoryByStoreIdResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/11.
 */

public class GoodsTypeAdapter extends BaseAdapter {
    private Context context;
    private List<GetStoreCategoryByStoreIdResponse.DataBean> stringList = new ArrayList<>();

    public GoodsTypeAdapter(Context context) {
        this.context = context;
    }

    public void setStringList(List<GetStoreCategoryByStoreIdResponse.DataBean> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stringList != null ? stringList.size() : 0;
//        return stringList.size();
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
        GoodsTypeViewHolder goodsTypeViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_type, null);
            goodsTypeViewHolder = new GoodsTypeViewHolder(convertView);
            convertView.setTag(goodsTypeViewHolder);
        } else {
            goodsTypeViewHolder = (GoodsTypeViewHolder) convertView.getTag();
        }
        goodsTypeViewHolder.tv_goods_type.setText(stringList.get(position).getStoreCategoryName());
        return convertView;
    }

    class GoodsTypeViewHolder {
        private TextView tv_goods_type;

        public GoodsTypeViewHolder(View view) {
            tv_goods_type = view.findViewById(R.id.tv_goods_type);
        }
    }
}
