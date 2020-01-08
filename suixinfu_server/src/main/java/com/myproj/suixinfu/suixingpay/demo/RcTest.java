package com.myproj.suixinfu.suixingpay.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.myproj.suixinfu.suixingpay.utils.ApiRequestBean;
import com.myproj.suixinfu.suixingpay.utils.HttpUtils;
import com.myproj.suixinfu.suixingpay.utils.RSASignature;

import java.text.SimpleDateFormat;
import java.util.*;

public class RcTest {
	
	
	public static void main(String[] args) {
		
		try {
			//合作方私钥(替换成自己的)
			String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI6aysW0U9OcTN+kn+EOXlE4MHHDiL0THc2aYb83sDX5vLTfbNlmAsul02HzLmi2MVjQTfs3KvgUYoVFJK4OJOTy9/Hem/oiZLVHGOODip1Uch+qEJ4HRnZk+4EPcZuPyEcUS1dmrz6awAW7/llCOJWGCzlZYGOHngthZK6b5337AgMBAAECgYAEqku37A5R/esF5fzVAANV5OCw2BTBGr7+2u4Xs1qwaVsjD6wf8JZm0yX3Ll12T3+NyELE1SkytHgEpB5vE0dhqW0BEft+Z0RWERxyszAbW2y/lk23rN0TMefmxbGUYR2CJF1x5cGv0Cl+s8RtJ/3OcpNmiZoystRvWhMbySClAQJBAN3ewdzK5i/T/kXXEmNkptQO0AI0eNdj9v0s2NWIDj7q7yE8OK7U4cPv+E7qvzq3IrJATwRFJUzZ4xsqAvLjTOUCQQCkio+mhoY+p/VmNeYvGMNz9RnMevQplhKhtMj9sPI/cfg0EEhdoktsoG1Gfnn6u+dRqVl/DGa0LuHEBmODO9FfAkAIIAA5dbS4S6skI5wox6bUXTaA3isOuDpzSxElwLXE2BWpwerRfDpIUqFlQnN+UvaSUIiUP3P+PHx0ojU5b9mBAkBZ8m0IyW1FfyeFUl2czVq7XvdVcrlaqnFQ+LUPCdXDnRfjzirhFMFKhoB2Etm3mVSgrYUBENRsF1zPffaUXPTdAkBsy1U3YULc1lbgIZoa5N+nn60IbEuLkcW22DM+GGS+BejCR4cGF/4NPw6yVLEikGGxvaX6jg+/jAoF0GDlYmVH";

			//随行付公钥
			String sxfPublic =
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOmsrFtFPTnEzfpJ/hDl5RODBxw4i9Ex3NmmG/N7A1+by032zZZgLLpdNh8y5otjFY0E37Nyr4FGKFRSSuDiTk8vfx3pv6ImS1Rxjjg4qdVHIfqhCeB0Z2ZPuBD3Gbj8hHFEtXZq8+msAFu/5ZQjiVhgs5WWBjh54LYWSum+d9+wIDAQAB";
			//请求json
			//String req= "{\"orgId\":\"67290416\",\"reqData\":{\"mno\":\"836102376310006\",\"ordNo\":\"48e138ed7db245a666a490941b6c09b2\"},\"reqId\":\"f67271bedd2041e79ed3754dcc5319db\",\"signType\":\"RSA\",\"timestamp\":\"201809112345\",\"version\":\"1.0\"}";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String dataStr = df.format(new Date());
			ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
			reqBean.setOrgId("94870841");
			reqBean.setReqId(UUID.randomUUID().toString().replaceAll("-",""));
			reqBean.setSignType("RSA");
			reqBean.setTimestamp(dataStr);
			reqBean.setVersion("1.0");
			JSONObject reqData = new JSONObject();

			//业务参数
			reqData.put("mno","399000010000002");
			Random random = new Random();





	/*//被扫
			reqData.put("ordNo",(int)((Math.random()*9+1)*100000)+"20"+random.nextInt(7));
//			reqData.put("merchantId","226801000000220093509");
//			reqData.put("merchantId","700000001195567");
			reqData.put("amt","0.01");
//			reqData.put("subAppid","wxbd6c8015a642ad22");
			//reqData.put("goodsId","1111");
			reqData.put("payType","ALIPAY");
			reqData.put("authCode","282243384925341934");
			reqData.put("subject","2222");
			reqData.put("detail","222222");*/
			//reqData.put("trmIp","127.0.0.1");
//			reqData.put("subMechId","226801000000197524657");
//			reqData.put("channelId","test");
			//reqData.put("userId","test");
			//reqData.put("subOpenid","test");
			//reqData.put("operatorId","test");
			//reqData.put("storeId","test");
			reqData.put("extend","test");


			//主扫
			reqData.put("ordNo",(int)((Math.random()*9+1)*100000)+"20"+random.nextInt(7));

//			reqData.put("merchantId","226801000000197524657");
//			reqData.put("merchantId","226801000000221076637");

			reqData.put("amt","0.01");
			//reqData.put("subAppid","wx0c0bc056e26b9981");
			reqData.put("goodsId","fy10002");
			reqData.put("payType","ALIPAY");
//			reqData.put("payWay","02");
			reqData.put("subject","汤臣一品111");
			reqData.put("detail","body");
//			reqData.put("trmIp","127.0.0.1");
//			reqData.put("subMechId","226801000000197524657");
//			reqData.put("channelId","test");
			//reqData.put("userId","test");
			//reqData.put("subOpenid","test");
//			reqData.put("operatorId","test");
//			reqData.put("storeId","test");
//			reqData.put("extend","test");
			//reqData.put("goodsTag","test");
//			reqData.put("trmNo","test");
//			reqData.put("ledgerAccountEffectTime","");
//			reqData.put("ledgerAccountFlag","00");
			reqData.put("timeExpire","6");
			reqData.put("quantity","3");
			reqData.put("limitPay","00");




			reqBean.setReqData(reqData);
			String req = JSONObject.toJSONString(reqBean);
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

//			String url = "https://openapi-rc.suixingpay.com/order/qr/activeScan";
//			String url = "http://172.16.155.45:8080/order/qr/activeScan";
			String url = "http://172.16.160.214:8080/order/qr/activeScan";
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
