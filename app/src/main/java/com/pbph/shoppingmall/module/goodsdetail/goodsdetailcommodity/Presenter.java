package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity;


import android.widget.Toast;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.model.message.GoodsDetailDescHtmlMsg;
import com.pbph.shoppingmall.model.request.GetCouponOfGoodsDetailRequest;
import com.pbph.shoppingmall.model.request.GetMarketingOfGoodsDetailRequest;
import com.pbph.shoppingmall.model.request.GetPitchOnSpecRequest;
import com.pbph.shoppingmall.model.request.ProductDetailRequest;
import com.pbph.shoppingmall.model.request.SaveGoodsFollowRequest;
import com.pbph.shoppingmall.model.request.SaveShoppingCartRequest;
import com.pbph.shoppingmall.model.request.SaveThirdStoreCollectRequest;
import com.pbph.shoppingmall.model.response.GetCouponOfGoodsDetailResponse;
import com.pbph.shoppingmall.model.response.GetMarketingOfGoodsDetailResponse;
import com.pbph.shoppingmall.model.response.GetPitchOnSpecResponse;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveShoppingCartResponse;
import com.pbph.shoppingmall.model.response.SaveThirdStoreCollectResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract
        .Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情

    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }

    /**
     * @param productId
     * @param customerId
     * @param browerMethod
     */
    @Override
    public void getHttpData(int productId, Integer customerId, int browerMethod) {
        WaitUI.Show(getBaseView().getContext());
        ProductDetailRequest<ProductDetailResponse> productDetailRequest = new
                ProductDetailRequest<>();
        productDetailRequest.browerMethod = browerMethod;
        productDetailRequest.customerId = customerId;
        productDetailRequest.goodsInfoId = productId;
        productDetailRequest.request().subscribe(new LogoutConsumer<ProductDetailResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(ProductDetailResponse vo) throws Exception {
                WaitUI.Cancel();
                ProductDetailResponse.DataBean.GoodsDetailBean.StoreInfoVoBean storeInfoVoBean =
                        vo.getData().getGoodsDetail().getStoreInfoVo();
                ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean.ProductCommentVoBean
                        productCommentVoBean = vo.getData().getGoodsDetail().getGoodsInfo()
                        .getProductCommentVo();
                ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean goodsInfoBean = vo
                        .getData().getGoodsDetail().getGoodsInfo();
                List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                        .GoodsDetailCommentListBean> npCommentBean = vo.getData().getGoodsDetail
                        ().getGoodsInfo().getGoodsDetailCommentList();
                getBaseView().setSpecifications(vo.getData().getGoodsDetail().getGoodsInfo()
                        .getGoodsSpec(), goodsInfoBean.getGoodsInfoImgUrl(), String.valueOf
                        (goodsInfoBean.getGoodsInfoPreferPrice()), goodsInfoBean
                        .getGoodsInfoItemNo());
                getBaseView().setGoodsInfo(goodsInfoBean);
                getBaseView().setStoreInfoVo(storeInfoVoBean);
                getBaseView().setProductCommentVo(productCommentVoBean);
                getBaseView().setGoodsImgList(vo.getData().getGoodsDetail().getGoodsInfo()
                        .getGoodsImageList());
                getBaseView().setNpComment(npCommentBean);
                getBaseView().setServiceList(vo.getData().getGoodsDetail().getServiceList());
                GoodsDetailDescHtmlMsg goodsDetailDescHtmlMsg = new GoodsDetailDescHtmlMsg();
                goodsDetailDescHtmlMsg.goodsDetailDescHtml = vo.getData().getGoodsDetail()
                        .getGoodsInfo().getNpGoods().getMobileDesc();
                goodsDetailDescHtmlMsg.mobileDescHtml = vo.getData().getGoodsDetail()
                        .getGoodsInfo().getPackagSale();
                RxBusF.post0(goodsDetailDescHtmlMsg);

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                WaitUI.Cancel();
            }
        });

    }


    @Override
    public void saveShoppingCart(int goodsInfoId, int goodsNum, int shopType, String clientType) {

        SaveShoppingCartRequest<SaveShoppingCartResponse> saveShoppingCartRequest = new
                SaveShoppingCartRequest<>();
        saveShoppingCartRequest.customerId = UserInfo.getInstance().getCustomerId();
        saveShoppingCartRequest.goodsInfoId = goodsInfoId;
        saveShoppingCartRequest.goodsNum = goodsNum;
        saveShoppingCartRequest.shopType = shopType;
//        saveShoppingCartRequest.mobile = UserInfo.getInstance().getMobile();

        saveShoppingCartRequest.request().subscribe(new LogoutConsumer<SaveShoppingCartResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(SaveShoppingCartResponse vo) throws Exception {
                Toast.makeText(getBaseView().getContext(), vo.getMsg(), Toast.LENGTH_SHORT).show();

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {

            }
        });
    }


    @Override
    public void saveGoodsFollow(int productIds,boolean b) {
        SaveGoodsFollowRequest<ResultResponse> saveGoodsFollowRequest = new
                SaveGoodsFollowRequest<>();
        saveGoodsFollowRequest.customerId = UserInfo.getInstance().getCustomerId();
        saveGoodsFollowRequest.productIds = String.valueOf(productIds);
        saveGoodsFollowRequest.request().subscribe(new LogoutConsumer<ResultResponse>(getBaseView
                ().getContext()) {
            @Override
            public void onDo(ResultResponse vo) throws Exception {
              if (b==true){
                  Toast.makeText(getContext(), "取消关注", Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(getContext(), "关注成功", Toast.LENGTH_SHORT).show();
              }
            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

    }


    @Override
    public void saveThirdStoreCollect(int shopId) {
        SaveThirdStoreCollectRequest<SaveThirdStoreCollectResponse> saveThirdStoreCollectRequest
                = new SaveThirdStoreCollectRequest<>();
        saveThirdStoreCollectRequest.customerId = UserInfo.getInstance().getCustomerId();
        saveThirdStoreCollectRequest.storeIds = String.valueOf(shopId);
        saveThirdStoreCollectRequest.token = UserInfo.getInstance().getToken();
        saveThirdStoreCollectRequest.request().subscribe(new LogoutConsumer<SaveThirdStoreCollectResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(SaveThirdStoreCollectResponse vo) throws Exception {
                if (vo.getData().getCollectionVal() == 0) {
                    Toast.makeText(getBaseView().getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                    getBaseView().collectionOrUncollection(1);
                } else {
                    Toast.makeText(getBaseView().getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                    getBaseView().collectionOrUncollection(-1);
                }

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }



    @Override
    public void getCouponOfGoodsDetail(int goodsInfoId, int customerId) {
        GetCouponOfGoodsDetailRequest<GetCouponOfGoodsDetailResponse> getCouponOfGoodsDetailRequest =
                new GetCouponOfGoodsDetailRequest<>();
        getCouponOfGoodsDetailRequest.customerId = customerId;
        getCouponOfGoodsDetailRequest.goodsInfoId = goodsInfoId;
        getCouponOfGoodsDetailRequest.request().subscribe(new LogoutConsumer<GetCouponOfGoodsDetailResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(GetCouponOfGoodsDetailResponse vo) throws Exception {

            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }



    @Override
    public void getMarketingOfGoodsDetail(int goodsInfoId, int customerId) {
        GetMarketingOfGoodsDetailRequest<GetMarketingOfGoodsDetailResponse>
                getMarketingOfGoodsDetailRequest = new GetMarketingOfGoodsDetailRequest<>();
        getMarketingOfGoodsDetailRequest.goodsInfoId = goodsInfoId;
        getMarketingOfGoodsDetailRequest.request().subscribe(new LogoutConsumer<GetMarketingOfGoodsDetailResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(GetMarketingOfGoodsDetailResponse vo) throws Exception {

                Toast.makeText(getContext(), "" + vo.getCode(), Toast.LENGTH_SHORT).show();
            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void getPitchOnSpec(int goodsId, String specIdArr, String specValueIdArr) {
        GetPitchOnSpecRequest<GetPitchOnSpecResponse> getPitchOnSpecRequest = new
                GetPitchOnSpecRequest<>();
        getPitchOnSpecRequest.goodsId = goodsId;
        getPitchOnSpecRequest.specIdArr = specIdArr;
        getPitchOnSpecRequest.specValueIdArr = specValueIdArr;
        getPitchOnSpecRequest.request().subscribe(new LogoutConsumer<GetPitchOnSpecResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(GetPitchOnSpecResponse vo) throws Exception {

                getBaseView().setGetPitchOnSpec(vo.getData());
            }

        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

    }


}
