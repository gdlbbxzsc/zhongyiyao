package com.pbph.shoppingmall.module.orders.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.utils.Logger;
import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.model.vo.OrderOptionInfo;
import com.pbph.shoppingmall.model.vo.OrderStatusInfo;
import com.pbph.shoppingmall.module.orders.adapter.MyOrdersDataAdapter;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;


public class OrdersGoodsOneViewHolder extends ViewHolder<GetOrderResponse.DataBean.ListBean> {

    private TextView tvShopName;
    private TextView tvState;
    private ImageView ivDel;
    private ImageView ivPic;
    private TextView tvContents;
    private TextView ivGoodsNum;
    private TextView ivMoneyAll;

    private TextView[] tv_options = new TextView[4];

    @Override
    protected int getLayout() {
        return R.layout.listview_myorders1;
    }

    @Override
    protected void getView(View view) {
        tvShopName = view.findViewById(R.id.tv_shop_name);
        tvState = view.findViewById(R.id.tv_state);
        ivDel = view.findViewById(R.id.iv_del_finish);
        ivDel.setOnClickListener(listener);

        ivPic = view.findViewById(R.id.iv_pic);
        tvContents = view.findViewById(R.id.tv_contents);

        ivGoodsNum = view.findViewById(R.id.iv_goods_num);
        ivMoneyAll = view.findViewById(R.id.iv_money_all);

//
        tv_options[0] = view.findViewById(R.id.tv_option_1);
        tv_options[0].setOnClickListener(listener);

        tv_options[1] = view.findViewById(R.id.tv_option_2);
        tv_options[1].setOnClickListener(listener);

        tv_options[2] = view.findViewById(R.id.tv_option_3);
        tv_options[2].setOnClickListener(listener);

        tv_options[3] = view.findViewById(R.id.tv_option_4);
        tv_options[3].setOnClickListener(listener);

        MyOrdersDataAdapter myAdapter = (MyOrdersDataAdapter) adapter;

    }

    @Override
    protected void showView() {

        MyOrdersDataAdapter myAdapter = (MyOrdersDataAdapter) adapter;


        OrderStatusInfo statusInfo = myAdapter.orderHelper.getOrderStatusInfo(item.getOrderStatus());

        ivDel.setVisibility(View.GONE);

        flushBtns(statusInfo.option_ids);

//   ========
        tvShopName.setText(item.getOrderCode());
        tvState.setText(statusInfo.text);

        ivMoneyAll.setText(StringUtils.moneyFen2Yuan(item.getOrderPrice()));

        ivGoodsNum.setText(String.valueOf(item.getOrderGoodsDtoList() == null ? 0 : item.getOrderGoodsDtoList().size()));

        if (item.getOrderGoodsDtoList() == null || item.getOrderGoodsDtoList().size() <= 0) return;

        GetOrderResponse.DataBean.ListBean.OrderGoodsDtoListBean goods = item.getOrderGoodsDtoList().get(0);
        tvContents.setText(goods.getGoodsInfoName());

        Glide.with(adapter.context).load(goods.getGoodsInfoImgUrl())
                //                .error(R.drawable.tianjiatupian)           //设置错误图片
                //                .placeholder(R.drawable.tianjiatupian)
                .into(ivPic);

    }

    void flushBtns(int[] btns) {
        MyOrdersDataAdapter myAdapter = (MyOrdersDataAdapter) adapter;
        int num = 0;
        for (int i = tv_options.length - 1; i >= 0; i--) {
            int btn_pos = btns.length - (++num);
            if (btn_pos < 0 || btn_pos >= btns.length) {
                tv_options[i].setVisibility(View.GONE);
                tv_options[i].setTag(null);
                continue;
            }

            OrderOptionInfo optionInfo = myAdapter.orderHelper.getOrderOptionInfo(btns[btn_pos]);

            //删除按钮 有个图片删除 可以不用文字按钮展示
            if (optionInfo.id == 2) {
                ivDel.setVisibility(View.VISIBLE);
                ivDel.setTag(optionInfo.method_name);
                ivDel.setOnClickListener(listener);

                tv_options[i].setVisibility(View.GONE);
                tv_options[i].setTag(null);
                continue;
            }


            tv_options[i].setVisibility(View.VISIBLE);
            tv_options[i].setTag(optionInfo.method_name);

            tv_options[i].setText(optionInfo.text);
            tv_options[i].setTextColor(optionInfo.text_color);
            tv_options[i].setBackgroundResource(optionInfo.text_bg);


            //            是否评价 0未评价 1已经评价 2 已评价伊晒单
            if (!(item.getOrderStatus() == 14 || item.getOrderStatus() == 13)) continue;
            if (optionInfo.id != 8) continue;
            
            int flag = item.getEvaluateFlag();
            if (flag == 2) {
                tv_options[i].setVisibility(View.GONE);
                tv_options[i].setTag(null);
            } else if (flag == 1) {
                tv_options[i].setText("晒单");
            }

        }
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            adapter.getListener().onItemViewClick(v.getId(), OrdersGoodsOneViewHolder.this, v.getTag());
        }
    };
}
