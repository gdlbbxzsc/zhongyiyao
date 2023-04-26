package com.pbph.shoppingmall.module.main.index;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.utils.Logger;
import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAppDefaultTemplateResponse;
import com.pbph.shoppingmall.model.vo.MenuVo;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.module.capture.CaptureResultActivity;
import com.pbph.shoppingmall.module.collect.CollectIndexActivity;
import com.pbph.shoppingmall.module.goodsdetail.GoodsDetailActivity;
import com.pbph.shoppingmall.module.main.index.viewholder.BannerViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.GoodImageViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.ImageAdViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.ImageSpeViewHolder;
import com.pbph.shoppingmall.module.main.index.viewholder.MenuViewHolder;
import com.pbph.shoppingmall.module.message.type.MessageTypeActivity;
import com.pbph.shoppingmall.module.orders.myorders.index.MyOrdersActivity;
import com.pbph.shoppingmall.module.search.SearchActivity;
import com.pbph.shoppingmall.module.shopstreet.ShopStreetActivity;
import com.pbph.shoppingmall.module.typegoodslist.TypeGoodsListActivity;
import com.pbph.shoppingmall.utils.adapter.recyclerview.OnItemClickListener;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.utils.StringUtils;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;

/**
 * Created by Administrator on 2018/1/19.
 */

public class IndexFragment extends BaseFragmentV4<Contract.Presenter> implements Contract.View {


    TextView tv_search;
    ImageButton ibtn_msg;


    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private IndexDataAdapter adapter;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_index111;
    }


    int screen_width;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        screen_width = outMetrics.widthPixels;
    }

    @Override
    public void initView(View view) {

        tv_search = view.findViewById(R.id.tv_search);
        ibtn_msg = view.findViewById(R.id.ibtn_msg);
        tv_search.setOnClickListener(onSingleClickListener);
        ibtn_msg.setOnClickListener(onSingleClickListener);


        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatas());
//        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        recyclerView = view.findViewById(R.id.main_view);

        adapter = new IndexDataAdapter(this, recyclerView, 11);
        recyclerView.setLayoutManager(initLayoutManager());

        adapter.listener = onBannerListener;

        recyclerView.addItemDecoration(new IndexItemDecoration(getContext(), adapter));

        adapter.setOnItemClickListener(onItemClickListener);

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.getHttpDatas();

//        if (secTimer != null) secTimer.start();
    }

    @Override
    public void onPause() {
//        if (secTimer != null) secTimer.cancel();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseBanner();
    }

