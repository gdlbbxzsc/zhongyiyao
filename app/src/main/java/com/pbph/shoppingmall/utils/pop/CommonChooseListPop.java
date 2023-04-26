package com.pbph.shoppingmall.utils.pop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.adapter.DataAdapter;


public class CommonChooseListPop extends PopupWindow implements PopupWindow.OnDismissListener {

    private Activity context;
    private LayoutInflater inflater;

    public ListView listView;
    public DataAdapter adapter;

    public View showView;

    OnItemChooseListener listener;

    public CommonChooseListPop(Activity context, View showView, OnItemChooseListener listener) {
        this(context, showView, listener, CommonChooseListViewHolder.class);
    }

    public CommonChooseListPop(Activity context, View showView, OnItemChooseListener listener, Class viewholder) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.showView = showView;
        this.listener = listener;

        setBackgroundDrawable(new BitmapDrawable());

//        WindowManager wm = context.getWindowManager();

//        int width = wm.getDefaultDisplay().getWidth();
        // int height = wm.getDefaultDisplay().getHeight();


        setWidth(LayoutParams.FILL_PARENT);
        setHeight(LayoutParams.FILL_PARENT);

        setFocusable(true);
        setOutsideTouchable(true);

        setOnDismissListener(this);

//        setAnimationStyle(R.style.popwin_rightanim_style);

        View view = inflater.inflate(R.layout.pop_common_choose_list, null);

        setContentView(view);

        LinearLayout layout = view.findViewById(R.id.LinearLayout1);
        layout.setOnClickListener(v -> dismiss());

        listView = view.findViewById(R.id.ListView1);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adapter = new DataAdapter(context, listView, viewholder);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {

            adapter.putChoicedNotify(position);

            Object obj = adapter.getItem(position);
            listener.onItemChoose(obj);

            dismiss();
        });
        update();
    }

    public void show() {
        show(showView);
    }

    public void show(View view) {
        if (adapter.getCount() <= 0) {
            Toast.makeText(context, "暂无退货原因", Toast.LENGTH_SHORT).show();
            return;
        }


//        showAsDropDown(view, 0, 0);
        showAtLocation(view, Gravity.CENTER, 0, 0);
//            showAsDropDown(view, 0, -view.getHeight());

//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);

    }

    @Override
    public void dismiss() {
        super.dismiss();
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 1f;
//        context.getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
    }

    public interface OnItemChooseListener {
        void onItemChoose(Object obj);
    }

}
