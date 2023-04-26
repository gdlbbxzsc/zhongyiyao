package com.utils;

import java.io.File;
import java.io.IOException;


public class FileUtils {

    //////////创建文件

    public static File createFile(String filePath) throws IOException {
        return createFile(filePath, false);
    }

    public static File createFile(String filePath, boolean isDelIfExists) throws IOException {

        File file = new File(filePath);

        if (file.exists()) {
            if (isDelIfExists) {
                file.delete();
            } else {
                return file;
            }
        }

        file.getParentFile().mkdirs();

        file.createNewFile();

        return file;
    }

    //////////创建目录
    public static boolean createDir(String dirPath) {
        return new File(dirPath).mkdirs();
    }

    ///////////删除文件或一个空目录,不能有子文件或目录
    public static boolean delete(String filePath) {
        return new File(filePath).delete();
    }

    /////////////删除一棵树
    public static boolean deleteAll(String path) {
        return deleteAll(new File(path));
    }

    public static boolean deleteAll(File file) {
        //如果为树结构末端，可直接删除
        if (!file.isDirectory()) return file.delete();

        //递归删除目录中的子目录下
        File[] children = file.listFiles();
        for (File aChildren : children) {
            boolean success = deleteAll(aChildren);
            if (!success) return false;
        }
        if (file.listFiles().length <= 0) {
            return file.delete();
        }
        return true;
    }

    public static long getSize(String path) {
        return getSize(new File(path));
    }

    public static long getSize(File file) {

        long size = 0;
        //如果为树结构末端，可直接删除
        if (!file.isDirectory()) return file.length();

        //递归删除目录中的子目录下
        File[] children = file.listFiles();
        for (File aChildren : children) {
            size += getSize(aChildren);
        }
        return size;
    }
}
