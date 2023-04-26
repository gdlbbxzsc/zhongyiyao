package com.pbph.shoppingmall.module.main.index.viewholder;

import android.view.View;
import android.widget.LinearLayout;

import com.android.utils.Logger;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.module.main.index.IndexDataAdapter;
import com.pbph.shoppingmall.utils.adapter.recyclerview.ViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.loader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;


public class BannerViewHolder extends ViewHolder implements IndexDataAdapter.OnBannerChangeListener {

    Banner banner;

    @Override
    protected int getLayout() {
        return R.layout.recyclerview_index_banner;
    }

    @Override
    protected void getView(View view) {

        Logger.e("getview banner===============");

        IndexDataAdapter myAdapter = (IndexDataAdapter) adapter;

        banner = view.findViewById(R.id.index_banner);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) banner.getLayoutParams();
        params.height = myAdapter.banner_height;
        banner.setLayoutParams(params);

        banner.setImageLoader(new GlideImageLoader());
        ArrayList<String> list = new ArrayList<String>();

        banner.setImages(list);

        banner.setOnBannerListener(myAdapter.listener);

        banner.start();

        myAdapter.onBannerChangeListener = this;
    }

    @Override
    protected void showView() {
    }

    @Override
    public void onUpdate(List list) {
        banner.update(list);
    }

    @Override
    public void onRelease() {
        banner.releaseBanner();
    }
}
