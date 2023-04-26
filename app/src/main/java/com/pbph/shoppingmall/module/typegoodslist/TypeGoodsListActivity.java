package com.pbph.shoppingmall.module.typegoodslist;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.model.vo.ListOrGridBean;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.module.browserecords.BrowseRecordsActivity;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.module.typegoodslist.comprehensive.ComprehensiveFragment;
import com.pbph.shoppingmall.utils.custom.OnSPClickListener;
import com.pbph.shoppingmall.utils.ui.goodstypechoosedrawer.GoodsTypeChooseDrawer;
import com.utils.StringUtils;

import java.util.List;


/**
 * Created by 连嘉凡 on 2018/3/6.Per
 */

public class TypeGoodsListActivity extends BaseActivity<TypeGoodsListPresenter> implements
        TypeGoodsListContract.View, CompoundButton.OnCheckedChangeListener {

    private int goodsType = 0;
    private RadioGroup typeshoplist_rgp;
    private RadioButton comprehensive_rbn;
    private RadioButton salesvolume_rbn;
    private RadioButton price_rbn;
    private int priceType = 0;
    private ComprehensiveFragment comprehensiveFragment, comprehensiveFragment1,
            comprehensiveFragment2, comprehensiveFragment3;
    private CheckBox all_commodity_cbx;
    private TextView screen_tv;
    private DrawerLayout type_shop_drawer;

    private View searchView;//搜索框include
    private TextView tv_search;//include
    private ImageView iv_back;
    GoodsTypeChooseDrawer drawer;
    private ImageView iv_browse_record;// 浏览记录
    private LinearLayout drawerlayout_showview;

    @Override
    protected int layoutResID() {

        return R.layout.activity_typeshoplist;
    }

    @Override
    protected void initView() {

        typeshoplist_rgp = findViewById(R.id.typeshoplist_rgp);
        comprehensive_rbn = findViewById(R.id.comprehensive_rbn);
        salesvolume_rbn = findViewById(R.id.salesvolume_rbn);
        price_rbn = findViewById(R.id.price_rbn);

        all_commodity_cbx = findViewById(R.id.all_commodity_cbx);
        screen_tv = findViewById(R.id.screen_tv);
        type_shop_drawer = findViewById(R.id.type_shop_drawer);
//        lv_screen = findViewById(R.id.lv_screen);
//        listHeard1 = LayoutInflater.from(this).inflate(R.layout.screen_list_heard_view, null);
        searchView = findViewById(R.id.type_include);
        tv_search = searchView.findViewById(R.id.tv_search);
        iv_back = searchView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(onSPClickListener);
        tv_search.setOnClickListener(onSPClickListener);
        screen_tv.setOnClickListener(onSPClickListener);
        comprehensive_rbn.setOnClickListener(onSPClickListener);
        salesvolume_rbn.setOnClickListener(onSPClickListener);
        price_rbn.setOnClickListener(onSPClickListener);
        all_commodity_cbx.setOnCheckedChangeListener(this);
        iv_browse_record = findViewById(R.id.iv_browse_records);
        iv_browse_record.setOnClickListener(onSPClickListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                comprehensiveFragment).commit();
        type_shop_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerlayout_showview = findViewById(R.id.drawerlayout_showview);
        drawer = new GoodsTypeChooseDrawer(this, type_shop_drawer, drawerlayout_showview, msg ->
                presenter.postRxBus4getFilterDatas(msg));
    }

    @Override
    protected void initData() {

        String cateId;
        int cateIds = getIntent().getIntExtra("cateId", -1);
        String keyword = getIntent().getStringExtra("keyword");
        int isThird = getIntent().getIntExtra("isThird", 0);

        if (keyword == null) {
            keyword = "";
        }

        if (cateIds == -1) {
            cateId = "";
        } else {
            cateId = String.valueOf(String.valueOf(cateIds));
        }
        comprehensiveFragment = ComprehensiveFragment.newInstance(cateId, "0D", keyword);
        comprehensiveFragment1 = ComprehensiveFragment.newInstance(cateId, "22D", keyword);
        comprehensiveFragment2 = ComprehensiveFragment.newInstance(cateId, "11D", keyword);
        comprehensiveFragment3 = ComprehensiveFragment.newInstance(cateId, "1D", keyword);

    }

    @Override
    protected TypeGoodsListPresenter createPresenter() {
        return new TypeGoodsListPresenter(this);
    }

    OnSPClickListener onSPClickListener = new OnSPClickListener() {
        @Override
        public void onClickSucc(View v) {
            switch (v.getId()) {
                case R.id.comprehensive_rbn: {
//                all_commodity_cbx.setChecked(true);
                    Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                    dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                    price_rbn.setCompoundDrawables(null, null, dra1, null);
                    priceType = 0;
                    price_rbn.setChecked(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .typeshoplist_frame, comprehensiveFragment).commit();
                }

                break;
                case R.id.salesvolume_rbn: {
//                all_commodity_cbx.setChecked(true);
                    Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                    dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                    price_rbn.setCompoundDrawables(null, null, dra1, null);
                    priceType = 0;
                    price_rbn.setChecked(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id
                            .typeshoplist_frame, comprehensiveFragment1).commit();
                }

                break;

                case R.id.price_rbn: {
//                all_commodity_cbx.setChecked(true);
                    if (priceType == 0) {
                        Drawable dra = getResources().getDrawable(R.drawable.shang);
                        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
                        price_rbn.setCompoundDrawables(null, null, dra, null);

                        priceType = 1;
                        getSupportFragmentManager().beginTransaction().replace(R.id
                                .typeshoplist_frame, comprehensiveFragment2).commit();

                    } else {
                        Drawable dra1 = getResources().getDrawable(R.drawable.xia);
                        dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                        price_rbn.setCompoundDrawables(null, null, dra1, null);
                        priceType = 0;
                        getSupportFragmentManager().beginTransaction().replace(R.id
                                .typeshoplist_frame, comprehensiveFragment3).commit();

                    }
                }
                break;
                case R.id.screen_tv: {
                    showtype_shop_drawer();
                }
                break;

                case R.id.tv_search: {
                    Intent intent = new Intent();
                    intent.setClass(TypeGoodsListActivity.this, SearchActivity.class);
                    intent.putExtra("search_type", SearchActivity.SEARCH_TYPE_GOODS);
                    startActivity(intent);
                }
                break;
                case R.id.iv_back: {
                    finish();
                }
                break;
                case R.id.iv_browse_records: {
                    if (!isLogin()) return;
                    startActivity(new Intent(TypeGoodsListActivity.this, BrowseRecordsActivity
                            .class));
                }
                break;
                default:
                    break;
            }
        }
    };


    private boolean isLogin() {
        if (StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return false;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            RxBusF.post0(new ListOrGridBean(isChecked));
            return;
        } else {
            RxBusF.post0(new ListOrGridBean(isChecked));
            return;
        }
    }

    private void showtype_shop_drawer() {
        if (!type_shop_drawer.isDrawerOpen(Gravity.RIGHT)) {
            type_shop_drawer.openDrawer(Gravity.RIGHT);
        } else {
            type_shop_drawer.closeDrawer(Gravity.RIGHT);
        }
    }

    @Override
    public void setHttpData() {
    }

    @Override
    public void setFilterDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean>
                                           params, List<SearchProductResponse.DataBeanX
            .GoodsInfoBean.BrandsBean> brands, boolean b) {
        drawer.setFilterDatas(params, brands, b);
    }

}
