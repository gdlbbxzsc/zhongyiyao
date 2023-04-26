package com.pbph.shoppingmall.module.goodsdetail.goodsdetailcommodity;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetPitchOnSpecResponse;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        void setGoodsImgList(List<String> i);

        void setSpecifications(List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                .GoodsSpecBean> openSpecBeans, String goodsUrl, String goodsPrice, String goodsCode);

        void setGoodsInfo(ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                                  goodsInfoBean);

        void setStoreInfoVo(ProductDetailResponse.DataBean.GoodsDetailBean.StoreInfoVoBean storeInfoVo);

        void setProductCommentVo(ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                                         .ProductCommentVoBean productCommentVoBean);

        void setNpComment(List<ProductDetailResponse.DataBean.GoodsDetailBean.GoodsInfoBean
                .GoodsDetailCommentListBean> goodsDetailCommentListBeans);

        void setServiceList(List<String> serviceList);

        void setGetPitchOnSpec(GetPitchOnSpecResponse.DataBean dataBean);

        void collectionOrUncollection(int num);

    }

    interface Presenter extends IBasePresenter {
        void getHttpData(int productId, Integer customerId, int browerMethod);

        void saveShoppingCart(int goodsInfoId, int goodsNum, int shopType, String clientType);

        void saveGoodsFollow(int productIds,boolean b); //

        void saveThirdStoreCollect(int shopId);

        void getCouponOfGoodsDetail(int goodsInfoId, int customerId);//获取优惠券

        void getMarketingOfGoodsDetail(int goodsInfoId, int customerId);//根据货品id查询促销活动

        void getPitchOnSpec(int goodsId, String specIdArr, String specValueIdArr);//

    }
}
