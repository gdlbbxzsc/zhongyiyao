package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.promotion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/24.
 */

public class PromotionAdapter extends BaseAdapter {
    private List<PromotionBean> promotionBeans;
    private Context context;

    public PromotionAdapter(Context context) {
        this.context = context;
    }

    public void setPromotionBeans(List<PromotionBean> promotionBeans) {
        this.promotionBeans = promotionBeans;
    }

    @Override
    public int getCount() {
        return promotionBeans != null ? promotionBeans.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return promotionBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PromotionViewHolder promotionViewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_promotion,null);
            promotionViewHolder = new PromotionViewHolder(convertView);
            convertView.setTag(promotionViewHolder);
        }else {


        }
        return convertView;
    }

    class PromotionViewHolder {


        public PromotionViewHolder(View view) {
        }
    }
}
