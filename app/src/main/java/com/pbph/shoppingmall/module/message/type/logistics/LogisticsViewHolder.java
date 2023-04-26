package com.pbph.shoppingmall.module.message.type.logistics;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetMyMessageListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.utils.DateUtils;


public class LogisticsViewHolder extends ViewHolder<GetMyMessageListResponse.DataBean.MessageListBean.ListBean> {


    private ImageView ivWuliuImg;
    private TextView tv_wuliuzhuangtai;
    private TextView tvWuliuName;
    private TextView tvWuliuDesc;
    private TextView tvWuliuDate, tv_wuliu_desc1;


    @Override
    protected int getLayout() {
        return R.layout.listview_messagetype_logistics;
    }

    @Override
    protected void getView(View view) {

        ivWuliuImg = view.findViewById(R.id.iv_wuliu_img);

        tv_wuliuzhuangtai = view.findViewById(R.id.tv_wuliuzhuangtai);
        tvWuliuName = view.findViewById(R.id.tv_wuliu_name);
        tvWuliuDesc = view.findViewById(R.id.tv_wuliu_desc);
        tvWuliuDate = view.findViewById(R.id.tv_wuliu_date);
        tv_wuliu_desc1 = view.findViewById(R.id.tv_wuliu_desc1);
    }

    @Override
    protected void showView() {


        tv_wuliuzhuangtai.setText(item.getMessageName());

        DateUtils dateUtils = new DateUtils(item.getModifyTime());
        tvWuliuDate.setText(dateUtils.getString(DateUtils.PATTERN_11));

        Glide.with(adapter.context).load(item.getMessageImgUrl()).into(ivWuliuImg);


        tvWuliuName.setText(item.getRemark());
        tvWuliuDesc.setText("物流信息:" + item.getMessageContent());
        tv_wuliu_desc1.setText("订单编号:" + item.getExpressNo());


    }
}
