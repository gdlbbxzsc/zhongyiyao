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
public class InvoiceContentAdapter extends RecyclerView.Adapter <InvoiceContentAdapter.InvoiceContentListHolder> implements View.OnClickListener{

    private List<InvoiceContentBean> invoiceContentBeans ;
    private Context context;
    private OnContentItemClickListener mOnContentItemClickListener = null;
    public InvoiceContentAdapter(Context context, List<InvoiceContentBean> list) {
        this.context = context;
        this.invoiceContentBeans = list;
    }
    public InvoiceContentAdapter(Context context) {
        this.context = context;
    }
    public interface OnContentItemClickListener {
        void onContentItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnContentItemClickListener != null) {
            //注意这里使用getTag方法获取position
            int position = Integer.parseInt(v.getTag().toString());
            mOnContentItemClickListener.onContentItemClick(v,position);
        }
    }
    public void setOnContentItemClickListener(OnContentItemClickListener listener) {
        this.mOnContentItemClickListener = listener;
    }

    @Override
    public InvoiceContentListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceContentListHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(InvoiceContentListHolder holder, final int position) {
        holder.tvInvoiceContent.setText(invoiceContentBeans.get(position).getInvoiceContentName());
        if (invoiceContentBeans.get(position).isChecked()){
            holder.tvInvoiceContent.setBackgroundResource(R.drawable.invoice_type_item_sel_bg);
            holder.tvInvoiceContent.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            holder.tvInvoiceContent.setBackgroundResource(R.drawable.invoice_type_item_not_sel_bg);
            holder.tvInvoiceContent.setTextColor(context.getResources().getColor(R.color.black_gray));
        }
        holder.tvInvoiceContent.setTag(position);
        holder.tvInvoiceContent.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return invoiceContentBeans.size();
    }
    static class InvoiceContentListHolder extends RecyclerView.ViewHolder {
        View card;
        TextView tvInvoiceContent;
        public InvoiceContentListHolder(View view) {
            super(view);
            card = view;
            tvInvoiceContent = view.findViewById(R.id.tv_invoice);
        }
    }
    public void notifyData(List<InvoiceContentBean> list){
        this.invoiceContentBeans = list;
        this.notifyDataSetChanged();
    }
}
