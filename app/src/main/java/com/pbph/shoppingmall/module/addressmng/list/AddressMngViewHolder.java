package com.pbph.shoppingmall.module.addressmng.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.utils.adapter.ViewHolder;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public class AddressMngViewHolder extends ViewHolder<GetAddressListResponse.DataBean> {

    private TextView tvName;
    private TextView tvCall;
    private TextView tvAddress;
    private TextView tvDel;
    private TextView tvEdit;

    private LinearLayout ll_def;
    private ImageView iv_def;

    @Override

    protected int getLayout() {
        return R.layout.listview_addressmng;
    }

    @Override
    protected void getView(View view) {
        tvName = view.findViewById(R.id.tv_name);
        tvCall = view.findViewById(R.id.tv_call);
        tvAddress = view.findViewById(R.id.tv_bill_id);

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

        tvName.setText(item.getAddressName());

        tvCall.setText(item.getAddressMoblie());

        tvAddress.setText(item.getProvinceName());
        tvAddress.append(item.getCityName());
        tvAddress.append(item.getDistrictName());
        tvAddress.append(item.getAddressDetail());

        iv_def.setImageLevel(adapter.isChoiced(position) ? 1 : 0);
    }


    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {

            switch (v.getId()) {
                case R.id.tv_del: {
                    adapter.getListener().onItemViewClick(v.getId(), AddressMngViewHolder.this);
                }
                break;
                case R.id.tv_edit: {
                    adapter.getListener().onItemViewClick(v.getId(), AddressMngViewHolder.this);
                }
                break;
                case R.id.ll_def: {
                    adapter.getListener().onItemViewClick(v.getId(), AddressMngViewHolder.this);
                }
                break;
            }
        }
    };


}
