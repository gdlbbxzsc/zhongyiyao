package com.pbph.shoppingmall.module.shop.allcommodity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.vo.ListOrGridBean;
import com.pbph.shoppingmall.module.shop.ShopActivity;
import com.pbph.shoppingmall.module.shop.allcommodity.allcommoditytype.AllCommodityTypeFragment;

/**
 * Created by Administrator on 2018/1/19.
 */

public class AllCommodityFragment extends BaseFragmentV4<Presenter> implements Contract.View,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private RadioButton comprehensive_rbn;
    private RadioButton salesvolume_rbn;
    private RadioButton price_rbn;
    private CheckBox all_commodity_cbx;
    private AllCommodityTypeFragment allCommodityTypeFragment;
    private AllCommodityTypeFragment allCommodityTypeFragment1;
    private AllCommodityTypeFragment allCommodityTypeFragment2;
    private AllCommodityTypeFragment allCommodityTypeFragment3;
    private int priceType = 0;
    private int storeId;
    public static String STORE_ID = "storeId";

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_all_commodity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        storeId = ((ShopActivity) context).storeId;
    }

    public static AllCommodityFragment newInstance(int storeId) {
        AllCommodityFragment fragment = new AllCommodityFragment();
        Bundle args = new Bundle();
        args.putInt(STORE_ID, storeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {
        comprehensive_rbn = view.findViewById(R.id.comprehensive_rbn);
        salesvolume_rbn = view.findViewById(R.id.salesvolume_rbn);
        price_rbn = view.findViewById(R.id.price_rbn);
        all_commodity_cbx = view.findViewById(R.id.all_commodity_cbx);
        comprehensive_rbn.setOnClickListener(this);
        salesvolume_rbn.setOnClickListener(this);
        price_rbn.setOnClickListener(this);
        all_commodity_cbx.setOnCheckedChangeListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            storeId = getArguments().getInt(STORE_ID);
        }
        allCommodityTypeFragment = AllCommodityTypeFragment.newInstance(storeId, "0D");
        allCommodityTypeFragment1 = AllCommodityTypeFragment.newInstance(storeId, "22D");
        allCommodityTypeFragment2 = AllCommodityTypeFragment.newInstance(storeId, "11D");
        allCommodityTypeFragment3 = AllCommodityTypeFragment.newInstance(storeId, "1D");
        getChildFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                allCommodityTypeFragment).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comprehensive_rbn: {
                Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                price_rbn.setCompoundDrawables(null, null, dra1, null);
                getChildFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                        allCommodityTypeFragment).commit();

            }
            break;

            case R.id.salesvolume_rbn: {
                Drawable dra1 = getResources().getDrawable(R.drawable.rbn_right_drawable);
                dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                price_rbn.setCompoundDrawables(null, null, dra1, null);
                getChildFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                        allCommodityTypeFragment1).commit();

            }
            break;

            case R.id.price_rbn: {
                if (priceType == 0) {
                    Drawable dra = getResources().getDrawable(R.drawable.shang);
                    dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
                    price_rbn.setCompoundDrawables(null, null, dra, null);
                    priceType = 1;
                    getChildFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                            allCommodityTypeFragment2).commit();

                } else {
                    Drawable dra1 = getResources().getDrawable(R.drawable.xia);
                    dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
                    price_rbn.setCompoundDrawables(null, null, dra1, null);
                    priceType = 0;
                    getChildFragmentManager().beginTransaction().replace(R.id.typeshoplist_frame,
                            allCommodityTypeFragment3).commit();
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
