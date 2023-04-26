package com.pbph.shoppingmall.module.collect.goods.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetCustomerFollowListResponse;
import com.pbph.shoppingmall.module.collect.goods.adapter.CollectGoodsListDataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;


public class CollectGoodsListViewHolder extends ViewHolder<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> {

    private CheckBox cb_coupon;
    private View ViewRight;
    RelativeLayout.LayoutParams params;

    private ImageView ivPic;
    private TextView tvName;

    //    private LinearLayout llLine;
    private TextView tvPriceNew;
    TextView iv_self;

    private TextView tvNum;


    private TextView tvLookOthers;
    ImageView iv_gwc;


    @Override
    protected int getLayout() {
        return R.layout.listview_browserecords;
    }

    @Override
    protected void getView(View view) {

        cb_coupon = view.findViewById(R.id.cb_coupon);
        ViewRight = view.findViewById(R.id.ViewRight);
        params = (RelativeLayout.LayoutParams) ViewRight.getLayoutParams();


        ivPic = view.findViewById(R.id.iv_pic);
        tvName = view.findViewById(R.id.tv_name);

//        llLine = (LinearLayout) view.findViewById(R.id.ll_money);
        tvPriceNew = view.findViewById(R.id.tv_price_new);
        iv_self = view.findViewById(R.id.iv_self);

        tvNum = view.findViewById(R.id.tv_num);

        tvLookOthers = view.findViewById(R.id.tv_look_others);
        tvLookOthers.setOnClickListener(onSingleClickListener);
        iv_gwc = view.findViewById(R.id.iv_gwc);
        iv_gwc.setOnClickListener(onSingleClickListener);


    }

    @Override
    protected void showView() {
        CollectGoodsListDataAdapter cadapter = (CollectGoodsListDataAdapter) adapter;
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

        tvName.setText(item.getProductName());


        Glide.with(adapter.context).load(item.getGoodsInfoImgUrl())
//                .error(R.drawable.tianjiaotupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiaotupian)
                .into(ivPic);


        tvPriceNew.setText(StringUtils.moneyFen2Yuan(item.getGoodsInfoPreferPrice()));



        tvNum.setText(String.valueOf(item.getCommcont()));
        tvNum.append("条评价，");
        tvNum.append(String.valueOf(item.getPraise()));
        tvNum.append("%好评");


    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            CollectGoodsListDataAdapter myAdapter = (CollectGoodsListDataAdapter) adapter;
            switch (v.getId()) {
                case R.id.tv_look_others: {
                    myAdapter.getListener().onItemViewClick(v.getId(), CollectGoodsListViewHolder.this);
                }
                break;
                case R.id.iv_gwc: {
                    myAdapter.getListener().onItemViewClick(v.getId(), CollectGoodsListViewHolder.this);
                }
                break;
            }
        }
    };
}
