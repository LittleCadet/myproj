/**
 * 
 */
package com.fuiou.mchnt.entry.insEntry.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * @project wbp
 * @description 
 * @author abel.li
 * @creation 2017年7月3日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version	 
 */
public class HttpClientUtil {

    private static CloseableHttpClient httpClient = null;
    
    static {
        httpClient = getInstance().getPoolingHttpClient();
    }
    
    private PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        try {
//            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,  
//                    new TrustSelfSignedStrategy())  
//                    .build();  
//            HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;  
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
//                    sslcontext,hostnameVerifier);  
            
//            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
//            FileInputStream instream = new FileInputStream(apiKeyAddr);
//            String key = apiKey;
//            try {
//                keyStore.load(instream, key.toCharArray());
//            } finally {
//                instream.close();
//            }
//            try {
//                SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, key.toCharArray()).build();
//                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            
            ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
            LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
            
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()  
                    .register("http", plainsf)  
                    .register("https", sslsf)  
                    .build();  
            PoolingHttpClientConnectionManager poolConnManager = 
                    new PoolingHttpClientConnectionManager(socketFactoryRegistry);  
            // Increase max total connection to 200  
            poolConnManager.setMaxTotal(20000);  
            // Increase default max connection per route to 20  
            poolConnManager.setDefaultMaxPerRoute(200);  
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(200000).build();  
            poolConnManager.setDefaultSocketConfig(socketConfig);  
            
            return poolConnManager;
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return null;
    }
    
    private CloseableHttpClient getPoolingHttpClient() {  
        PoolingHttpClientConnectionManager poolConnManager = getHttpClientConnectionManager();
        
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(180000)  
                .setConnectTimeout(180000).setSocketTimeout(180000).build();  
        CloseableHttpClient httpClient = HttpClients.custom()  
                .setConnectionManager(poolConnManager).setDefaultRequestConfig(requestConfig).build(); 
        /**
         * PoolStats
         * 
         * leased ：the number of persistent connections tracked by the connection manager currently being used to execute requests.  
         * available ：the number idle persistent connections. 
         * pending : the number of connection requests being blocked awaiting a free connection.  
         * max: the maximum number of allowed persistent connections. 
         */
        if(poolConnManager!=null&&poolConnManager.getTotalStats()!=null) {  
            System.out.println("now client pool "+poolConnManager.getTotalStats().toString());  
        }
        return httpClient;  
    }
    
    private HttpClientUtil(){
    }
    
    public static HttpClientUtil getInstance(){
        return new HttpClientUtil();
    }
    
    /*public static Object postObject(String url, Serializable param) {
        String returnStr = null; 
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url); 
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {  
            long currentTime=System.currentTimeMillis();  
            SerializableEntity params = new SerializableEntity(param);
            httpPost.addHeader("Content-Type", "application/x-java-serialized-object");
//            httpPost.addHeader("Connection", "close");
            httpPost.setEntity(params);
            System.out.println(currentTime+" 开始发送 请求：url"+url);
            
            response = httpClient.execute(httpPost);  
            int status = response.getStatusLine().getStatusCode();  
            HttpEntity entity = response.getEntity();  
            if (status >= 200 && status < 300) {  
                byte[] resopnse = EntityUtils.toByteArray(entity);  
                bais = new ByteArrayInputStream(resopnse);
                ois = new ObjectInputStream(bais); 
                System.out.println(currentTime+" 接收响应：url"+url+" status="+status);
                EntityUtils.consume(entity);
                Object readObject = ois.readObject();
                System.out.println("response:" + JsonUtil.beanToJson(readObject));
                return readObject != null ? readObject : null;  
            } else {  
                System.out.println(currentTime+" 接收响应：url"+url+" status="+status+" resopnse="+EntityUtils.toString(entity,"utf-8"));  
                EntityUtils.consume(entity);
                throw new ClientProtocolException("Unexpected response status: " + status);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
        } finally {  
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if(bais != null){
                    bais.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }  
        return returnStr;
    }*/
    
    public static String postString(String url, String param, String contentType) {
        String returnStr = null; 
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);  
        try {  
            long currentTime=System.currentTimeMillis();  
            StringEntity params = new StringEntity(param, "UTF-8");
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("Connection", "close");
            httpPost.setEntity(params);
            System.out.println(currentTime+" 开始发送 请求：url"+url);
            
            response = httpClient.execute(httpPost);  
            int status = response.getStatusLine().getStatusCode();  
            HttpEntity entity = response.getEntity();  
            if (status >= 200 && status < 300) {  
                String resopnse="";  
                if(entity != null) {  
                    resopnse = EntityUtils.toString(entity,"utf-8");  
                }  
                System.out.println(currentTime+" 接收响应：url"+url+" status="+status);
                EntityUtils.consume(entity);
                return entity != null ? resopnse : null;  
            } else {  
                System.out.println(currentTime+" 接收响应：url"+url+" status="+status+" resopnse="+EntityUtils.toString(entity,"utf-8"));  
                EntityUtils.consume(entity);
                throw new ClientProtocolException("Unexpected response status: " + status);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
        } finally {  
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }  
        return returnStr;  
    } 
    
    /**
     * 
     * @param url
     * @param params
     * @param attach
     * @param contentType
     * @return
     */
    public static String postParamWithAttach(String url, Map<String, String> params, File attach, String contentType) {
        String returnStr = null; 
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);  
        try {  
            long currentTime=System.currentTimeMillis();  
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();  
            // 上传的文件  
            if(attach != null){
                builder.addBinaryBody("picFile", attach);  
            }
            // 设置其他参数  
            for(Entry<String, String> entry : params.entrySet()) {
                System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
                builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));  
            } 
            
            HttpEntity httpEntity = builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE).build();  
            httpPost.setEntity(httpEntity);
            
            System.out.println(currentTime+" 开始发送 请求：url"+url);
            
            response = httpClient.execute(httpPost);  
            int status = response.getStatusLine().getStatusCode();  
            HttpEntity entity = response.getEntity();  
            if (status >= 200 && status < 300) {  
                String resopnse="";  
                if(entity != null) {  
                    resopnse = EntityUtils.toString(entity,"utf-8");  
                }  
                System.out.println(currentTime+" 接收响应：url:"+url+" status="+status);
                EntityUtils.consume(entity);
                return entity != null ? resopnse : null;  
            } else {  
                System.out.println(currentTime+" 接收响应：url"+url+" status="+status+" resopnse="+EntityUtils.toString(entity,"utf-8"));  
                EntityUtils.consume(entity);
                throw new ClientProtocolException("Unexpected response status: " + status);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
        } finally {  
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }  
        return returnStr;  
    } 
    
    public static String postJson(String url, String param) {
        return postString(url, param, "application/json");
    } 
    
    public static String postXml(String url, String param) {
        return postString(url, param, "text/xml");
    }
    
    public static String postXmlWithAttach(String url, Map<String, String> params, File attach) {
        return postParamWithAttach(url, params, attach, "text/xml");
    }
    
    public static String postJsonWithAttach(String url, Map<String, String> params, File attach) {
        return postParamWithAttach(url, params, attach, "application/json");
    }
    
    public static String postJsonWithMap(String url, Map<String, String> params) {
        return postParamWithAttach(url, params, null, "application/json");
    }
}
