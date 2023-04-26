package com.pbph.shoppingmall.module.search;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.dao.SearchRecord;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.search.goods.SearchGoodsTypeFragment;
import com.pbph.shoppingmall.module.search.shops.SearchShopsTypeFragment;
import com.pbph.shoppingmall.utils.ui.FlowLayout;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.NoScrollViewPager;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.pbph.shoppingmall.utils.ui.goodstypechoosedrawer.GoodsTypeChooseDrawer;
import com.utils.StringUtils;

import java.util.List;

public class SearchActivity extends BaseActivity<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    public static final int SEARCH_TYPE_GOODS = 0;
    public static final int SEARCH_TYPE_SHOPS = 1;

    ImageButton ibtn_left;
    EditText edt_search;
    TextView ibtn_msg;

    LinearLayout include_recent;
    ImageButton ibtn_del_recent;
    private FlowLayout flowLayout;
    //
    LinearLayout include_list;

    RelativeLayout relativelayout_menu;
    private RadioButton[] radioButtons = new RadioButton[2];
    View[] rbLines = new View[2];

    private NoScrollViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;


    int search_type = SEARCH_TYPE_GOODS;
//    String searchText;


    GoodsTypeChooseDrawer drawer;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_search;
    }


    @Override
    protected void initData() {
        search_type = getIntent().getIntExtra("search_type", SEARCH_TYPE_GOODS);
//        searchText = getIntent().getStringExtra("searchText");
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
        include_recent.setOnClickListener(v -> {
        });

        ibtn_del_recent = findViewById(R.id.ibtn_del_recent);
        ibtn_del_recent.setOnClickListener(onSingleClickListener);

        flowLayout = findViewById(R.id.flowLayout);
        flowLayout.setMargins(0, 30, 15, 0);

        include_list = findViewById(R.id.include_list);

        relativelayout_menu = findViewById(R.id.relativelayout_menu);

        mViewPager = findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);

        rbLines[0] = findViewById(R.id.radioButtonline1);
        rbLines[1] = findViewById(R.id.radioButtonline2);

        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }
        initViewPager();

        radioButtons[search_type].setChecked(true);
        for (int i = 0; i < rbLines.length; i++) {
            rbLines[i].setVisibility(search_type == i ? View.VISIBLE : View.INVISIBLE);
        }


        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        LinearLayout llDrawerLayout = findViewById(R.id.drawerlayout_showview);

        drawer = new GoodsTypeChooseDrawer(this, drawerLayout, llDrawerLayout, msg -> presenter.postRxBus4getFilterDatas(msg));
    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.list.add(new SearchGoodsTypeFragment());
        pagerAdapter.list.add(new SearchShopsTypeFragment());
        mViewPager.setAdapter(pagerAdapter);
    }

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.ibtn_left: {
                    if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                        edt_search.setText("");
                        changeView(MsgType.Msg_Type_Recent);
                        presenter.doSearch("");
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        switch (buttonView.getId()) {
            case R.id.radioButton1:
                rbLines[0].setVisibility(View.VISIBLE);
                rbLines[1].setVisibility(View.INVISIBLE);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.radioButton2:
                rbLines[1].setVisibility(View.VISIBLE);
                rbLines[0].setVisibility(View.INVISIBLE);
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        radioButtons[position].setChecked(true);
        presenter.postRxBus4SendSeachText();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void changeView(MsgType type) {

        switch (type) {
            case Msg_Type_Recent: {
                include_list.setVisibility(View.INVISIBLE);
                include_recent.setVisibility(View.VISIBLE);
            }
            break;
            case Msg_Type_Fragment: {
                include_recent.setVisibility(View.INVISIBLE);
                include_list.setVisibility(View.VISIBLE);
            }
            break;
        }
    }

    @Override
    public void showDrawer() {
        drawer.showDrawer();
    }

    @Override
    public void setFilterDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean> params, List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands, boolean b) {
        drawer.setFilterDatas(params, brands, b);
    }

    @Override
    public void initSearchRecords(List<SearchRecord> list) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        TextView cb;

        for (int i = 0, count = flowLayout.getChildCount(); i < count; i++) {
            cb = (TextView) flowLayout.getChildAt(i);
            cb.setVisibility(View.GONE);
        }
        if (list == null) return;

        for (int i = 0, count = list.size(); i < count; i++) {

            SearchRecord vo = list.get(i);

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

        if (drawer.isOpen()) {
            drawer.showDrawer();
            return false;
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                edt_search.setText("");
                changeView(MsgType.Msg_Type_Recent);
                presenter.doSearch("");
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
