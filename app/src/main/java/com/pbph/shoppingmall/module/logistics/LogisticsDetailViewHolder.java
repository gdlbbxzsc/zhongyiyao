package com.pbph.shoppingmall.module.logistics;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetLogisticsInformationResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.utils.DateUtils;


public class LogisticsDetailViewHolder extends ViewHolder<GetLogisticsInformationResponse.DataBean.LogisticsListBean> {


    private LinearLayout llLogisticsLineTop;
    private LinearLayout llLogisticsLineBottom;
    private TextView tvGone;
    private ImageView tvLogisticsImg;
    private TextView tvLogisticsInfo1;
    private TextView tvLogisticsInfo2;


    @Override
    protected int getLayout() {
        return R.layout.listview_logisticsdetail;
    }

    @Override
    protected void getView(View view) {

        llLogisticsLineTop = view.findViewById(R.id.ll_logistics_line_top);
        llLogisticsLineBottom = view.findViewById(R.id.ll_logistics_line_bottom);

        tvLogisticsImg = view.findViewById(R.id.tv_logistics_img);

        tvGone = view.findViewById(R.id.tv_gone);
        tvLogisticsInfo1 = view.findViewById(R.id.tv_logistics_info1);

        tvLogisticsInfo2 = view.findViewById(R.id.tv_logistics_info2);
    }

    @Override
    protected void showView() {

        if (position == 0) {
            //第一条 不显示 上边那条线
            llLogisticsLineTop.setVisibility(View.INVISIBLE);
            tvLogisticsImg.setImageLevel(1);//显示红色图片
//设置红色加粗
            tvGone.setTextAppearance(adapter.context, R.style.font_red_normal1_bold);
            tvLogisticsInfo1.setTextAppearance(adapter.context, R.style.font_red_normal2_bold);
//设置红色
            tvLogisticsInfo2.setTextAppearance(adapter.context, R.style.font_red_small1);
        } else {
            llLogisticsLineTop.setVisibility(View.VISIBLE);
            tvLogisticsImg.setImageLevel(0);//显示灰色图片

//            设置普通黑色文字
            tvGone.setTextAppearance(adapter.context, R.style.font_black_normal2);
            tvLogisticsInfo1.setTextAppearance(adapter.context, R.style.font_black_normal2);
//            设置灰色
            tvLogisticsInfo2.setTextAppearance(adapter.context, R.style.font_gray6_small1);
        }

        //最后一条不显示下边那条线
        llLogisticsLineBottom.setVisibility(position == adapter.getCount() - 1 ? View.INVISIBLE : View.VISIBLE);

//
        tvLogisticsInfo1.setText(item.getOrderLogReason());
        tvLogisticsInfo2.setText(new DateUtils(item.getOrderLogTime()).getString(DateUtils.PATTERN_28));

    }

}
