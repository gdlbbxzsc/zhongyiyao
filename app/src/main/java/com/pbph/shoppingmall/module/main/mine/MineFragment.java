package com.pbph.shoppingmall.module.main.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.GetOrderNumberResponse;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.module.account.usersetting.UserSettingActivity;
import com.pbph.shoppingmall.module.addressmng.list.AddressMngActivity;
import com.pbph.shoppingmall.module.bill.list.BillMngActivity;
import com.pbph.shoppingmall.module.browserecords.BrowseRecordsActivity;
import com.pbph.shoppingmall.module.collect.CollectIndexActivity;
import com.pbph.shoppingmall.module.coupon.mine.CouponMineActivity;
import com.pbph.shoppingmall.module.message.type.MessageTypeActivity;
import com.pbph.shoppingmall.module.myscore.MyScoreActivity;
import com.pbph.shoppingmall.module.orders.evaluateorders.index.EvaluateOrdersActivity;
import com.pbph.shoppingmall.module.orders.myorders.index.MyOrdersActivity;
import com.pbph.shoppingmall.module.orders.refundorders.index.ReFundOrdersActivity;
import com.pbph.shoppingmall.utils.GlideCatchUtil;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.toast.BlackToast;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

/**
 * Created by Administrator on 2018/1/19.
 */

public class MineFragment extends BaseFragmentV4<Presenter> implements Contract.View {

    ImageButton setting;
    ImageButton message;


    ImageView iv_mine_photo;
    //登陆后显示界面
    View include_mine_state_login;
    private TextView tvMineName;
    private ImageView ivLevel;
    private TextView tvMineScore;
    private TextView tvMineSign;
    SignSuccPop pop;

    //    未登录
    View include_mine_state_logout;


    //    收藏数 浏览记录
    private LinearLayout llMineColGoods;
    private LinearLayout llMineColShops;
    private LinearLayout llMineRecords;
    private TextView tvMineColGoods;
    private TextView tvMineColShops;
    private TextView tvMineRecords;

    private TextView tv_all_orders;


    private OrderCard orders1;
    private OrderCard orders2;
    private OrderCard evaluate;
    private OrderCard refund;

    //优惠券
    TextView tv_couponmine;
    //客服
    TextView tv_service;
    //收货地址管理
    private LinearLayout ll_address_mng;
    //发票信息
    private LinearLayout ll_bill_mng;
    //清除缓存
    private LinearLayout ll_clear_chache;
    private TextView tv_chache;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        tv_chache = view.findViewById(R.id.tv_chache);
        tv_chache.setText(GlideCatchUtil.getCacheSize(context));
        ll_address_mng = view.findViewById(R.id.ll_address_mng);
        ll_address_mng.setOnClickListener(listener);
        ll_bill_mng = view.findViewById(R.id.ll_bill_mng);
        ll_bill_mng.setOnClickListener(listener);
        ll_clear_chache = view.findViewById(R.id.ll_clear_chache);
        ll_clear_chache.setOnClickListener(listener);

        message = view.findViewById(R.id.titlebar_right);
        message.setOnClickListener(listener);
        setting = view.findViewById(R.id.titlebar_right2);
        setting.setOnClickListener(listener);


        iv_mine_photo = view.findViewById(R.id.iv_mine_photo);
        iv_mine_photo.setOnClickListener(listener);

        include_mine_state_login = view.findViewById(R.id.include_mine_state_login);
        tvMineName = include_mine_state_login.findViewById(R.id.tv_mine_name);
        tvMineName.setOnClickListener(listener);
        ivLevel = include_mine_state_login.findViewById(R.id.iv_level);

        include_mine_state_login.findViewById(R.id.tv_mine_score_pre).setOnClickListener(listener);
        tvMineScore = include_mine_state_login.findViewById(R.id.tv_mine_score);
        tvMineScore.setOnClickListener(listener);

        pop = new SignSuccPop(getContext(), view);
        tvMineSign = include_mine_state_login.findViewById(R.id.tv_mine_sign);
        tvMineSign.setOnClickListener(listener);

        include_mine_state_logout = view.findViewById(R.id.include_mine_state_logout);
        include_mine_state_logout.setOnClickListener(listener);

        llMineColGoods = view.findViewById(R.id.ll_mine_col_goods);
        llMineColShops = view.findViewById(R.id.ll_mine_col_shops);
        llMineRecords = view.findViewById(R.id.ll_mine_records);
        llMineColGoods.setOnClickListener(listener);
        llMineColShops.setOnClickListener(listener);
        llMineRecords.setOnClickListener(listener);
        tvMineColGoods = view.findViewById(R.id.tv_mine_col_goods);
        tvMineColShops = view.findViewById(R.id.tv_mine_col_shops);
        tvMineRecords = view.findViewById(R.id.tv_mine_records);


