package com.pbph.shoppingmall.constant;

/**
 * Created by Administrator on 2018/2/27.
 */

public final class Constant {


    public static final class Data {
        public static final int PAGE_COUNT = 20;
        public static final int PAGE_COUNT_MAX = Integer.MAX_VALUE;


        public static final String WX_APP_ID = "wx3e5624b6a9f3c28b";

        public static boolean listType = true;


    }

    public static final class URL {
        //    项目请求地址

        //这是 改版后的中医药地址
        public static final String BASE_URL = "https://testhgs.pcc58.com/pb_shop_web/";
//        这是原地址
//        public static final String BASE_URL = "https://testhgs.pcc58.com/shop_web_nv/";
    }


    public static final class Path {
        //    项目请求地址
//        登录
        public static final String MOBLIELOGIN = "moblieLogin";
        //获取验证码
        // 分类
        public static final String GET_CLASSIFY = "getClassify";

        public static final String SENDSMSVALCODE = "sendSmsValCode";
        //优惠券列表
        public static final String SELECTMYCOUPONLIST = "selectMyCouponList";
        //删除优惠券
        public static final String DELETECOUPONNO = "deleteCouponNo";
        //领取优惠券分类
        public static final String GETCOUPONCATEGORYLIST = "getCouponCategoryList";
        //领取优惠券列表
        public static final String GETALLCOUPONLIST = "getAllCouponList";
        //开关状态接口
        public static final String GETSYSSWITCH = "getSysSwitch";

        //根据分类查询商品列表
        public static final String GET_GOODS_LIST = "getGoodsList";
        //查询购物车
        public static final String GET_SHOPPING_CART = "getShoppingCart";
        //查询全部支付方式
        public static final String PAY_METHOD = "payMethod";
        //修改购物车
        public static final String UPDATE_SHOPPING_CART = "updateShoppingCart";
        //商品详情收货地址
        public static final String DEL_SHOPPING_CART = "delShoppingCart";
        //查找用户默认
        public static final String GET_DEFAULT_ADDRESS = "getDefaultAddress";
        //查找用户默认发票
        public static final String GET_DEFAULT_BILL = "getDefaultBill";
        //关注/取消关注商品
        public static final String SAVE_GOODS_FOLLOW = "saveGoodsFollow";
        //提交订单
        public static final String SUBMIT_ORDER = "submitOrder";
        //获取结算页价格
        public static final String GET_SUBMIT_PRICE = "getSubmitPrice";
        //微信支付
        public static final String PAYORDER = "payOrder";

        //查询购物车商品数量
        public static final String GET_SHOPPING_CART_NUMBER = "getShoppingCartNumber";

        //商品详情
        public static final String PRODUCT_DETAIL = "productDetail";

        //我的积分列表
        public static final String GETMYCUSTOMERPOINTLIST = "getMyCustomerPointList";
        //签到
        public static final String PSETSIGN = "psetSign";
        //发票列表
        public static final String GETBILLLIST = "getBillList";
        //添加发票
        public static final String INSERTBILL = "insertBill";
        //根据id 获取发票详情
        public static final String GETBILLBYID = "getBillById";
        //修改发票
        public static final String UPDATEBILL = "updateBill";
        //        删除发票
        public static final String DELETEBILL = "deleteBill";
        //        设置默认
        public static final String SETDEFAULTBILLBYID = "setDefaultBillById";
        // 根据商品查询商品的评论
        public static final String GET_PRODUCT_COMMENT = "getProductComment";

