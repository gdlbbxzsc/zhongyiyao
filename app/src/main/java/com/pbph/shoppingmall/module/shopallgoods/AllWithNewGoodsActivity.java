package com.pbph.shoppingmall.module.shopallgoods;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.model.vo.ListOrGridBean;
import com.pbph.shoppingmall.module.shopallgoods.comprehensive.ComprehensiveFragment;
import com.pbph.shoppingmall.utils.ui.ShopSearchTitleBarType;

import java.util.List;

public class AllWithNewGoodsActivity extends BaseActivity<Presenter> implements Contract.View,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RadioButton comprehensive_rbn, salesvolume_rbn, price_rbn, saixuan_rbn;
    private ComprehensiveFragment comprehensiveFragment, comprehensiveFragment1,
            comprehensiveFragment2, comprehensiveFragment3;
    private CheckBox all_commodity_cbx;
    private int storeId;
    private int priceType = 0;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_andwithnewgoods;
    }


    @Override
    protected void initData() {
        storeId = getIntent().getIntExtra("storeId", storeId);
        comprehensiveFragment = ComprehensiveFragment.newInstance(storeId, "0D");
        comprehensiveFragment1 = ComprehensiveFragment.newInstance(storeId, "22D");
        comprehensiveFragment2 = ComprehensiveFragment.newInstance(storeId, "11D");
        comprehensiveFragment3 = ComprehensiveFragment.newInstance(storeId, "1D");
    }

    @Override
    protected void initView() {
        comprehensive_rbn = findViewById(R.id.comprehensive_rbn);
        salesvolume_rbn = findViewById(R.id.salesvolume_rbn);
        price_rbn = findViewById(R.id.price_rbn);
        all_commodity_cbx = findViewById(R.id.all_commodity_cbx);

        comprehensive_rbn.setOnClickListener(this);
        salesvolume_rbn.setOnClickListener(this);
        price_rbn.setOnClickListener(this);
        all_commodity_cbx.setOnCheckedChangeListener(this);

        ShopSearchTitleBarType shopSearchTitleBarType = new ShopSearchTitleBarType(this, "请输入搜索关键字", true,
                SearchActivity.SEARCH_TYPE_SHOPS,storeId);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_all_with_new,
                comprehensiveFragment)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void clearHttpDatas() {

    }

    @Override
    public void setHttpDatas(List<String> list) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comprehensive_rbn: {
                all_commodity_cbx.setChecked(true);
                Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                price_rbn.setCompoundDrawables(null, null, dra1, null);
                priceType = 0;
//                price_rbn.setChecked(false);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_all_with_new, comprehensiveFragment)
                        .commit();
            }
            break;
            case R.id.salesvolume_rbn: {
                all_commodity_cbx.setChecked(true);
                Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                price_rbn.setCompoundDrawables(null, null, dra1, null);
                priceType = 0;
//                price_rbn.setChecked(false);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_all_with_new, comprehensiveFragment1)
                        .commit();
            }
            break;

            case R.id.price_rbn: {
                all_commodity_cbx.setChecked(true);
                if (priceType == 0) {
                    Drawable dra = getResources().getDrawable(R.drawable.shang);
                    dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
                    price_rbn.setCompoundDrawables(null, null, dra, null);

                    priceType = 1;
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.frame_all_with_new, comprehensiveFragment2)
                            .commit();

                } else {
                    Drawable dra1 = getResources().getDrawable(R.drawable.xia);
                    dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                    price_rbn.setCompoundDrawables(null, null, dra1, null);
                    priceType = 0;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_all_with_new, comprehensiveFragment3)
                            .commit();

                }
            }
            break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        RxBusF.post0(new ListOrGridBean(isChecked));

    }
}
