package com.pbph.shoppingmall.module.message.type.notices;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetMyMessageListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.utils.DateUtils;
import com.utils.StringUtils;


public class NoticesViewHolder extends ViewHolder<GetMyMessageListResponse.DataBean.MessageListBean.ListBean> {

    private ImageView iv_tongzhi_img;
    private TextView tvTongzhiName;
    private TextView tvTongzhiDesc;
    private TextView tvTongzhiDate;


    @Override
    protected int getLayout() {
        return R.layout.listview_messagetype_notices;
    }

    @Override
    protected void getView(View view) {

        iv_tongzhi_img = view.findViewById(R.id.iv_tongzhi_img);
        tvTongzhiName = view.findViewById(R.id.tv_tongzhi_name);
        tvTongzhiDesc = view.findViewById(R.id.tv_tongzhi_desc);
        tvTongzhiDate = view.findViewById(R.id.tv_tongzhi_date);
    }

    @Override
    protected void showView() {


        tvTongzhiName.setText(item.getMessageName());

        DateUtils dateUtils = new DateUtils(item.getModifyTime());
        tvTongzhiDate.setText(dateUtils.getString(DateUtils.PATTERN_11));

        if (StringUtils.isEmpty(item.getMessageImgUrl())) {
            iv_tongzhi_img.setVisibility(View.GONE);
        } else {
//            iv_tongzhi_img.setVisibility(View.VISIBLE);
//            Glide.with(adapter.context).load(item.getMessageImgUrl()).into(iv_tongzhi_img);
        }

        tvTongzhiDesc.setText(item.getMessageContent());

    }
}
