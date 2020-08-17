package com.xxx.xxx.bean;

/**
 * @作者 xzb
 * @描述: 首页滑动菜单
 * @创建时间 :2020/3/28 14:56
 */
public class HomePagerMenus {
    private String name;
    private int imageRes;

    public HomePagerMenus(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
