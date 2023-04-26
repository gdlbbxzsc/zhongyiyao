package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.poupup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/24.
 */

public class CouponAdapter extends BaseAdapter {
    private Context context;
    private List<CouponBean> couponBeanList;
    // item view的类型总数。
    private final int VIEW_TYPE_COUNT = 2;
    private final int title = -2;
    private final int content = -3;

    public CouponAdapter(Context context) {
        this.context = context;
    }

    public void setCouponBeanList(List<CouponBean> couponBeanList) {
        this.couponBeanList = couponBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return couponBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return couponBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (couponBeanList.get(position).getContent() == null) {
            return title;
        } else {
            return content;
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StringViewHolder stringViewHolder;
        VoViewHolder voViewHolder;

        int type = getItemViewType(position);
        switch (type) {
            case title: {
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout
                            .item_coupon_title, null);
                    stringViewHolder = new StringViewHolder(convertView);
                    convertView.setTag(stringViewHolder);
                } else {
                    stringViewHolder = (StringViewHolder) convertView.getTag();
                }


            }
            break;

            case content: {
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout
                            .item_coupon_content, null);
                    voViewHolder = new VoViewHolder(convertView);
                    convertView.setTag(voViewHolder);
                } else {
                    voViewHolder = (VoViewHolder) convertView.getTag();
                }
            }
            break;
        }

        return convertView;
    }

    class StringViewHolder {
        private TextView tvTitle;

        public StringViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
        }
    }

    class VoViewHolder {

        private TextView tv_coupon_money_rmb;//金钱
        private TextView tv_msg_content;//条件使用
        private TextView tv_type;//使用限制
        private TextView tv_data;//有效期
        private TextView tv_coupon_immediately;//立即领券

        public VoViewHolder(View view) {
            tv_coupon_money_rmb = view.findViewById(R.id.tv_coupon_money_rmb);
            tv_msg_content = view.findViewById(R.id.tv_msg_content);
            tv_type = view.findViewById(R.id.tv_type);
            tv_data = view.findViewById(R.id.tv_data);
            tv_coupon_immediately = view.findViewById(R.id.tv_coupon_immediately);
        }
    }
}
