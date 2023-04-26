package com.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class HardwareUtils {
    public static boolean netWorkCheck(Context context) {

        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == connectivity)
                return false;

            NetworkInfo info = connectivity.getActiveNetworkInfo();

            return null != info && info.isAvailable();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean netWorkCheckAll(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null)
            return false;

        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info == null)
            return false;

        for (NetworkInfo anInfo : info) {
            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean gpsCheck(Context context) {

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (lm == null) return false;

        boolean boolgp = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        boolean boolgn = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return boolgp || boolgn;
    }

    public static double GetDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * Math.PI / 180.0;
        double radLat2 = lat2 * Math.PI / 180.0;

        double a = radLat1 - radLat2;
        double b = (lng1 * Math.PI / 180.0) - (lng2 * Math.PI / 180.0);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static String getVersionName(Context context) throws Exception {
        return getPackageInfo(context).versionName;
    }

    public static int getVersionCode(Context context) throws Exception {
        return getPackageInfo(context).versionCode;
    }

    public static PackageInfo getPackageInfo(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.getPackageInfo(context.getPackageName(), 0);
    }
}