        public static final String SAVE_SHOPPING_CART = "saveShoppingCart";
        //        获取收藏浏览记录等数量
        public static final String GETMYCOLLECTIONANDBROWSE = "getMyCollectionAndBrowse";
        //        个人信息
        public static final String GETMYCUSTOMER = "getMyCustomer";
        //        收藏店铺
        public static final String GETCOLLECTIONSELLERLIST = "getCollectionSellerList";
        //        删除浏览记录
        public static final String DELBROWSERECORD = "delBrowseRecord";
        //        浏览记录
        public static final String GETBROWSERECORDLIST = "getBrowseRecordList";
        //        查询收货地址
        public static final String GETADDRESSLIST = "getAddressList";
        //添加地址
        public static final String SAVEADDRESS = "saveAddress";
        //        修改收货地址
        public static final String UPDATEADDRESS = "updateAddress";
        //     删除地址
        public static final String DELETEADDRESS = "deleteAddress";
        //        获取地址详情
        public static final String GETADDRESS = "getAddress";
        //        设置默认地址
        public static final String SETDEFAULTADDRESS = "setDefaultAddress";
        //        更改个人信息
        public static final String UPDATECUSTOMER = "updateCustomer";
        //        更改个人信息
        public static final String UPDATECUSTOMERINFO = "updateCustomerInfo";
        //        修改手机号码第一步 验证旧手机
        public static final String CHECKCODE = "checkCode";
        //        修改手机号码第二步 验证新手机
        public static final String SENDSMSVALCODEFORBINDNEWPHONE = "sendSmsValCodeForBindNewPhone";
        //        获取收藏店铺分类
        public static final String GETSTORECATEGORYLISTFORCOLLECTION =
                "getStoreCategoryListForCollection";
        //        收藏商品列表
        public static final String GETCUSTOMERFOLLOWLIST = "getCustomerFollowList";
        //        取消、收藏 商品
        public static final String GETGOODSCATEGORYLISTFORFOLLOW = "getGoodsCategoryListForFollow";
        //        收藏/取消收藏店铺
        public static final String SAVE_THIRD_STORE_COLLECT = "saveThirdStoreCollect";
        //        首页
        public static final String GETAPPDEFAULTTEMPLATE = "getAppDefaultTemplate";
        //        获取我的优惠券总数
        public static final String SELECTMYCOUPONCOUNT = "selectMyCouponCount";
        //        获取省市区
        public static final String SELECTSYSADDRESSLIST = "selectSysAddressList";
        //        店铺街 接口
//        public static final String GETSTORESTREETHOTMARKET = "getStoreStreetHotMarket";
//        public static final String GETSTORESTREETPOPULARITY = "getStoreStreetPopularity";
//        public static final String GETSTORESTREETHOTDOOR = "getStoreStreetHotDoor";
//        public static final String GETSTORESTREETGOODREPUTATION = "getStoreStreetGoodReputation";
        //        领取优惠券
        public static final String RECEIVECOUPON = "receiveCoupon";
        //获取alioss token
        public static final String GETOSSTOKEN = "getOssToken";
        //搜索商品
        public static final String SEARCHPRODUCT = "searchProduct";
        //        店铺街 接口
        public static final String GETSTORESTREET = "getStoreStreet";
        //        根据货品id查询该商品下的优惠券
        public static final String GET_COUPON_Of_GOODS_DETAIL = "getCouponOfGoodsDetail";
        //         根据货品id查询促销活动
        public static final String GET_MARKETING_OF_GOODS_DETAIL = "getMarketingOfGoodsDetail";
        //        我的订单列表
        public static final String GETORDER = "getOrder";
        //        我的订单数量
        public static final String GETORDERNUMBER = "getOrderNumber";
        //        删除订单
        public static final String DELORDER = "delOrder";
        //        取消订单
        public static final String CANCELORDER = "cancelOrder";
        //        确认收货
        public static final String CONFIRMORDER = "confirmOrder";
        //        晒单评价
        public static final String COMMENT = "comment";
        //        获取退单原因列表
        public static final String GETBACKORDERREASONLIST = "getBackOrderReasonList";
        //        获取退单申请凭据列表
        public static final String GETAPPLYCREDENTIALSLIST = "getApplyCredentialsList";
        //        退款退货
        public static final String REFUND = "refund";
        //        查看物流
        public static final String GETLOGISTICSINFORMATION = "getLogisticsInformation";
        //        获取评价详情
        public static final String GETCOMMENTDETAIL = "getCommentDetail";
        //        订单详情
        public static final String ORDERINFO = "orderInfo";
        //        商品分类
        public static final String GET_STORE_CATEGORY_BY_STORE_ID = "getStoreCategoryByStoreId";
        //        获取店铺详情
        public static final String GET_STORE_DETAIL = "getStoreDetail";
        //        获取店铺详情
        public static final String GET_STORE_PAGE_FLOOR = "getStorePageFloor";
        //        填写物流信息接口
        public static final String ADDLOGISTICSINFO = "addLogisticsInfo";
        //       获取店铺新品上架sdfsdf
        public static final String GET_STORE_NEW_GOODS_FOR_PAGE = "getStoreNewGoodsForPage";
        //搜索店铺
        public static final String SEARCHSTORE = "searchStore";
        //       商品详情选中规格值
        public static final String GET_PITCH_ON_SPEC = "getPitchOnSpec";
        //        再次购买
        public static final String ORDERBUYAGAIN = "orderBuyAgain";
        //        消息列表
        public static final String GETMYMESSAGELIST = "getMyMessageList";
    }

}
