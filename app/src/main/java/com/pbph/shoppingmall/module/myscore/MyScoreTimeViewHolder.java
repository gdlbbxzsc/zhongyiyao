package com.pbph.shoppingmall.module.myscore;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.utils.DateUtils;
import com.utils.StringUtils;


public class MyScoreTimeViewHolder extends ViewHolder {

    private ImageView ivScore;
    private TextView tvScoreTime;


    @Override
    protected int getLayout() {
        return R.layout.listview_myscoretime;
    }

    @Override
    protected void getView(View view) {

        ivScore = view.findViewById(R.id.iv_score);
        tvScoreTime = view.findViewById(R.id.tv_score_time);
    }

    @Override
    protected void showView() {
        String vo = (String) item;

        if (StringUtils.isEqual(vo, "本月")) {
            ivScore.setImageLevel(1);
            tvScoreTime.setText(vo);
        } else {
            ivScore.setImageLevel(0);
            tvScoreTime.setText(vo);
//            try {
//                DateUtils dateUtils = new DateUtils(vo, DateUtils.PATTERN_6);
//                tvScoreTime.setText(dateUtils.getString(DateUtils.PATTERN_27));
//            } catch (Exception e) {
//                e.printStackTrace();
//                tvScoreTime.setText("未知");
//            }

        }


    }

}
