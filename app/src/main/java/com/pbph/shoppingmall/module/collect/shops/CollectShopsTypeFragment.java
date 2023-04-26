package com.pbph.shoppingmall.module.collect.shops;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetStoreCategoryListForCollectionResponse;
import com.pbph.shoppingmall.module.collect.pop.CollectTypeListPop;
import com.pbph.shoppingmall.module.collect.search.shops.CollectTypeShopListPopViewHolder;
import com.pbph.shoppingmall.module.collect.shops.common.CollectShopsListCommonFragment;
import com.pbph.shoppingmall.module.collect.shops.type.CollectShopsListTypeFragment;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CollectShopsTypeFragment extends BaseFragmentV4<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioButton[] radioButtons = new RadioButton[2];

//    CollectTypeListPop pop;
//    int pop_choose = -1;

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_collectshopstype;
    }

    @Override
    public void initView(View view) {

        mViewPager = view.findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = view.findViewById(R.id.radioButton1);
        radioButtons[1] = view.findViewById(R.id.radioButton2);
//        radioButtons[2] = view.findViewById(R.id.radioButton3);
//        radioButtons[2] = view.findViewById(R.id.radioButton4);

//        pop = new CollectTypeListPop(this, radioButtons[2], CollectTypeShopListPopViewHolder.class);
//        pop.gridView.setOnItemClickListener((parent, itemView, position, id) -> {
//            pop.adapter.putChoiced(position);
//            GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean vo = (GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean) pop.adapter.getItem(position);
//            pop_choose = vo.getCateId();
//            pop.dismiss();
//        });
//        pop.setOnDismissListener(() -> {
////            pop_choose
//            presenter.postRxBus4setTypeId(pop_choose);
//        });
//        radioButtons[2].setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onCanClick(View v) {
//                pop.show();
//            }
//        });
        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }

        initViewPager();

        radioButtons[0].setChecked(true);

    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.list.add(CollectShopsListCommonFragment.newInstance("1"));
        pagerAdapter.list.add(CollectShopsListCommonFragment.newInstance("2"));
//        pagerAdapter.list.add(CollectShopsListCommonFragment.newInstance("3"));
//        pagerAdapter.list.add(CollectShopsListTypeFragment.newInstance());


        mViewPager.setAdapter(pagerAdapter);
    }


    boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        isVisible = isVisibleToUser;

        if (isVisibleToUser) {

        } else {

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) return;

        switch (buttonView.getId()) {
            case R.id.radioButton1:
                check(0);
                break;
            case R.id.radioButton2:
                check(1);
                break;
//            case R.id.radioButton3:
//                check(2);
//                break;
//            case R.id.radioButton4:
//                check(2);
//                break;

        }
    }

    private void check(int pos) {
        mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageSelected(int position) {
        radioButtons[position].setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }


    @Override
    public void clearHttpDatas() {
//        pop.adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean> list) {

//        if (list == null || list.size() <= 0) return;
//
//        pop.adapter.setDatas(list);
//
//        pop.adapter.putChoicedNotify(0);
//
//        GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean vo = (GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean) pop.adapter.getItem(0);
//        pop_choose = vo.getCateId();
    }
}
