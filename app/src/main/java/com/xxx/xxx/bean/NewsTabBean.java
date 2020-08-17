package com.xxx.xxx.bean;

import java.util.List;

public class NewsTabBean {
    /**
     * errno : 0
     * sid : c99d34453485896636913a297fe75b0e
     * data : [{"name":"推荐","c":"youlike","status":"show"},{"name":"娱乐","c":"fun","status":"show"},{"name":"社会","c":"social","status":"show"},{"name":"军事","c":"militery","status":"show"},{"name":"科技","c":"science","status":"show"},{"name":"财经","c":"economy","status":"show"},{"name":"汽车","c":"car","status":"show"},{"name":"体育","c":"sport","status":"show"},{"name":"明星","c":"gossip","status":"show"},{"name":"国际","c":"international","status":"show"},{"name":"历史","c":"history","status":"show"},{"name":"国内","c":"domestic","status":"show"},{"name":"星座","c":"zodiac","status":"show"},{"name":"情感","c":"emotion","status":"show"},{"name":"养生","c":"healthcare","status":"show"},{"name":"时尚","c":"fashion","status":"show"},{"name":"搞笑","c":"funny","status":"show"},{"name":"健康","c":"health","status":"hide"},{"name":"美食","c":"food","status":"hide"},{"name":"探索","c":"probe","status":"hide"},{"name":"旅游","c":"travel","status":"hide"},{"name":"游戏","c":"game","status":"hide"},{"name":"教育","c":"education","status":"hide"},{"name":"股票","c":"stock","status":"hide"},{"name":"房产","c":"estate","status":"hide"},{"name":"比特币","c":"hotBlockChain","status":"hide"},{"name":"综艺","c":"variety","status":"hide"},{"name":"好物","c":"goodThing","status":"hide","update":"1"},{"name":"辟谣","c":"refuteRumour","status":"hide"},{"name":"育儿","c":"child","status":"hide"}]
     */

    private int errno;
    private String sid;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 推荐
         * c : youlike
         * status : show
         * update : 1
         */
        private String name;
        private String c;
        private String status;
        private String update;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }
    }
}