//    SecTimer secTimer = new SecTimer() {
//
//        @Override
//        public void onTick(long passTime) throws Exception {
//            if (adapter.onTimeChangeListener != null)
//                adapter.onTimeChangeListener.onTimeChange(passTime);
//        }
//    };

    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {

            switch (v.getId()) {
                case R.id.tv_search: {
                    startActivity(new Intent(getContext(), SearchActivity.class)
                            .putExtra("search_type", SearchActivity.SEARCH_TYPE_GOODS));
                }
                break;
                case R.id.ibtn_msg: {

                    if (!MyApplication.checkLogin(getContext())) return;

//                    startActivity(new Intent(getContext(), MessageIndexActivity.class));
                    startActivity(new Intent(getContext(), MessageTypeActivity.class)
                            .putExtra("message_type", MessageTypeActivity.MESSAGE_TYPE_WULIU)
                    );
                }
                break;
            }
        }
    };

    OnItemClickListener onItemClickListener = (parent, viewHolder, position) -> {

        Class clz = viewHolder.getClass();
//                = adapter.getItemViewTypeClass(position);
        if (clz == BannerViewHolder.class) {
            return;
        }
        if (clz == MenuViewHolder.class) {
//            促销活动
//            startActivity(new Intent(getContext(), SalesPromotionActivity.class));
//            领券中心
//            startActivity(new Intent(getContext(), CouponIndexActivity.class));
            MenuVo vo = (MenuVo) viewHolder.item;
            switch (vo.id) {
                case 1:
                    presenter.checkPermission2gotoScan();
                    break;
                case 2:
                    startActivity(new Intent(getContext(), ShopStreetActivity.class));
                    break;
                case 3:
                    if (!MyApplication.checkLogin(getContext())) return;
                    startActivity(new Intent(getContext(), MyOrdersActivity.class)
                            .putExtra("message_type", MyOrdersActivity.MESSAGE_TYPE_ALL));
                    break;
                case 4:
                    presenter.postRxBus4goShopType();
                    break;
                case 5:
                    if (!isLogin()) return;
                    startActivity(new Intent(getContext(), CollectIndexActivity.class)
                            .putExtra("collect_type", CollectIndexActivity.COLLECT_TYPE_GOODS));
                    break;
            }
            return;
        }
//        if (clz == Active1ViewHolder.class) {
//            startActivity(new Intent(getContext(), SecKillIndexActivity.class)
//                    .putExtra("seckill_type", SecKillIndexActivity.SECKILLI_TYPE_GOODS));
//            return;
//        }
//        if (clz == Active2ViewHolder.class) {
//            toastShort("aaaa2");
//            return;
//        }
//        if (clz == Active3ViewHolder.class) {
//            startActivity(new Intent(getContext(), SecKillIndexActivity.class)
//                    .putExtra("seckill_type", SecKillIndexActivity.SECKILLI_TYPE_BRAND));
//            return;
//        }
//        if (clz == Active4ViewHolder.class) {
//            toastShort("aaaa4");
//            return;
//        }
        if (clz == ImageSpeViewHolder.class) {
            GetAppDefaultTemplateResponse.ItemData avo = (GetAppDefaultTemplateResponse.ItemData) viewHolder.item;
            String action = avo.getAction();

            if (StringUtils.isEqual(action, "GoodsList")) {
                GetAppDefaultTemplateResponse.ItemData.ActionParamBean.SearchParamBean spVo = avo.getActionParam().getSearchParam();
                List<String> cates = spVo.getCates();
                if (cates != null && cates.size() > 0) {
                    cates.get(0);
                    return;
                }
                List<String> brands = spVo.getBrands();
                if (brands != null && brands.size() > 0) {
                    brands.get(0);
                    return;
                }
                return;
            }
            if (StringUtils.isEqual(action, "GoodsDetail")) {
                avo.getActionParam().getGoodsInfoId();
                return;
            }
            return;
        }
        if (clz == ImageAdViewHolder.class) {
            GetAppDefaultTemplateResponse.ItemData avo = (GetAppDefaultTemplateResponse.ItemData) viewHolder.item;
            String action = avo.getAction();

            if (StringUtils.isEqual(action, "GoodsList")) {
                GetAppDefaultTemplateResponse.ItemData.ActionParamBean.SearchParamBean spVo = avo.getActionParam().getSearchParam();
                List<String> cates = spVo.getCates();
                if (cates != null && cates.size() > 0) {
                    startActivity(new Intent(context, TypeGoodsListActivity.class)
                            .putExtra("keyword", cates.get(0))
                    );
                    return;
                }
                List<String> brands = spVo.getBrands();
                if (brands != null && brands.size() > 0) {
                    startActivity(new Intent(context, TypeGoodsListActivity.class)
                            .putExtra("keyword", brands.get(0))
                    );
                    return;
                }
 
                return;
            }
            if (StringUtils.isEqual(action, "GoodsDetail")) {
                avo.getActionParam().getGoodsInfoId();
                //商品详情
                String goodsInfoId = avo.getActionParam().getGoodsInfoId();

                startActivity(new Intent(context, GoodsDetailActivity.class)
                        .putExtra("goodsInfoId", Integer.parseInt(goodsInfoId))
                );
                return;
            }
            return;
        }
        if (clz == GoodImageViewHolder.class) {

            GetAppDefaultTemplateResponse.ItemData vo = (GetAppDefaultTemplateResponse.ItemData) adapter.getItem(position);
//            GetAppDefaultTemplateResponse.ItemData vo = bannerDatas.get(position);
            if (StringUtils.isEqual(vo.getAction(), "GoodsDetail")) {

                //商品详情
                String goodsInfoId = vo.getActionParam().getGoodsInfoId();

                startActivity(new Intent(context, GoodsDetailActivity.class)
                        .putExtra("goodsInfoId", Integer.parseInt(goodsInfoId))
                );


            } else if (StringUtils.isEqual(vo.getAction(), "GoodsList")) {
                GetAppDefaultTemplateResponse.ItemData.ActionParamBean.SearchParamBean searchParam = vo.getActionParam().getSearchParam();
//            品牌
                List<String> brands = searchParam.getBrands();
                if (brands != null && brands.size() > 0) {
                    startActivity(new Intent(context, TypeGoodsListActivity.class)
                            .putExtra("keyword", brands.get(0))
                    );
                    return;
                }
//            品类
                List<String> cates = searchParam.getCates();
                if (cates != null && cates.size() > 0) {
                    startActivity(new Intent(context, TypeGoodsListActivity.class)
                            .putExtra("keyword", cates.get(0))
                    );
                    return;
                }
            }

//
//            List<GetAppDefaultTemplateResponse.ItemData> type=vo.getAdverts();
//            startActivity(new Intent(getContext(), GoodsDetailActivity.class)
//                    .putExtra("goodsInfoId", )
//            );
            return;
        }
//        if (clz == ImageMoreAdViewHolder.class) {
//            return;
//        }
//        if (clz == GoodMoreViewHolder.class) {
//            return;
//        }


    };

    GridLayoutManager initLayoutManager() {
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 20);

        gridLayoutManager.setSpanSizeLookup(new IndexSpanSizeLookup(adapter));

        return gridLayoutManager;
    }


    void updateBanner(List listsrc) {
        Logger.e("update banner");
        if (adapter.onBannerChangeListener == null) return;

        if (listsrc == null || listsrc.size() <= 0) {
            List list = new ArrayList(1);
            list.add(R.drawable.banner);
            adapter.onBannerChangeListener.onUpdate(list);

        } else {
            adapter.onBannerChangeListener.onUpdate(listsrc);
        }
    }

    void releaseBanner() {
        if (adapter.onBannerChangeListener == null) return;

        Logger.e("releaseBanner=================");
        adapter.onBannerChangeListener.onRelease();
    }

    List<GetAppDefaultTemplateResponse.ItemData> bannerDatas;
    OnBannerListener onBannerListener = position -> {
        if (bannerDatas == null) return;
        if (position >= bannerDatas.size()) return;

        GetAppDefaultTemplateResponse.ItemData vo = bannerDatas.get(position);
        if (StringUtils.isEqual(vo.getAction(), "GoodsDetail")) {

            //商品详情
            String goodsInfoId = vo.getActionParam().getGoodsInfoId();

            startActivity(new Intent(context, GoodsDetailActivity.class)
                    .putExtra("goodsInfoId", Integer.parseInt(goodsInfoId))
            );


        } else if (StringUtils.isEqual(vo.getAction(), "GoodsList")) {
            GetAppDefaultTemplateResponse.ItemData.ActionParamBean.SearchParamBean searchParam = vo.getActionParam().getSearchParam();
//            品牌
            List<String> brands = searchParam.getBrands();
            if (brands != null && brands.size() > 0) {
                startActivity(new Intent(context, TypeGoodsListActivity.class)
                        .putExtra("keyword", brands.get(0))
                );
                return;
            }
//            品类
            List<String> cates = searchParam.getCates();
            if (cates != null && cates.size() > 0) {
                startActivity(new Intent(context, TypeGoodsListActivity.class)
                        .putExtra("keyword", cates.get(0))
                );
                return;
            }
        }

    };

    void addBanner() {
        adapter.addData("", BannerViewHolder.class);
    }

    void addMenus() {

        int[] ids = context.getResources().getIntArray(R.array.index_menus_id);

        String[] titles = context.getResources().getStringArray(R.array.index_menus_text);

        TypedArray ar = context.getResources().obtainTypedArray(R.array.index_menus_img);
        int len = ar.length();
        int[] imgs = new int[len];
        for (int i = 0; i < len; i++)
            imgs[i] = ar.getResourceId(i, 0);
        ar.recycle();

        for (int i = 0; i < ids.length; i++) {
            MenuVo vo = new MenuVo();
            vo.id = ids[i];
            vo.text = titles[i];
            vo.img = imgs[i];
            addMenu(vo);
        }
    }


    void addMenu(MenuVo vo) {
        adapter.addData(vo, MenuViewHolder.class);
    }


