package com.pbph.shoppingmall.module.collect.goods;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetGoodsCategoryListForFollowResponse;
import com.pbph.shoppingmall.module.collect.goods.common.CollectGoodsListCommonFragment;
import com.pbph.shoppingmall.module.collect.goods.type.CollectGoodsListTypeFragment;
import com.pbph.shoppingmall.module.collect.search.goods.CollectTypeListGoodsPopViewHolder;
import com.pbph.shoppingmall.module.collect.pop.CollectTypeListPop;
import com.pbph.shoppingmall.utils.ui.MyFragmentPagerAdapter;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class CollectGoodsTypeFragment extends BaseFragmentV4<Presenter> implements Contract.View, CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioButton[] radioButtons = new RadioButton[2];

    CollectTypeListPop pop;
    int pop_choose = -1;

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_collectgoodstype;
    }

    @Override
    public void initView(View view) {

        mViewPager = view.findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(this);

        radioButtons[0] = view.findViewById(R.id.radioButton1);
        radioButtons[1] = view.findViewById(R.id.radioButton2);

        pop = new CollectTypeListPop(this, radioButtons[1], CollectTypeListGoodsPopViewHolder.class);
        pop.gridView.setOnItemClickListener((parent, itemView, position, id) -> {
            pop.adapter.putChoiced(position);
            GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean vo = (GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean) pop.adapter.getItem(0);
            pop_choose = vo.getCatId();
            pop.dismiss();
        });
        pop.setOnDismissListener(() -> {
//            pop_choose
            presenter.postRxBus4setTypeId(pop_choose);
        });
        radioButtons[1].setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onCanClick(View v) {
                pop.show();
            }
        });
        for (RadioButton rb : radioButtons) {
            rb.setOnCheckedChangeListener(this);
        }

        initViewPager();

        radioButtons[0].setChecked(true);

    }

    private void initViewPager() {
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.list.add(CollectGoodsListCommonFragment.newInstance("1"));
        pagerAdapter.list.add(CollectGoodsListTypeFragment.newInstance());

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
        pop.adapter.clearDatas();
    }

    @Override
    public void setHttpDatas(List<GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean> list) {

        if (list == null || list.size() <= 0) return;

        pop.adapter.setDatas(list);

        pop.adapter.putChoicedNotify(0);

        GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean vo = (GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean) pop.adapter.getItem(0);
        pop_choose = vo.getCatId();
    }
}
