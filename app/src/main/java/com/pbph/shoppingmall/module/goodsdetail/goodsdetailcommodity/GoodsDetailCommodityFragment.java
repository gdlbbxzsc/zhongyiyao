package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.model.response.GetPitchOnSpecResponse;
import com.pbph.shoppingmall.model.response.GetProductCommentResponse;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;
import com.pbph.shoppingmall.model.vo.GoodsVoInfo;
import com.pbph.shoppingmall.module.firmorder.FirmOrderActivity;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity.choice.ChoicePopup;
import com.pbph.shoppingmall.module.lookgoodsbigpri.LookGoodsBigPriActivity;
import com.pbph.shoppingmall.module.orders.evaluateorders.evaluatedetail
        .GoodsEvaluateDetailActivity;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.module.shoppingcar.ShoppingCarActivity;
import com.pbph.shoppingmall.module.similarity.SimilarityActivity;
import com.pbph.shoppingmall.myview.MyRecyclerviewView;
import com.pbph.shoppingmall.myview.ScrollLinearLayoutManager;
import com.pbph.shoppingmall.utils.GlideRoundTransform;
import com.utils.StringUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.GlideImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class GoodsDetailCommodityFragment extends BaseFragmentV4<Presenter> implements Contract
        .View, View.OnClickListener, GoodsDetailEvaluateAdapter.GoToEvaluateDetail {
    private int goodsInfoId;
    private MyRecyclerviewView recycler_tuijian;
    private MyRecyclerviewView recycler_goods_evaluates;
    private TextView rv_all_evaluate;//查看全部评价
    private TextView tv_look_more_recommend;//查看更多为你推荐

    private GoodsDetailEvaluateAdapter goodsDetailEvaluateAdapter;
    private GoodsDetailRecommendAdapter goodsDetailRecommendAdapter;
    private ChoicePopup choicePopup;

    ArrayList<String> imgs = new ArrayList<>();

    private View goodsInfoHeardView;
    private TextView tv_shop_detail_name;//货品名称
    private TextView tv_shop_detail_info;//货品副名称
    private TextView tv_goods_price;//货品单价
    private CheckBox ckb_follow;//关注商品
    private Banner shop_detail_banner;
    private View surplusTimeView;
    private TextView tv_goods_evaluate_num;//商品评价条数
    private TextView tv_praise;//好评度
    private TagFlowLayout id_flowlayout;
    private String goodsImage = "";
    private LinearLayout linear_evaluate;

    private View goodsInfoMoreView;
    private TextView tv_goods_coupon;//优惠券
    private TextView tv_goods_promotion;//促销
    private TextView tv_goods_choice;//选择商品类型

    private View goodsInfoFootView;
    private TextView tv_contact_sell;//联系卖家
    private TextView tv_goto_shop;//去店铺
    private TextView tv_goto_shop_car;//去购物车
    private TextView tv_immediately_go_buy;//立即购买
    private TextView tv_add_shop_car;//添加购物车

    private View shopInfoView;
    private ImageView iv_shop_logo;//商铺logo
    private TextView tv_shop_name;//商铺名称
    private TextView tv_collection_num;//收藏数量
    private TextView tv_all_shopping;//全部商品数量
    private TextView tv_comprehensive_evaluate;//综合评分
    private CheckBox chb_soucang_shop;//收藏店铺
    private TextView tv_go_shop;//进入店铺
    private int storeId;//店铺id
    int goodNum = 1;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_goods_detail_commodity;
    }

    @Override
    public void initView(View view) {
        recycler_tuijian = view.findViewById(R.id.recycler_tuijian);
        recycler_goods_evaluates = view.findViewById(R.id.recycler_goods_evaluates);
        rv_all_evaluate = view.findViewById(R.id.rv_all_evaluate);
        tv_look_more_recommend = view.findViewById(R.id.tv_look_more_recommend);
        tv_goods_evaluate_num = view.findViewById(R.id.tv_goods_evaluate_num);
        tv_praise = view.findViewById(R.id.tv_praise);
        linear_evaluate = view.findViewById(R.id.linear_evaluate);
        linear_evaluate.setOnClickListener(this);
        tv_look_more_recommend.setOnClickListener(this);
        rv_all_evaluate.setOnClickListener(this);

        goodsInfoHeardView = view.findViewById(R.id.include_goods_info_detail_view);
        initGoodsInfoHeardView(goodsInfoHeardView);
        goodsInfoMoreView = view.findViewById(R.id.include_goods_info_more);
        initGoodsInfoMoreView(goodsInfoMoreView);
        goodsInfoFootView = view.findViewById(R.id.goods_info_foot_view);
        initGoodsInfoFootView(goodsInfoFootView);
        shopInfoView = view.findViewById(R.id.include_shop_info_view);
        initShopInfoView(shopInfoView);
        initData();

    }

    private void initGoodsInfoHeardView(View view) {
        shop_detail_banner = view.findViewById(R.id.shop_detail_banner);
        tv_shop_detail_name = view.findViewById(R.id.tv_shop_detail_name);
        tv_shop_detail_info = view.findViewById(R.id.tv_shop_detail_info);
        tv_goods_price = view.findViewById(R.id.tv_goods_price);
        shop_detail_banner.setOnBannerListener(position -> {
            Intent intent = new Intent(context, LookGoodsBigPriActivity.class);
            intent.putExtra("pos", position);
            intent.putStringArrayListExtra("imgs", imgs);
            startActivity(intent);
        });
        ckb_follow = view.findViewById(R.id.ckb_follow);
        ckb_follow.setEnabled(false);
        ckb_follow.setOnClickListener(v -> {
            if (MyApplication.checkLogin(context)) {
                presenter.saveGoodsFollow(goodsInfoId,ckb_follow.isChecked());
            }

        });

        surplusTimeView = view.findViewById(R.id.include_surplus_time_view);
        id_flowlayout = view.findViewById(R.id.id_flowlayout);

    }


    private void initGoodsInfoMoreView(View view) {
        tv_goods_coupon = view.findViewById(R.id.tv_goods_coupon);
        tv_goods_promotion = view.findViewById(R.id.tv_goods_promotion);
        tv_goods_choice = view.findViewById(R.id.tv_goods_choice);
        tv_goods_coupon.setOnClickListener(this);
        tv_goods_promotion.setOnClickListener(this);
        tv_goods_choice.setOnClickListener(this);
    }

    private void initGoodsInfoFootView(View view) {
        tv_contact_sell = view.findViewById(R.id.tv_contact_sell);
        tv_goto_shop = view.findViewById(R.id.tv_goto_shop);
        tv_goto_shop_car = view.findViewById(R.id.tv_goto_shop_car);
        tv_immediately_go_buy = view.findViewById(R.id.tv_immediately_go_buy);
        tv_add_shop_car = view.findViewById(R.id.tv_add_shop_car);
        tv_contact_sell.setOnClickListener(this);
        tv_goto_shop.setOnClickListener(this);
        tv_goto_shop_car.setOnClickListener(this);
        tv_immediately_go_buy.setOnClickListener(this);
        tv_add_shop_car.setOnClickListener(this);

    }

    private void initShopInfoView(View shopInfoView) {
        iv_shop_logo = shopInfoView.findViewById(R.id.iv_shop_logo);
        tv_shop_name = shopInfoView.findViewById(R.id.tv_shop_name);
        tv_collection_num = shopInfoView.findViewById(R.id.tv_collection_num);
        tv_all_shopping = shopInfoView.findViewById(R.id.tv_all_shopping);
        tv_comprehensive_evaluate = shopInfoView.findViewById(R.id.tv_comprehensive_evaluate);
        chb_soucang_shop = shopInfoView.findViewById(R.id.chb_soucang_shop);
        chb_soucang_shop.setEnabled(false);
        tv_go_shop = shopInfoView.findViewById(R.id.tv_go_shop);
        tv_go_shop.setOnClickListener(this);
        chb_soucang_shop.setOnClickListener(v -> {
            if (MyApplication.checkLogin(context)) {
                presenter.saveThirdStoreCollect(storeId);
            }

        });

    }

    private void initData() {

        GoodsDetailItemDecoration goodsDetailItemDecoration = new GoodsDetailItemDecoration
                (context);
        goodsDetailRecommendAdapter = new GoodsDetailRecommendAdapter(context);
        recycler_tuijian.setAdapter(goodsDetailRecommendAdapter);
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager
                (context, 2);
        scrollLinearLayoutManager.setScrollEnabled(false);
        recycler_tuijian.setLayoutManager(scrollLinearLayoutManager);
        recycler_tuijian.addItemDecoration(goodsDetailItemDecoration);
        recycler_tuijian.setAdapter(goodsDetailRecommendAdapter);

        goodsDetailEvaluateAdapter = new GoodsDetailEvaluateAdapter(context);
        goodsDetailEvaluateAdapter.setGoToEvaluateDetail(this);
        //创建LinearLayoutManager 对象
        ScrollLinearLayoutManager scrollLinearLayoutManager1 = new ScrollLinearLayoutManager
                (context, 1);
        scrollLinearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        scrollLinearLayoutManager1.setScrollEnabled(false);
        recycler_goods_evaluates.setLayoutManager(scrollLinearLayoutManager1);
        recycler_goods_evaluates.setAdapter(goodsDetailEvaluateAdapter);
    }

    int price;

    /**
     * 设置商品详情
     *
     * @param goodsInfoBean
     */
    @Override
    public void setGoodsInfo(ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                                         goodsInfoBean) {
        goodsImage = goodsInfoBean.getGoodsInfoImgUrl();
        tv_shop_detail_name.setText(goodsInfoBean.getGoodsInfoName());
        tv_shop_detail_info.setText(goodsInfoBean.getGoodsInfoSubtitle());
        goodsInfoId = goodsInfoBean.getPpid();
        price = goodsInfoBean.getGoodsInfoPreferPrice();
        tv_goods_price.setText("¥");
        tv_goods_price.append(StringUtils.moneyFen2Yuan(price));

        if (goodsInfoBean.getIsFollow() == 0) {
            ckb_follow.setChecked(true);
        } else {
            ckb_follow.setChecked(false);
        }
        ckb_follow.setEnabled(true);
    }

    /**
     * 设置商铺
     *
     * @param storeInfoVo
     */
    @Override
    public void setStoreInfoVo(ProductDetailResponse.DataBean.GoodsDetailBean.StoreInfoVoBean
                                           storeInfoVo) {
        Glide.with(this).load(storeInfoVo.getStoreLogo()).transform(new GlideRoundTransform
                (context)).into(iv_shop_logo);
        tv_shop_name.setText(storeInfoVo.getStoreName());
        tv_collection_num.setText(String.valueOf(storeInfoVo.getCollectionNumber()));
        tv_all_shopping.setText(String.valueOf(storeInfoVo.getGoodsInfoCount()));
        tv_comprehensive_evaluate.setText(String.valueOf(storeInfoVo.getOverallMerit()));
        storeId = storeInfoVo.getStoreId();

        if (storeInfoVo.getIsCollection() == 0) {
            chb_soucang_shop.setChecked(true);
        } else {
            chb_soucang_shop.setChecked(false);
        }
        chb_soucang_shop.setEnabled(true);
    }

    @Override
    public void setProductCommentVo(ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                                                .ProductCommentVoBean productCommentVoBean) {
        tv_goods_evaluate_num.setText("商品评价(");
        tv_goods_evaluate_num.append(productCommentVoBean.getCount());
        tv_goods_evaluate_num.append(")");
        tv_praise.setText(String.valueOf(productCommentVoBean.getColligate()));
        tv_praise.append("%");
    }

    @Override
    public void setNpComment(List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
            .GoodsDetailCommentListBean> goodsDetailCommentListBeans) {
        goodsDetailEvaluateAdapter.setNpCommentBean(goodsDetailCommentListBeans);
    }

    /**
     * 七天包邮 集合
     *
     * @param serviceList
     */
    @Override
    public void setServiceList(List<String> serviceList) {
        id_flowlayout.setAdapter(new TagAdapter<String>(serviceList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout
                        .goods_info_textview, id_flowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    public void setGetPitchOnSpec(GetPitchOnSpecResponse.DataBean dataBean) {
        presenter.getHttpData(dataBean.getGoodsInfoId(), UserInfo.getInstance().getCustomerId(), 1);
    }

    @Override
    public void collectionOrUncollection(int num) {
        int nums = Integer.parseInt(tv_collection_num.getText().toString());
        tv_collection_num.setText(String.valueOf(nums + num));
    }

    @Override
    public void setGoodsImgList(List<String> stringList) {
        imgs = (ArrayList<String>) stringList;
        shop_detail_banner.setImages(stringList).setImageLoader(new GlideImageLoader())
                .setDelayTime(1500).start();
    }

    @Override
    public void setSpecifications(List<ProductDetailResponse.DataBean.GoodsDetailBean
            .GoodsInfoBean.GoodsSpecBean> openSpecBeans, String goodsUrl, String goodsPrice,
                                  String goodsCode) {
        choicePopup = new ChoicePopup(context, mContentView, openSpecBeans);
        tv_goods_choice.setText("已选:");
        StringBuilder sb = new StringBuilder();
        if (!openSpecBeans.isEmpty()) {
            for (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean
                    goodsSpecnBean : openSpecBeans) {
                sb.append(",");
                for (ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.GoodsSpecBean
                        .SpecDetailsBean specDetailsBean : goodsSpecnBean.getSpecDetails()) {
                    if (specDetailsBean.getIsPitchOn() == 1) {
                        sb.append(specDetailsBean.getSpecValue());
                    }
                }
            }

            tv_goods_choice.append(sb.toString().subSequence(1, sb.length()));
        }

        goodsImage = goodsUrl;
        choicePopup.setContentData(goodsUrl, goodsPrice, goodsCode);
        choicePopup.setChoiceInfo(new ChoicePopup.ChoiceInfo() {
            @Override
            public void getShopId(String goodsId, String typeId, String goodsName, int goodsNum) {
                tv_goods_choice.setText("已选:");
                tv_goods_choice.append(goodsName);
                goodNum = goodsNum;
            }

            @Override
            public void getGoodsInfoId(String goodsChildId, String typeId, int goodsId) {
                presenter.getPitchOnSpec(goodsId, typeId, goodsChildId);

            }
        });
    }


    /*
    查看评论详情
     */
    @Override
    public void setPos(ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                                   .GoodsDetailCommentListBean goodsDetailCommentListBean) {
        GetProductCommentResponse.DataBean.CommentPageBeanBean.ListBean listBean = new
                GetProductCommentResponse.DataBean.CommentPageBeanBean.ListBean();
        listBean.setCommentContent(goodsDetailCommentListBean.getCommentContent());
        listBean.setCommentScore(goodsDetailCommentListBean.getCommentScore());
        listBean.setStoreName(goodsDetailCommentListBean.getStoreName());
        listBean.setShareImgList(goodsDetailCommentListBean.getShareImgList());
        listBean.setPublishTime(goodsDetailCommentListBean.getPublishTime());
        listBean.setProductDescription(goodsDetailCommentListBean.getProductDescription());
        listBean.setCustomerImg(goodsDetailCommentListBean.getCustomerImg());
        listBean.setCommentId(goodsDetailCommentListBean.getCommentId());
        listBean.setCustomerName(goodsDetailCommentListBean.getCustomerName());
        listBean.setGoodsInfoName(goodsDetailCommentListBean.getGoodsInfoName());
        Intent intent = new Intent(context, GoodsEvaluateDetailActivity.class);
        intent.putExtra("evaluateDetail", listBean);
        context.startActivity(intent);

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        goodsInfoId = ((GoodsDetailActivity) getActivity()).goodsInfoId;

        presenter.getHttpData(goodsInfoId, UserInfo.getInstance().getCustomerId(), 1);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_go_shop: {
                Intent intent = new Intent(context, ShopActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("storeId", storeId);
                startActivity(intent);
            }
            break;

            case R.id.linear_evaluate: {//评价
                ((GoodsDetailActivity) getActivity()).setViewPagerPos(2);
            }

            case R.id.rv_all_evaluate: {//评价
                ((GoodsDetailActivity) getActivity()).setViewPagerPos(2);
            }
            break;
            case R.id.tv_goods_coupon: {//优惠券
                presenter.getCouponOfGoodsDetail(goodsInfoId, UserInfo.getInstance()
                        .getCustomerId());
            }

            break;
            case R.id.tv_goods_promotion: {
                presenter.getMarketingOfGoodsDetail(goodsInfoId, UserInfo.getInstance()
                        .getCustomerId());
            }
            break;

            case R.id.tv_goods_choice: {//选择商品类型
                if (choicePopup == null) return;
                choicePopup.show();
            }

            break;

            case R.id.tv_look_more_recommend: {
                startActivity(new Intent(context, SimilarityActivity.class));
            }

            break;

            case R.id.tv_add_shop_car: {//添加购物车
                if (!MyApplication.checkLogin(context)) return;
                if (goodNum == 0) {
                    Toast.makeText(context, "请添加数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.saveShoppingCart(goodsInfoId, goodNum, 1, "1");
            }

            break;
            case R.id.tv_goto_shop: {//去店铺
                Intent intent = new Intent(context, ShopActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("storeId", storeId);
                startActivity(intent);
            }

            break;
            case R.id.tv_goto_shop_car: {//去购物车
                context.startActivity(new Intent(context, ShoppingCarActivity.class));

            }

            break;
            case R.id.tv_immediately_go_buy: {//立即购买

                if (!MyApplication.checkLogin(context)) return;
                if (choicePopup == null) return;
                if (tv_goods_choice.getText().toString().equals("已选")) {
                    choicePopup.show();
                    return;
                }
                GoodsVoInfo goodsVoInfo = new GoodsVoInfo();
                goodsVoInfo.goodsNum = goodNum;
                goodsVoInfo.goodsInfoId = goodsInfoId;
                goodsVoInfo.goodsName = tv_shop_detail_name.getText().toString();
                goodsVoInfo.goodsPrice = price;
                goodsVoInfo.goodsImage = goodsImage;
                goodsVoInfo.goodsParInfo = tv_goods_choice.getText().toString().trim();
                MyApplication.setDataMapData(FirmOrderActivity.class.getName(), goodsVoInfo);
                startActivity(new Intent(context, FirmOrderActivity.class).putExtra("orderType",
                        FirmOrderActivity.FROM_OTHER));
            }
            break;
        }
    }

}
