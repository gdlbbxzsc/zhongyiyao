package com.pbph.shoppingmall.module.main.type.childtypelist;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/5.
 */

@SuppressLint("ValidFragment")
public class ChildTypesFragment extends BaseFragmentV4<ChildTypesPresenter> implements
        ChildTypeContract.View {
    private String typename;
    private int typePos;
    private ExpandableListView expandable_list_view;
    private ChildTypeExpandableAdapter childTypeExpandableAdapter;
    private ImageView imageView;
    private View heardView;

    @Override
    protected int layoutResID() {
        return R.layout.fragment_child_type;
    }


    @Override
    public void initView(View view) {
        expandable_list_view = mContentView.findViewById(R.id.expandable_list_view);
        childTypeExpandableAdapter = new ChildTypeExpandableAdapter(context);
//        imageView = new ImageView(context);
        TextView textView = new TextView(context);
        typename = getArguments().getString("typename");
        typePos = getArguments().getInt("typePos",-1);
        textView.setText(typename);
        heardView = LayoutInflater.from(context).inflate(R.layout.view_type_heard, null);
        imageView = heardView.findViewById(R.id.iv_heard);
        expandable_list_view.addHeaderView(heardView);
//        expandable_list_view.addHeaderView(textView);

    }

    @Override
    protected ChildTypesPresenter createPresenter() {
        return new ChildTypesPresenter(this, context);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.getHttpData(Integer.valueOf(typename));

    }

    /**
     */
    @Override
    public void setHttpData(List<GetClassifyResponse.DataBean.CateBarVosBean> cateBarVosBeans, List<GetClassifyResponse.DataBean.CatesBean> catesBeanList) {

        if (!cateBarVosBeans.isEmpty()){
            Glide.with(context).load(catesBeanList.get(typePos).getCatAdvertisementImage()).transform(new
                    GlideRoundTransform(context, 3)).error(R.drawable.shouye_750x200)
//                .centerCrop()
                    .override(300, 100).into(imageView);
        }

        childTypeExpandableAdapter.setchildTypeBean(cateBarVosBeans);
        expandable_list_view.setAdapter(childTypeExpandableAdapter);
        for (int i = 0; i < cateBarVosBeans.size(); i++) {
            expandable_list_view.expandGroup(i);
        }
        expandable_list_view.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            // TODO Auto-generated method stub
            return true;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