        tv_all_orders = view.findViewById(R.id.tv_all_orders);
        tv_all_orders.setOnClickListener(listener);


        orders1 = new OrderCard();
        orders1.rlOrders = (RelativeLayout) view.findViewById(R.id.rl_orders1);
        orders1.ivOrders = (ImageView) view.findViewById(R.id.iv_orders1);
        orders1.tvOrders = (TextView) view.findViewById(R.id.tv_orders1);
        orders1.tvOrdersNum = (TextView) view.findViewById(R.id.tv_orders_num1);
        orders1.rlOrders.setOnClickListener(listener);

        orders2 = new OrderCard();
        orders2.rlOrders = (RelativeLayout) view.findViewById(R.id.rl_orders2);
        orders2.ivOrders = (ImageView) view.findViewById(R.id.iv_orders2);
        orders2.tvOrders = (TextView) view.findViewById(R.id.tv_orders2);
        orders2.tvOrdersNum = (TextView) view.findViewById(R.id.tv_orders_num2);
        orders2.rlOrders.setOnClickListener(listener);


        evaluate = new OrderCard();
        evaluate.rlOrders = (RelativeLayout) view.findViewById(R.id.rl_orders3);
        evaluate.ivOrders = (ImageView) view.findViewById(R.id.iv_orders3);
        evaluate.tvOrders = (TextView) view.findViewById(R.id.tv_orders3);
        evaluate.tvOrdersNum = (TextView) view.findViewById(R.id.tv_orders_num3);
        evaluate.rlOrders.setOnClickListener(listener);

        refund = new OrderCard();
        refund.rlOrders = (RelativeLayout) view.findViewById(R.id.rl_orders4);
        refund.ivOrders = (ImageView) view.findViewById(R.id.iv_orders4);
        refund.tvOrders = (TextView) view.findViewById(R.id.tv_orders4);
        refund.tvOrdersNum = (TextView) view.findViewById(R.id.tv_orders_num4);
        refund.rlOrders.setOnClickListener(listener);


