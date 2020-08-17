package com.xxx.xxx.utils.permission;

import android.Manifest;

/**
 * @作者 xzb
 * @描述: 权限列表
 * @创建时间 :2020/4/3 11:21
 */
public class PermissionList {

    /**
     * 权限组
     */
    public static String[] permissionsGroup = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            //读取日历
            //Manifest.permission.READ_CALENDAR,
            Manifest.permission.CAMERA,
            //读取 通讯录
            //Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            //录音
            //Manifest.permission.RECORD_AUDIO,
            //传感器
            //Manifest.permission.BODY_SENSORS,
            //收短信
            //Manifest.permission.RECEIVE_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    /**
     * 电话状态
     */
    public static String[] permissionsPhoneState = new String[]{
            Manifest.permission.READ_PHONE_STATE};

    /**
     * 拍照
     */
    public static String[] permissionsCamera = new String[]{
            Manifest.permission.CAMERA};

    /**
     * 位置
     */
    public static String[] permissionsLocation = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION};

    /**
     * 录音
     */
    public static String[] permissionsAudio = new String[]{
            Manifest.permission.RECORD_AUDIO};

    /**
     * 存储
     */
    public static String[] permissionsStorage = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
