package com.pbph.shoppingmall.module.orders.evaluateorders.evaluatedetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/11.
 */

public class GoodsEvaluateDetailAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList = new ArrayList<>();

    public GoodsEvaluateDetailAdapter(Context context) {
        this.context = context;
    }

    public void setStringList(List<String> temps) {
        this.stringList = temps;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stringList != null ? stringList.size() : 0;

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
            convertView = LayoutInflater.from(context).inflate(R.layout
                    .item_goods_evaluate_details_iv, null);
            goodsTypeViewHolder = new GoodsTypeViewHolder(convertView);
            convertView.setTag(goodsTypeViewHolder);
        } else {
            goodsTypeViewHolder = (GoodsTypeViewHolder) convertView.getTag();
        }
        String imgUrl = stringList.get(position);
        Glide.with(context).load(imgUrl)
                .error(R.drawable.pingjiazhong_220x220)
                .into(goodsTypeViewHolder.iv_goods);
        return convertView;
    }

    class GoodsTypeViewHolder {

        ImageView iv_goods;

        public GoodsTypeViewHolder(View view) {
            iv_goods = view.findViewById(R.id.iv_goods);
        }
    }
}
