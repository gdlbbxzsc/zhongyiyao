package com.pbph.shoppingmall.module.shopdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreDetailResponse;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/6/4.
 */

public class ShopDetailAdapter extends BaseAdapter {
    private Context context;
    private List<GetStoreDetailResponse.DataBean.CategoryListBean> categoryListBeans;

    public ShopDetailAdapter(Context context) {
        this.context = context;
    }

    public void setCategoryListBeans(List<GetStoreDetailResponse.DataBean.CategoryListBean>
                                             categoryListBeans) {
        this.categoryListBeans = categoryListBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categoryListBeans != null ? categoryListBeans.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return categoryListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopDetailViewHolder shopDetailViewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_management_range,
                    null);
            shopDetailViewHolder = new ShopDetailViewHolder(convertView);
            convertView.setTag(shopDetailViewHolder);
        }else {
           shopDetailViewHolder = (ShopDetailViewHolder) convertView.getTag();
        }
        shopDetailViewHolder.tv_management_range.setText("经营范围:"+categoryListBeans.get(position)
                .getCatName());
        return convertView;
    }
    class ShopDetailViewHolder{
        TextView tv_management_range ;
        public ShopDetailViewHolder(View view) {
            tv_management_range = view.findViewById(R.id.tv_management_range);

        }
    }
}
