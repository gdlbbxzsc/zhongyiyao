package com.pbph.shoppingmall.module.search.shops.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchStoreResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;


public class SearchShopsListViewHolder extends ViewHolder<SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean> {

    private ImageView ivPic;
    private TextView tvName;
    private TextView tvGoShop;
    private ImageView ivGood1;
    private ImageView ivGood2;
    private ImageView ivGood3;

    @Override
    protected int getLayout() {
        return R.layout.listview_searchshopslist;
    }

    @Override
    protected void getView(View view) {


        ivPic = view.findViewById(R.id.iv_pic);
        tvName = view.findViewById(R.id.tv_name);
        tvGoShop = view.findViewById(R.id.tv_go_shop);
        tvGoShop.setOnClickListener(onSingleClickListener);
        ivGood1 = view.findViewById(R.id.iv_good1);
        ivGood2 = view.findViewById(R.id.iv_good2);
        ivGood3 = view.findViewById(R.id.iv_good3);
        ivGood1.setOnClickListener(onSingleClickListener);
        ivGood2.setOnClickListener(onSingleClickListener);
        ivGood3.setOnClickListener(onSingleClickListener);

        SearchShopsListDataAdapter myAdapter = (SearchShopsListDataAdapter) adapter;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivGood1.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivGood1.setLayoutParams(params);

        params = (LinearLayout.LayoutParams) ivGood2.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivGood2.setLayoutParams(params);

        params = (LinearLayout.LayoutParams) ivGood3.getLayoutParams();
        params.width = myAdapter.wh;
        params.height = myAdapter.wh;
        ivGood3.setLayoutParams(params);


    }

    @Override
    protected void showView() {
        Glide.with(adapter.context).load(item.getStoreLogo())
//                .error(R.drawable.tianjiatupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiatupian)
                .into(ivPic);

        tvName.setText(item.getStoreName());

        List<SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean.GoodsInfoListBean> list = item.getGoodsInfoList();

        Glide.with(adapter.context).load(list.get(0).getGoodsInfoImgUrl()).into(ivGood1);
        Glide.with(adapter.context).load(list.get(1).getGoodsInfoImgUrl()).into(ivGood2);
        Glide.with(adapter.context).load(list.get(2).getGoodsInfoImgUrl()).into(ivGood3);
    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.tv_go_shop: {
                    adapter.getListener().onItemViewClick(v.getId(), SearchShopsListViewHolder.this);
                }
                break;
                case R.id.iv_good1: {
                    adapter.getListener().onItemViewClick(v.getId(), SearchShopsListViewHolder.this);
                }
                break;
                case R.id.iv_good2: {
                    adapter.getListener().onItemViewClick(v.getId(), SearchShopsListViewHolder.this);
                }
                break;
                case R.id.iv_good3: {
                    adapter.getListener().onItemViewClick(v.getId(), SearchShopsListViewHolder.this);
                }
                break;
            }
        }
    };
}
