package com.xxx.xxx.bean.new_version;

import java.io.Serializable;

/**
 * @类描述
 * @创建人 邢志标 微信号:shengwusuoyi
 * @创建时间 2018/9/12
 * @修改人 邢志标
 * @修改备注 新版本安装包  对象
 */
public class NewVersion implements Serializable {
    /**
     * result : success
     * data : {"no":19,"app_name":"小牛翻译","version":1,"apk_name":"yatrans.apk","force_update":0,"remark":"修复了一些已知的问题。","package_name":"com.calf_translate.yatrans","ios_url":"","input_date":"2018-09-11 15:03","update_date":"2018-09-11 15:52","url":"http://39.104.139.47:80/NIU_MMP/doc/com.calf_translate.yatrans/yatrans.apk"}
     */
    private String result;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * no : 19
         * app_name : 小牛翻译
         * version : 1
         * apk_name : yatrans.apk
         * force_update : 0
         * remark : 修复了一些已知的问题。
         * package_name : com.calf_translate.yatrans
         * ios_url :
         * input_date : 2018-09-11 15:03
         * update_date : 2018-09-11 15:52
         * url : http://39.104.139.47:80/NIU_MMP/doc/com.calf_translate.yatrans/yatrans.apk
         *
         * http://p.gdown.baidu.com/2921dc37bddc53e4e94ae9dc5df07fa952c347683c2ca4f1bac79abdfb4467e59ca1992558f1447114aec8ad9ca3c669207306fed51922d44eb1ebc80fcb59e3e8cfe771dae6cd9921fade9424a21a0b32390d31699fbf9cad2c244d8a4b48b77a8b03492dbbf66d7b988b1d2b1d45e51b0861f3598455c16fa846b3da3e54e4fe95ccd0cdca89582093102d7e6c0d3eca6923c91f21c2d850d70f2208aed10cc6cd5ddf089604f76e1dc633457da3967fdac2a3a42e8da4558eaf53fedecbd0d88a4008064afd5c69ac25aec9cf4674e70b33a3357d05d8942af0120f883834a448fcadaa785d990ea3266804b51d42
         */
        private int no;
        private String app_name;
        private int version;
        private String apk_name;
        private int force_update;
        private String remark;
        private String package_name;
        private String ios_url;
        private String input_date;
        private String update_date;
        private String url;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getApk_name() {
            return apk_name;
        }

        public void setApk_name(String apk_name) {
            this.apk_name = apk_name;
        }

        public int getForce_update() {
            return force_update;
        }

        public void setForce_update(int force_update) {
            this.force_update = force_update;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getIos_url() {
            return ios_url;
        }

        public void setIos_url(String ios_url) {
            this.ios_url = ios_url;
        }

        public String getInput_date() {
            return input_date;
        }

        public void setInput_date(String input_date) {
            this.input_date = input_date;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
