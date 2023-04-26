package com.pbph.shoppingmall.module.search.goods.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.List;


public class SearchGoodsListViewHolder extends ViewHolder<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> {


    private ImageView ivPic;
    private TextView tvName;
    private RelativeLayout llMoney;


    private TextView tvPriceNew;
    //    private TextView ivSelf;
    private TextView tvNum;
    private TextView tvGoShop;


    @Override
    protected int getLayout() {
        return R.layout.listview_searchgoodslist;
    }

    @Override
    protected void getView(View view) {

        ivPic = view.findViewById(R.id.iv_pic);
        tvName = view.findViewById(R.id.tv_name);
        llMoney = view.findViewById(R.id.ll_money);

        tvPriceNew = view.findViewById(R.id.tv_price_new);
//        ivSelf = view.findViewById(R.id.iv_self);
        tvNum = view.findViewById(R.id.tv_num);
        tvGoShop = view.findViewById(R.id.tv_go_shop);
        tvGoShop.setOnClickListener(onSingleClickListener);
    }

    @Override
    protected void showView() {

        List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean.ImgListBean> imgList = item.getImgList();
        if (imgList != null && !imgList.isEmpty()) {
            Glide.with(adapter.context).load(imgList.get(0).getImageArtworkName()).into(ivPic);
        }

        tvName.setText(item.getGoodsInfoName());
        tvPriceNew.setText(String.valueOf(StringUtils.moneyFen2Yuan(item.getGoodsInfoPreferPrice())));

        tvNum.setText(String.valueOf(item.getProductCommentVo().getCount()));
        tvNum.append("条评价，");
        tvNum.append(String.valueOf(item.getProductCommentVo().getColligate()));
        tvNum.append("%好评");


    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.tv_go_shop: {
                    adapter.getListener().onItemViewClick(v.getId(), SearchGoodsListViewHolder.this);
                }
                break;
            }
        }
    };
}
