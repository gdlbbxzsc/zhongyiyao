package com.pbph.shoppingmall.utils.pop;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.browserecords.BrowseRecordsActivity;
import com.pbph.shoppingmall.module.collect.CollectIndexActivity;
import com.pbph.shoppingmall.module.main.MainActivity;
import com.pbph.shoppingmall.module.message.type.MessageTypeActivity;
import com.pbph.shoppingmall.module.suggest.SuggestActivity;

import java.util.ArrayList;
import java.util.List;


public class CommonTitlebarRightListPop extends PopupWindow implements OnItemClickListener {

    private Context context;
    private Resources resources;
    private LayoutInflater inflater;


    private ListView listView;
    public PopAdapter adapter;

    public View showView;

    public CommonTitlebarRightListPop(Context context) {
        this(context, null);
    }

    public CommonTitlebarRightListPop(Context context, View showView) {
        super(context);
        this.context = context;
        resources = context.getResources();
        inflater = LayoutInflater.from(context);

        setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体的宽
        // MainActivity.screenWidth / 4
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        View view = inflater.inflate(R.layout.pop_common_titlebar_right, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        listView = view.findViewById(R.id.ListViewPop);

        adapter = new PopAdapter();

        initDatas();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        // 刷新状态
        this.update();

        this.showView = showView;

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        PopVo vo = adapter.getItem(arg2);

        switch (vo.id) {
            case 1: {//     消息
                jumpTo(MessageTypeActivity.class);
            }
            break;
            case 2: {//     首页
                jumpTo(MainActivity.class);
            }
            break;
            case 3: {//     分享
            }
            break;
            case 4: {//     我的收藏
                jumpTo(CollectIndexActivity.class);
            }
            break;
            case 5: {//     浏览记录
                jumpTo(BrowseRecordsActivity.class);
            }
            break;
            case 6: {//     意见反馈
                jumpTo(SuggestActivity.class);
            }
            break;
        }
        this.dismiss();
    }

    void jumpTo(Class clz) {
        if (clz == context.getClass()) return;

        Intent intent = new Intent(context, clz);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);


    }

    private void initDatas() {
        int[] ids = context.getResources().getIntArray(R.array.pop_ids);

        String[] titles = context.getResources().getStringArray(R.array.pop_texts);

        TypedArray ar = context.getResources().obtainTypedArray(R.array.pop_imgs);
        int len = ar.length();
        int[] imgs = new int[len];
        for (int i = 0; i < len; i++)
            imgs[i] = ar.getResourceId(i, 0);
        ar.recycle();

        for (int i = 0; i < ids.length; i++) {
            addData(ids[i], titles[i], imgs[i]);
        }
        adapter.notifyDataSetChanged();
    }

    private void addData(int id, String title, int drawable) {

        PopVo vo = new PopVo();
        vo.id = id;
        vo.title = title;
        vo.drawable = drawable;
        adapter.addData(vo);
    }

    public void show() {
        show(showView);
    }

    public void show(View view) {
        if (adapter.getCount() <= 0) {
            return;
        }
        try {
            int width1 = view.getWidth();
            int width2 = listView.getLayoutParams().width;
            if (width1 > width2) {
                listView.getLayoutParams().width = width1;
            }
        } catch (Exception e) {
        }
        this.showAsDropDown(view);
    }


    public class PopAdapter extends BaseAdapter {

        public List<PopVo> viewDataList = new ArrayList<PopVo>();

        @Override
        public int getCount() {
            return viewDataList.size();
        }

        @Override
        public PopVo getItem(int position) {
            return viewDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();

                convertView = inflater.inflate(R.layout.listview_common_titlebar_right, null);

                holder.title = convertView.findViewById(R.id.textViewpop);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final PopVo vo = viewDataList.get(position);

            if (vo.drawable != -1) {
                try {
                    Drawable d = resources.getDrawable(vo.drawable);
                    holder.title.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                    holder.title.setCompoundDrawablePadding(15);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
            // /////////////
            holder.title.setText(vo.title);

            return convertView;
        }

        class ViewHolder {
            TextView title;
        }

        public void addDatas(List<PopVo> datas) {
            viewDataList.addAll(datas);
            notifyDataSetChanged();
        }

        public void setDatas(List<PopVo> datas) {
            viewDataList.clear();
            viewDataList.addAll(datas);
            notifyDataSetChanged();
        }

        public void addData(PopVo data) {
            viewDataList.add(data);
        }

        public void clearDatas() {
            viewDataList.clear();
            notifyDataSetChanged();
        }

        @Override
        public void notifyDataSetChanged() {
            measure();
            super.notifyDataSetChanged();
        }


        private void measure() {
            int last = listView.getLayoutParams().width;
            for (int i = 0; i < getCount(); i++) {
                View listItem = getView(i, null, listView);
                listItem.measure(0, 0);
                int w = listItem.getMeasuredWidth();
                last = last < w ? w : last;
            }
            listView.getLayoutParams().width = last;
        }
    }

    public class PopVo {

        public int id = 0;
        public String title;

        public int drawable = -1;
    }

}
