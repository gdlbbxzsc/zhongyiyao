package com.pbph.shoppingmall.module.shoppingcar.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;


public class GoodsViewHolder extends ViewHolder {


    private ImageView ivGoodsImg;
    private TextView tvGoodsName;
    private TextView tvSpec;
    private TextView tvReducePrice;
    private TextView iv1;
    private TextView tvGoodsPrice;

    CheckBox getIsGoodChecked;

    TextView getTvGoodsNum;


    @Override
    protected int getLayout() {
        return R.layout.item_retail_child;
    }

    @Override
    protected void getView(View view) {

        ivGoodsImg = (ImageView) view.findViewById(R.id.iv_goods_img);
        tvGoodsName = (TextView) view.findViewById(R.id.tv_goods_name);
        tvSpec = (TextView) view.findViewById(R.id.tv_spec);
        tvReducePrice = (TextView) view.findViewById(R.id.tv_reduce_price);
        iv1 = (TextView) view.findViewById(R.id.iv_1);
        tvGoodsPrice = (TextView) view.findViewById(R.id.tv_goods_price);

        view.findViewById(R.id.reduce).setOnClickListener(listener);
        view.findViewById(R.id.plus).setOnClickListener(listener);

        getIsGoodChecked = (CheckBox) view.findViewById(R.id.is_good_checked);
        getIsGoodChecked.setOnClickListener(listener);


        getTvGoodsNum = view.findViewById(R.id.tv_goods_num);
    }

    @Override
    protected void showView() {

        ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean product = (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean) item;

        ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean.GoodsInfoBean goodsInfo = product.getGoodsInfo();

        getIsGoodChecked.setChecked(product.goodsCheck);

        Glide.with(adapter.context).load(goodsInfo.getGoodsInfoImgUrl())
//                .placeholder(R.mipmap.banner_zw)
//                .error(R.mipmap.banner_zw)
                .into(ivGoodsImg);


        tvGoodsName.setText(goodsInfo.getGoodsInfoName());
        tvSpec.setText("");
        for (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean.GoodsInfoBean.GoodsInfoSpecListBean par : goodsInfo.getGoodsInfoSpecList()) {
            tvSpec.append(par.getSpecName());
            tvSpec.append(":");
            tvSpec.append(par.getSpecDetailName());
            tvSpec.append(" ");
        }

        tvReducePrice.setText("已降价");
        tvReducePrice.append(StringUtils.moneyFen2Yuan(product.getGoodsPreferentialPrice()));
        tvReducePrice.append("元");

        tvGoodsPrice.setText(StringUtils.moneyFen2Yuan(product.getGoodsJoinPrice()));
//        tvGoodsPrice.append("元");

        getTvGoodsNum.setText(String.valueOf(product.getGoodsNum()));
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            adapter.getListener().onItemViewClick(v.getId(), GoodsViewHolder.this);
        }
    };
}