//    void addActives() {
//        addActive1();
//        addActive2();
//        addActive3();
//        addActive4();
//    }

//    void addActive1() {
//        adapter.addData("", Active1ViewHolder.class);
//    }
//
//    void addActive2() {
//        adapter.addData("", Active2ViewHolder.class);
//    }
//
//    void addActive3() {
//        adapter.addData("", Active3ViewHolder.class);
//    }
//
//    void addActive4() {
//        adapter.addData("", Active4ViewHolder.class);
//    }


    void addSpe(GetAppDefaultTemplateResponse.ItemData avo) {
        adapter.addData(avo, ImageSpeViewHolder.class);
    }

    void addAd(GetAppDefaultTemplateResponse.ItemData avo) {
        adapter.addData(avo, ImageAdViewHolder.class);
    }

    void addGood(GetAppDefaultTemplateResponse.ItemData avo) {
        adapter.addData(avo, GoodImageViewHolder.class);
    }

//    void addImageMoreAd(int r2) {
//        adapter.addData(((Integer) r2), ImageMoreAdViewHolder.class);
//    }
//
//    void addMoreGood() {
//        adapter.addData("", GoodMoreViewHolder.class);
//    }


    @AfterPermissionGranted(Presenter.REQUEST_CODE_4_GOTOSCAN)
    public void gotoScan() {
        startActivity(new Intent(context, CaptureResultActivity.class));
    }

    @Override
    public void setHttpDatas(final GetAppDefaultTemplateResponse.DataBean dataBean) {

        adapter.clearDatas();

        initWH4AdapterItem(dataBean);

        changeMsgState();

        addBanner();
        addMenus();


//        addActives();
//        if (secTimer != null) secTimer.start();

        List<GetAppDefaultTemplateResponse.ItemData> floorsList = dataBean.getFloors();
        for (int i = 0, c = floorsList.size(); i < c; i++) {
            GetAppDefaultTemplateResponse.ItemData floorsBean = floorsList.get(i);
            if (i % 4 == 0) {
                GetAppDefaultTemplateResponse.ItemData avo = floorsBean.getAdverts().get(0);
                addSpe(avo);
                continue;
            }
            if (i % 4 == 1) {
                GetAppDefaultTemplateResponse.ItemData avo = floorsBean.getAdverts().get(0);
                addAd(avo);
                continue;
            }
            List<GetAppDefaultTemplateResponse.ItemData> f4s = floorsBean.getAdverts();
            for (GetAppDefaultTemplateResponse.ItemData vo : f4s) {
                addGood(vo);
            }
        }
        ///


//        addImageMoreAd(R.drawable.f1banner);
//        addMoreGood();
//        addMoreGood();

        adapter.notifyDataSetChanged();


        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                List<String> list = new ArrayList<>();
                bannerDatas = dataBean.getSliders();
                for (GetAppDefaultTemplateResponse.ItemData vo : bannerDatas) {
                    list.add(vo.getImg());
                }
                updateBanner(list);
            }
        }.sendEmptyMessageDelayed(0, 800);

    }

    void changeMsgState() {
        ibtn_msg.setImageLevel(1);
    }

    void initWH4AdapterItem(GetAppDefaultTemplateResponse.DataBean dataBean) {
        adapter.banner_height = screen_width * 4 / 7;

        adapter.good_img_wh = scaleWH(4, 2);

        GetAppDefaultTemplateResponse.ItemData floors4 = dataBean.getFloors().get(2);
        adapter.good_img_w = adapter.good_img_wh;
        adapter.good_img_h = (int) (adapter.good_img_w / Float.parseFloat(floors4.getAdverts().get(0).getText()));

        int more_good_span_space = context.getResources().getDimensionPixelSize(R.dimen.dp_16);
        adapter.more_good_img_wh = scaleWH(2, more_good_span_space);

        adapter.active_img_wh = scaleWH(2, 1);
    }

    private int scaleWH(int num, int space) {
        int res = screen_width;
        int wh = space * (num - 1 + 2);
        res -= wh;
        res /= num;
        return res;
    }


    private boolean isLogin() {
        if (StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return false;
        }
        return true;
    }

    @Override
    public void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore) {
        smartRefreshLayout.setEnableRefresh(finishRefresh);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(finishLoadMore);//是否启用上拉加载功能
    }

    @Override
    public void clearHttpDatas() {
        adapter.clearDatas();
    }

    @Override
    public void finishSmartRefresh() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
