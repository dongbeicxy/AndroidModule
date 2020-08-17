package com.xxx.xxx.utils.database;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * @作者 xzb
 * @描述: 处理版本数据变化Migration
 * @创建时间 :2020/5/22 14:10
 */
public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        //Migrate from version 0 to version 1
        if (0 == oldVersion && 1 == newVersion) {
            Log.e("Realm", newVersion + "");
            RealmObjectSchema personSchema = schema.get("RealmTest");
            //fieldName 要添加的字段的名称
            personSchema.addField("dataID", String.class, FieldAttribute.REQUIRED).transform(new RealmObjectSchema.Function() {
                @Override
                public void apply(DynamicRealmObject obj) {
                    obj.set("dataID", "0");//为dataID设置值
                }
            });  //.removeField("age") //移除age属性
            oldVersion++;
        }
    }
}