package com.pbph.shoppingmall.module.main.type.childtypelist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.module.typegoodslist.TypeGoodsListActivity;

import java.util.List;


/**
 * Created by 连嘉凡 on 2018/3/5.
 */

public class ChildTypeExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GetClassifyResponse.DataBean.CateBarVosBean> cateBarVosBeans;
    private LayoutInflater layoutInflater;


    public ChildTypeExpandableAdapter(Context context1) {
        this.context = context1;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setchildTypeBean(List<GetClassifyResponse.DataBean.CateBarVosBean>
                                         cateBarVosBeans) {
        this.cateBarVosBeans = cateBarVosBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return cateBarVosBeans != null ? cateBarVosBeans.size() : 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return cateBarVosBeans.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return cateBarVosBeans.get(i).getChilds().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int j) {
        return j;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        FViewHolder fViewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_type_y_layout, viewGroup, false);
            fViewHolder = new FViewHolder(view);
            view.setTag(fViewHolder);
        } else {
            fViewHolder = (FViewHolder) view.getTag();
        }
        fViewHolder.tvF.setText(cateBarVosBeans.get(i).getName());

        return view;
    }

    @Override
    public View getChildView(int i, int j, boolean b, View view, ViewGroup viewGroup) {
        CViewHolder cViewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_type_c_layout, viewGroup, false);
            cViewHolder = new CViewHolder(view);
            view.setTag(cViewHolder);
        } else {
            cViewHolder = (CViewHolder) view.getTag();

        }

        ChildTypesGridAdapter childTypesGridAdapter = new ChildTypesGridAdapter(context,
                cateBarVosBeans.get(i).getChilds());
        cViewHolder.child_gv.setAdapter(childTypesGridAdapter);
        cViewHolder.child_gv.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(context, TypeGoodsListActivity.class);
            intent.putExtra("cateId", cateBarVosBeans.get(i).getChilds().get(position)
                    .getGoodsCateId());
            context.startActivity(intent);
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class FViewHolder {

        private TextView tvF;

        public FViewHolder(View view) {
            tvF = view.findViewById(R.id.tvF);
        }
    }

    class CViewHolder {
        private GridView child_gv;

        public CViewHolder(View view) {
            child_gv = view.findViewById(R.id.child_gv);

        }
    }
}
