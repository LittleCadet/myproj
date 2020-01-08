package com.myproj.suixinfu.suixingpay.demo;

import com.alibaba.fastjson.JSONObject;
import com.myproj.suixinfu.suixingpay.utils.HttpUtils;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testdemo {
    public static void main(String[] args) throws Exception {


        /* String url = "http://172.16.155.32:8080/ws/bankback";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aaa","aaa");
        jsonObject.put("bbb","bbb");
        String resultJson = HttpUtils.connectPostUrl(url, jsonObject.toJSONString());
        System.out.println("resultJson:"+resultJson);*/

         String url = "https://qr.95516.com/qrcGtwWeb-web/api/userAuth?version=1.0.0&redirectUrl=";
         String redirectUrl = URLEncoder.encode("https://openapi-test.suixingpay.com/order/test/getUserAuthCode","UTF-8");
         url = url + redirectUrl;
         JSONObject jsonObject = new JSONObject();
        jsonObject.put("aaa","aaa");
        jsonObject.put("bbb","bbb");
        String resultJson = HttpUtils.connectGetUrl(url);
        System.out.println("resultJson:"+resultJson);

    }

    /** * 判断是否为合法IP * @return the ip */
    public static boolean isboolIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
}
