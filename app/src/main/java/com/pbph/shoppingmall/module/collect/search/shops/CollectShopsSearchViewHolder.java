package com.pbph.shoppingmall.module.collect.search.shops;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetCollectionSellerListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public class CollectShopsSearchViewHolder extends ViewHolder<GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean> {


    private ImageView ivShoppic;
    private TextView tvShopname;

    private TextView tvCountContent;
    private TextView tvTotalevaluteContent;

    private TextView tvShopstreetShopsEnter;


    @Override
    protected int getLayout() {
        return R.layout.listview_collect_shops_search;
    }

    @Override
    protected void getView(View view) {

        ivShoppic = view.findViewById(R.id.iv_shoppic);
        tvShopname = view.findViewById(R.id.tv_shopname);

        tvCountContent = view.findViewById(R.id.tv_count_content);

        tvTotalevaluteContent = view.findViewById(R.id.tv_totalevalute_content);
        tvShopstreetShopsEnter = view.findViewById(R.id.tv_shopstreet_shops_enter);
        tvShopstreetShopsEnter.setOnClickListener(onSingleClickListener);

    }

    @Override
    protected void showView() {

        tvShopname.setText(item.getStoreName());

        Glide.with(adapter.context).load(item.getStoreLogo())
//                .error(R.drawable.tianjiaotupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiaotupian)
                .into(ivShoppic);


        tvCountContent.setText(String.valueOf(item.getCollectionNumber()));
        tvTotalevaluteContent.setText(item.getOverallMerit());
    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.tv_shopstreet_shops_enter: {
                    adapter.getListener().onItemViewClick(v.getId(), CollectShopsSearchViewHolder.this);
                }
                break;
            }
        }
    };
}
