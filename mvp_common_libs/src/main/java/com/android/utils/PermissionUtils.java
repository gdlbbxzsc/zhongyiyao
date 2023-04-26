package com.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.pbph.mvp.R;
import com.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public final class PermissionUtils {

    //    这个是谷歌第三方权限框架才能用的方法，如果不是EasyPermissions，请注释掉
    public static void requestByEasyPermissions(Activity activity, int requestCode, String[] perms) {

        String permsStr = StringUtils.builderJoin("\n", perms);
        EasyPermissions.requestPermissions(activity,
                "需要获取:\n" + permsStr + "权限",
                R.string.lib_perms_yes,
                R.string.lib_perms_no,
                requestCode,
                perms);
    }

    public static void requestByEasyPermissions(Fragment fragment, int requestCode, String[] perms) {
        String permsStr = StringUtils.builderJoin("\n", perms);
        EasyPermissions.requestPermissions(fragment,
                "需要获取:\n" + permsStr + "权限",
                R.string.lib_perms_yes,
                R.string.lib_perms_no,
                requestCode,
                perms);
    }

    public static void requestByEasyPermissions(android.support.v4.app.Fragment fragment, int requestCode, String[] perms) {
        String permsStr = StringUtils.builderJoin("\n", perms);
        EasyPermissions.requestPermissions(fragment,
                "需要获取:\n" + permsStr + "权限",
                R.string.lib_perms_yes,
                R.string.lib_perms_no,
                requestCode,
                perms);
    }


    public static String checkAllowPermissions2String(Context context, String... permissions) {

        String[] strs = checkPermissions(context, permissions);

        if (strs == null || strs.length <= 0) return null;

        return StringUtils.builderJoin("\n", strs);
    }

    public static String checkNotAllowPermissions2String(Context context, String... permissions) {

        String[] strs = checkNotAllowPermissions(context, permissions);

        if (strs == null || strs.length <= 0) return null;

        return StringUtils.builderJoin("\n", strs);
    }

    //return the permissions which does had
    public static String[] checkPermissions(Context context, String... permissions) {
        if (permissions == null || permissions.length <= 0) return null;

        List<String> list = new ArrayList<>(permissions.length);

        for (String str : permissions) {
            if (StringUtils.isEmpty(str)) continue;

            if (!checkPermission(context, str)) continue;

            list.add(str);
        }
        if (list.size() <= 0) return null;

        return list.toArray(new String[list.size()]);
    }

    //return the permissions which doesnot have
    public static String[] checkNotAllowPermissions(Context context, String... permissions) {
        if (permissions == null || permissions.length <= 0) return null;

        List<String> list = new ArrayList<>(permissions.length);

        for (String str : permissions) {
            if (StringUtils.isEmpty(str)) continue;

            if (checkPermission(context, str)) continue;

            list.add(str);
        }
        if (list.size() <= 0) return null;

        return list.toArray(new String[list.size()]);
    }

    //if true had the permission otherwise not
    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    // if true need  request some Permissions otherwise not
    public static boolean checkVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


}
