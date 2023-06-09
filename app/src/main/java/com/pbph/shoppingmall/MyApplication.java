package com.pbph.shoppingmall;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.android.utils.Logger;
import com.android.utils.SystemUtils;
import com.pbph.mvp.base.BaseApplication;
import com.pbph.shoppingmall.greendao.DaoMaster;
import com.pbph.shoppingmall.greendao.DaoSession;
import com.pbph.shoppingmall.module.account.login.LoginActivity;
import com.pbph.shoppingmall.utils.SpHelper;
import com.utils.StringUtils;


public class MyApplication extends BaseApplication {

    public static MyApplication instances;

    public static MyApplication getInstances() {
        return instances;
    }


    public static UserInfo userInfo = UserInfo.getInstance();


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("MyApplication onCreate===>" + getPackageName());

        if (!getApplicationInfo().packageName.equals(SystemUtils.getCurProcessName(getApplicationContext())))
            return;

        instances = this;


        SpHelper.getInstance().init(this);
        //greendao
        setDatabase();
    }


    //greendao
    private DaoMaster.DevOpenHelper mHelper;
    //private Helper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        //mHelper = new Helper(new GreenDaoUtils(this));
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


    public static boolean checkLogin(Context context) {
        if (MyApplication.userInfo.getCustomerId() == null || StringUtils.isEmpty(MyApplication.userInfo.getToken())) {
            context.startActivity(new Intent(context, LoginActivity.class));
            return false;
        }
        return true;
    }
}
