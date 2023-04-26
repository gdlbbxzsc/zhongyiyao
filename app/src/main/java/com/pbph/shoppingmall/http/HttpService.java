package com.pbph.shoppingmall.http;


import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.model.response.GetAddressResponse;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;
import com.pbph.shoppingmall.model.response.GetAppDefaultTemplateResponse;
import com.pbph.shoppingmall.model.response.GetApplyCredentialsListResponse;
import com.pbph.shoppingmall.model.response.GetBackOrderReasonListResponse;
import com.pbph.shoppingmall.model.response.GetBillByIdResponse;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.model.response.GetBrowseRecordListResponse;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.model.response.GetCollectionSellerListResponse;
import com.pbph.shoppingmall.model.response.GetCommentDetailResponse;
import com.pbph.shoppingmall.model.response.GetCouponCategoryListResponse;
import com.pbph.shoppingmall.model.response.GetCouponOfGoodsDetailResponse;
import com.pbph.shoppingmall.model.response.GetCustomerFollowListResponse;
import com.pbph.shoppingmall.model.response.GetDefaultAddressResponse;
import com.pbph.shoppingmall.model.response.GetDefaultBillResponse;
import com.pbph.shoppingmall.model.response.GetGoodsCategoryListForFollowResponse;
import com.pbph.shoppingmall.model.response.GetGoodsListResponse;
import com.pbph.shoppingmall.model.response.GetLogisticsInformationResponse;
import com.pbph.shoppingmall.model.response.GetMarketingOfGoodsDetailResponse;
import com.pbph.shoppingmall.model.response.GetMyCollectionAndBrowseResponse;
import com.pbph.shoppingmall.model.response.GetMyCustomerPointListResponse;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.GetMyMessageListResponse;
import com.pbph.shoppingmall.model.response.GetOrderNumberResponse;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.model.response.GetOssTokenResponse;
import com.pbph.shoppingmall.model.response.GetPitchOnSpecResponse;
import com.pbph.shoppingmall.model.response.GetProductCommentResponse;
import com.pbph.shoppingmall.model.response.GetShoppingCartNumberResponse;
import com.pbph.shoppingmall.model.response.GetStoreCategoryByStoreIdResponse;
import com.pbph.shoppingmall.model.response.GetStoreCategoryListForCollectionResponse;
import com.pbph.shoppingmall.model.response.GetStoreDetailResponse;
import com.pbph.shoppingmall.model.response.GetStoreNewGoodsForPageResponse;
import com.pbph.shoppingmall.model.response.GetStorePageFloorResponse;
import com.pbph.shoppingmall.model.response.GetStoreStreetResponse;
import com.pbph.shoppingmall.model.response.GetSubmitPriceResponse;
import com.pbph.shoppingmall.model.response.GetSysSwitchResponse;
import com.pbph.shoppingmall.model.response.InsertBillResponse;
import com.pbph.shoppingmall.model.response.MoblieLoginResponse;
import com.pbph.shoppingmall.model.response.OrderInfoResponse;
import com.pbph.shoppingmall.model.response.OrderStatusResponse;
import com.pbph.shoppingmall.model.response.PayMethodResponse;
import com.pbph.shoppingmall.model.response.PayOrderResponse;
import com.pbph.shoppingmall.model.response.ProductDetailResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveAddressResponse;
import com.pbph.shoppingmall.model.response.SaveShoppingCartResponse;
import com.pbph.shoppingmall.model.response.SaveThirdStoreCollectResponse;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.model.response.SearchStoreResponse;
import com.pbph.shoppingmall.model.response.SelectMyCouponCountResponse;
import com.pbph.shoppingmall.model.response.SelectMyCouponListResponse;
import com.pbph.shoppingmall.model.response.SelectSysAddressListResponse;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;
import com.pbph.shoppingmall.model.response.SubmitOrderResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpService {

    @FormUrlEncoded
    @POST("moblieLogin.htm")
    Observable<MoblieLoginResponse> moblieLogin(@FieldMap Map<String, Object> map);

    /*查询购物车*/
    @FormUrlEncoded
    @POST("getShoppingCart.htm")
    Observable<ShoppingCarResponse> getShoppingCart(@FieldMap Map<String, Object> map);

    /*查询购物车商品数量*/
    @FormUrlEncoded
    @POST("getShoppingCartNumber.htm")
    Observable<GetShoppingCartNumberResponse> getShoppingCartNumber(@FieldMap Map<String, Object> map);

    /**
     * 查询全部支付方式
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("payMethod.htm")
    Observable<PayMethodResponse> payMethod(@FieldMap Map<String, Object> map);

    /**
     * 修改购物车
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("updateShoppingCart.htm")
    Observable<ResultResponse> updateShoppingCart(@FieldMap Map<String, Object> map);

    /**
     * 删除购物车
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("delShoppingCart.htm")
    Observable<ResultResponse> delShoppingCart(@FieldMap Map<String, Object> map);

    /**
     * 查询默认收货地址
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getDefaultAddress.htm")
    Observable<GetDefaultAddressResponse> getDefaultAddress(@FieldMap Map<String, Object> map);

    /**
     * 查找用户默认发票
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getDefaultBill.htm")
    Observable<GetDefaultBillResponse> getDefaultBill(@FieldMap Map<String, Object> map);

    /**
     * 关注/取消关注商品
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("saveGoodsFollow.htm")
    Observable<ResultResponse> saveGoodsFollow(@FieldMap Map<String, Object> map);

    /**
     * 提交订单
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("submitOrder.htm")
    Observable<SubmitOrderResponse> submitOrder(@FieldMap Map<String, Object> map);

    /**
     * 获取结算页价格
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getSubmitPrice.htm")
    Observable<GetSubmitPriceResponse> getSubmitPrice(@FieldMap Map<String, Object> map);

    /**
     * 微信支付
     * 孟庆奎
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("payOrder.htm")
    Observable<PayOrderResponse> payOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getClassify.htm")
    Observable<GetClassifyResponse> getClassify(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("sendSmsValCode.htm")
    Observable<ResultResponse> sendSmsValCode(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("selectMyCouponList.htm")
    Observable<SelectMyCouponListResponse> selectMyCouponList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("deleteCouponNo.htm")
    Observable<ResultResponse> deleteCouponNo(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getGoodsList.htm")
    Observable<GetGoodsListResponse> getGoodsList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getCouponCategoryList.htm")
    Observable<GetCouponCategoryListResponse> getCouponCategoryList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getAllCouponList.htm")
    Observable<GetAllCouponListResponse> getAllCouponList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getSysSwitch.htm")
    Observable<GetSysSwitchResponse> getSysSwitch(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("productDetail.htm")
    Observable<ProductDetailResponse> productDetail(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getMyCustomerPointList.htm")
    Observable<GetMyCustomerPointListResponse> getMyCustomerPointList(@FieldMap Map<String,
            Object> map);

    @FormUrlEncoded
    @POST("psetSign.htm")
    Observable<ResultResponse> psetSign(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getBillList.htm")
    Observable<GetBillListResponse> getBillList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("insertBill.htm")
    Observable<InsertBillResponse> insertBill(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getBillById.htm")
    Observable<GetBillByIdResponse> getBillById(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("updateBill.htm")
    Observable<ResultResponse> updateBill(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("deleteBill.htm")
    Observable<ResultResponse> deleteBill(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("setDefaultBillById.htm")
    Observable<ResultResponse> setDefaultBillById(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("saveShoppingCart.htm")
    Observable<SaveShoppingCartResponse> saveShoppingCart(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getProductComment.htm")
    Observable<GetProductCommentResponse> getProductComment(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getMyCollectionAndBrowse.htm")
    Observable<GetMyCollectionAndBrowseResponse> getMyCollectionAndBrowse(@FieldMap Map<String,
            Object> map);

    @FormUrlEncoded
    @POST("getMyCustomer.htm")
    Observable<GetMyCustomerResponse> getMyCustomer(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getCollectionSellerList.htm")
    Observable<GetCollectionSellerListResponse> getCollectionSellerList(@FieldMap Map<String,
            Object> map);


    @FormUrlEncoded
    @POST("getBrowseRecordList.htm")
    Observable<GetBrowseRecordListResponse> getBrowseRecordList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("delBrowseRecord.htm")
    Observable<ResultResponse> delBrowseRecord(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getAddressList.htm")
    Observable<GetAddressListResponse> getAddressList(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("saveAddress.htm")
    Observable<SaveAddressResponse> saveAddress(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("updateAddress.htm")
    Observable<ResultResponse> updateAddress(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("deleteAddress.htm")
    Observable<ResultResponse> deleteAddress(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getAddress.htm")
    Observable<GetAddressResponse> getAddress(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("setDefaultAddress.htm")
    Observable<ResultResponse> setDefaultAddress(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("updateCustomer.htm")
    Observable<ResultResponse> updateCustomer(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("updateCustomerInfo.htm")
    Observable<ResultResponse> updateCustomerInfo(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("checkCode.htm")
    Observable<ResultResponse> checkCode(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("sendSmsValCodeForBindNewPhone.htm")
    Observable<ResultResponse> sendSmsValCodeForBindNewPhone(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getStoreCategoryListForCollection.htm")
    Observable<GetStoreCategoryListForCollectionResponse> getStoreCategoryListForCollection(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getCustomerFollowList.htm")
    Observable<GetCustomerFollowListResponse> getCustomerFollowList(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getGoodsCategoryListForFollow.htm")
    Observable<GetGoodsCategoryListForFollowResponse> getGoodsCategoryListForFollow(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("saveThirdStoreCollect.htm")
    Observable<SaveThirdStoreCollectResponse> saveThirdStoreCollect(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getAppDefaultTemplate.htm")
    Observable<GetAppDefaultTemplateResponse> getAppDefaultTemplate(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("selectMyCouponCount.htm")
    Observable<SelectMyCouponCountResponse> selectMyCouponCount(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("selectSysAddressList.htm")
    Observable<SelectSysAddressListResponse> selectSysAddressList(@FieldMap Map<String, Object> map);

//    @FormUrlEncoded
//    @POST("getStoreStreetHotMarket.htm")
//    Observable<GetStoreStreetResponse> getStoreStreetHotMarket(@FieldMap Map<String, Object> map);
//
//    @FormUrlEncoded
//    @POST("getStoreStreetPopularity.htm")
//    Observable<GetStoreStreetResponse> getStoreStreetPopularity(@FieldMap Map<String, Object> map);
//
//
//    @FormUrlEncoded
//    @POST("getStoreStreetHotDoor.htm")
//    Observable<GetStoreStreetResponse> getStoreStreetHotDoor(@FieldMap Map<String, Object> map);
//
//
//    @FormUrlEncoded
//    @POST("getStoreStreetGoodReputation.htm")
//    Observable<GetStoreStreetResponse> getStoreStreetGoodReputation(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("receiveCoupon.htm")
    Observable<ResultResponse> receiveCoupon(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getOssToken.htm")
    Observable<GetOssTokenResponse> getOssToken(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("searchProduct.htm")
    Observable<SearchProductResponse> searchProduct(@FieldMap Map<String, Object> map);

    /**
     * 根据货品id查询该商品下的优惠券
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getCouponOfGoodsDetail.htm")
    Observable<GetCouponOfGoodsDetailResponse> getCouponOfGoodsDetail(@FieldMap Map<String, Object> map);

    /**
     * 根据货品id查询该商品下的优惠券
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("getMarketingOfGoodsDetail.htm")
    Observable<GetMarketingOfGoodsDetailResponse> getMarketingOfGoodsDetail(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getOrder.htm")
    Observable<GetOrderResponse> getOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getOrderNumber.htm")
    Observable<GetOrderNumberResponse> getOrderNumber(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("delOrder.htm")
    Observable<ResultResponse> delOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("cancelOrder.htm")
    Observable<OrderStatusResponse> cancelOrder(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("confirmOrder.htm")
    Observable<OrderStatusResponse> confirmOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("comment.htm")
    Observable<ResultResponse> comment(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getBackOrderReasonList.htm")
    Observable<GetBackOrderReasonListResponse> getBackOrderReasonList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getApplyCredentialsList.htm")
    Observable<GetApplyCredentialsListResponse> getApplyCredentialsList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("refund.htm")
    Observable<ResultResponse> refund(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getLogisticsInformation.htm")
    Observable<GetLogisticsInformationResponse> getLogisticsInformation(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("getCommentDetail.htm")
    Observable<GetCommentDetailResponse> getCommentDetail(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("orderInfo.htm")
    Observable<OrderInfoResponse> orderInfo(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getStoreCategoryByStoreId.htm")
    Observable<GetStoreCategoryByStoreIdResponse> getStoreCategoryByStoreId(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getStoreDetail.htm")
    Observable<GetStoreDetailResponse> getStoreDetail(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getStorePageFloor.htm")
    Observable<GetStorePageFloorResponse> getStorePageFloor(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getStoreNewGoodsForPage.htm")
    Observable<GetStoreNewGoodsForPageResponse> getStoreNewGoodsForPage(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("addLogisticsInfo.htm")
    Observable<ResultResponse> addLogisticsInfo(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getStoreStreet.htm")
    Observable<GetStoreStreetResponse> getStoreStreet(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("searchStore.htm")
    Observable<SearchStoreResponse> searchStore(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getPitchOnSpec.htm")
    Observable<GetPitchOnSpecResponse> getPitchOnSpec(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("orderBuyAgain.htm")
    Observable<ResultResponse> orderBuyAgain(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("getMyMessageList.htm")
    Observable<GetMyMessageListResponse> getMyMessageList(@FieldMap Map<String, Object> map);
}
