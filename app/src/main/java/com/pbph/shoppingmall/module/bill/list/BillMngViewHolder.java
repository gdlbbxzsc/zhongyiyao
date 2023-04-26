package com.pbph.shoppingmall.module.bill.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;
import com.utils.StringUtils;


public class BillMngViewHolder extends ViewHolder<GetBillListResponse.DataBean> {

    private TextView tvName;
    private TextView tvType;
    private TextView tvBillId;
    private TextView tvDel;
    private TextView tvEdit;

    private LinearLayout ll_def;
    private ImageView iv_def;


    @Override

    protected int getLayout() {
        return R.layout.listview_billmng;
    }

    @Override
    protected void getView(View view) {
        tvName = view.findViewById(R.id.tv_name);
        tvType = view.findViewById(R.id.tv_type);
        tvBillId = view.findViewById(R.id.tv_bill_id);

        tvDel = view.findViewById(R.id.tv_del);
        tvDel.setOnClickListener(onSingleClickListener);
        tvEdit = view.findViewById(R.id.tv_edit);
        tvEdit.setOnClickListener(onSingleClickListener);

        ll_def = view.findViewById(R.id.ll_def);
        ll_def.setOnClickListener(onSingleClickListener);

        iv_def = view.findViewById(R.id.iv_def);

    }

    @Override
    protected void showView() {


        tvName.setText(item.getBillTitle());

        if (item.getBillType().equals("1")) {
            tvType.setText("公司");
            tvBillId.setText(item.getBillParagraph());
            tvBillId.setVisibility(View.VISIBLE);
        } else {
            tvType.setText("个人");
            tvBillId.setText(null);
            tvBillId.setVisibility(View.GONE);
        }


        iv_def.setImageLevel(adapter.isChoiced(position) ? 1 : 0);
    }


    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {

            switch (v.getId()) {
                case R.id.tv_del: {
                    adapter.getListener().onItemViewClick(v.getId(), BillMngViewHolder.this);
                }
                break;
                case R.id.tv_edit: {
                    adapter.getListener().onItemViewClick(v.getId(), BillMngViewHolder.this);
                }
                break;
                case R.id.ll_def: {
                    adapter.getListener().onItemViewClick(v.getId(), BillMngViewHolder.this);
                }
                break;
            }
        }
    };


}
