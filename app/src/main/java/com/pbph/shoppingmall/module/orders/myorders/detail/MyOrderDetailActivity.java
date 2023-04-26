package com.pbph.shoppingmall.module.orders.myorders.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.OrderInfoResponse;
import com.pbph.shoppingmall.model.vo.OrderOptionInfo;
import com.pbph.shoppingmall.model.vo.OrderStatusInfo;
import com.pbph.shoppingmall.module.logistics.LogisticsDetailActivity;
import com.pbph.shoppingmall.module.orders.evaluateorders.submit.EvaluateOrderSubmitActivity;
import com.pbph.shoppingmall.module.orders.ioptions.IOptionsId;
import com.pbph.shoppingmall.module.orders.refundorders.submit.ReFundOrderSubmitActivity;
import com.pbph.shoppingmall.module.payment.PaymentActivity;
import com.pbph.shoppingmall.module.sendbackinfo.SendBackInfoActivity;
import com.pbph.shoppingmall.module.shoppingcar.ShoppingCarActivity;
import com.pbph.shoppingmall.utils.LongTime2HMS;
import com.pbph.shoppingmall.utils.OrderStatusOptionInfoHelper;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.pbph.shoppingmall.utils.ui.SecTimer;
import com.utils.DateUtils;
import com.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

public class MyOrderDetailActivity extends BaseActivity<Presenter> implements Contract.View, IOptionsId {

    LayoutInflater inflater;

    CommonTitlebar commonTitlebar;

    private LinearLayout layoutRoot;

    OrderStateInfoView stateInfoView;
    OrderCodeTimeInfoView codeTimeInfoView;
    LogisticsInfoView logisticsInfoView;

    UserInfoView userInfoView;

    GoodsInfoView goodsInfoView;
    OrderInfoView orderInfoView;

    BillInfoView billInfoView;

    RefoundInfoView refoundInfoView;

    Btns btns;

    int orderId;
    OrderInfoResponse.DataBean orderInfo;

    OrderStatusOptionInfoHelper orderHelper;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
        inflater = LayoutInflater.from(context);

