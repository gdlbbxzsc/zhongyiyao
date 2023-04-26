package com.pbph.shoppingmall.module.collect.shops.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetCollectionSellerListResponse;
import com.pbph.shoppingmall.module.collect.shops.adapter.CollectShopsListDataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public class CollectShopsListViewHolder extends ViewHolder {

    private CheckBox cb_coupon;
    private View ViewRight;
    RelativeLayout.LayoutParams params;

    private ImageView ivShoppic;
    private TextView tvShopname;

    private TextView tvCountContent;
    private TextView tvTotalevaluteContent;

    private TextView tvShopstreetShopsEnter;


    @Override
    protected int getLayout() {
        return R.layout.listview_collectshopslist;
    }

    @Override
    protected void getView(View view) {

        cb_coupon = view.findViewById(R.id.cb_coupon);
        ViewRight = view.findViewById(R.id.ViewRight);
        params = (RelativeLayout.LayoutParams) ViewRight.getLayoutParams();


        ivShoppic = view.findViewById(R.id.iv_shoppic);
        tvShopname = view.findViewById(R.id.tv_shopname);

        tvCountContent = view.findViewById(R.id.tv_count_content);

        tvTotalevaluteContent = view.findViewById(R.id.tv_totalevalute_content);
        tvShopstreetShopsEnter = view.findViewById(R.id.tv_shopstreet_shops_enter);
        tvShopstreetShopsEnter.setOnClickListener(onSingleClickListener);

    }

    @Override
    protected void showView() {
        GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean vo = (GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean) item;
        CollectShopsListDataAdapter cadapter = (CollectShopsListDataAdapter) adapter;
        if (cadapter.isEdit()) {
            cb_coupon.setVisibility(View.VISIBLE);

            params.rightMargin = cadapter.marginRight;
            ViewRight.setLayoutParams(params);

            cb_coupon.setChecked(adapter.isChoiced(position));
        } else {
            cb_coupon.setVisibility(View.GONE);

            params.rightMargin = 0;
            ViewRight.setLayoutParams(params);
        }

        tvShopname.setText(vo.getStoreName());

        Glide.with(adapter.context).load(vo.getStoreLogo())
//                .error(R.drawable.tianjiaotupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiaotupian)
                .into(ivShoppic);


        tvCountContent.setText(String.valueOf(vo.getCollectionNumber()));
        tvTotalevaluteContent.setText(vo.getOverallMerit());

    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            CollectShopsListDataAdapter myAdapter = (CollectShopsListDataAdapter) adapter;
            switch (v.getId()) {
                case R.id.tv_shopstreet_shops_enter: {
                    myAdapter.getListener().onItemViewClick(v.getId(), CollectShopsListViewHolder.this);
                }
                break;
            }
        }
    };
}
