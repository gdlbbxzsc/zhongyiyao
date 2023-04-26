package com.pbph.shoppingmall.module.firmorder.goodslist;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.utils.StringUtils;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/10/18 16:42
 * @ email：gdutxiaoxu@163.com
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListHolder> implements View.OnClickListener {

    private List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean> goodsInfoList;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public GoodsListAdapter(Context context, List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean> goodsInfoList) {
        this.context = context;
        this.goodsInfoList = goodsInfoList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int type);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            int position = Integer.parseInt(v.getTag().toString().split(",")[0]);
            int type = Integer.parseInt(v.getTag().toString().split(",")[1]);
            mOnItemClickListener.onItemClick(v, position, type);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public GoodsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_list, parent, false);
        return new GoodsListHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(GoodsListHolder holder, final int position) {

        ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo = goodsInfoList.get(position);
        ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean.GoodsInfoBean goodsInfo = vo.getGoodsInfo();

        holder.tvGoodsName.setText(goodsInfo.getGoodsInfoName());

        holder.tvGoodsSpec.setText("");
        for (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean.GoodsInfoBean.GoodsInfoSpecListBean par : goodsInfo.getGoodsInfoSpecList()) {
            holder.tvGoodsSpec.append(par.getSpecName());
            holder.tvGoodsSpec.append(":");
            holder.tvGoodsSpec.append(par.getSpecDetailName());
            holder.tvGoodsSpec.append(" ");
        }

        holder.tvGoodsPrice.setText(StringUtils.moneyFen2Yuan(vo.getGoodsJoinPrice()));

        holder.tvGoodsNum.setText("×" + String.valueOf(vo.getGoodsNum()));


        Glide.with(context).load(goodsInfo.getGoodsInfoImgUrl())
//                .placeholder(R.mipmap.banner_zw)
//                .error(R.mipmap.banner_zw)
                .into(holder.goodsImg);
    }

    @Override
    public int getItemCount() {
        return goodsInfoList.size();
    }

    static class GoodsListHolder extends RecyclerView.ViewHolder {
        View card;
        ImageView goodsImg;
        TextView tvGoodsName, tvGoodsSpec, tvGoodsNum, tvGoodsPrice;

        public GoodsListHolder(View view) {
            super(view);
            card = view;
            goodsImg = view.findViewById(R.id.iv_goods_img);
            tvGoodsName = view.findViewById(R.id.tv_goods_name);
            tvGoodsNum = view.findViewById(R.id.tv_goods_num);
            tvGoodsPrice = view.findViewById(R.id.tv_goods_price);
            tvGoodsSpec = view.findViewById(R.id.tv_goods_spec);
        }
    }


}