        orderHelper = new OrderStatusOptionInfoHelper(context);
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_myorderdetail;
    }


    @Override
    protected void initData() {
        orderId = getIntent().getIntExtra("orderId", 0);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "订单详情", true);


        layoutRoot = findViewById(R.id.layout_root);


        stateInfoView = new OrderStateInfoView();
        codeTimeInfoView = new OrderCodeTimeInfoView(findViewById(R.id.include_code_time));
        logisticsInfoView = new LogisticsInfoView(findViewById(R.id.include_logistics));
        userInfoView = new UserInfoView(findViewById(R.id.include_user_info));
        goodsInfoView = new GoodsInfoView(findViewById(R.id.include_goods_info));
        orderInfoView = new OrderInfoView(findViewById(R.id.include_orderinfo));
        billInfoView = new BillInfoView(findViewById(R.id.include_bill));

        refoundInfoView = new RefoundInfoView(findViewById(R.id.include_refound));

        btns = new Btns(findViewById(R.id.ll_btns));

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public int getOrderId() {
        return orderId;
    }


    @Override
    public void onResume() {
        super.onResume();
        stateInfoView.startTime();
    }

    @Override
    public void onPause() {
        stateInfoView.cancelTime();
        super.onPause();
    }

    OrderStatusInfo orderStateInfo;

    @Override
    public void setOrderInfo(OrderInfoResponse.DataBean data) {

        orderInfo = data;

        orderStateInfo = orderHelper.getOrderStatusInfo(data.getOrderStatus());

        stateInfoView.setDatas(data);
        codeTimeInfoView.setDatas(data);
        logisticsInfoView.setDatas(data);
        userInfoView.setDatas(data);
        goodsInfoView.setDatas(data);
        orderInfoView.setDatas(data);

        billInfoView.setDatas(data);

        refoundInfoView.setDatas(data);

        btns.setDatas(data);
    }


    public class OrderStateInfoView {
        private ImageView ivState;
        private TextView tvState;

        private View includeNopayTime;
        private TextView tvHh;
        private TextView tvMm;
        private TextView tvSs;

        SecTimer secTimer;

        private TextView tvSendName;

        public OrderStateInfoView() {
            ivState = findViewById(R.id.iv_state);
            tvState = findViewById(R.id.tv_state);

            includeNopayTime = findViewById(R.id.include_nopay_time);
            tvHh = includeNopayTime.findViewById(R.id.tv_hh);
            tvMm = includeNopayTime.findViewById(R.id.tv_mm);
            tvSs = includeNopayTime.findViewById(R.id.tv_ss);


            tvSendName = findViewById(R.id.tv_send_name);
        }

        public void setDatas(OrderInfoResponse.DataBean data) {


            tvState.setText(orderStateInfo.text);

            includeNopayTime.setVisibility(View.GONE);
            tvSendName.setVisibility(View.GONE);
            switch (orderStateInfo.type_id) {
                case 1: {// 已取消
                    ivState.setImageLevel(1);
                }
                break;
                case 2: {// 待付款
                    ivState.setImageLevel(2);

                    includeNopayTime.setVisibility(View.GONE);
                    long overTime = data.getCreateTime();
                    startTime(overTime);
                }
                break;
                case 3: {// 待收货
                    ivState.setImageLevel(0);

                    tvSendName.setVisibility(View.VISIBLE);
                    tvSendName.setText(data.getOrderExpressName());
                }
                break;
                case 4: {// 已完成
                    ivState.setImageLevel(3);

                    tvSendName.setVisibility(View.VISIBLE);
                    tvSendName.setText(data.getOrderExpressName());
                }
                break;
                case 5: {// 退款
                    ivState.setImageLevel(3);
                }
                break;
                case 6: {// 退货
                    ivState.setImageLevel(3);
                }
                break;
                default:
                    ivState.setImageLevel(3);
                    break;
            }
//这些状态 都显示差
            switch (data.getOrderStatus()) {
                case 32:
                    ivState.setImageLevel(4);
                    break;
                case 22:
                    ivState.setImageLevel(4);
                    break;
                case 27:
                    ivState.setImageLevel(4);
                    break;
                case 25:
                    ivState.setImageLevel(4);
                    break;
                case 34:
                    ivState.setImageLevel(4);
                    break;
            }
        }

        public void startTime(long overTime) {
            secTimer = new SecTimer() {
                @Override
                public void onTick(long passTime) throws Exception {

                    long time_len = overTime - passTime;
                    if (time_len <= 0) {
                        time_len = 0;
                        secTimer.finish();
                    }

                    LongTime2HMS time = LongTime2HMS.longTime2HMS(time_len);
                    tvHh.setText(time.getH());
                    tvMm.setText(time.getM());
                    tvSs.setText(time.getS());
                }
            };
            secTimer.start();
        }

        public void startTime() {
            if (secTimer != null) secTimer.start();
        }

        public void cancelTime() {
            if (secTimer != null) secTimer.cancel();
        }
    }

    public class OrderCodeTimeInfoView {

        private TextView tvOrderdetailCode;
        private TextView tvOrderdetailCreateTime;

        public OrderCodeTimeInfoView(View view) {
            tvOrderdetailCode = view.findViewById(R.id.tv_orderdetail_code);
            tvOrderdetailCreateTime = view.findViewById(R.id.tv_orderdetail_create_time);
        }

        public void setDatas(OrderInfoResponse.DataBean data) {
            tvOrderdetailCode.setText(data.getOrderCode());
            DateUtils dateUtils = new DateUtils(data.getCreateTime());
            tvOrderdetailCreateTime.setText(dateUtils.getString(DateUtils.PATTERN_28));
        }
    }

    public class LogisticsInfoView {

        private View includeLogistics;

        private TextView tvOrderdetailLogisticsState;
        private TextView tvOrderdetailLogisticsAddress;
        private TextView tvOrderdetailLogisticsTime;

        public LogisticsInfoView(View view) {
            includeLogistics = view;
            tvOrderdetailLogisticsState = view.findViewById(R.id.tv_orderdetail_logistics_state);
            tvOrderdetailLogisticsAddress = view.findViewById(R.id.tv_orderdetail_logistics_address);
            tvOrderdetailLogisticsTime = view.findViewById(R.id.tv_orderdetail_logistics_time);
        }

        public void setDatas(OrderInfoResponse.DataBean data) {

            includeLogistics.setVisibility(View.VISIBLE);
            switch (orderStateInfo.type_id) {
                case 1: {// 已取消
                    includeLogistics.setVisibility(View.GONE);
                    return;
                }
//                break;
                case 2: {// 待付款
                    includeLogistics.setVisibility(View.GONE);
                    return;
                }
//                break;
                case 3: {// 待收货
                }
                break;
                case 4: {// 已完成
                }
                break;
                case 5: {// 退款
                }
                break;
                case 6: {// 退货
                }
                break;
            }
            List<OrderInfoResponse.DataBean.OrderLogBean> logs = data.getOrderLog();
            if (logs == null || logs.isEmpty()) {
                includeLogistics.setVisibility(View.GONE);
                return;
            }
            OrderInfoResponse.DataBean.OrderLogBean bean = logs.get(0);

//            tvOrderdetailLogisticsState.setText("");
            tvOrderdetailLogisticsAddress.setText(bean.getOrderLogReason());
            tvOrderdetailLogisticsTime.setText(bean.getOrderLogTime());

        }
    }


    public class UserInfoView {
        private TextView tvOrderdetailUsername;
        private TextView tvOrderdetailUsercall;
        private TextView tvOrderdetailUserAddress;

        public UserInfoView(View view) {
            tvOrderdetailUsername = view.findViewById(R.id.tv_orderdetail_username);
            tvOrderdetailUsercall = view.findViewById(R.id.tv_orderdetail_usercall);
            tvOrderdetailUserAddress = view.findViewById(R.id.tv_orderdetail_user_address);
        }

        public void setDatas(OrderInfoResponse.DataBean data) {
            tvOrderdetailUsername.setText(data.getShippingPerson());
            tvOrderdetailUsercall.setText(data.getShippingMobile());

            tvOrderdetailUserAddress.setText(data.getShippingProvince());
            tvOrderdetailUserAddress.append(data.getShippingCity());
            tvOrderdetailUserAddress.append(data.getShippingCounty());
            tvOrderdetailUserAddress.append(data.getShippingAddress());
        }

    }


    public class GoodsInfoView {

        private TextView tvShopName;
        private TextView tvCallSeller;

        private LinearLayout llGoods;

        private View ll_num_money;
        private TextView iv_goods_num;
        private TextView iv_money_all;

        public GoodsInfoView(View view) {
            tvShopName = view.findViewById(R.id.tv_shop_name);
            tvCallSeller = view.findViewById(R.id.tv_call_seller);
            llGoods = view.findViewById(R.id.ll_goods);
            ll_num_money = view.findViewById(R.id.ll_num_money);

            iv_goods_num = ll_num_money.findViewById(R.id.iv_goods_num);
            iv_money_all = ll_num_money.findViewById(R.id.iv_money_all);

        }

        public void setDatas(OrderInfoResponse.DataBean data) {
            tvShopName.setText(data.getStoreName());
            tvCallSeller.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onCanClick(View v) {
                    data.getStoreId();
                }
            });

            iv_goods_num.setText(String.valueOf(data.getOrderGoods().size()));
            iv_money_all.setText(StringUtils.moneyFen2Yuan(data.getOrderPrice()));


