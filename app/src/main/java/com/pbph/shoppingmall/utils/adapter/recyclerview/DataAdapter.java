package com.pbph.shoppingmall.utils.adapter.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder.RecyclerViewViewHolder> {

    public Object activity;
    public Context context;
    public LayoutInflater inflater;
    public Resources resources;

    public RecyclerView recyclerView;// adapter 持有者控件

    public int choice_type = ListView.CHOICE_MODE_NONE;

    //
    private int view_type_count = 1;
    // key position value view type
    private List<Class> viewTypeCount;
    // viewDataList.get(i)'s viewholder is viewTypeList.get(i)
    private List<Integer> viewTypeList;
    // vo
    public List<Object> viewDataList = new ArrayList<Object>(10);
    //

    // if CHOICE_MODE_NONE not use
    // if CHOICE_MODE_SINGLE it just has only one key that you choiced position.
    // if CHOICE_MODE_MULTIPLE has many choice positions wich you choiced.
    public Map<Integer, Boolean> choiceMap;

    private OnItemClickListener onItemClickListener;

    private DataAdapter(Object activity, RecyclerView view, Class viewholder,
                        int view_type_count) {
        this.activity = activity;
        if (activity instanceof Activity) {
            context = (Activity) activity;
        } else {
            context = ((Fragment) activity).getActivity();
        }
        this.inflater = LayoutInflater.from(context);
        this.resources = (context).getResources();

        this.recyclerView = recyclerView;

        this.view_type_count = view_type_count;

        viewTypeCount = new ArrayList<Class>(view_type_count);
        if (viewholder != null) {
            viewTypeCount.add(viewholder);
        }
        if (view_type_count > 1) {
            viewTypeList = new ArrayList<Integer>(10);
        }
    }


    public DataAdapter(Object activity, RecyclerView recyclerView) {
        this(activity, recyclerView, null);
    }

    public DataAdapter(Object activity, RecyclerView view, Class viewholder) {
        this(activity, view, viewholder, 1);
    }

    public DataAdapter(Object activity, RecyclerView view, int view_type_count) {
        this(activity, view, null, view_type_count);
    }

    @Override
    public int getItemCount() {
        return viewDataList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (viewTypeList == null || viewTypeList.size() <= 0) {
            return 0;
        }
        return viewTypeList.get(position);
    }

    public Object getItem(int position) {
        return viewDataList.get(position);
    }

    public Class getItemViewTypeClass(int position) {
        return viewTypeCount.get(getItemViewType(position));
    }

    @Override
    public ViewHolder.RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;

        try {
            holder = (ViewHolder) Class.forName(viewTypeCount.get(viewType).getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = LayoutInflater.from(context).inflate(holder.getLayout(), null);
        holder.recyclerViewViewHolder = holder.new RecyclerViewViewHolder(holder, view);

        holder.adapter = this;
        view.setOnClickListener(holder.listener);

        holder.getView(holder.recyclerViewViewHolder.itemView);

        return holder.recyclerViewViewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder.RecyclerViewViewHolder holder, int position) {

        holder.viewHolder.position = position;
        holder.viewHolder.item = viewDataList.get(position);
        holder.viewHolder.showView();
    }


    // ////single adapter use.*if separator adapter use will get error;

    public <T extends Object> void setDatas(List<T> datas) {

        clearDatas();

        addDatas(datas);
    }

    //
    public <T extends Object> void addDatas(List<T> datas) {
        if (datas != null && datas.size() > 0) {
            viewDataList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    //
    public <T extends Object> void addData(T data) {
        viewDataList.add(data);
        notifyDataSetChanged();
    }

    public <T extends Object> void addData(int pos, T data) {
        if (pos >= viewDataList.size()) {
            pos = viewDataList.size();
        }
        viewDataList.add(pos, data);
    }

    public <T extends Object> void addData(T data, Class viewholder) {

        addData(null, data, viewholder);
    }

    public <T extends Object> void addData(Integer pos, T data, Class viewholder) {

        int type = viewTypeCount.indexOf(viewholder);
        if (type == -1) {
            viewTypeCount.add(viewholder);
            type = viewTypeCount.indexOf(viewholder);
        }
        if (pos == null) {
            viewDataList.add(data);
            viewTypeList.add(type);
        } else {
            if (pos >= viewDataList.size()) {
                pos = viewDataList.size();
            }
            viewDataList.add(pos, data);
            viewTypeList.add(pos, type);
        }
    }

    //
    public void removeData(int pos) {
        viewDataList.remove(pos);
        if (viewTypeList != null) {
            viewTypeList.remove(pos);
        }
    }

    public <T extends Object> void removeData(T vo) {
        int pos = viewDataList.indexOf(vo);
        if (pos < 0) {
            return;
        }
        viewDataList.remove(pos);
        if (viewTypeList != null) {
            viewTypeList.remove(pos);
        }
    }

    //
    public void clearDatas() {
        viewDataList.clear();
        if (viewTypeList != null) {
            viewTypeList.clear();
        }
        clearChoices();
    }

    //
    public void clearChoices() {
        if (choiceMap == null) {
            return;
        }
        choiceMap.clear();
        notifyDataSetChanged();
    }

    public void putChoicedNotify(int pos) {
        putChoiced(pos);
        notifyDataSetChanged();
    }

    public void putChoiced(int pos) {
        switch (choice_type) {
            case ListView.CHOICE_MODE_SINGLE: {
                choiceMap.clear();
                choiceMap.put(pos, true);
            }
            break;
            case ListView.CHOICE_MODE_MULTIPLE: {
                Object obj = choiceMap.get(pos);
                if (obj != null) {
                    choiceMap.remove(pos);
                } else {
                    choiceMap.put(pos, true);
                }
            }
            break;
        }
    }


    public boolean isChoiced(int pos) {
        return choiceMap.get(pos) != null;
    }

    //
    public void setChoice_type(int choice_type) {
        this.choice_type = choice_type;

        if (ListView.CHOICE_MODE_NONE != choice_type) {
            choiceMap = new HashMap<Integer, Boolean>(10);
        }
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}