package com.pbph.shoppingmall.module.shop.newtheshelves;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreNewGoodsForPageResponse;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/6.
 */

public class NewTheShelvesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<GetStoreNewGoodsForPageResponse.DataBean.ListBean> list = new ArrayList<>();
    private SetData setData;

    public NewTheShelvesAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<GetStoreNewGoodsForPageResponse.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setSetData(SetData setData) {
        this.setData = setData;
    }

    public void addList(List<GetStoreNewGoodsForPageResponse.DataBean.ListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View baseView;
        baseView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_gridview_goods_list, null);
        GridViewHolder gridViewHolder = new GridViewHolder(baseView);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridViewHolder gridViewHolder = (GridViewHolder) holder;
        GetStoreNewGoodsForPageResponse.DataBean.ListBean listBean = list.get(position);
        gridViewHolder.tvName.setText(listBean.getGoodsInfoName());
        Glide.with(context).load(listBean.getGoodsInfoImg()).into(gridViewHolder.iv);
        gridViewHolder.tvPrice.setText(StringUtils.moneyFen2Yuan(listBean.getGoodsInfoPrice()));
        gridViewHolder.tvPriceYZ.setText(String.valueOf(listBean.getCommcont()));
        gridViewHolder.tvPriceYZ.append("条评价,");
        gridViewHolder.tvPriceYZ.append(String.valueOf(listBean.getPraise()));
        gridViewHolder.tvPriceYZ.append("%好评");
            gridViewHolder.iv.setOnClickListener(v -> {
                setData.setGoodsInfoId(listBean.getPpid());
            });


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    public interface SetData {
        public void setGoodsInfoId(int i);
    }


    public static class GridViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvName, tvPriceYZ, tvPrice;
        private View rightView;

        public GridViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPriceYZ = itemView.findViewById(R.id.tv_content);
            tvPrice = itemView.findViewById(R.id.tv_price);

        }
    }
}
