package com.pbph.shoppingmall.module.orders.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseActivity;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.module.logistics.LogisticsDetailActivity;
import com.pbph.shoppingmall.module.orders.adapter.MyOrdersDataAdapter;
import com.pbph.shoppingmall.module.orders.evaluateorders.submit.EvaluateOrderSubmitActivity;
import com.pbph.shoppingmall.module.orders.ioptions.IOptionsData;
import com.pbph.shoppingmall.module.orders.myorders.detail.MyOrderDetailActivity;
import com.pbph.shoppingmall.module.orders.refundorders.submit.ReFundOrderSubmitActivity;
import com.pbph.shoppingmall.module.orders.viewholder.OrdersGoodsOneViewHolder;
import com.pbph.shoppingmall.module.orders.viewholder.OrdersGoodsMoreViewHolder;
import com.pbph.shoppingmall.module.sendbackinfo.SendBackInfoActivity;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;
import com.pbph.shoppingmall.utils.dialog.YesNoDialog;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

public class OrdersSearchActivity extends BaseActivity<Presenter> implements Contract.View, AdapterView.OnItemClickListener, IOptionsData {


    ImageButton ibtn_left;
    EditText edt_search;
    TextView ibtn_msg;

    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private MyOrdersDataAdapter adapter;


    int type_id;

    @Override
    protected void initSysSetting() {
        super.initSysSetting();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_myorderssearch;
    }


    @Override
    protected void initData() {
        getIntent().getIntExtra("type_id", 0);
    }

    @Override
    protected void initView() {

//        View view = findViewById(R.id.titlebar_layout);
//        view.setBackgroundColor(Color.WHITE);

        ibtn_left = findViewById(R.id.ibtn_left);
        ibtn_left.setOnClickListener(onSingleClickListener);

        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(textWatcher);
        edt_search.setOnEditorActionListener(onEditorActionListener);


        ibtn_msg = findViewById(R.id.ibtn_msg);
        ibtn_msg.setOnClickListener(onSingleClickListener);

        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        enableSmartRefresh(true, false);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setHeaderHeight(60);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> presenter.getHttpDatasFirstPage());
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> presenter.getHttpDatasNextPage());


        listView = findViewById(R.id.lv_shops1);
        listView.setVisibility(View.GONE);
        adapter = new MyOrdersDataAdapter(this, listView, 2);
        adapter.setListener(onItemViewClickListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }


    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.ibtn_left: {
                    if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                        edt_search.setText("");
                        adapter.clearDatas();
                        listView.setVisibility(View.GONE);
                        return;
                    }
                    finish();
                }
                break;
                case R.id.ibtn_msg: {
                    presenter.doSearch(edt_search.getText().toString().trim());
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
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!StringUtils.isEmpty(edt_search.getText().toString())) {
                edt_search.setText("");
                presenter.doSearch("");
                adapter.clearDatas();
                listView.setVisibility(View.GONE);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
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
            Method method = OrdersSearchActivity.this.getClass().getMethod(methodName, GetOrderResponse.DataBean.ListBean.class);
            GetOrderResponse.DataBean.ListBean vo = (GetOrderResponse.DataBean.ListBean) holder.item;
            method.invoke(OrdersSearchActivity.this, vo);
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
    public int getTypeId() {
        return type_id;
    }

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
        listView.setVisibility(View.GONE);
    }

    @Override
    public void setHttpDatas(List<GetOrderResponse.DataBean.ListBean> list) {

        if (list == null || list.size() <= 0) return;

        listView.setVisibility(View.VISIBLE);

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
        //        订单类型（0：全部，1：待付款，2：待收货，3：已完成，4：已取消，5：待评价，6：退还售后）
//        全部里面此订单 修改订单状态 重新展示。
        if (type_id == 0) {
            adapter.notifyDataSetChanged();
            return;
        }

        if (type_id == 3) {
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
                .putExtra("orderCode", String.valueOf(vo.getOrderCode()))
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
        startActivity(new Intent(context, SendBackInfoActivity.class));
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
