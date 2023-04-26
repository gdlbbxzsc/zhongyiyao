package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/5/3.
 */

public class GoodsDetailRecommendAdapter extends RecyclerView.Adapter<GoodsDetailRecommendAdapter
        .RecommendViewHolder> {

    private Context context;
    private List<String> stringList;

    public GoodsDetailRecommendAdapter(Context context) {
        this.context = context;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_recommend,null);
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
        return recommendViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        public RecommendViewHolder(View itemView) {
            super(itemView);
        }
    }

}
