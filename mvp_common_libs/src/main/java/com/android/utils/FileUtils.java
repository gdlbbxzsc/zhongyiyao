package com.android.utils;

import java.io.File;

public class FileUtils extends com.utils.FileUtils {

    ///////////
    public static String createSdFilePath(String path, String fileName) {

        String str = getSdFilePath(path, fileName);

        new File(str).getParentFile().mkdirs();

        return str;
    }

    public static String createSdDir(String path) {

        String str = getSdDir(path);

        createDir(str);

        return str;
    }

    ///////////
    public static String getSdFilePath(String path, String fileName) {
        return getSdFileSb(path, fileName).toString();
    }

    public static String getSdDir(String path) {

        return getSdDirSb(path).toString();
    }

    /////////////
    public static StringBuilder getSdFileSb(String path, String fileName) {
        return getAppSdPath().append(path).append(fileName);
    }

    public static StringBuilder getSdDirSb(String path) {
        return getAppSdPath().append(path);
    }

    ///////////////
    private static String NAME = "app_name";


    public static StringBuilder getAppSdPath() {
        return new StringBuilder()//
                .append(android.os.Environment.getExternalStorageDirectory().getAbsolutePath())//
                .append("/")//
                .append(NAME)//
                .append("/");
    }

    public static void init(String name) {
        NAME = name;
    }
}