//            if (llGoods.getChildCount() > 0) return;
            llGoods.removeAllViews();

            for (int i = 0, c = data.getOrderGoods().size(); i < c; i++) {
                OrderInfoResponse.DataBean.OrderGoodsBean vo = data.getOrderGoods().get(i);
                addGoods(vo);
            }
        }

        public void addGoods(OrderInfoResponse.DataBean.OrderGoodsBean vo) {
            GoodsItem goodsItem = new GoodsItem(vo);
            llGoods.addView(goodsItem.view);
        }
    }

    public class GoodsItem {
        public View view;
        private ImageView ivPic;
        private TextView tvContents;
        private TextView tv_count;
        private TextView tvPrice;
        private TextView tvGwc;


        public GoodsItem(OrderInfoResponse.DataBean.OrderGoodsBean vo) {
            view = inflater.inflate(R.layout.layout_orderdetail_item_good, null);
            ivPic = view.findViewById(R.id.iv_pic);
            tvContents = view.findViewById(R.id.tv_contents);
            tv_count = view.findViewById(R.id.tv_count);
            tvPrice = view.findViewById(R.id.tv_price);
            tvGwc = view.findViewById(R.id.tv_gwc);

            setDatas(vo);
        }

        private void setDatas(OrderInfoResponse.DataBean.OrderGoodsBean vo) {
            Glide.with(context).load(vo.getGoodsInfoImgUrl())
//                .error(R.drawable.tianjiatupian)           //设置错误图片
//                .placeholder(R.drawable.tianjiatupian)
                    .into(ivPic);

            tvContents.setText(vo.getGoodsInfoName());
            tv_count.setText("×" + vo.getGoodsInfoNum());

            tvPrice.setText(StringUtils.moneyFen2Yuan(vo.getGoodsInfoOldPrice() * vo.getGoodsInfoNum()));
            tvGwc.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onCanClick(View v) {
                    presenter.saveShoppingCart(vo.getGoodsInfoId(), 1, 1);
                }
            });
        }
    }

    public class OrderInfoView {
        View view;
        private TextView tvOrderdetailPayType;
        private TextView tvOrderdetailSendType;
        private TextView tvOrderdetailReceiptType;
        private TextView tvOrderdetailReceiptTitle;
        private TextView tvOrderdetailReceiptContent;

        public OrderInfoView(View view) {
            this.view = view;
            tvOrderdetailPayType = view.findViewById(R.id.tv_orderdetail_pay_type);
            tvOrderdetailSendType = view.findViewById(R.id.tv_orderdetail_send_type);
            tvOrderdetailReceiptType = view.findViewById(R.id.tv_orderdetail_receipt_type);
            tvOrderdetailReceiptTitle = view.findViewById(R.id.tv_orderdetail_receipt_title);
            tvOrderdetailReceiptContent = view.findViewById(R.id.tv_orderdetail_receipt_content);
        }

        private void setDatas(OrderInfoResponse.DataBean data) {

            switch (orderStateInfo.type_id) {
                case 1: {// 已取消
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 2: {// 待付款
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 3: {// 待收货
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 4: {// 已完成
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 5: {// 退款
                    view.setVisibility(View.GONE);
                }
                return;
                case 6: {// 退货
                    view.setVisibility(View.GONE);
                }
                return;
            }

            switch (data.getOrderLinePay()) {
                case 1:
                    tvOrderdetailPayType.setText("在线支付");
                    break;
                case 2:
                    tvOrderdetailPayType.setText("货到付款");
                    break;
            }

            tvOrderdetailSendType.setText(data.getOrderExpressName());

            tvOrderdetailReceiptType.setText(data.getInvoiceType() == 0 ? "个人" : "公司");

            tvOrderdetailReceiptTitle.setText(data.getInvoiceTitle());
            tvOrderdetailReceiptContent.setText(data.getInvoiceCustomerContent());
        }
    }

    public class BillInfoView {
        View view;
        private TextView tvOrderdetailBillMoneyAll;
        private TextView tvOrderdetailBillSendPay;
        private TextView tvOrderdetailBillCoupon;
        private TextView tvOrderdetailBillSalespromotion;
        private TextView tvOrderdetailBillRebate;
        private TextView tvOrderdetailBillPayall;

        public BillInfoView(View view) {
            this.view = view;
            tvOrderdetailBillMoneyAll = view.findViewById(R.id.tv_orderdetail_bill_money_all);
            tvOrderdetailBillSendPay = view.findViewById(R.id.tv_orderdetail_bill_send_pay);
            tvOrderdetailBillCoupon = view.findViewById(R.id.tv_orderdetail_bill_coupon);
            tvOrderdetailBillSalespromotion = view.findViewById(R.id.tv_orderdetail_bill_salespromotion);
            tvOrderdetailBillRebate = view.findViewById(R.id.tv_orderdetail_bill_rebate);
            tvOrderdetailBillPayall = view.findViewById(R.id.tv_orderdetail_bill_payall);
        }

        private void setDatas(OrderInfoResponse.DataBean data) {

            switch (orderStateInfo.type_id) {
                case 1: {// 已取消
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 2: {// 待付款
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 3: {// 待收货
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 4: {// 已完成
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 5: {// 退款
                    view.setVisibility(View.GONE);
                }
                return;
                case 6: {// 退货
                    view.setVisibility(View.GONE);
                }
                return;
            }


            tvOrderdetailBillMoneyAll.setText(StringUtils.moneyFen2Yuan(data.getOrderOldPrice()));
            tvOrderdetailBillSendPay.setText(StringUtils.moneyFen2Yuan(data.getExpressPrice()));
//            tvOrderdetailBillCoupon;
//            tvOrderdetailBillSalespromotion;
//            tvOrderdetailBillRebate;
            tvOrderdetailBillPayall.setText(StringUtils.moneyFen2Yuan(data.getOrderPrice()));
        }
    }


    public class RefoundInfoView {
        View view;
        private TextView tvOrderdetailRefoundReceive;
        private TextView tvOrderdetailRefoundReason;
        private TextView tvOrderdetailRefoundProve;
        private TextView tvOrderdetailRefoundPrice;
        private TextView tvOrderdetailRefoundQuestion;
        private TextView tvOrderdetailRefoundUpProve;
        private TextView tvOrderdetailRefoundLogisticsType;
        private TextView tvOrderdetailRefoundLogisticsCompany;
        private TextView tvOrderdetailRefoundLogisticsCode, tv_orderdetail_refound_shopaddress;


        private TextView tv_orderdetail_refound_reason_pre;
        private TextView tv_orderdetail_refound_price_pre;

        public RefoundInfoView(View view) {
            this.view = view;
            tvOrderdetailRefoundReceive = (TextView) view.findViewById(R.id.tv_orderdetail_refound_receive);
            tvOrderdetailRefoundReason = (TextView) view.findViewById(R.id.tv_orderdetail_refound_reason);
            tvOrderdetailRefoundProve = (TextView) view.findViewById(R.id.tv_orderdetail_refound_prove);
            tvOrderdetailRefoundPrice = (TextView) view.findViewById(R.id.tv_orderdetail_refound_price);
            tvOrderdetailRefoundQuestion = (TextView) view.findViewById(R.id.tv_orderdetail_refound_question);
            tvOrderdetailRefoundUpProve = (TextView) view.findViewById(R.id.tv_orderdetail_refound_up_prove);
            tvOrderdetailRefoundLogisticsType = (TextView) view.findViewById(R.id.tv_orderdetail_refound_logistics_type);
            tvOrderdetailRefoundLogisticsCompany = (TextView) view.findViewById(R.id.tv_orderdetail_refound_logistics_company);
            tvOrderdetailRefoundLogisticsCode = (TextView) view.findViewById(R.id.tv_orderdetail_refound_logistics_code);
            tv_orderdetail_refound_shopaddress = view.findViewById(R.id.tv_orderdetail_refound_shopaddress);

            tv_orderdetail_refound_reason_pre = (TextView) view.findViewById(R.id.tv_orderdetail_refound_reason_pre);
            tv_orderdetail_refound_price_pre = (TextView) view.findViewById(R.id.tv_orderdetail_refound_price_pre);

        }

        private void setDatas(OrderInfoResponse.DataBean data) {

            switch (orderStateInfo.type_id) {
                case 1: {// 已取消
                    view.setVisibility(View.GONE);
                    return;
                }
                case 2: {// 待付款
                    view.setVisibility(View.GONE);
                    return;
                }
                case 3: {// 待收货
                    view.setVisibility(View.GONE);
                    return;
                }
                case 4: {// 已完成
                    view.setVisibility(View.GONE);
                    return;
                }
                case 5: {// 退款
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 6: {// 退货
                    view.setVisibility(View.VISIBLE);
                }
                break;
            }
            OrderInfoResponse.DataBean.BackOrderInfoBean bean = data.getBackOrderInfo();

            tvOrderdetailRefoundReceive.setText(bean.getIsCollect() == 0 ? "未收货" : "已收货");
            tvOrderdetailRefoundReason.setText(bean.getBackReason());
            tvOrderdetailRefoundPrice.setText(StringUtils.moneyFen2Yuan(bean.getBackPrice()));
            tvOrderdetailRefoundPrice.append("元");
            tvOrderdetailRefoundQuestion.setText(bean.getBackRemark());


            ///
            if (bean.getIsBack() == 1) {
                tv_orderdetail_refound_reason_pre.setText("退货");
                tv_orderdetail_refound_price_pre.setText("退货");
            } else {
                tv_orderdetail_refound_reason_pre.setText("退款");
                tv_orderdetail_refound_price_pre.setText("退款");
            }
            tv_orderdetail_refound_reason_pre.append("原因");
            tv_orderdetail_refound_price_pre.append("金额");
////
            if (bean.getIsCollect() == 0) {
                tvOrderdetailRefoundProve.setVisibility(View.GONE);

                tvOrderdetailRefoundUpProve.setVisibility(View.GONE);
                tvOrderdetailRefoundLogisticsType.setVisibility(View.GONE);
                tvOrderdetailRefoundLogisticsCompany.setVisibility(View.GONE);
                tvOrderdetailRefoundLogisticsCode.setVisibility(View.GONE);
                tv_orderdetail_refound_shopaddress.setVisibility(View.GONE);

                return;
            }
            tvOrderdetailRefoundProve.setVisibility(View.VISIBLE);

            tvOrderdetailRefoundUpProve.setVisibility(View.VISIBLE);
            tvOrderdetailRefoundLogisticsType.setVisibility(View.VISIBLE);
            tvOrderdetailRefoundLogisticsCompany.setVisibility(View.VISIBLE);
            tvOrderdetailRefoundLogisticsCode.setVisibility(View.VISIBLE);
            tv_orderdetail_refound_shopaddress.setVisibility(View.VISIBLE);

///
            tvOrderdetailRefoundProve.setText(bean.getApplyCredentials());

            tvOrderdetailRefoundUpProve.setText(StringUtils.isEmpty(bean.getUploadDocuments()) ? "无" : "有");
            tvOrderdetailRefoundLogisticsType.setText(bean.getBackWay() == 0 ? "快递" : "未知");
            tvOrderdetailRefoundLogisticsCompany.setText(bean.getOrderExpressName());
            tvOrderdetailRefoundLogisticsCode.setText(bean.getOrderExpressNo());
            tv_orderdetail_refound_shopaddress.setText(bean.getStoreAddress());
        }
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            try {
                String methodName = (String) v.getTag();
                Method method = MyOrderDetailActivity.this.getClass().getMethod(methodName, Integer.class);
                method.invoke(MyOrderDetailActivity.this, orderId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public final class Btns {

        View view;
        private TextView[] tvBtns = new TextView[4];

        public Btns(View view) {
            this.view = view;
            view.setVisibility(View.GONE);
            tvBtns[0] = findViewById(R.id.tv_btn1);
            tvBtns[1] = findViewById(R.id.tv_btn2);
            tvBtns[2] = findViewById(R.id.tv_btn3);
            tvBtns[3] = findViewById(R.id.tv_btn4);
            for (int i = 0; i < tvBtns.length; i++) {
                tvBtns[i].setOnClickListener(listener);
            }
        }

        private void setDatas(OrderInfoResponse.DataBean data) {


            switch (orderStateInfo.type_id) {
                case 1: {// 已取消
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 2: {// 待付款
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 3: {// 待收货
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 4: {// 已完成
                    view.setVisibility(View.VISIBLE);
                }
                break;
                case 5: {// 退款
                    view.setVisibility(View.GONE);
                }
                return;
                case 6: {// 退货
                    view.setVisibility(View.GONE);
                }
                return;
            }

            switch (orderStateInfo.id) {
                case 22: {// 退货
                    view.setVisibility(View.GONE);
                }
                return;
                case 32: {// 退货
                    view.setVisibility(View.GONE);
                }
                return;
            }


            int[] btns = orderStateInfo.option_ids;

            int num = 0;
            for (int i = tvBtns.length - 1; i >= 0; i--) {
                int btn_pos = btns.length - (++num);
                if (btn_pos < 0 || btn_pos >= btns.length) {
                    tvBtns[i].setVisibility(View.INVISIBLE);
                    tvBtns[i].setTag(null);
                    tvBtns[i].setEnabled(false);
                    continue;
                }


                OrderOptionInfo optionInfo = orderHelper.getOrderOptionInfo(btns[btn_pos]);

                tvBtns[i].setVisibility(View.VISIBLE);
                tvBtns[i].setTag(optionInfo.method_name);
                tvBtns[i].setEnabled(true);

                tvBtns[i].setText(optionInfo.text);
                tvBtns[i].setTextColor(optionInfo.text_color);
                tvBtns[i].setBackgroundResource(optionInfo.text_bg);

//                if (optionInfo.id == 9||optionInfo.id == 12){
//                    tvBtns[i].setVisibility(View.GONE);
//                }


//            此两种状态的订单的 评价晒单按钮 的展示规则如下

                if (!(orderStateInfo.id == 14 || orderStateInfo.id == 13)) continue;
                if (optionInfo.id != 8) continue;

//            修改评价晒单按钮

                int flag = data.getEvaluateFlag();    // 是否评价 0未评价 1已经评价 2 已评价伊晒单
                if (flag == 2) {
                    tvBtns[i].setVisibility(View.INVISIBLE);
                    tvBtns[i].setTag(null);
                    tvBtns[i].setEnabled(false);
                } else if (flag == 1) {
                    tvBtns[i].setText("晒单");
                }
            }
        }

    }


    @Override
    public void buyAgain(Integer id) {
        presenter.buyAgain(orderInfo.getOrderCode());
    }


    @Override
    public void delOrder(Integer id) {
        YesNoDialog.show(getContext(), "确认删除此订单", 0, position -> {
            presenter.delOrder(id);
        });
    }

    @Override
    public void payOrder(Integer id) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("orderType", 0);
        intent.putExtra("totolPrice", orderInfo.getOrderPrice());
        intent.putExtra("orderCode", orderInfo.getOrderCode());
        startActivity(intent);
    }

    @Override
    public void cancelOrder(Integer id) {
        presenter.cancelOrder(id);
    }

    @Override
    public void confirmOrder(Integer id) {
        presenter.confirmOrder(id);
    }


    @Override
    public void checkLogistics(Integer id) {
        startActivity(new Intent(getContext(), LogisticsDetailActivity.class)
                .putExtra("orderId", String.valueOf(id))
                .putExtra("orderCode", orderInfo.getOrderCode())
        );
    }

    @Override
    public void applyRefundMoney(Integer id) {
//        未收货	11
//        已收货	13
//        已收货	14
        int goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
        switch (orderStateInfo.id) {
            case 11:
                goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
                break;
            case 13:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
            case 14:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
        }

        startActivity(new Intent(getContext(), ReFundOrderSubmitActivity.class)
//                是否可编辑
                        .putExtra("canEdit", true)
//                是否收货
                        .putExtra("goods_type", goods_type)
//                退款退货
                        .putExtra("refund_type", ReFundOrderSubmitActivity.TYPE_REFUND_MONEY)
                        .putExtra("orderId", String.valueOf(id))
        );
    }

    @Override
    public void evaluateOrder(Integer id) {
        Intent intent = new Intent(context, EvaluateOrderSubmitActivity.class)
                .putExtra("orderId", String.valueOf(id));
        startActivityForResult(intent, 1);
    }

    @Override
    public void refundGoodsDetail(Integer id) {
        startActivity(new Intent(context, MyOrderDetailActivity.class)
                .putExtra("orderId", id)
        );
    }

    @Override
    public void subLogisticsInfo(Integer id) {
        startActivity(new Intent(context, SendBackInfoActivity.class)
                .putExtra("orderId", String.valueOf(id))
        );
    }

    @Override
    public void applyRefundGoods(Integer id) {
        //        未收货	11
//        已收货	13
//        已收货	14
        int goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
        switch (orderStateInfo.id) {
            case 11:
                goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
                break;
            case 13:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
            case 14:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
        }
        Intent intent = new Intent(getContext(), ReFundOrderSubmitActivity.class)
//                是否可编辑
                .putExtra("canEdit", true)
//                是否收货
                .putExtra("goods_type", goods_type)
//                退款退货
                .putExtra("refund_type", ReFundOrderSubmitActivity.TYPE_REFUND_GOODS)
                .putExtra("orderId", String.valueOf(id));
        startActivityForResult(intent, 1);
    }

    @Override
    public void refundMoneysDetail(Integer id) {
        startActivity(new Intent(context, MyOrderDetailActivity.class)
                .putExtra("orderId", id)
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isFinish = data.getExtras().getBoolean("isFinish", false);
        if (isFinish) {
            finish();
        }
    }

    @Override
    public void onDelOrder(int id) {
        toastShort("删除成功");
        finish();
    }

    @Override
    public void onBuyAgain() {
        startActivity(new Intent(context, ShoppingCarActivity.class));
    }
}
