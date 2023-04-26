package com.pbph.shoppingmall.module.myscore;

import android.view.View;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetMyCustomerPointListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.utils.DateUtils;


public class MyScoreContentViewHolder extends ViewHolder {

    private TextView tvScoreMsg;
    //    private TextView tvScoreTimePre;
    private TextView tvScoreTime;
    private TextView tv_score_num;


    @Override
    protected int getLayout() {
        return R.layout.listview_myscorecontent;
    }

    @Override
    protected void getView(View view) {
        tvScoreMsg = view.findViewById(R.id.tv_score_msg);
//        tvScoreTimePre = (TextView) view.findViewById(R.id.tv_score_time_pre);
        tvScoreTime = view.findViewById(R.id.tv_score_time);

        tv_score_num = view.findViewById(R.id.tv_score_num);
    }

    @Override
    protected void showView() {
        GetMyCustomerPointListResponse.DataBean.ListBean.PointListBean vo = (GetMyCustomerPointListResponse.DataBean.ListBean.PointListBean) item;

        tvScoreMsg.setText(vo.getPointDetail());
        DateUtils dateUtils = new DateUtils(vo.getCreateTime());
        tvScoreTime.setText(dateUtils.getString(DateUtils.PATTERN_5));

        tv_score_num.setText(vo.getPointType() == 1 ? "+" : "-");
        tv_score_num.append(String.valueOf(vo.getPointScore()));
    }

}
