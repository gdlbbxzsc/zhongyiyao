package com.pbph.shoppingmall.module.coupon.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.dao.CouponSearchRecord;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.ui.FlowLayout;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.utils.StringUtils;

import java.util.List;

public class CouponSearchActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener {

    ImageButton ibtn_left;
    EditText edt_search;
    TextView ibtn_msg;

    LinearLayout include_recent;
    ImageButton ibtn_del_recent;
    private FlowLayout flowLayout;

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private DataAdapter adapter;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_couponsearch;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        ibtn_left = findViewById(R.id.ibtn_left);
        ibtn_left.setOnClickListener(onSingleClickListener);

        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(textWatcher);
        edt_search.setOnEditorActionListener(onEditorActionListener);


        ibtn_msg = findViewById(R.id.ibtn_msg);
        ibtn_msg.setOnClickListener(onSingleClickListener);

        //
        include_recent = findViewById(R.id.include_recent);

        ibtn_del_recent = findViewById(R.id.ibtn_del_recent);
        ibtn_del_recent.setOnClickListener(onSingleClickListener);

        flowLayout = findViewById(R.id.flowLayout);
        flowLayout.setMargins(0, 30, 15, 0);


        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = findViewById(R.id.lv_shops1);
        listView.setEmptyView(include_recent);

        adapter = new DataAdapter(this, listView, CouponSearchViewHolder.class);
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }


    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.ibtn_left: {
                    if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                        edt_search.setText("");
                        clearHttpDatas();
                        return;
                    }
                    finish();
                }
                break;
                case R.id.ibtn_msg: {
                    presenter.doSearch(edt_search.getText().toString().trim());
                }
                break;
                case R.id.ibtn_del_recent: {
                    presenter.deleteSearchRecords();
                }
                break;
            }
        }
    };


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
//            doSearch(s.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            //当actionId == XX_SEND 或者 XX_DONE时都触发
            //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                //处理事件
                presenter.doSearch(edt_search.getText().toString().trim());
            }
            return false;
        }
    };


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void initSearchRecords(List<CouponSearchRecord> list) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        TextView cb;

        for (int i = 0, count = flowLayout.getChildCount(); i < count; i++) {
            cb = (TextView) flowLayout.getChildAt(i);
            cb.setVisibility(View.GONE);
        }
        if (list == null) return;

        for (int i = 0, count = list.size(); i < count; i++) {

            CouponSearchRecord vo = list.get(i);

            if (i < flowLayout.getChildCount()) {
                cb = (TextView) flowLayout.getChildAt(i);
                setRecordTextView(cb, i, vo.getSearchText());
            } else {
                cb = (TextView) inflater.inflate(R.layout.layout_recent_textview, null);
                setRecordTextView(cb, i, vo.getSearchText());
                flowLayout.addViewByLayoutParams(cb);
            }
            cb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {

        switch (rid) {
            case R.id.btn_coupon: {
                GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean vo = (GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean) holder.item;
                presenter.receiveCouponRequest(vo.getPpid());
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
    public void setHttpDatas(List<GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        for (int i = 0, count = list.size(); i < count; i++) {
            adapter.addData(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    private void setRecordTextView(TextView cb, int i, String str) {
        cb.setId(i);
        cb.setText(str);
        cb.setOnClickListener(onFlowClickListener);
        cb.setMaxLines(1);
    }

    OnSingleClickListener onFlowClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            TextView tv = (TextView) view;
            String str = tv.getText().toString().trim();
            edt_search.setText(str);
            presenter.doSearch(str);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                edt_search.setText("");
                presenter.doSearch("");
                clearHttpDatas();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void receiveCouponRequest(int id) {
        for (int i = 0, c = adapter.getCount(); i < c; i++) {
            GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean vo = (GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean) adapter.getItem(i);
            if (vo.getPpid() != id) continue;
            vo.setGetStatus(1);
            adapter.notifyDataSetChanged();
        }
    }


}
