package com.oss.app.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

/**
 * LittleCadet
 */
@Slf4j
public class HttpUtils {

    private static Integer lock = 0;
    public static HttpUtils httpUtils = null;

    private final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private HttpUtils(){
    }

    public static HttpUtils getInstance(){
        if(null == httpUtils){
            synchronized (lock){
                if(null == httpUtils){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * http get
     * @param url
     * @return
     */
    public String requestGet(String url){
        try {
           return this.requestGet(url, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http get
     * @param url
     * @param params
     * @return
     */
    public String requestGet(String url, Map<String, Object> params){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            StringBuilder urlStr = new StringBuilder(url);
            if(urlStr.indexOf("?") <= 0){
                urlStr.append("?random="+System.nanoTime());
            }

            if(null != params){
                for (String key : params.keySet()){
                    urlStr.append(String.format("&%s=%s",key,params.get(key)));
                }
            }

            HttpGet httpGet = new HttpGet(urlStr.toString());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(null != httpEntity){
                return EntityUtils.toString(httpEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http get
     * @param url
     * @param params
     * @return
     */
    public InputStream requestGetStream(String url, Map<String, Object> params){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            StringBuilder urlStr = new StringBuilder(url);
            if(urlStr.indexOf("?") <= 0){
                urlStr.append("?random="+System.nanoTime());
            }

            if(null != params){
                for (String key : params.keySet()){
                    urlStr.append(String.format("&%s=%s",key,params.get(key)));
                }
            }

            HttpGet httpGet = new HttpGet(urlStr.toString());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            return httpEntity.getContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http get
     * @param url
     * @param params
     * @return
     */
    public HttpEntity requestGetAsEntity(String url, Map<String, Object> params){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            StringBuilder urlStr = new StringBuilder(url);
            if(urlStr.indexOf("?") <= 0){
                urlStr.append("?random="+System.nanoTime());
            }

            if(null != params){
                for (String key : params.keySet()){
                    urlStr.append(String.format("&%s=%s",key,params.get(key)));
                }
            }

            HttpGet httpGet = new HttpGet(urlStr.toString());
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            return httpEntity;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * http get
     * @param url
     * @param params
     * @return
     */
    public String requestPost(String url, Map<String, Object> params){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
//            StringEntity se = new StringEntity(JSONObject.fromObject(params).toString());
            StringEntity se = new StringEntity(JSONObject.toJSONString(params));
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);

            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"UTF-8");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * http get
     * @param url
     * @param params
     * @return
     */
    public HttpEntity requestPostAsEntity(String url, Map<String, Object> params){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        try{
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(JSONObject.toJSONString(params),"UTF-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            log.info("请求参数:{}", JSONObject.toJSONString(params));
            HttpResponse response = httpClient.execute(httpPost);
            return response.getEntity();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String requestPost(String url, String params){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(params, "UTF-8");
            se.setContentType("application/json");
            se.setContentEncoding("UTF-8");
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);

            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"UTF-8");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
