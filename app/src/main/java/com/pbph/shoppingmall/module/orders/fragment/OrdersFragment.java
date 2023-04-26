package com.pbph.shoppingmall.module.orders.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.module.logistics.LogisticsDetailActivity;
import com.pbph.shoppingmall.module.orders.adapter.MyOrdersDataAdapter;
import com.pbph.shoppingmall.module.orders.evaluateorders.submit.EvaluateOrderSubmitActivity;
import com.pbph.shoppingmall.module.orders.ioptions.IOptionsData;
import com.pbph.shoppingmall.module.orders.myorders.detail.MyOrderDetailActivity;
import com.pbph.shoppingmall.module.orders.refundorders.submit.ReFundOrderSubmitActivity;
import com.pbph.shoppingmall.module.orders.viewholder.OrdersGoodsMoreViewHolder;
import com.pbph.shoppingmall.module.orders.viewholder.OrdersGoodsOneViewHolder;
import com.pbph.shoppingmall.module.payment.PaymentActivity;
import com.pbph.shoppingmall.module.sendbackinfo.SendBackInfoActivity;
import com.pbph.shoppingmall.module.shoppingcar.ShoppingCarActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class OrdersFragment extends BaseFragmentV4<Presenter> implements Contract.View, AdapterView.OnItemClickListener, IOptionsData {

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private MyOrdersDataAdapter adapter;

    int type_id;

    public static OrdersFragment newInstance(int id) {

        OrdersFragment f = new OrdersFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type_id = getArguments() != null ? getArguments().getInt("id") : -1;
    }

    @Override
    public int getTypeId() {
        return type_id;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_myorders_all;
    }

    @Override
    public void initView(View view) {

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = view.findViewById(R.id.lv_shops1);

        adapter = new MyOrdersDataAdapter(this, listView, 2);
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHttpDatasFirstPage();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        GetOrderResponse.DataBean.ListBean vo = (GetOrderResponse.DataBean.ListBean) adapter.getItem(position);

        startActivity(new Intent(context, MyOrderDetailActivity.class)
                .putExtra("orderId", vo.getPpid())
        );
    }

    DataAdapter.OnItemViewClickListener onItemViewClickListener = (rid, holder, objects) -> {

        try {
            String methodName = (String) objects[0];
            Method method = OrdersFragment.this.getClass().getMethod(methodName, GetOrderResponse.DataBean.ListBean.class);
            GetOrderResponse.DataBean.ListBean vo = (GetOrderResponse.DataBean.ListBean) holder.item;
            method.invoke(OrdersFragment.this, vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        switch (rid) {
//            case R.id.tv_option_1: {
//            }
//            break;
//            case R.id.tv_option_2: {
//            }
//            break;
//            case R.id.tv_option_3: {
//            }
//            break;
//            case R.id.tv_option_4: {
//            }
//            break;
//        }
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
    public void setHttpDatas(List<GetOrderResponse.DataBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        for (GetOrderResponse.DataBean.ListBean vo : list) {

            if (vo.getOrderGoodsDtoList().size() <= 1)
                adapter.addData(vo, OrdersGoodsOneViewHolder.class);
            else
                adapter.addData(vo, OrdersGoodsMoreViewHolder.class);
        }

        adapter.notifyDataSetChanged();
    }


    @Override
    public void buyAgain(GetOrderResponse.DataBean.ListBean vo) {
        presenter.buyAgain(vo);
    }

    @Override
    public void onBuyAgain(GetOrderResponse.DataBean.ListBean vo) {
        startActivity(new Intent(context, ShoppingCarActivity.class));
    }

    @Override
    public void delOrder(GetOrderResponse.DataBean.ListBean vo) {
        YesNoDialog.show(getContext(), "确认删除此订单", 0, position -> {
            presenter.delOrder(vo);
        });
    }

    @Override
    public void onDelOrder(GetOrderResponse.DataBean.ListBean vo) {
        adapter.removeData(vo);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void payOrder(GetOrderResponse.DataBean.ListBean vo) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("orderType", 0);
        intent.putExtra("totolPrice", vo.getOrderPrice());
        intent.putExtra("orderCode", vo.getOrderCode());
        startActivity(intent);
    }

    @Override
    public void cancelOrder(GetOrderResponse.DataBean.ListBean vo) {
        presenter.cancelOrder(vo);
    }

    @Override
    public void onCancelOrder(GetOrderResponse.DataBean.ListBean vo) {
        //        订单类型（0：全部，1：待付款，2：待收货，3：已完成，4：已取消，5：待评价，6：退还售后）
//        全部里面此订单 修改订单状态 重新展示。
        if (type_id == 0) {
            adapter.notifyDataSetChanged();
            return;
        }
//        其他情况从列表中删除即可
        adapter.removeData(vo);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void confirmOrder(GetOrderResponse.DataBean.ListBean vo) {
        presenter.confirmOrder(vo);
    }

    @Override
    public void onConfirmOrder(GetOrderResponse.DataBean.ListBean vo) {
        //        订单类型（0：全部，1：待付款，2：待收货，3：已完成，4：已取消，5：待评价，6：以评价并以晒单，7：售后申请，8：申请记录）
//        全部里面此订单 修改订单状态 重新展示。
        if (type_id == 0) {
            adapter.notifyDataSetChanged();
            return;
        }

        if (type_id == 3) {
            adapter.notifyDataSetChanged();
            return;
        }

        if (type_id == 7) {
            adapter.notifyDataSetChanged();
            return;
        }
//        其他情况从列表中删除即可
        adapter.removeData(vo);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void checkLogistics(GetOrderResponse.DataBean.ListBean vo) {
        startActivity(new Intent(getContext(), LogisticsDetailActivity.class)
                .putExtra("orderId", String.valueOf(vo.getPpid()))
                .putExtra("orderCode",vo.getOrderCode())
        );
    }

    @Override
    public void applyRefundMoney(GetOrderResponse.DataBean.ListBean vo) {

//        未收货	11
//        已收货	13
//        已收货	14


        int goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
        switch (vo.getOrderStatus()) {
            case 11:
                goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
                break;
            case 13:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
            case 14:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
        }


        startActivity(new Intent(getContext(), ReFundOrderSubmitActivity.class)
//                是否可编辑
                        .putExtra("canEdit", true)
//                是否收货
                        .putExtra("goods_type", goods_type)
//                退款退货
                        .putExtra("refund_type", ReFundOrderSubmitActivity.TYPE_REFUND_MONEY)
                        .putExtra("orderId", String.valueOf(vo.getPpid()))
        );
    }

    @Override
    public void evaluateOrder(GetOrderResponse.DataBean.ListBean vo) {
        startActivity(new Intent(context, EvaluateOrderSubmitActivity.class)
                .putExtra("orderId", String.valueOf(vo.getPpid()))
        );
    }

    @Override
    public void refundGoodsDetail(GetOrderResponse.DataBean.ListBean vo) {
        startActivity(new Intent(context, MyOrderDetailActivity.class)
                .putExtra("orderId", vo.getPpid())
        );
    }

    @Override
    public void subLogisticsInfo(GetOrderResponse.DataBean.ListBean vo) {
        startActivity(new Intent(context, SendBackInfoActivity.class)
                .putExtra("orderId", String.valueOf(vo.getPpid()))
        );
    }

    @Override
    public void applyRefundGoods(GetOrderResponse.DataBean.ListBean vo) {


        //        未收货	11
//        已收货	13
//        已收货	14


        int goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
        switch (vo.getOrderStatus()) {
            case 11:
                goods_type = ReFundOrderSubmitActivity.TYPE_UNTAKE_GOODS;
                break;
            case 13:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
            case 14:
                goods_type = ReFundOrderSubmitActivity.TYPE_TAKE_GOODS;
                break;
        }

        startActivity(new Intent(getContext(), ReFundOrderSubmitActivity.class)
//                是否可编辑
                        .putExtra("canEdit", true)
//                是否收货
                        .putExtra("goods_type", goods_type)
//                退款退货
                        .putExtra("refund_type", ReFundOrderSubmitActivity.TYPE_REFUND_GOODS)
                        .putExtra("orderId", String.valueOf(vo.getPpid()))
        );

    }

    @Override
    public void refundMoneysDetail(GetOrderResponse.DataBean.ListBean vo) {
        startActivity(new Intent(context, MyOrderDetailActivity.class)
                .putExtra("orderId", vo.getPpid())
        );
    }
}
