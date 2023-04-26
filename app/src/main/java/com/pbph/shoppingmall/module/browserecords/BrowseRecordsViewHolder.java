package com.pbph.shoppingmall.module.browserecords;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetBrowseRecordListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;


public class BrowseRecordsViewHolder extends ViewHolder {

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
        GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean vo = (GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean) item;
        BrowsRecordsDataAdapter cadapter = (BrowsRecordsDataAdapter) adapter;
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

        Glide.with(adapter.context).load(vo.getGoodsInfoImgUrl())
//                .error(R.drawable.tianjiatupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiatupian)
                .into(ivPic);


        tvName.setText(vo.getProductName());

        tvPriceNew.setText(String.valueOf(StringUtils.moneyFen2Yuan(vo.getGoodsInfoPreferPrice())));
//        iv_self;

        tvNum.setText(String.valueOf(vo.getCommcont()));
        tvNum.append("条评价，");
        tvNum.append(String.valueOf(vo.getPraise()));
        tvNum.append("%好评");

    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            BrowsRecordsDataAdapter myAdapter = (BrowsRecordsDataAdapter) adapter;
            switch (v.getId()) {
                case R.id.tv_look_others: {
                    myAdapter.getListener().onItemViewClick(v.getId(), BrowseRecordsViewHolder.this);
                }
                break;
                case R.id.iv_gwc: {
                    myAdapter.getListener().onItemViewClick(v.getId(), BrowseRecordsViewHolder.this);
                }
                break;

            }
        }
    };
}
