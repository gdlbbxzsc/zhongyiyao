package com.pbph.shoppingmall.module.firmorder;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.SubmitOrderRequest;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.model.response.GetDefaultAddressResponse;
import com.pbph.shoppingmall.model.response.GetSubmitPriceResponse;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.model.response.SubmitOrderResponse;
import com.pbph.shoppingmall.model.vo.GoodsVoInfo;
import com.pbph.shoppingmall.module.addressmng.list.AddressMngActivity;
import com.pbph.shoppingmall.module.firmorder.goodslist.GoodsListActivity;
import com.pbph.shoppingmall.module.firmorder.invoice.InvoiceActivity;
import com.pbph.shoppingmall.module.orders.myorders.index.MyOrdersActivity;
import com.pbph.shoppingmall.module.payment.PaymentActivity;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;

import java.util.List;


public class FirmOrderActivity extends BaseActivity<Presenter> implements Contract.View {

    public static final int FROM_SHOPPINGCAR = 0;
    public static final int FROM_OTHER = 1;

    private CommonTitlebar commonTitlebar;
    private LinearLayout lyNameMobile;
    private ConstraintLayout lyInvoice;
    RelativeLayout lyChooseAddress;
    private TextView pay, tvGoodsCount, tvInvoiceContent, tvDefaultAddress, tvAddressMobile, tvAddressName, tvChooseAddress, tvOrderPrice, tvExpressPrice, tvTotolPrice;
    private EditText etCustomerRemark;
    private ImageView ivDefault;

    private LinearLayout ly_goods;

    private ConstraintLayout lyImgs;
    private LinearLayout llImgs;


    GoodsVoInfo goodsVoInfo;
    List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean> goodsList;

    private int addressId = -1;
    private int billId = -1;//发票ID
    private int billOpen = 0;//0：不开，1：开
    private String customerContent = "";

    private int totolPrice;

    int orderType;

    @Override
    protected int layoutResID() {
        return R.layout.activity_firm_order;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void initData() {


        orderType = getIntent().getIntExtra("orderType", FROM_OTHER);

        if (orderType == 0) {
            goodsList = (List<ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean>) MyApplication.getDataMapData(FirmOrderActivity.class.getName());
        } else {
            goodsVoInfo = (GoodsVoInfo) MyApplication.getDataMapData(FirmOrderActivity.class.getName());
        }
    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "确认订单", false);
        commonTitlebar.titlebar_left.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                YesNoDialog.show(context, "确定离开?", 0, position -> finish());
            }
        });

        lyNameMobile = findViewById(R.id.ly_name_mobile);

        ly_goods = findViewById(R.id.ly_goods);

        lyImgs = findViewById(R.id.ly_imgs);
        lyImgs.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                MyApplication.setDataMapData(GoodsListActivity.class.getName(), goodsList);
                startActivity(new Intent(context, GoodsListActivity.class));
            }
        });
        lyChooseAddress = findViewById(R.id.ly_choose_address);
        lyChooseAddress.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                Intent intent = new Intent(context, AddressMngActivity.class);
                intent.putExtra("need_result", true);
                intent.putExtra("service_id", addressId);
                startActivityForResult(intent, 444);
            }
        });
        lyInvoice = findViewById(R.id.ly_invoice);
        lyInvoice.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                Intent intent = new Intent(context, InvoiceActivity.class);
                startActivityForResult(intent, 222);
            }
        });
        pay = findViewById(R.id.pay);
        pay.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                if (addressId == -1 || addressId == 0) {
                    toastShort("请选择地址");
                    return;
                }
                if (billId == -1) {
                    toastShort("请选择发票");
                    return;
                }

                SubmitOrderRequest<SubmitOrderResponse> request = new SubmitOrderRequest<>();

                request.addressId = addressId;
                request.billId = billId;
                request.customerRemark = etCustomerRemark.getText().toString();

                request.customerContent = customerContent;
                request.billOpen = billOpen;

                request.orderType = orderType;

                //        注1：当orderType=0时，为提交订单，ids传购物车id，goodsNum不传
