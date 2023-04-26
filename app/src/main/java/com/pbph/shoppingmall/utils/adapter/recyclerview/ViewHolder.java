package com.pbph.shoppingmall.utils.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;


public abstract class ViewHolder {

    protected DataAdapter adapter;

    public int position;

    public Object item;


    protected RecyclerViewViewHolder recyclerViewViewHolder;

    protected abstract int getLayout();


    protected abstract void getView(View itemView);

    protected abstract void showView();

    protected class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder viewHolder;

        public RecyclerViewViewHolder(ViewHolder viewHolder, View itemView) {
            super(itemView);
            this.viewHolder = viewHolder;
        }
    }

    OnSingleClickListener listener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            if (adapter == null) return;
            adapter.getOnItemClickListener().onItemClick(adapter.recyclerView, ViewHolder.this, position);
        }
    };

}