/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author zhouchen[zhou_chen1@suixingpay.com]
 * @date 2019/08/22 10:32
 * @version 01
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.myproj.suixinfu.suixingpay.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @description: 上传图片
 * @author: zhouchen[zhou_chen1@suixingpay.com]
 * @date: 2019/08/22 10:32
 */

public class UpLoadPicture {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

//      String uploadUrl = "https://openapi.suixingpay.com/merchant/uploadPicture";
        //String uploadUrl = "http://172.16.138.162:5041/merchant/uploadPicture";
        String uploadUrl = "https://openapi-test.suixingpay.com/merchant/uploadPicture";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpPost httppost = new HttpPost(uploadUrl);

            FileBody bin = new FileBody(new File("C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)\\聚合二维码\\聚合收款码-saas 微信.png"));
            //替换成自己的orgId  67290416
            StringBody orgId = new StringBody("26680846", ContentType.TEXT_PLAIN);
            StringBody pictureType = new StringBody("04", ContentType.TEXT_PLAIN);
            StringBody reqId = new StringBody(UUID.randomUUID().toString().replace("-", ""),
                    ContentType.TEXT_PLAIN);


            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("file", bin)
                    .addPart("orgId", orgId)
                    .addPart("pictureType", pictureType)
                    .addPart("reqId", reqId).build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String result = EntityUtils.toString(resEntity, "UTF-8");
                    JSONObject resultJson = JSONObject.parseObject(result);
                    if ("0000".equals(resultJson.getString("code"))) {
                        JSONObject respJson = resultJson.getJSONObject("respData");
                        String taskCode = respJson.getString("PhotoUrl");
                        System.out.println("taskCode:" + taskCode);
                    }
                    System.out.println("返回结果：" + result);
                } else {
                    System.out.println("上传图片异常：");
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
