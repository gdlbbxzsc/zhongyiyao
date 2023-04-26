package com.pbph.shoppingmall.module.firmorder.invoice;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pbph.shoppingmall.R;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/10/18 16:42
 * @ email：gdutxiaoxu@163.com
 */
public class InvoiceTypeAdapter extends RecyclerView.Adapter <InvoiceTypeAdapter.InvoiceTypeListHolder> implements View.OnClickListener{

    private List<InvoiceTypeBean> invoiceTypeBeans ;
    private Context context;
    private OnTypeItemClickListener mOnItemClickListener = null;
    public InvoiceTypeAdapter(Context context, List<InvoiceTypeBean> list) {
        this.context = context;
        this.invoiceTypeBeans = list;
    }
    public InvoiceTypeAdapter(Context context) {
        this.context = context;
    }
    public interface OnTypeItemClickListener {
        void onTypeItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            int position = Integer.parseInt(v.getTag().toString());
            mOnItemClickListener.onTypeItemClick(v,position);
        }
    }
    public void setOnTypeItemClickListener(OnTypeItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public InvoiceTypeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceTypeListHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(InvoiceTypeListHolder holder, final int position) {
        holder.tvInvoiceType.setText(invoiceTypeBeans.get(position).getInvoiceTypeName());
        if (invoiceTypeBeans.get(position).isChecked()){
            holder.tvInvoiceType.setBackgroundResource(R.drawable.invoice_type_item_sel_bg);
            holder.tvInvoiceType.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            holder.tvInvoiceType.setBackgroundResource(R.drawable.invoice_type_item_not_sel_bg);
            holder.tvInvoiceType.setTextColor(context.getResources().getColor(R.color.black_gray));
        }
        holder.tvInvoiceType.setTag(position);
        holder.tvInvoiceType.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return invoiceTypeBeans.size();
    }
    static class InvoiceTypeListHolder extends RecyclerView.ViewHolder {
        View card;
        TextView tvInvoiceType;
        public InvoiceTypeListHolder(View view) {
            super(view);
            card = view;
            tvInvoiceType = view.findViewById(R.id.tv_invoice);
        }
    }
    public void notifyData(List<InvoiceTypeBean> list){
        this.invoiceTypeBeans = list;
        this.notifyDataSetChanged();
    }
}
