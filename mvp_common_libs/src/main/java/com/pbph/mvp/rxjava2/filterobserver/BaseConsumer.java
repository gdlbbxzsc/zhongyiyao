package com.pbph.mvp.rxjava2.filterobserver;

import android.content.Context;
import android.widget.Toast;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.mvp.rxjava2.errorlistener.OnErrorToastShortListener;

import io.reactivex.functions.Consumer;


public abstract class BaseConsumer<T extends BaseResponesModel> extends BaseFilter implements Consumer<T> {


    protected boolean filterAllError;

    public BaseConsumer(Context context) {
        this(context, false);
    }

    public BaseConsumer(Context context, boolean filterAllError) {
        super(context);

        this.filterAllError = filterAllError;

//        putDataFilter(300, "系统错误", OnErrorToastShortListener.class);
        putDataFilter(408, "oss信息为空，请联系管理员。", OnErrorToastShortListener.class);
        putDataFilter(409, "参数错误", OnErrorToastShortListener.class);
        putDataFilter(410, "签名错误", OnErrorToastShortListener.class);
//        putDataFilter(, "token错误", OnErrorToastShortListener.class);
        putDataFilter(412, "签名或token错误", OnErrorToastShortListener.class);

    }

    @Override
    public void accept(T vo) throws Exception {
        boolean b = datafilter(vo);
        if (!b) return;

        if (filterAllError && vo.getCode() != 200) {
            Toast.makeText(getContext(), vo.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }

        onDo(vo);
    }

    public abstract void onDo(T vo) throws Exception;
}
