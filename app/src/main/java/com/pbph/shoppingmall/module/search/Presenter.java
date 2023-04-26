package com.pbph.shoppingmall.module.search;


import com.android.utils.Logger;
import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.greendao.SearchRecordDao;
import com.pbph.shoppingmall.model.dao.SearchRecord;
import com.pbph.shoppingmall.model.message.FilterDatasMsg;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;
import com.pbph.shoppingmall.model.message.SearchShowDrawerMsg;
import com.pbph.shoppingmall.model.message.SearchTextMsg;
import com.utils.StringUtils;

import java.util.Date;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        subscribeRxBus2showDrawer();
        selectSearchRecords();
        subscribeRxBus2setFilterDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2showDrawer();
        unSubscribeRxBus2setFilterDatas();
    }

    String search_text;
    SearchTextMsg searchTextMsg = new SearchTextMsg();

    @Override
    public void doSearch(String str) {

        search_text = str;

        postRxBus4SendSeachText();

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

//        if (StringUtils.isEqual(str, search_text)) {
//            return;
//        }


    }

    @Override
    public void selectSearchRecords() {
        List<SearchRecord> list = MyApplication.getInstances().getDaoSession().getSearchRecordDao()//
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

    @Override
    public void postRxBus4SendSeachText() {
        searchTextMsg.searchText = search_text;
        RxBusF.post0(searchTextMsg);
    }


    @Override

    public void subscribeRxBus2showDrawer() {
        RxBusF.register0(Presenter.this, SearchShowDrawerMsg.class, it -> {
            getBaseView().showDrawer();
        });
    }

    @Override
    public void unSubscribeRxBus2showDrawer() {
        RxBusF.removeDisposable0(Presenter.this, SearchShowDrawerMsg.class);
    }


    @Override
    public void subscribeRxBus2setFilterDatas() {
        RxBusF.register0(Presenter.this, FilterDatasMsg.class, it -> {
            getBaseView().setFilterDatas(it.params, it.brands, it.isClearData);
        });
    }

    @Override
    public void unSubscribeRxBus2setFilterDatas() {
        RxBusF.removeDisposable0(Presenter.this, FilterDatasMsg.class);
    }

    @Override
    public void postRxBus4getFilterDatas(FilterDatasResultMsg msg) {

        RxBusF.post0(msg);
    }
}
