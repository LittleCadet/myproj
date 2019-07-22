package com.dingTalk.app.dingtalk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingTalk.app.exception.NetworkException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 接入钉钉服务
 * @author LittleCadet
 */
@Slf4j
@Service
public class DingTalkService {

    private OkHttpClient httpClient;

    private String appkey = "dingmftz1oc3eiy0zcx3";

    private String appsecret = "TjqhT85aCBP_U4JJYnBCVWY2YBtOz97AbbzkIT-RHkarV2H0DmhVWsplUXzAZ_oO";

    private String url = "https://oapi.dingtalk.com/gettoken?appkey=" + appkey + "&appsecret=" + appsecret;

    private String hook = "https://oapi.dingtalk.com/robot/send?access_token=fc100712270d291cf950e8485dbb24bb46b8ab0605c572e1edffa0e67675677a";

    private String metaType = "application/x-www-form-urlencoded;charset=utf-8";

    public static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    @PostConstruct
    public void init() {

        httpClient = new OkHttpClient();
    }

    /**
     * 通过OkHttp3用钉钉的appKey + appSercet 去获取accessToken，
     */
    public String getAccessToken() throws NetworkException {

        //okHttp自身的返回码
        int code = 200;
        int errcode = 0;
        String accessToken = null;
        Response response = null;

        Request request = new Request.Builder()
                .get()
                .url(url)
                .header("appkey", appkey)
                .header("appsecret", appsecret)
                .build();

        try {
            response = httpClient.newCall(request).execute();

            // 校验okHttp自身返回码
            if (null == response || code != response.code()) {
                log.error("Response error,response:{}", null == response ? null : response.code());
                if (null != response) {
                    throw new NetworkException(response.code(), response.message());
                } else {
                    return null;
                }
            }
            String body = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(body);

            //校验钉钉返回码
            if (errcode == jsonObject.getInteger("errcode")) {
                accessToken = jsonObject.getString("access_token");
            }else{
                log.error("errcode:{},errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
                throw new NetworkException(jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
            }
        } catch (IOException e) {
            log.error("network error!");
        }finally {
            response.close();
        }
        return accessToken;
    }

    /**
     * 发送消息到钉钉
     * 注意：
     * 1.务必使用dingTalk的自定义机器人
     * 2.传输格式：务必定义为json。否则即使代码运行不报错，也是发送无效的
     * 3.字符集格式：务必定义为UTF-8，否则传到钉钉的时候，会出现乱码【例如：？？？】
     */
    public void sendMsgTODIngTalk(Exception e){

        Response response = null;

        //定义发送文本
        PrintWriter pw = new PrintWriter(new StringWriter());
        pw.print(e);

        //远程调用dingtalk
        Map<String, Object> items = new HashMap<>();
        items.put("msgtype", "text");
        Map<String, String> textContent = new HashMap<>();
        textContent.put("content", "测试文本");
        items.put("text", textContent);

        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(items));

        Request request = new Request.Builder()
                .post(requestBody)
                .url(hook)
                .build();

        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException ex) {
            log.error("network error!,ex:" + ex);
        }finally {
            //关闭response
            response.close();
        }

    }

}
