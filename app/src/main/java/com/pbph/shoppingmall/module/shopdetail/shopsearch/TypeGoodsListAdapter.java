package com.pbph.shoppingmall.module.shopdetail.shopsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/6.
 */

public class TypeGoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private int type = 0;//0:LinearViewHolder  1:GridViewHolder
    private TransmitData transmitData;
    private List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean>
            strings = new ArrayList<>();

    public void setStrings(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean
            .DataBean> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    public void addStrings(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean
            .DataBean> strings) {
        this.strings.addAll(strings);
        notifyDataSetChanged();

    }


    public TypeGoodsListAdapter(Context context) {
        this.context = context;
    }

    public void setTransmitData(TransmitData transmitData) {
        this.transmitData = transmitData;
    }

    //点击切换布局的时候通过这个方法设置type
    public void setType(int type) {
        this.type = type;
    }

    @Override
    //用来获取当前项Item是哪种类型的布局
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View baseView;
        if (viewType == 0) {
            baseView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_listview_goods_list, parent, false);
            LinearViewHolder linearViewHolder = new LinearViewHolder(baseView);
            return linearViewHolder;
        } else {
            baseView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_gridview_goods_list, null);
            GridViewHolder gridViewHolder = new GridViewHolder(baseView);
            return gridViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean dataBean = strings.get(position);
        if (type == 0) {
            LinearViewHolder linearViewHolder = (LinearViewHolder) holder;
            linearViewHolder.tvName.setText(dataBean.getGoodsInfoName());

            linearViewHolder.tvPriceYZ.setText(StringUtils.moneyFen2Yuan(dataBean.getGoodsInfoPreferPrice()));
            linearViewHolder.tvPrice.setText(dataBean.getProductCommentVo().getCount());
            linearViewHolder.tvPrice.append("条评价,");
            linearViewHolder.tvPrice.append(dataBean.getProductCommentVo().getColligate());
            linearViewHolder.tvPrice.append("%好评");
            linearViewHolder.relative_view.setOnClickListener(v -> {
                if (dataBean == null) return;
                if (dataBean.getGoodsInfoId() == -1) return;
                if (transmitData == null) return;
                transmitData.transmitGoodsId(dataBean.getGoodsInfoId());
            });
            if (dataBean.getImgList() == null) return;
            if (dataBean.getImgList().size() != 0) {
                Glide.with(context).load(dataBean.getImgList().get(0).getImageArtworkName()).into(linearViewHolder
                        .iv);
            }

        } else {
            GridViewHolder gridViewHolder = (GridViewHolder) holder;
            gridViewHolder.tvName.setText(dataBean.getGoodsInfoName());
            gridViewHolder.tv_price.setText(StringUtils.moneyFen2Yuan(dataBean.getGoodsInfoPreferPrice()));
            gridViewHolder.tv_content.setText(dataBean.getProductCommentVo().getCount() + "条评价，" + dataBean
                    .getProductCommentVo().getColligate() + "%好评");


            gridViewHolder.relative_view.setOnClickListener(v -> {
                if (dataBean == null) return;
                if (dataBean.getGoodsInfoId() == -1) return;
                transmitData.transmitGoodsId(dataBean.getGoodsInfoId());
            });
            if (dataBean.getImgList() == null) return;
            if (dataBean.getImgList().size() != 0) {
                Glide.with(context).load(dataBean.getImgList().get(0).getImageArtworkName()).into(gridViewHolder.iv);
            }
        }


    }

    @Override
    public int getItemCount() {
        return strings != null ? strings.size() : 0;
    }

    public static class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tvName, tvPriceYZ, tvPrice;
        private RelativeLayout relative_view;

        public LinearViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPriceYZ = itemView.findViewById(R.id.tv_price_yz);
            tvPrice = itemView.findViewById(R.id.tv_price);
            relative_view = itemView.findViewById(R.id.relative_view);
        }
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tvName, tv_price, tv_content;
        private View rightView;
        private RelativeLayout relative_view;

        public GridViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_content = itemView.findViewById(R.id.tv_content);
            relative_view = itemView.findViewById(R.id.relative_view);

        }
    }

    public interface TransmitData {
        void transmitGoodsId(int goodsId);
    }

}
