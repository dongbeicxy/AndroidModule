package com.xxx.xxx.view.activity_storage;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.hawk.Hawk;
import com.xxx.xxx.R;
import com.xxx.xxx.bean.RealmTest;
import com.xxx.xxx.presenter.base.BasePresenter;
import com.xxx.xxx.utils.FileStorage;
import com.xxx.xxx.utils.database.RealmHelper;
import com.xxx.xxx.view.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;

/**
 * @作者 xzb
 * @描述: 存储 Activity
 * @创建时间 :2020/4/8 17:13
 */
public class StorageActivity extends BaseActivity {

    //页面没有网络请求 返回null
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    //初始化 布局
    @Override
    protected int initLayout() {
        return R.layout.activity_storage;
    }

    @Override
    protected void initView() {
        //ImmersionBar.with(this).statusBarColor(R.color.red) .navigationBarColor(R.color.white) .fitsSystemWindows(true).init();
    }

    @Override
    protected Toolbar initToolBar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    public void hawk(View view) {
        String testValue = Hawk.get("Hawk", null);
        if (null == testValue) {
            Hawk.put("Hawk", "哈哈");
        } else {
            testValue = Hawk.get("Hawk");
            Toast.makeText(this, testValue, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * https://blog.csdn.net/H176Nhx7/article/details/79737604
     * Gson 解析服务端返回的多种类型的 JSON
     * <p>
     * 经测试：用org.json包下的JSONObject解析Json格式数据时，value无论是null，Null，"null"，"Null"
     * jsonObject.getXXX(String key) 都会报异常 (Gson 不会发生异常 但没有值 空白) 所以调用getXXX方法前 应先用jsonObject.isNull(String key)方法判断
     * 再取值  避免不必要的异常发生
     */
    public void Onrealm(View view) {
//        String json = "{\n" + "“code”:200,\n" + "“message”:”FAIL”,\n" + "“detail”:Null\n" + "}";
//        GsonBuilder gsonbuilder = new GsonBuilder();
//        gsonbuilder.serializeNulls();
//        Test test = new Gson().fromJson(json, Test.class);
//        Toast.makeText(this, test.getDetail(), Toast.LENGTH_SHORT).show();
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            Toast.makeText(this, jsonObject.get("detail") + "", Toast.LENGTH_SHORT).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "发生异常", Toast.LENGTH_SHORT).show();
//        }

//        RealmTest realmTest = new RealmTest();
//        realmTest.setId("3");
//        new RealmHelper(this).addOneData(realmTest);
    }

    class Test {
        private int code;
        private String message;
        private String detail;

        public String getDetail() {
            return detail;
        }
    }

    public void file(View view) {
        FileStorage.saveJsonToSDCard(this, "haha", "{\n" +
                "    \"cityList\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"武汉市\"\n" +
                "        },\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"name\": \"随州市\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"name\": \"宜昌市\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"湖北省\"\n" +
                "}");
        Toast.makeText(this, FileStorage.readJsonFromSDCard(this, "haha"), Toast.LENGTH_SHORT).show();
    }
}
