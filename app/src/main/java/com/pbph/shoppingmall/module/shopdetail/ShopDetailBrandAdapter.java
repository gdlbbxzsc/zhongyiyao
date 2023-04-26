package com.pbph.shoppingmall.module.shopdetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreDetailResponse;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/6/4.
 */

public class ShopDetailBrandAdapter extends RecyclerView.Adapter<ShopDetailBrandAdapter
        .BrandViewHolder> {
    private Context context;
    private List<GetStoreDetailResponse.DataBean.BrandListBean> brandListBeans;

    public ShopDetailBrandAdapter(Context context) {
        this.context = context;
    }

    public void setBrandListBeans(List<GetStoreDetailResponse.DataBean.BrandListBean>
                                          brandListBeans) {
        this.brandListBeans = brandListBeans;
        notifyDataSetChanged();
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand_price, null);
        BrandViewHolder brandViewHolder = new BrandViewHolder(view);
        return brandViewHolder;
    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {
        Glide.with(context)
                .load(brandListBeans.get(position).getBrandLogo())
                .error(R.drawable.dianpu_230x200)
                .into(holder.iv_brand);
    }

    @Override
    public int getItemCount() {
        return brandListBeans != null ? brandListBeans.size() : 0;

    }

    class BrandViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_brand;

        public BrandViewHolder(View itemView) {
            super(itemView);
            iv_brand = itemView.findViewById(R.id.iv_brand);
        }
    }

}
