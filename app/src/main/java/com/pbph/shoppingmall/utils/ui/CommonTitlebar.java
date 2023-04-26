package com.pbph.shoppingmall.utils.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.pop.CommonTitlebarRightListPop;

/**
 * Created by Administrator on 2018/4/3.
 */

public class CommonTitlebar {

    Activity activity;
    Fragment fragment;

    public View layout;

    public ImageButton titlebar_left;
    TextView titlebar_center;
    public ImageButton titlebar_right;

    CommonTitlebarRightListPop pop;

    public CommonTitlebar(Activity activity, String title, boolean right, Object... objs) {

        this.activity = activity;

        layout = activity.findViewById(R.id.titlebar_layout);

        titlebar_left = activity.findViewById(R.id.titlebar_left);

        if (objs != null && objs.length > 0 && objs[0] instanceof Boolean && !(Boolean) objs[0]) {
            titlebar_left.setVisibility(View.GONE);
        } else {
            titlebar_left.setOnClickListener(onSingleClickListener);
        }

        titlebar_center = activity.findViewById(R.id.titlebar_center);
        titlebar_center.setText(title);

        titlebar_right = activity.findViewById(R.id.titlebar_right);
        titlebar_right.setOnClickListener(onSingleClickListener);

        if (right) {
            pop = new CommonTitlebarRightListPop(activity, titlebar_right);
            titlebar_right.setVisibility(View.VISIBLE);
        }

    }

    public CommonTitlebar(Fragment fragment, View view, String title, boolean right, Object... objs) {
        this.fragment = fragment;

        layout = view.findViewById(R.id.titlebar_layout);

        titlebar_left = view.findViewById(R.id.titlebar_left);

        if (objs != null && objs.length > 0 && objs[0] instanceof Boolean && !(Boolean) objs[0]) {
            titlebar_left.setVisibility(View.INVISIBLE);
        } else {
            titlebar_left.setOnClickListener(onSingleClickListener);
        }

        titlebar_center = view.findViewById(R.id.titlebar_center);
        titlebar_center.setText(title);

        titlebar_right = view.findViewById(R.id.titlebar_right);
        titlebar_right.setOnClickListener(onSingleClickListener);

        if (right) {
            pop = new CommonTitlebarRightListPop(activity, titlebar_right);
            titlebar_right.setVisibility(View.VISIBLE);
        }
    }


    OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onCanClick(View v) {
            switch (v.getId()) {
                case R.id.titlebar_left: {

                    if (fragment != null) {
                        fragment.getActivity().finish();
                        return;
                    }

                    activity.finish();
                }
                break;
                case R.id.titlebar_right: {
                    if (pop != null) pop.show();
                }
                break;
            }
        }
    };
}
