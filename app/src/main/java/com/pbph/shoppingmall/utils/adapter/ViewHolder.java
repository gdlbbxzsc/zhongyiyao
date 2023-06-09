package com.pbph.shoppingmall.utils.adapter;

import android.view.View;

public abstract class ViewHolder<T extends Object> {

    protected DataAdapter adapter;

    public int position;

    public T item;

    protected abstract int getLayout();

    protected abstract void getView(View view);

    protected abstract void showView();

}