        tv_couponmine = view.findViewById(R.id.tv_couponmine);
        tv_couponmine.setOnClickListener(listener);
        tv_service = view.findViewById(R.id.tv_service);
        tv_service.setOnClickListener(listener);

    }

    @Override
    public void onResume() {
        super.onResume();

        orders1.setNum(0);
        orders2.setNum(0);
        evaluate.setNum(0);
        refund.setNum(0);

        changViewIsLogin();

        //未登录不请求个人数据
        if (StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            iv_mine_photo.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.morentouxiang));
            tvMineColGoods.setText("0");
            tvMineColShops.setText("0");
            tvMineRecords.setText("0");
            return;
        }
        presenter.getMyCustomer();
        presenter.getSysSwitch();
        presenter.getMyCollectionAndBrowseRequest();
        presenter.getOrderNumber();
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.titlebar_right: {//消息
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), MessageTypeActivity.class).putExtra("message_type", MessageTypeActivity.MESSAGE_TYPE_WULIU));
//                    startActivity(new Intent(getContext(), MessageIndexActivity.class));
                }
                break;
                case R.id.titlebar_right2: {// 设置
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), UserSettingActivity.class));
                }
                break;
                case R.id.iv_mine_photo: {// 头像-》设置
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), UserSettingActivity.class));
                }
                break;
                case R.id.tv_mine_name: {// 名称-》设置
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), UserSettingActivity.class));
                }
                break;
                case R.id.include_mine_state_logout: {//点击登录
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;

                case R.id.tv_mine_score_pre: {//我的积分
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), MyScoreActivity.class)
                            .putExtra("score", tvMineScore.getText().toString().trim())
                    );
                }
                break;
                case R.id.tv_mine_score: {//我的积分
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), MyScoreActivity.class)
                            .putExtra("score", tvMineScore.getText().toString().trim())
                    );
                }
                break;
                case R.id.tv_mine_sign: {//签到
                    presenter.sign();
                }
                break;

                case R.id.ll_mine_col_goods: {//收藏 商品
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), CollectIndexActivity.class)
                            .putExtra("collect_type", CollectIndexActivity.COLLECT_TYPE_GOODS));
                }
                break;
                case R.id.ll_mine_col_shops: {//收藏店铺
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), CollectIndexActivity.class)
                            .putExtra("collect_type", CollectIndexActivity.COLLECT_TYPE_SHOPS));
                }
                break;
                case R.id.ll_mine_records: {//浏览记录
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), BrowseRecordsActivity.class));
                }
                break;
                case R.id.tv_all_orders: {//全部订单0
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), MyOrdersActivity.class)
                            .putExtra("message_type", MyOrdersActivity.MESSAGE_TYPE_ALL));

                }
                break;
                case R.id.rl_orders1: {//待付款1
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), MyOrdersActivity.class)
                            .putExtra("message_type", MyOrdersActivity.MESSAGE_TYPE_NOPAY));
                }
                break;
                case R.id.rl_orders2: {//待收货2
                    startActivity(new Intent(getContext(), MyOrdersActivity.class)
                            .putExtra("message_type", MyOrdersActivity.MESSAGE_TYPE_NOTAKE));
                }
                break;
                case R.id.rl_orders3: {//待评价
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), EvaluateOrdersActivity.class));
                }
                break;
                case R.id.rl_orders4: {//退款
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), ReFundOrdersActivity.class));
                }
                break;


                case R.id.tv_couponmine: {//优惠券
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), CouponMineActivity.class));
                }
                break;
                case R.id.tv_service: {//客服
//                    startActivity(new Intent(getContext(), CouponMineActivity.class));
                }
                break;
                case R.id.ll_address_mng://收货地址管理
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), AddressMngActivity.class)
                            .putExtra("need_result", false));
                    break;

                case R.id.ll_bill_mng://发票
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), BillMngActivity.class)
                            .putExtra("need_result", false));
                    break;

                case R.id.ll_clear_chache://清除缓存
                    if (!isLogin()) return;
                    YesNoDialog.show(getContext(), "是否清除本地缓存?", 0, position -> {
                        GlideCatchUtil.clearCacheDisk(getContext());
                        tv_chache.setText("0Byte");
                    });

                    break;

            }
        }
    };

    @Override
    public void changViewIsLogin() {
        if (!StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            include_mine_state_login.setVisibility(View.VISIBLE);
            include_mine_state_logout.setVisibility(View.INVISIBLE);
        } else {
            include_mine_state_logout.setVisibility(View.VISIBLE);
            include_mine_state_login.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void signSucc() {
        pop.show();
    }

    @Override
    public void signFail() {
        BlackToast.showToast(getContext(), "签到失败");
    }

    @Override
    public void changeSignType(boolean type) {
        tvMineSign.setVisibility(type ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setMyCollectionAndBrowse(int browseRecordCount, int goodsCount, int storeCount) {
        tvMineColGoods.setText(String.valueOf(goodsCount));
        tvMineColShops.setText(String.valueOf(storeCount));
        tvMineRecords.setText(String.valueOf(browseRecordCount));
    }

    @Override
    public void setMyCustomer(GetMyCustomerResponse.DataBean dataBean) {
        GetMyCustomerResponse.DataBean.CustomerBean customerBean = dataBean.getCustomer();

//        Glide.with(this).load(customerBean.getCustomerImg()).error(R.drawable.morentouxiang)
//                .placeholder(R.drawable.morentouxiang).into(iv_mine_photo);
        setRoundImg(customerBean.getCustomerImg(), iv_mine_photo, R.drawable.morentouxiang);

        if (!StringUtils.isEmpty(customerBean.getCustomerNickname())) {
            tvMineName.setText(customerBean.getCustomerNickname());
        } else if (!StringUtils.isEmpty(customerBean.getCustomerUsername())) {
            tvMineName.setText(customerBean.getCustomerUsername());
        } else {
            tvMineName.setText(customerBean.getMobile());
        }

        tvMineScore.setText(String.valueOf(dataBean.getCustomerInfo().getCustomerPointSum()));

        Glide.with(this).load(dataBean.getPointLevelNameImg()).error(R.drawable.huiyuan)
                .placeholder(R.drawable.huiyuan).into(ivLevel);
    }

    @Override
    public void setOrderNumber(GetOrderNumberResponse.DataBean bean) {
        orders1.setNum(bean.getNotPayNumber());
        orders2.setNum(bean.getNotCollectGoodsNumber());
        evaluate.setNum(bean.getNotEvaluateNumber());
        refund.setNum(bean.getRetreatNumber());
    }

    private boolean isLogin() {
        if (StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return false;
        }
        return true;
    }

    private void setRoundImg(String url, ImageView imageView, int defaultImg) {
        Glide.with(this).load(url)
                .asBitmap()
//                .centerCrop()
                .error(defaultImg)
                .placeholder(defaultImg)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    public class OrderCard {
        public RelativeLayout rlOrders;
        public ImageView ivOrders;
        public TextView tvOrders;
        public TextView tvOrdersNum;

        public void setNum(int num) {
            if (num <= 0) {
                tvOrdersNum.setVisibility(View.GONE);
                return;
            }
            tvOrdersNum.setVisibility(View.VISIBLE);
            tvOrdersNum.setText(String.valueOf(num));
        }
    }
}
