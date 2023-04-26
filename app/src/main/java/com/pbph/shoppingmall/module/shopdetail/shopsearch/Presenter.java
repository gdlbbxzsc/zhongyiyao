package com.pbph.shoppingmall.module.shopdetail.shopsearch;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.greendao.SearchRecordDao;
import com.pbph.shoppingmall.model.dao.SearchRecord;
import com.pbph.shoppingmall.model.message.SearchTextMsg;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.search.MsgType;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.utils.StringUtils;

import java.util.Date;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract
        .Presenter {

    public Presenter(T baseView) {
        super(baseView);

    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        selectSearchRecords();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }


    String search_text;
    SearchTextMsg searchTextMsg = new SearchTextMsg();

    @Override
    public void doSearch(String str, int storeId, int pageNo) {
        search_text = str;
        if (StringUtils.isEmpty(str)) {
            getBaseView().changeView(MsgType.Msg_Type_Recent);
            return;
        }
        getBaseView().changeView(MsgType.Msg_Type_Fragment);

        SearchRecordDao dao = MyApplication.getInstances().getDaoSession().getSearchRecordDao();

        List<SearchRecord> list = dao.queryBuilder()//
                .where(SearchRecordDao.Properties.SearchText.eq(str))//
                .list();

        if (list == null || list.size() <= 0) {
            SearchRecord vo = new SearchRecord();
            vo.setSearchText(str);
            vo.setCreateTime(new Date());
            dao.insert(vo);
        } else {
            SearchRecord vo = list.get(0);
            vo.setCreateTime(new Date());
            dao.update(vo);
        }
        selectSearchRecords();

        getGoodsTypeListData(str, storeId, pageNo);
    }

    private void getGoodsTypeListData(String str, int storeId, int pageNo) {
        SearchProductRequest<SearchProductResponse> searchProductRequest = new
                SearchProductRequest<>();
        /**
         * @param keyword     搜索关键字
         * @param sort        排序
         * @param storeId     商家id
         * @param isThird     是否第三方（0boss，i店铺）
         * @param startRowNum 开始页
         * @param endRowNum   结束页
         * @param catIds      直营分类id
         * @param thirdCats   第三方分类id
         * @param brands      品牌名称
         * @param priceMin    最低价格
         * @param priceMax    最高价格
         *         params       属性
         */
        searchProductRequest.customerId = UserInfo.getInstance().getCustomerId();
        searchProductRequest.keyword = str;
        searchProductRequest.sort = "0D";
        searchProductRequest.storeId = storeId;
        searchProductRequest.isThird = 1;
        searchProductRequest.pageNo = pageNo;
        searchProductRequest.pageSize = Constant.Data.PAGE_COUNT;
//        searchProductRequest.catIds = cateId;
//        searchProductRequest.thirdCats = thirdCats;
//        searchProductRequest.brands = brands;
//        searchProductRequest.priceMin = priceMin;
//        searchProductRequest.priceMax = priceMax;
//        searchProductRequest.params = params;
        searchProductRequest.request().subscribe(new LogoutConsumer<SearchProductResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(SearchProductResponse vo) throws Exception {

                getBaseView().setHttpData(vo.getData().getGoodsInfo().getGoodsInfoList().getData());


            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

    }


    @Override
    public void selectSearchRecords() {
        List<SearchRecord> list = MyApplication.getInstances().getDaoSession().getSearchRecordDao
                ()//
                .queryBuilder()//
//                .where(SearchRecordDao.Properties.SearchType.eq(1))//
                .orderDesc(SearchRecordDao.Properties.CreateTime)//
                .limit(10)//
                .list();
        getBaseView().initSearchRecords(list);
    }

    @Override
    public void deleteSearchRecords() {
        MyApplication.getInstances().getDaoSession().getSearchRecordDao()//
                .deleteAll();
        getBaseView().initSearchRecords(null);
    }
}