//        注2：当orderType=1时，为立即购买，ids传货品id，goodsNum必传

                if (orderType == 1) {
                    request.ids = String.valueOf(goodsVoInfo.goodsInfoId);
                    request.goodsNum = goodsVoInfo.goodsNum;
                } else {
                    request.ids = appendIds4Shoppingcar();
                }

                presenter.submitOrder(request);

            }
        });
        tvGoodsCount = findViewById(R.id.tv_goods_count);
        tvInvoiceContent = findViewById(R.id.tv_invoice_content);
        tvDefaultAddress = findViewById(R.id.tv_default_address);
        ivDefault = findViewById(R.id.iv_default);
        tvAddressMobile = findViewById(R.id.tv_address_mobile);
        tvAddressName = findViewById(R.id.tv_address_name);
        tvChooseAddress = findViewById(R.id.tv_choose_address);
        etCustomerRemark = findViewById(R.id.et_customer_remark);
        tvOrderPrice = findViewById(R.id.tv_order_price);
        tvExpressPrice = findViewById(R.id.tv_express_price);
        tvTotolPrice = findViewById(R.id.tv_totol_price);
        llImgs = findViewById(R.id.ll_imgs);


        if (orderType == FROM_SHOPPINGCAR) {
            lyImgs.setVisibility(View.VISIBLE);
            ly_goods.setVisibility(View.GONE);
            showGoodsImgs();
            scaleGoodsCounts();
        } else {
            lyImgs.setVisibility(View.GONE);
            ly_goods.setVisibility(View.VISIBLE);
            showGoodsInfo();
            scaleGoodsCount();
        }

        //默认不开发票
        billId = 201;
        billOpen = 0;
        tvInvoiceContent.setText("不开发票");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        YesNoDialog.show(context, "确定离开?", 0, position -> finish());
    }


    //    注1：当orderType=0时，为提交订单，ids传购物车id，goodsNum不传
    String appendIds4Shoppingcar() {
        if (goodsList == null) return null;

        StringBuilder sb = new StringBuilder();
        for (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo : goodsList) {
            sb.append(",");
            sb.append(vo.getPpid());
        }
        if (sb.length() <= 0) return null;

        return sb.substring(1);
    }

    String appendIdNum4Buy() {
        if (goodsVoInfo == null) return null;
        return StringUtils.builderJoin(",", String.valueOf(goodsVoInfo.goodsInfoId), String.valueOf(goodsVoInfo.goodsNum));
    }

    @Override
    public void toPayment(String orderCode, int orderType) {
        /*Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("orderType", orderType);
        intent.putExtra("totolPrice", totolPrice);
        intent.putExtra("orderCode", orderCode);
        startActivity(intent);*/
        startActivity(new Intent(getContext(), MyOrdersActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra("message_type", MyOrdersActivity.MESSAGE_TYPE_ALL));

        finish();
    }

    @Override
    public void setSubmitPrice(GetSubmitPriceResponse.DataBean vo) {
        tvTotolPrice.setText(StringUtils.moneyFen2Yuan(vo.getOrderPrice()));
        tvExpressPrice.setText(StringUtils.moneyFen2Yuan(vo.getExpressPrice()));
        tvOrderPrice.setText(StringUtils.moneyFen2Yuan(vo.getTotolPrice()));
        totolPrice = vo.getTotolPrice();
    }

    @Override
    public void payResult(boolean payResult) {
        if (payResult) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) return;

        resultBill(requestCode, resultCode, data);
        resultAddressSucc(requestCode, resultCode, data);
        resultAddressFail(requestCode, resultCode, data);
    }

    void resultBill(int requestCode, int resultCode, Intent data) {
        if (222 != requestCode) return;
        if (resultCode != 222) return;
        String content = data.getExtras().getString("content");
        customerContent = data.getExtras().getString("customerContent");
        billId = data.getExtras().getInt("billId");
        billOpen = data.getExtras().getInt("billOpen");
        tvInvoiceContent.setText(content);
    }

    void resultAddressSucc(int requestCode, int resultCode, Intent data) {
        if (444 != requestCode) return;
        if (resultCode != RESULT_OK) return;

        GetAddressListResponse.DataBean vo = (GetAddressListResponse.DataBean) data.getExtras().get("result");

        addressId = vo.getPpid();


        tvAddressMobile.setText(vo.getAddressMoblie());
        tvAddressName.setText(vo.getAddressName());

        if (vo.getDefaultVal() == 0) {
            ivDefault.setVisibility(View.GONE);
        } else if (vo.getDefaultVal() == 1) {
            ivDefault.setVisibility(View.VISIBLE);
        }

        tvDefaultAddress.setVisibility(View.VISIBLE);
        lyNameMobile.setVisibility(View.VISIBLE);
        tvChooseAddress.setVisibility(View.GONE);
        tvDefaultAddress.setText(vo.getProvinceName());
        tvDefaultAddress.append(vo.getCityName());
        tvDefaultAddress.append(vo.getDistrictName());
        tvDefaultAddress.append(vo.getAddressDetail());
        presenter.getSubmitPrice(addressId, appendIds4Shoppingcar(), appendIdNum4Buy());
    }

    void resultAddressFail(int requestCode, int resultCode, Intent data) {
        if (444 != requestCode) return;
        if (resultCode != AddressMngActivity.RESULT_CODE_BACK) return;

        ivDefault.setVisibility(View.GONE);
        tvDefaultAddress.setVisibility(View.GONE);
        lyNameMobile.setVisibility(View.GONE);
        tvChooseAddress.setVisibility(View.VISIBLE);
        addressId = -1;
    }

    @Override
    public void setDefaultAddress(GetDefaultAddressResponse.DataBean vo) {

        addressId = vo.getPpid();

        tvAddressMobile.setText(vo.getAddressMoblie());
        tvAddressName.setText(vo.getAddressName());

        if (vo.getDefaultVal() == 0) {
            ivDefault.setVisibility(View.GONE);
            tvDefaultAddress.setVisibility(View.GONE);
            lyNameMobile.setVisibility(View.GONE);
            tvChooseAddress.setVisibility(View.VISIBLE);
        } else if (vo.getDefaultVal() == 1) {
            ivDefault.setVisibility(View.VISIBLE);
            tvDefaultAddress.setVisibility(View.VISIBLE);
            lyNameMobile.setVisibility(View.VISIBLE);
            tvChooseAddress.setVisibility(View.GONE);

            tvDefaultAddress.setText(vo.getProvinceName());
            tvDefaultAddress.append(vo.getCityName());
            tvDefaultAddress.append(vo.getDistrictName());
            tvDefaultAddress.append(vo.getAddressDetail());
        }
        presenter.getSubmitPrice(addressId, appendIds4Shoppingcar(), appendIdNum4Buy());
    }


    void scaleGoodsCounts() {
        int num = 0;
        for (ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean vo : goodsList) {
            num += vo.getGoodsNum();
        }
        tvGoodsCount.setText("共计");
        tvGoodsCount.append(String.valueOf(num));
        tvGoodsCount.append("件");
    }

    void scaleGoodsCount() {
        tvGoodsCount.setText("共计");
        tvGoodsCount.append(String.valueOf(goodsVoInfo.goodsNum));
        tvGoodsCount.append("件");
    }


    void showGoodsImgs() {

        int size = goodsList.size();
        if (size <= 0) return;

        if (size > 4) size = 4;


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        width -= 3 * 10;
        width /= 6;
        int height = width;

        LayoutInflater inflater = LayoutInflater.from(context);


        for (int i = 0; i < size; i++) {
            ImageView iv = (ImageView) inflater.inflate(R.layout.layout_img, null);
            llImgs.addView(iv);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
            params.width = width;
            params.height = height;
            params.setMargins(i == 0 ? 0 : 10, 0, 0, 0);

            Glide.with(context).load(goodsList.get(i).getGoodsInfo().getGoodsInfoImgUrl()).asBitmap().centerCrop()
//                    .error(R.mipmap.ic_launcher)
//                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv);
        }
    }

    void showGoodsInfo() {


//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        width -= (4 - 1) * 10;
//        width /= 4;
//        int height = width * 3 / 4;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_goodsinfo, null);
        ly_goods.addView(view);

        ImageView ivGoodsImg = (ImageView) view.findViewById(R.id.iv_goods_img);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvParram = (TextView) view.findViewById(R.id.tv_parram);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView tvNum = (TextView) view.findViewById(R.id.tv_num);


        Glide.with(context).load(goodsVoInfo.goodsImage)
//                .placeholder(R.mipmap.banner_zw)
//                .error(R.mipmap.banner_zw)
                .into(ivGoodsImg);

        tvTitle.setText(goodsVoInfo.goodsName);
        tvParram.setText(goodsVoInfo.goodsParInfo);

        price.setText(StringUtils.moneyFen2Yuan(goodsVoInfo.goodsPrice));

        tvNum.setText(String.valueOf(goodsVoInfo.goodsNum));
    }

}



