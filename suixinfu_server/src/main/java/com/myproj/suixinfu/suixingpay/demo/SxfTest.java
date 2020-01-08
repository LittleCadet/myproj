package com.myproj.suixinfu.suixingpay.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.myproj.suixinfu.suixingpay.utils.ApiRequestBean;
import com.myproj.suixinfu.suixingpay.utils.HttpUtils;
import com.myproj.suixinfu.suixingpay.utils.RSASignature;

import java.text.SimpleDateFormat;
import java.util.*;

public class SxfTest {
	
	
	public static void main(String[] args) {
		String ordNo = (int)(Math.random()*1000000000)+""+(int)(Math.random()*1000000000);
		try {
			//合作方私钥(替换成自己的)
			//String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI6aysW0U9OcTN+kn+EOXlE4MHHDiL0THc2aYb83sDX5vLTfbNlmAsul02HzLmi2MVjQTfs3KvgUYoVFJK4OJOTy9/Hem/oiZLVHGOODip1Uch+qEJ4HRnZk+4EPcZuPyEcUS1dmrz6awAW7/llCOJWGCzlZYGOHngthZK6b5337AgMBAAECgYAEqku37A5R/esF5fzVAANV5OCw2BTBGr7+2u4Xs1qwaVsjD6wf8JZm0yX3Ll12T3+NyELE1SkytHgEpB5vE0dhqW0BEft+Z0RWERxyszAbW2y/lk23rN0TMefmxbGUYR2CJF1x5cGv0Cl+s8RtJ/3OcpNmiZoystRvWhMbySClAQJBAN3ewdzK5i/T/kXXEmNkptQO0AI0eNdj9v0s2NWIDj7q7yE8OK7U4cPv+E7qvzq3IrJATwRFJUzZ4xsqAvLjTOUCQQCkio+mhoY+p/VmNeYvGMNz9RnMevQplhKhtMj9sPI/cfg0EEhdoktsoG1Gfnn6u+dRqVl/DGa0LuHEBmODO9FfAkAIIAA5dbS4S6skI5wox6bUXTaA3isOuDpzSxElwLXE2BWpwerRfDpIUqFlQnN+UvaSUIiUP3P+PHx0ojU5b9mBAkBZ8m0IyW1FfyeFUl2czVq7XvdVcrlaqnFQ+LUPCdXDnRfjzirhFMFKhoB2Etm3mVSgrYUBENRsF1zPffaUXPTdAkBsy1U3YULc1lbgIZoa5N+nn60IbEuLkcW22DM+GGS+BejCR4cGF/4NPw6yVLEikGGxvaX6jg+/jAoF0GDlYmVH";
			//合作方私钥(替换成自己的)
			String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOmykbxaWScoPQlBxO30TZXUOEYEZcyNsd2EWYG7CAmkcm9izyFmRcGGsHzsUqqlU5C2tPYbhNdqiX0NKX2PUefPOyZ78Ln4+L4kDiIobgINggDuENE3s9B+1IU18t58kEyxhWtxhUIlD2b/p8d+KuvSvgLBJoIeDHI+ywczPPxXAgMBAAECgYEA5NZU2qllz3AZhG0Alzm4l1R93iIERO8jEhS9SMmKL1b2L5gD12K8P6rSOyF2OV82mugt/ygnRoEuLGOtEyQ6P/Jew3H2y5kJPUT0S70dJFVDN9Kn7V9F0XT9h6lawUca5lmcoX/W40D6Oq/LYq/nJAZSw2M9A7wEXVO9KdmCSkECQQD9PoufNz/ps6z5Jk2DYG+b5SAoucgSqsNHPAsvkHPW94lbRuyXMIco0okUUooneRY+QaeomIe4W5hDVaHTqpZRAkEA7D2Sso7l94n5eKPWM+7bllrOKHCGvAMa2U/fFzN4PhHJxfn3I9NbltXkZcaZ+kZNjU9kKMXcvudk0gRsvhs2JwJBAN/cgzYKdAAV6akRF+IzxFVt1ODLYbZGp3r38/dm3U/A3/YFGy2HI2ONwMlrMV81ZdlA1oFt3EO3DEExmGzw1hECQQDZUcshd1MV5FcaI51pKNHX/rCngtxvGGwafN88+JwkfTigZtJyonwexaTV+1yqXwH4fJAm8d2fwD1+9ZMTTvUBAkA76cxiPpnPryMCmE7X3lJuBN5FWdJuZurVZMh1HTB5vM92ni/+k1sUb7rYnb6VPf/99VnWYuhphEgShNE2weh/";

			//随行付公钥
			String sxfPublic =
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOmsrFtFPTnEzfpJ/hDl5RODBxw4i9Ex3NmmG/N7A1+by032zZZgLLpdNh8y5otjFY0E37Nyr4FGKFRSSuDiTk8vfx3pv6ImS1Rxjjg4qdVHIfqhCeB0Z2ZPuBD3Gbj8hHFEtXZq8+msAFu/5ZQjiVhgs5WWBjh54LYWSum+d9+wIDAQAB";
			//请求json
			//String req= "{\"orgId\":\"67290416\",\"reqData\":{\"mno\":\"836102376310006\",\"ordNo\":\"48e138ed7db245a666a490941b6c09b2\"},\"reqId\":\"f67271bedd2041e79ed3754dcc5319db\",\"signType\":\"RSA\",\"timestamp\":\"201809112345\",\"version\":\"1.0\"}";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String dataStr = df.format(new Date());
			ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
			//reqBean.setOrgId("86636806");
			reqBean.setOrgId("26680846");

			reqBean.setReqId(UUID.randomUUID().toString().replaceAll("-",""));
			reqBean.setSignType("RSA");
			reqBean.setTimestamp(dataStr);
			reqBean.setVersion("2.0");
			JSONObject reqData = new JSONObject();

			//业务参数
			reqData.put("mno","399190618057330");
			reqData.put("tradeSource","02");
			reqData.put("trmIp","172.16.2.1");
			//reqData.put("trmNo","03");
			//reqData.put("trmSn","03");*/

//			reqData.put("mno","399000000000001");
			Random random = new Random();
			/*//获取openid
			reqData.put("subMchId","287128003");
			reqData.put("merchantId","836102370110042");
			reqData.put("authCode","134685444132319060");
			reqData.put("subAppId","wx4bba95e24f3e64b4");*/

			//手续费查询
		/*	reqData.put("ordNo","196949276106380819");
			reqData.put("merchantId","700000001195567");*/
		/*//正交易查询
			reqData.put("ordNo","64340668861294191");
			reqData.put("merchantId","700000001195567");*/

		/*//subopenid
			reqData.put("subMchId","226801000000197524657");
			reqData.put("merchantId","700000001195567");
			reqData.put("subAppId","wx2421b1c4370ec43b");
			reqData.put("authCode","700000001195567");*/
	//退款
			/*reqData.put("ordNo",(int)((Math.random()*9+1)*100000)+"20"+random.nextInt(7));
			reqData.put("origOrderNo","154948747915601751");
			reqData.put("merchantId","700000001195567");
			reqData.put("amt","0.01");*/
			/*//退款查询
			reqData.put("ordNo","109320202");
			reqData.put("merchantId","700000001195567");*/
			//聚合支付
		/*	reqData.put("ordNo",ordNo);
			//reqData.put("merchantId","700000001195567");
			reqData.put("amt","0.02");
			reqData.put("subAppid","wxbd6c8015a642ad22");
			reqData.put("goodsId","1111");
			reqData.put("payType","WECHAT");
			reqData.put("payWay","02");
			reqData.put("subject","2222");
			reqData.put("detail","body");
			reqData.put("trmIp","127.0.0.1");
//			reqData.put("subMechId","226801000000197524657");
//			reqData.put("channelId","test");
			//reqData.put("userId","2088802546142954");
			reqData.put("subOpenid","o7zRH0hZgzXVd9vJ3BCEyvzuXVto");
			//reqData.put("operatorId","test");
			//reqData.put("storeId","test");
			//reqData.put("extend","test");
			//reqData.put("goodsTag","test");
			//reqData.put("trmNo","test");
			//reqData.put("limitPay","01");*/




			//主扫冯燕//主扫
			reqData.put("ordNo",ordNo);
//			reqData.put("merchantId","226801000000220093509");

			reqData.put("amt","0.01");
			//reqData.put("subAppid","wxbd6c8015a642ad22");
			//reqData.put("goodsId","1111");
			reqData.put("payType","WECHAT");
			//reqData.put("payWay","02");
			reqData.put("subject","2222");
			reqData.put("detail","222222");
			reqData.put("trmIp","1.0.0.1");
//			reqData.put("subMechId","226801000000197524657");
//			reqData.put("channelId","test");
			//reqData.put("userId","test");
			//reqData.put("subOpenid","test");
			//reqData.put("operatorId","test");
			//reqData.put("storeId","test");
			reqData.put("extend","test");
			//reqData.put("goodsTag","test");
			//reqData.put("trmNo","test");
//			reqData.put("ledgerAccountEffectTime","");
//			reqData.put("ledgerAccountFlag","00");
			//reqData.put("timeExpire","");


			//reqData.put("trmIp","127.0.0.1");
			//reqData.put("subMechId","");
			//reqData.put("subAppid","");
			reqData.put("detail","222222");
			//reqData.put("limitPay","");
			reqData.put("notifyUrl","http://172.16.155.45:8080/order/test/call");
			//reqData.put("needReceip","");
//			reqData.put("channelId","test");
			//reqData.put("userId","test");
			//reqData.put("subOpenid","test");
			//reqData.put("operatorId","test");
			//reqData.put("storeId","test");
			//reqData.put("extend","");

			//reqData.put("trmNo","test");
			//reqData.put("ledgerAccountEffectTime","");
			//reqData.put("ledgerAccountFlag","");
			//reqData.put("goodsTag","00");
			//reqData.put("costPrice","");
			//reqData.put("receiptId","");
			//reqData.put("goodsId","fy10002");
			//reqData.put("goodsName","");
			//reqData.put("quantity","3");
			//reqData.put("price","0.05");

			/*//被扫
			reqData.put("ordNo",ordNo);
//			reqData.put("merchantId","226801000000220093509");
			//reqData.put("merchantId","836581770118417");
			reqData.put("amt","0.01");
			//reqData.put("subAppid","wxbd6c8015a642ad22");
			//reqData.put("goodsId","1111");
			reqData.put("payType","ALIPAY");
			reqData.put("authCode","134881635169285985");
			reqData.put("subject","2222");
			reqData.put("detail","222222");
			//reqData.put("trmIp","127.0.0.1");
//			reqData.put("subMechId","226801000000197524657");
//			reqData.put("channelId","test");
			//reqData.put("userId","test");
			//reqData.put("subOpenid","test");
			//reqData.put("operatorId","test");
			//reqData.put("storeId","test");
			reqData.put("extend","test");
			//reqData.put("goodsTag","test");
			//reqData.put("trmNo","test");
//			reqData.put("ledgerAccountEffectTime","");
//			reqData.put("ledgerAccountFlag","00");
			//reqData.put("timeExpire","");*/
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
//			String url = "http://localhost:8080/order/qr/tradeRefundQuery";
			String url = "http://localhost:8080/order/activeScan";
			String resultJson = HttpUtils.connectPostUrl(url, reqStr);
			System.out.println("返回信息："+resultJson);
			//不要对reqData排序 所以用LinkedHashMap
			HashMap<String, Object> result=  JSON.parseObject(resultJson,LinkedHashMap.class,Feature.OrderedField);
			//if("SXF0000".equals(result.get("code"))){
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
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	
}
