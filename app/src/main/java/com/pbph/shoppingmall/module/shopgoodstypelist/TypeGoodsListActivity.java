package com.pbph.shoppingmall.module.shopgoodstypelist;

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
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.model.vo.ShopGoodsListOrGridBean;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.module.browserecords.BrowseRecordsActivity;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.module.shopgoodstypelist.comprehensive.ComprehensiveFragment;
import com.pbph.shoppingmall.utils.ui.CommonTitlebar;
import com.pbph.shoppingmall.utils.ui.goodstypechoosedrawer.GoodsTypeChooseDrawer;
import com.utils.StringUtils;

import java.util.List;


/**
 * Created by 连嘉凡 on 2018/3/6.Per
 */

public class TypeGoodsListActivity extends BaseActivity<TypeGoodsListPresenter> implements
        TypeGoodsListContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RadioButton comprehensive_rbn;
    private RadioButton salesvolume_rbn;
    private RadioButton price_rbn;
    private int priceType = 0;
    private ComprehensiveFragment comprehensiveFragment, comprehensiveFragment1,
            comprehensiveFragment2, comprehensiveFragment3;
    private TextView screen_tv;
    private DrawerLayout type_shop_drawer;
    GoodsTypeChooseDrawer drawer;
    private ImageView iv_browse_record;// 浏览记录
    private LinearLayout drawerlayout_showview;
    CommonTitlebar commonTitlebar;
    private CheckBox all_commodity_cbx;
    private String title;


    @Override
    protected int layoutResID() {

        return R.layout.activity_shopgoodtypelist;
    }

    @Override
    protected void initView() {
        commonTitlebar = new CommonTitlebar(this, title, false);
        comprehensive_rbn = findViewById(R.id.comprehensive_rbn);
        salesvolume_rbn = findViewById(R.id.salesvolume_rbn);
        price_rbn = findViewById(R.id.price_rbn);

        screen_tv = findViewById(R.id.screen_tv);
        type_shop_drawer = findViewById(R.id.type_shop_drawer);
        all_commodity_cbx = findViewById(R.id.all_commodity_cbx);
        screen_tv.setOnClickListener(this);
        comprehensive_rbn.setOnClickListener(this);
        salesvolume_rbn.setOnClickListener(this);
        price_rbn.setOnClickListener(this);
        all_commodity_cbx.setOnCheckedChangeListener(this);
        iv_browse_record = findViewById(R.id.iv_browse_record);
        iv_browse_record.setVisibility(View.GONE);
        iv_browse_record.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                comprehensiveFragment).commit();
        type_shop_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerlayout_showview = findViewById(R.id.drawerlayout_showview);
        drawer = new GoodsTypeChooseDrawer(this, type_shop_drawer, drawerlayout_showview, msg ->
                presenter.postRxBus4getFilterDatas(msg));

    }

    @Override
    protected void initData() {

        int ppid = getIntent().getIntExtra("ppid", 0);
        int storeId = getIntent().getIntExtra("storeId", 0);
        title = getIntent().getStringExtra("title");
        comprehensiveFragment = ComprehensiveFragment.newInstance("0D", ppid, storeId);
        comprehensiveFragment1 = ComprehensiveFragment.newInstance("22D", ppid, storeId);
        comprehensiveFragment2 = ComprehensiveFragment.newInstance("11D", ppid, storeId);
        comprehensiveFragment3 = ComprehensiveFragment.newInstance("1D", ppid, storeId);

    }

    @Override
    protected TypeGoodsListPresenter createPresenter() {
        return new TypeGoodsListPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comprehensive_rbn: {
                Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                price_rbn.setCompoundDrawables(null, null, dra1, null);
                priceType = 0;
                price_rbn.setChecked(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                        comprehensiveFragment).commit();
            }

            break;
            case R.id.salesvolume_rbn: {
                Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                price_rbn.setCompoundDrawables(null, null, dra1, null);
                priceType = 0;
                price_rbn.setChecked(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                        comprehensiveFragment1).commit();
            }

            break;

            case R.id.price_rbn:
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
                break;
            case R.id.screen_tv:
                showtype_shop_drawer();
                break;

            case R.id.tv_search: {
                Intent intent = new Intent();
                intent.setClass(TypeGoodsListActivity.this, SearchActivity.class);
                intent.putExtra("search_type", SearchActivity.SEARCH_TYPE_GOODS);
                startActivity(intent);
            }
            break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_browse_record: {
                if (!isLogin()) return;
                startActivity(new Intent(this, BrowseRecordsActivity.class));
            }
            break;
            default:
                break;
        }
    }

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
            RxBusF.post0(new ShopGoodsListOrGridBean(isChecked));
            return;
        } else {
            RxBusF.post0(new ShopGoodsListOrGridBean(isChecked));
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
    public void setFilterDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean>
                                           params, List<SearchProductResponse.DataBeanX
            .GoodsInfoBean.BrandsBean> brands, boolean b) {
        drawer.setFilterDatas(params, brands, b);
    }

}
