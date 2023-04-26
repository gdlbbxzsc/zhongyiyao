package com.pbph.shoppingmall.module.browserecords;

import android.content.Intent;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetBrowseRecordListResponse;
import com.pbph.shoppingmall.module.similarity.SimilarityActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BrowseRecordsActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    //标题栏
    CommonTitlebar commonTitlebar;
    CheckBox titlebar_right2;

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private BrowsRecordsDataAdapter adapter;

    LinearLayout ll_bottom;
    CheckBox cb_all;
    TextView tv_del;

    View fl_gwc;
    TextView tv_gwc_num;
    List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> addGwcList = new ArrayList<>();//添加到购物车的货物
    List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> listBean = new ArrayList<>();

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_browserecords;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        commonTitlebar = new CommonTitlebar(this, "浏览记录", true);
        titlebar_right2 = findViewById(R.id.titlebar_right2);
        titlebar_right2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                titlebar_right2.setText("完成");
                ll_bottom.setVisibility(View.VISIBLE);

                cb_all.setOnCheckedChangeListener(null);
                cb_all.setChecked(false);
                cb_all.setOnCheckedChangeListener(allListener);

                delData(true);
            } else {
                titlebar_right2.setText("编辑");
                ll_bottom.setVisibility(View.GONE);

                delData(false);
            }

        });
        titlebar_right2.setText("编辑");
        titlebar_right2.setChecked(false);

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = findViewById(R.id.lv_wuliu);

        adapter = new BrowsRecordsDataAdapter(this, listView, BrowseRecordsViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        adapter.setChoice_type(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        ll_bottom = findViewById(R.id.ll_bottom);
        cb_all = findViewById(R.id.cb_all);

        tv_del = findViewById(R.id.tv_del);
        tv_del.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                //获取选中的id
             /*   StringBuffer sb = new StringBuffer();
                Iterator<Integer> iterator = adapter.choiceMap.keySet().iterator();
                while (iterator.hasNext()){
                    int position=iterator.next();
                    GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean obj= (GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean) adapter.getItem(position);
                    sb.append(",").append(obj.getGoodsInfoId());
                }
                String id = null;
                if (sb.length()>0){
                   id = sb.substring(1);
                }*/
                StringBuffer sb = new StringBuffer();
                Iterator<Integer> iterator = adapter.choiceMap.keySet().iterator();
                while (iterator.hasNext()){
                    int position=iterator.next();
                    GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean obj= (GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean) adapter.getItem(position);
                    sb.append(",").append(obj.getGoodsInfoId());
                    listBean.remove(obj);
                }
                String id = null;
                if (sb.length()>0){
                    id = sb.substring(1);
                }
                presenter.doDel(id);
            }
        });

        fl_gwc = findViewById(R.id.fl_gwc);
        fl_gwc.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
            }
        });
        tv_gwc_num = findViewById(R.id.tv_gwc_num);

    }

    CompoundButton.OnCheckedChangeListener allListener = (buttonView, isChecked) -> {
        chooseAll(isChecked);
    };


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (adapter.isEdit()) {
            adapter.putChoicedNotify(position);
        }
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (DataAdapter.OnItemViewClickListener<BrowseRecordsViewHolder>) (rid, holder, objects) -> {
        switch (rid) {
            case R.id.tv_look_others: {//看相似
                goSimilarity();
            }
            break;
            case R.id.iv_gwc: { //购物车
                updateShoppingCar((GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean) holder.item);
            }
            break;


        }
    };

    @Override
    public void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore) {
        smartRefreshLayout.setEnableRefresh(finishRefresh);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(finishLoadMore);//是否启用上拉加载功能
    }

    @Override
    public void finishSmartRefresh() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> list) {
        listBean =list;
        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }


    void delData(boolean isDel) {
        adapter.clearChoices();
        adapter.setEdit(isDel);
    }

    void chooseAll(boolean isChoose) {
        if (isChoose) {
            adapter.putChoicedAll();
        } else {
            adapter.clearChoices();
        }
    }


    @Override
    public List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> getDelIds() {

        List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> list = new ArrayList<>();

        Iterator<Map.Entry<Integer, Object>> iterator = adapter.choiceMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> data = iterator.next();

            GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean vo = (GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean) data.getValue();
            list.add(vo);
        }
        return list;
    }

    @Override
    public void doDel() {
        adapter.setDatas(listBean);
        adapter.clearChoices();
    }




    void goSimilarity() {
        startActivity(new Intent(context, SimilarityActivity.class)
                .putExtra("good_name", "")
                .putExtra("good_price", "")
                .putExtra("good_pic", ""));
    }

    void updateShoppingCar(GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean item) {

//            addGwcList.add(item);
            presenter.addShoppingCar(item.getGoodsInfoId(), 1, 1, "1");

//        updateShoppingCar();
    }

    void updateShoppingCar() {

        fl_gwc.setVisibility(addGwcList.size() <= 0 ? View.GONE : View.VISIBLE);

        tv_gwc_num.setText(String.valueOf(addGwcList.size()));
    }
}
