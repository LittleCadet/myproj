package com.myproj.suixinfu.suixingpay.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.myproj.suixinfu.suixingpay.utils.HttpUtils;
import com.myproj.suixinfu.suixingpay.utils.RSASignature;
import java.util.*;

public class Test1 {
    public static void main(String[] args) {

        try {
            //合作方私钥(替换成自己的)
            String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI6aysW0U9OcTN+kn+EOXlE4MHHDiL0THc2aYb83sDX5vLTfbNlmAsul02HzLmi2MVjQTfs3KvgUYoVFJK4OJOTy9/Hem/oiZLVHGOODip1Uch+qEJ4HRnZk+4EPcZuPyEcUS1dmrz6awAW7/llCOJWGCzlZYGOHngthZK6b5337AgMBAAECgYAEqku37A5R/esF5fzVAANV5OCw2BTBGr7+2u4Xs1qwaVsjD6wf8JZm0yX3Ll12T3+NyELE1SkytHgEpB5vE0dhqW0BEft+Z0RWERxyszAbW2y/lk23rN0TMefmxbGUYR2CJF1x5cGv0Cl+s8RtJ/3OcpNmiZoystRvWhMbySClAQJBAN3ewdzK5i/T/kXXEmNkptQO0AI0eNdj9v0s2NWIDj7q7yE8OK7U4cPv+E7qvzq3IrJATwRFJUzZ4xsqAvLjTOUCQQCkio+mhoY+p/VmNeYvGMNz9RnMevQplhKhtMj9sPI/cfg0EEhdoktsoG1Gfnn6u+dRqVl/DGa0LuHEBmODO9FfAkAIIAA5dbS4S6skI5wox6bUXTaA3isOuDpzSxElwLXE2BWpwerRfDpIUqFlQnN+UvaSUIiUP3P+PHx0ojU5b9mBAkBZ8m0IyW1FfyeFUl2czVq7XvdVcrlaqnFQ+LUPCdXDnRfjzirhFMFKhoB2Etm3mVSgrYUBENRsF1zPffaUXPTdAkBsy1U3YULc1lbgIZoa5N+nn60IbEuLkcW22DM+GGS+BejCR4cGF/4NPw6yVLEikGGxvaX6jg+/jAoF0GDlYmVH";

            //随行付公钥
            String sxfPublic =
                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOmsrFtFPTnEzfpJ/hDl5RODBxw4i9Ex3NmmG/N7A1+by032zZZgLLpdNh8y5otjFY0E37Nyr4FGKFRSSuDiTk8vfx3pv6ImS1Rxjjg4qdVHIfqhCeB0Z2ZPuBD3Gbj8hHFEtXZq8+msAFu/5ZQjiVhgs5WWBjh54LYWSum+d9+wIDAQAB";
            //请求json
            String req= "{\"orgId\":\"26680846\",\"reqData\":{\"mno\":\"399190605715187\",\"ordNo\":\"f8eecd14fc194229a2ea4efb5e6b2ada\"},\"reqId\":\"f67271bedd2041e79ed3754dcc5319db\",\"signType\":\"RSA\",\"timestamp\":\"201809112345\",\"version\":\"1.0\"}";
            System.out.println("req:"+req);
            //此处不要改变reqData里面值的顺序用LinkedHashMap
            HashMap reqMap=  JSON.parseObject(req,LinkedHashMap.class,Feature.OrderedField);
            //组装加密串
            String signContent = RSASignature.getOrderContent(reqMap);
            System.out.println("拼接后的参数："+signContent);
            //sign
            String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println(sign);

            reqMap.put("sign", sign);

            String reqStr = JSON.toJSONString(reqMap);
            System.out.println("请求参数:"+reqMap);
            System.out.println("请求参数:"+reqStr);
            String url = "https://openapi-test.suixingpay.com/query/tradeQuery";
            //String url = "http://172.16.155.45:8080/query/tradeQuery";
            String resultJson = HttpUtils.connectPostUrl(url, reqStr);
            System.out.println("返回信息："+resultJson);
            //不要对reqData排序 所以用LinkedHashMap
            HashMap<String, Object> result=  JSON.parseObject(resultJson,LinkedHashMap.class,Feature.OrderedField);
            if("SXF0000".equals(result.get("code"))){
                //验签
                String signResult = result.get("sign").toString();
                result.remove("sign");

                String resultStr = RSASignature.getOrderContent(result);
                System.out.println(resultStr);
                //sign
                String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
                System.out.println("resultSign:"+resultSign);
                //组装加密串
                if(RSASignature.doCheck(resultStr, signResult, sxfPublic)){
                    System.out.println("验签成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
