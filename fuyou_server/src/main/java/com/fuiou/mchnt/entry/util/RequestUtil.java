/**
 * 
 */
package com.fuiou.mchnt.entry.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version	 
 */
public class RequestUtil {

    public static final class Method{
        public static final String POST = "POST";
        public static final String GET = "GET";
    }
    
    public static String doRequest(String uri, String method, 
            String charset, String param,Integer type) throws NoSuchAlgorithmException, KeyManagementException, IOException{
        return doRequest(uri, method, charset, param, 30000, 30000,type);
    }
    
    public static String doRequest(String uri, String method, 
            String charset, String param, int connectTimeout, int readTimeout,Integer type) throws NoSuchAlgorithmException, KeyManagementException, IOException{
        if(method == null || (!method.equalsIgnoreCase(Method.GET) && !method.equalsIgnoreCase(Method.POST))){
            return null;
        }
        URL url = new URL(uri);
        if("https".equalsIgnoreCase(uri.substring(0, 5))){
            SSLContext sslContext = null;
            sslContext = SSLContext.getInstance("TLS");
            
            X509TrustManager xtmArray[] = {
                    new X509TrustManager() {
                        
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }
                        
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }
                    }
            };
            sslContext.init(null, xtmArray, new SecureRandom());
            if(sslContext != null)
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection ();
            httpURLConnection.setConnectTimeout(connectTimeout);
            httpURLConnection.setReadTimeout(readTimeout);
            
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("Accept-Charset", charset);
            httpURLConnection.setRequestProperty("contentType", charset);
            if(1==type){
                httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");//设置请求头
            }else {
            httpURLConnection.setRequestProperty("Content-type", "application/json");//设置请求头
            }
            httpURLConnection.setRequestMethod(method.toUpperCase());
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            
            if(method.equalsIgnoreCase(Method.POST)){
                PrintWriter printWriter = null;
                printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), charset));
                printWriter.write(param);
                printWriter.flush();
            }
            
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            
            int data = 0;
            int statusCode = httpURLConnection.getResponseCode();
            if(statusCode < HttpURLConnection.HTTP_OK || statusCode>=HttpURLConnection.HTTP_MULT_CHOICE){
                return null;
            }
            while((data=inputStream.read())!=-1){
                byteArrayOutputStream.write(data);
            }
            byte[] returnBytes = byteArrayOutputStream.toByteArray();
            return new String(returnBytes, charset);
        } finally {
            if (httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }
        }
    }
}
