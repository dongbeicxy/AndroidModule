package com.xxx.xxx.utils;

import android.app.Activity;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @作者 xzb
 * @描述: 文件存储
 * @创建时间 :2020/4/8 16:58
 */
public class FileStorage {
    /**
     * 保存json到本地
     */
    public static void saveJsonToSDCard(Activity activity, String filename, String jsonContent) {
        File file = new File(FileUtils.getFolderOrFilePath(activity, StringUtils.JSONFOLDER_NAME, true, filename));
        try {
            OutputStream out = new FileOutputStream(file);
            out.write(jsonContent.getBytes());
            out.close();
            Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(activity, "保存失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 从本地读取json
     */
    public static String readJsonFromSDCard(Activity activity, String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(FileUtils.getFolderOrFilePath(activity, StringUtils.JSONFOLDER_NAME, true, filename));
            InputStream in = null;
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                sb.append((char) tempbyte);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }
}
