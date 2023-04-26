package com.pbph.shoppingmall.module.main.type;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.module.search.SearchActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class TypeFragment extends BaseFragmentV4<Presenter> implements Contract.View, View
        .OnClickListener {
    private List<GetClassifyResponse.DataBean.CatesBean> catesBeans;
    private TextView[] textViews;
    private View[] views;
    private ImageView[] imageViews;
    private int viewId = 0;
    private int scrollViewWidth = 0, scrollViewMiddle = 0, currentItem = 0;
    private ScrollView left_scroll;
    private LayoutInflater inflater;
    private ViewPager goods_vp;
    private TypeAdapter typeAdapter;
    private LinearLayout tools_linear;
    private View heardView;
    private ImageView ivBack;
    private TextView tv_search;//搜索


    @Override
    protected int layoutResID() {
        return R.layout.fragment_type;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void initView(View view) {
        inflater = LayoutInflater.from(context);
        left_scroll = mContentView.findViewById(R.id.left_scroll);
        goods_vp = mContentView.findViewById(R.id.goods_vp);
        tools_linear = mContentView.findViewById(R.id.tools_linear);
        heardView = view.findViewById(R.id.type_include);
        tv_search = heardView.findViewById(R.id.tv_search);
        ivBack = heardView.findViewById(R.id.iv_back);
        ivBack.setVisibility(View.GONE);
        tv_search.setOnClickListener(this);

    }


    @Override
    public void onResume() {
        super.onResume();
        tools_linear.removeAllViews();
        presenter.getHttpData();

    }


    /**
     * 通过 presenter获取数据
     */
    public void setHttpData(List<GetClassifyResponse.DataBean.CatesBean> catesBeanList) {
        this.catesBeans = catesBeanList;
        views = new View[catesBeanList.size()];
        imageViews = new ImageView[catesBeanList.size()];
        textViews = new TextView[catesBeanList.size()];
        showToolsView();
        initViewPager();
        goods_vp.setCurrentItem(viewId);

    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        typeAdapter = new TypeAdapter(getChildFragmentManager());
        typeAdapter.setTitles(catesBeans);
        goods_vp.setAdapter(typeAdapter);
        goods_vp.setOnPageChangeListener(onPageChangeListener);

    }

    /**
     * 显示左侧控件
     */
    private void showToolsView() {

        for (int i = 0; i < catesBeans.size(); i++) {
            View view = inflater.inflate(R.layout.item_type_layout, null);
            view.setId(i);
            view.setOnClickListener(textListener);
            TextView textView = view.findViewById(R.id.text);
            ImageView imageView = view.findViewById(R.id.line);
            textView.setText(catesBeans.get(i).getName());
            tools_linear.addView(view);
            textViews[i] = textView;
            imageViews[i] = imageView;
            views[i] = view;
        }
        changeTextColor(viewId);

    }

    /**
     * OnPageChangeListener<br/>
     * 监听ViewPager选项卡变化事的事件
     */
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager
            .OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            if (goods_vp.getCurrentItem() != arg0) goods_vp.setCurrentItem(arg0);
            if (currentItem != arg0) {
                changeTextColor(arg0);
                changeTextLocation(arg0);
            }
            currentItem = arg0;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {
        int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight
                (views[clickPosition]) / 2));
        left_scroll.smoothScrollTo(0, x);
    }

    /**
     * 点击监听
     */
    private View.OnClickListener textListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeTextColor(v.getId());
            goods_vp.setCurrentItem(v.getId());
            getScrollViewMiddle();
        }

    };

    /**
     * 改变textView的颜色
     *
     * @param id
     */
    private void changeTextColor(int id) {
        viewId = id;
        for (int i = 0; i < textViews.length; i++) {
            if (i != id) {
                textViews[i].setBackgroundResource(android.R.color.white);
                textViews[i].setTextColor(0xff000000);
                imageViews[i].setVisibility(View.GONE);

            }
        }
        textViews[id].setBackgroundResource(R.color.gray);
        textViews[id].setTextColor(0xffff5d5e);
        imageViews[id].setVisibility(View.GONE);
        if (id == 0) return;
        imageViews[id - 1].setVisibility(View.GONE);
    }

    /**
     * 返回scrollview的中间位置
     *
     * @return
     */
    private int getScrollViewMiddle() {
        if (scrollViewMiddle == 0)
            scrollViewMiddle = getScrollViewheight() / 2;
        return scrollViewMiddle;
    }

    /**
     * 返回ScrollView的宽度
     *
     * @return
     */
    private int getScrollViewheight() {
        if (scrollViewWidth == 0)
            scrollViewWidth = left_scroll.getBottom() - left_scroll.getTop();
        return scrollViewWidth;
    }

    /**
     * 返回view的宽度
     *
     * @param view
     * @return
     */
    private int getViewheight(View view) {
        return view.getBottom() - view.getTop();
    }


    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search: {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("search_type", SearchActivity.SEARCH_TYPE_GOODS);
                startActivity(intent);
            }
            break;
        }
    }
}
