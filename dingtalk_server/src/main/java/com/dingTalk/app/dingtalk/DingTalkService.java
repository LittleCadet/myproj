package com.dingTalk.app.dingtalk;

import com.alibaba.fastjson.JSONObject;
import com.dingTalk.app.exception.NetworkException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

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

        Request request = new Request.Builder()
                .get()
                .url(url)
                .header("appkey", appkey)
                .header("appsecret", appsecret)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();

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
        }
        return accessToken;
    }

}
