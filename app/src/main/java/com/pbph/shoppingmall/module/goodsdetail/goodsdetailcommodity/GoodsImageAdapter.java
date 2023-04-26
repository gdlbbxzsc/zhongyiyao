package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity;

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
 * Created by 连嘉凡 on 2018/4/18.
 */

public class GoodsImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringImage = new ArrayList<>();

    public GoodsImageAdapter(Context context) {
        this.context = context;
    }

    public void setStringImage(List<String> temps) {
        stringImage = temps;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (stringImage == null) return 0;
        if (stringImage.size() >= 9) return 9;
        return stringImage.size();
    }

    @Override
    public Object getItem(int position) {
        return stringImage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsImageViewHolder goodsImageViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goodsimage, parent,
                    false);
            goodsImageViewHolder = new GoodsImageViewHolder(convertView);
            convertView.setTag(goodsImageViewHolder);
        } else {
            goodsImageViewHolder = (GoodsImageViewHolder) convertView.getTag();

        }
        String imgUrl = stringImage.get(position);

        Glide.with(context).load(imgUrl)
                .error(R.drawable.pingjiazhong_220x220)
                .into(goodsImageViewHolder.imageView);
        return convertView;
    }

    class GoodsImageViewHolder {
        ImageView imageView;

        public GoodsImageViewHolder(View view) {
            imageView = view.findViewById(R.id.iv_goods_image);

        }
    }
}
