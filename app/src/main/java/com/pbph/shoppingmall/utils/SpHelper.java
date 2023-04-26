package com.pbph.shoppingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.utils.StringUtils;

public final class SpHelper {

    private static volatile SpHelper instance = null;

    public static SpHelper getInstance() {
        if (instance == null) {
            synchronized (SpHelper.class) {
                if (instance == null) {
                    instance = new SpHelper();
                }
            }
        }
        return instance;
    }

    //    private SpHelper() {


    private SharedPreferences sp;

    public void init(Context context) {
        String SP_NAME = context.getPackageName() + "_sp";
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public boolean putAccountPassword(String username, String password) {

        if (StringUtils.isEmpty(username)) username = "";
        if (StringUtils.isEmpty(password)) password = "";

        SharedPreferences.Editor editor = sp.edit();//
        editor.putString("username", username);//
        editor.putString("password", password);//
        return editor.commit();
    }

    public boolean putPassword(String password) {

        if (StringUtils.isEmpty(password)) password = "";

        SharedPreferences.Editor editor = sp.edit();//
        editor.putString("password", password);//
        return editor.commit();
    }

    public String getPassword() {
        return sp.getString("password", "");
    }

    public String getAccount() {
        return sp.getString("username", "");
    }


    //
    public boolean putRemember(boolean isRemember) {
        SharedPreferences.Editor editor = sp.edit();//
        editor.putBoolean("isRemember", isRemember);//
        return editor.commit();
    }

    public boolean isRemember() {
        return sp.getBoolean("isRemember", false);
    }

//    //
//    public boolean putUserId(int userId) {
//        SharedPreferences.Editor editor = sp.edit();//
//        editor.putInt("UserId", userId);//
//        return editor.commit();
//    }
//
//    public int getUserId() {
//        return sp.getInt("UserId", 0);
//    }


    public boolean putUserId(int id) {
        SharedPreferences.Editor editor = sp.edit();//
        editor.putInt("userid", id);//
        return editor.commit();
    }

    public Integer getUserId() {
        int id = sp.getInt("userid", 0);
        if (id == 0) return null;
        return id;
    }


    public boolean putToken(String token) {

        if (StringUtils.isEmpty(token)) token = "";

        SharedPreferences.Editor editor = sp.edit();//
        editor.putString("token", token);//
        return editor.commit();
    }

    public String getToken() {
        return sp.getString("token", "");
    }


}
