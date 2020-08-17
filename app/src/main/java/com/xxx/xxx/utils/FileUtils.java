package com.xxx.xxx.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    /*
     获取  文件名字+ 后缀名
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }


    //https://blog.csdn.net/u010937230/article/details/73303034

    /**
     * @创建者 邢志标
     * @创建时间 2018/9/13  9:58
     * @修改者 邢志标
     * @方法描述 创建 APP 专属文件夹 . 文件     并返回路径
     */
    public static String getFolderOrFilePath(Context context, String folderName, boolean isCreateFile, String fileName) {
        String path = "";
        //这个函数用来获取SD卡的挂载状态
        //如果传入参数path则是获取该路径的的挂载状态
        //如果这个目录被用户的PC挂载，或者从设备中移除，或者其他问题发生，状态的返回是不一样的

        //外部存储可用
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (isCreateFile) {
                //Context.getExternalFilesDir()一般放一些长时间保存的数据
                //Context.getExternalCacheDir()一般存放临时缓存数据
                //(会随着应用的卸载一起删除掉) 【外部存储】
                path = context.getExternalFilesDir(folderName).getAbsolutePath() + File.separator + fileName;
            } else {
                path = context.getExternalFilesDir(folderName).getAbsolutePath();
            }
        } else {
            //使用内部存储
            if (isCreateFile) {
                //(会随着应用的卸载一起删除掉)【内部存储】
                path = context.getFilesDir() + File.separator + folderName + File.separator + fileName;
            } else {
                path = context.getFilesDir() + File.separator + folderName;
            }
        }
        File file = new File(path);
        //如果 是一个 文件夹
        if (file.isDirectory() && !file.exists()) {
            //为什么用mkdirs()呢？因为这个方法可以在不知道偶没有父类文件夹的情况下，创建文件夹，而mkdir（）必须在有父类的文件夹下创建文件
            file.mkdirs();
        }
        //如果是一个 文件
        if (file.isFile() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }
}
