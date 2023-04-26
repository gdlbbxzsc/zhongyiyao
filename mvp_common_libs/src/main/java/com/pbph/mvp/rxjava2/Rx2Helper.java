package com.pbph.mvp.rxjava2;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Rx2Helper {

    //    public MyFlowable<T> applySchedulers() {
//        return (MyFlowable<T>) MyFlowable.this.onBackpressureBuffer().subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap((t) -> flatFlowableResponse(t));
//    }
//
//    private MyFlowable<T> flatFlowableResponse(@NonNull final T response) {
//        return (MyFlowable<T>) MyFlowable.create((flowableEmitter) -> {
//            flowableEmitter.onNext(response);
//            flowableEmitter.onComplete();
//        }, BackpressureStrategy.BUFFER);
//    }

    public static <T extends Object> Observable<T> applySchedulers(Observable<T> observable) {

        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//                .filter((it) -> {
//                    Toast.makeText(context, "sdfsdf", Toast.LENGTH_SHORT).show();
//                    return false;
//                })
//                .filter((it) -> {
//
//                    new AlertDialog.Builder(context)
//                            .setTitle("确定框")
//                            .setMessage("简单消息框")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//                                public void onClick(DialogInterface dialog, int which) {
//                                    //按钮事件
//                                }
//                            })
//                            .show();
//                    return false;
//                });
//                .filter(new aa<T>());
    }


//防止数据频繁发送,一段时间s内只能有一个数据请求发送，但是同时，该请求也会延迟s长时间后发送，取舍在己
//.debounce(999, TimeUnit.MILLISECONDS)//或者为throttleWithTimeout(1000, TimeUnit.MILLISECONDS)

//    doOnSubscribe： 注册一个动作，在观察者订阅时使用。内部由OperatorDoOnSubscribe实现，
// doon 是一些列操作 作用如其名字。
}
