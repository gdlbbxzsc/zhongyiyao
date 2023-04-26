package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;
import com.pbph.shoppingmall.myview.MyGridView;
import com.utils.DateUtils;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/4/12.
 */

public class GoodsDetailEvaluateAdapter extends RecyclerView.Adapter<GoodsDetailEvaluateAdapter
        .GoodsDetailViewHolder> {

    private Context context;
    List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsDetailCommentListBean>
            npCommentBean;
    private GoToEvaluateDetail goToEvaluateDetail;


    public GoodsDetailEvaluateAdapter(Context context) {
        this.context = context;
    }

    public void setGoToEvaluateDetail(GoToEvaluateDetail goToEvaluateDetail) {
        this.goToEvaluateDetail = goToEvaluateDetail;
    }

    public void setNpCommentBean(List<ProductDetailResponse.DataBean.GoodsDetailBean
            .GoodsInfoBean.GoodsDetailCommentListBean>
                                         goodsDetailCommentListBeans) {
        this.npCommentBean = goodsDetailCommentListBeans;
        notifyDataSetChanged();
    }

    @Override
    public GoodsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_detail_evaluste,
                parent, false);
        GoodsDetailViewHolder viewHolder = new GoodsDetailViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GoodsDetailViewHolder holder, int position) {

        ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsDetailCommentListBean
                goodsDetailCommentListBean = npCommentBean.get(position);
        holder.item_linear.setOnClickListener(v -> goToEvaluateDetail.setPos
                (goodsDetailCommentListBean));
        holder.tv_goods_content.setText(goodsDetailCommentListBean.getCommentContent());
        holder.tv_iv_goods_evaluste_shop_name.setText(goodsDetailCommentListBean.getStoreName());
//        holder.tv_goods_evaluste_name.setText(npCommentBean.get(position).getCustomerNickname());
        holder.tv_goods_evaluste_date.setText(new DateUtils(goodsDetailCommentListBean
                .getPublishTime()).getString(DateUtils.PATTERN_5));
        holder.goodsImageAdapter.setStringImage(goodsDetailCommentListBean.getShareImgList());
        holder.grid_view_evaluste.setAdapter(holder.goodsImageAdapter);
        Glide.with(context).load(goodsDetailCommentListBean.getCustomerImg()).error(R.drawable
                .pingjiazhong_220x220)
                .into(holder.iv_goods_evaluste_icon);
        holder.tv_goods_evaluste_name.setText(goodsDetailCommentListBean.getCustomerName());
        holder.goodsImageAdapter.setStringImage(goodsDetailCommentListBean.getShareImgList());
        holder.rating_goods_evaluste.setRating(Float.valueOf(String.valueOf(goodsDetailCommentListBean.getCommentScore()/20)));
    }

    @Override
    public int getItemCount() {
        if (npCommentBean == null) return 0;
        if (npCommentBean.size() >= 3) return 3;
        return npCommentBean != null && npCommentBean.size() > 0 ? npCommentBean.size() : 0;
    }

    public interface GoToEvaluateDetail {
        void setPos(ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                            .GoodsDetailCommentListBean goodsDetailCommentListBean);
    }

    class GoodsDetailViewHolder extends RecyclerView.ViewHolder {
        MyGridView grid_view_evaluste;
        GoodsImageAdapter goodsImageAdapter;
        private ImageView iv_goods_evaluste_icon;//头像
        private TextView tv_goods_evaluste_name;//评论人姓名
        private RatingBar rating_goods_evaluste;//评价星星数量
        private TextView tv_goods_evaluste_date;//评价日期
        private TextView tv_goods_content;//评价内容
        private TextView tv_iv_goods_evaluste_shop_name;//官方旗舰店名称
        private TextView tv_goods_evaluste_zhan;//点赞数量
        private LinearLayout item_linear;

        public GoodsDetailViewHolder(View itemView) {
            super(itemView);
            goodsImageAdapter = new GoodsImageAdapter(context);
            grid_view_evaluste = itemView.findViewById(R.id.grid_view_evaluste);
            iv_goods_evaluste_icon = itemView.findViewById(R.id.iv_goods_evaluste_icon);
            tv_goods_evaluste_name = itemView.findViewById(R.id.tv_goods_evaluste_name);
            rating_goods_evaluste = itemView.findViewById(R.id.rating_goods_evaluste);
            tv_goods_evaluste_date = itemView.findViewById(R.id.tv_goods_evaluste_date);
            tv_goods_content = itemView.findViewById(R.id.tv_goods_content);
            tv_iv_goods_evaluste_shop_name = itemView.findViewById(R.id
                    .tv_iv_goods_evaluste_shop_name);
            tv_goods_evaluste_zhan = itemView.findViewById(R.id.tv_goods_evaluste_zhan);
            item_linear = itemView.findViewById(R.id.item_linear);

        }
    }

}
