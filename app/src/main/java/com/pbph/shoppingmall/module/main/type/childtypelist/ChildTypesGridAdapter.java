package com.pbph.shoppingmall.module.main.type.childtypelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/5.
 */

public class ChildTypesGridAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<GetClassifyResponse.DataBean.CateBarVosBean.ChildsBeanX> childsBeanXES;
    private Context context;

    public ChildTypesGridAdapter(Context context, List<GetClassifyResponse.DataBean
            .CateBarVosBean.ChildsBeanX> childsBeanXES) {
        this.context = context;
        this.childsBeanXES = childsBeanXES;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (childsBeanXES != null && childsBeanXES.size() > 0)
            return childsBeanXES.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return childsBeanXES.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder view;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_child_type_layout, null);
            view = new MyViewHolder(convertView);
            convertView.setTag(view);
        } else {
            view = (MyViewHolder) convertView.getTag();
        }
//        view.icon.setBackgroundResource(R.drawable.ic_launcher_background);
        GetClassifyResponse.DataBean.CateBarVosBean.ChildsBeanX childsBeanX = childsBeanXES.get
                (position);
        Glide.with(context)
                .load(childsBeanX.getCateImage())
                .error(R.drawable.fenlei_110x110)
                .into(view.typeicon);
        view.typename.setText(childsBeanXES.get(position).getName());

        return convertView;
    }


    private class MyViewHolder {
        private ImageView typeicon;
        private TextView typename;
        public MyViewHolder(View view) {
            typeicon = view.findViewById(R.id.typeicon);
            typename = view.findViewById(R.id.typename);
        }

    }
}
