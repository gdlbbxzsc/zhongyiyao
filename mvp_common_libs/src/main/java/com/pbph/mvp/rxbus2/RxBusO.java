package com.pbph.mvp.rxbus2;

/**
 * Created by Administrator on 2018/2/9.
 */

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


public class RxBusO {

    public static RxBusO getInstance() {
        return InnerInstance.INSTANCE;
    }

    private static class InnerInstance {
        private static final RxBusO INSTANCE = new RxBusO();
    }

    //
    private final Subject<Object> mBus;
    private final Map<String, CompositeDisposable> map = new HashMap();

    private RxBusO() {
        mBus = PublishSubject.create().toSerialized();
    }

    //
    public static void post0(@NonNull Object obj) {
        getInstance().post(obj);
    }

    public void post(@NonNull Object obj) {
        mBus.onNext(obj);
    }


    ///

    public static <T> void register0(String key, Class<T> clz, Consumer<T> consumer) {

        Disposable disposable = register0(clz, consumer);

        addDisposable0(key, disposable);
    }

    public static <T> Disposable register0(Class<T> clz, Consumer<T> consumer) {
        return register0(clz).subscribe(consumer);
    }

    public static <T> Observable<T> register0(Class<T> clz) {
        return getInstance().register(clz);
    }

    public <T> Observable<T> register(Class<T> tClass) {
        return register().ofType(tClass);
    }

    public Observable<Object> register() {
        mBus.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return mBus;
    }

    //
    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public void unregisterAll() {
        //会将所有由mBus生成的Observable都置completed状态,后续的所有消息都收不到了
        mBus.onComplete();
    }

    //
    public static void addDisposable0(String key, Disposable value) {
        getInstance().addDisposable(key, value);
    }

    public void addDisposable(String key, Disposable value) {
        CompositeDisposable cd = getInstance().map.get(key);
        if (cd == null) {
            cd = new CompositeDisposable();
            getInstance().map.put(key, cd);
        }
        cd.add(value);
    }

    public static void removeDisposable0(String key) {
        getInstance().removeDisposable(key);
    }

    public void removeDisposable(String key) {
        CompositeDisposable cd = getInstance().map.get(key);
        if (cd == null) return;
        cd.clear();
    }

}