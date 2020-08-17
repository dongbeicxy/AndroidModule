package com.xxx.xxx.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @作者 xzb
 * @描述: Realm 测试类
 * @创建时间 :2020/5/22 14:32
 */
public class RealmTest extends RealmObject {
    @PrimaryKey
    String id;
    @Required
    String dataID;
    private String name;
    private int age;

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
