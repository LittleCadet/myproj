package com.myproj.suixinfu.suixingpay.demo;

import java.text.SimpleDateFormat;
import java.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.myproj.suixinfu.suixingpay.utils.ApiRequestBean;
import com.myproj.suixinfu.suixingpay.utils.HttpUtils;
import com.myproj.suixinfu.suixingpay.utils.RSASignature;

public class Test {
	
	
	public static void main(String[] args) {
		
		try {
			//合作方私钥(替换成自己的)测试
			String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmjDcNOofBgZ2XOjdkTA2mnI+Q6W5ocOGk42QKF39qgZqh0UCqwgHRFsp0ogwxQHfnYd6kZa+yrFbfmH209yChzZMiyCM+UMHGob4UpSnKyMmDB8HrLdLlSm41EvrgmN0z86jUYJmfa0g+DbnirwzsQ5bwSnN5MIm66EnN7VLBM6uJF1NoUiF+qE2uhByhthhfil/2PNMPC6Oz1zpEZfo2FL+tUeMQ+Nn2cQjN2yARWZPcHqVgirDz2+ZUzeK8Bx/EdYCXbmom94UfOFvZzs0QmgYKSTqRWI4RMsC0xm+HgK+ec245c4fcPIOXz3bzCtxiNTcaOeBNW9JV8it0eagbAgMBAAECggEAe//FNSowzRfN1zxW5Wvidfv2v/OsakIxHezXHNRl30xjbrs3JGuRorUPQ0srPLg9DZ7GfyveZm79tzf3z/Z260D9nVXZN5o0y8i8lu5pcTuRHOd3r7vMWz7RUt6wvuvRNc7iEk1h01myzdC2Z1nhiz6/cGReGQrUYL51xMY/aOv19+BbQghQDDj+j3JmxMQWvnpqvpblEmVVebY/vO3JvUCR53L3AHupam7zxGMfW5aTLvJGPw/3xxdkIU/y4dLAIzYt5csvIUFCqZcQMfShBhnmcJP+GzmfNQnQYXDsoZCcjQwIjT0+lX4ZuemLeZ9riaRhKdLGAABuTyhHxKpkgQKBgQDRffRuzj3BqbOoFkeywPgweVfrtnqay7tvqvecTOsRXwFKbL3Wr8VTpndLURw2AVicwnVyqgwv6P7mUzcBHOiGZhYscDxqdReS9+nq8Wb8WTP90MIdWv89ZriyZct7xDiVjhGl4A1BFCFqLbXor3dZ1RjYpgn8UN614cQ6khUiuwKBgQDLhZwn3E3Cufjx5ev7nu9k07Zwt9VBiuUh5aj+LqUROPxF0BkpT+JZwhtj9rPK/82DeAc1v7S6XFNVsGoV1h2iSbhmFgB/f5b/lIp70LfiCWMB3QdmKhV5mJSor9Meur6GuMWlwd6s2Bf/VsveOcgNpF/bNzZII4gAWFE0ERCqIQKBgDBwwX6Frf4D8Yjibf3FZR5AULYuYbc9eL/fdpXrq0c/Aptk/ZiQ2D35dggyXyWVthm5VVMIQv0pERfNEiDtwE6jeGkaIr4zCRpwvOQYocmkDsOX6+rBydc202pc42pg+vyJ90a1whrUPs3AeBmvyRc6kR43doOjZ3wDEk7AzKZVAoGAYPu0ZBJJQ+fXR35nK6qbLZLtt10C1I7ZjYOhOi8EHebn4GS/+L3GgTtMTiB7oNqxFlsWJHjMKJpiY80DYJB94p36rzsA1cD5NaWVMFdPqPkMIxEuu3KGdUFOZewTUOtwa0aVeN2fvQHkvZqg367tNAMGYrI2vb2gy3lKiOMw68ECgYAmmHkiBG4NoA3A73fi3HETpYTXhaE229xBNWrIIhMLFEZNdIkW2Antwqc2Pe0LTrrcLKbs7ocDiHYwdF6WyJGK+ayyy7FAHQ0XIY5CWq9rxUpMqL46JoJVTPwZzHgp8KeDxbKM25zK4ItOD5KZnBqr8TSE92aWtWEvgK1ZIqGUSg==";
			//String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmjDcNOofBgZ2XOjdkTA2mnI+Q6W5ocOGk42QKF39qgZqh0UCqwgHRFsp0ogwxQHfnYd6kZa+yrFbfmH209yChzZMiyCM+UMHGob4UpSnKyMmDB8HrLdLlSm41EvrgmN0z86jUYJmfa0g+DbnirwzsQ5bwSnN5MIm66EnN7VLBM6uJF1NoUiF+qE2uhByhthhfil/2PNMPC6Oz1zpEZfo2FL+tUeMQ+Nn2cQjN2yARWZPcHqVgirDz2+ZUzeK8Bx/EdYCXbmom94UfOFvZzs0QmgYKSTqRWI4RMsC0xm+HgK+ec245c4fcPIOXz3bzCtxiNTcaOeBNW9JV8it0eagbAgMBAAECggEAe//FNSowzRfN1zxW5Wvidfv2v/OsakIxHezXHNRl30xjbrs3JGuRorUPQ0srPLg9DZ7GfyveZm79tzf3z/Z260D9nVXZN5o0y8i8lu5pcTuRHOd3r7vMWz7RUt6wvuvRNc7iEk1h01myzdC2Z1nhiz6/cGReGQrUYL51xMY/aOv19+BbQghQDDj+j3JmxMQWvnpqvpblEmVVebY/vO3JvUCR53L3AHupam7zxGMfW5aTLvJGPw/3xxdkIU/y4dLAIzYt5csvIUFCqZcQMfShBhnmcJP+GzmfNQnQYXDsoZCcjQwIjT0+lX4ZuemLeZ9riaRhKdLGAABuTyhHxKpkgQKBgQDRffRuzj3BqbOoFkeywPgweVfrtnqay7tvqvecTOsRXwFKbL3Wr8VTpndLURw2AVicwnVyqgwv6P7mUzcBHOiGZhYscDxqdReS9+nq8Wb8WTP90MIdWv89ZriyZct7xDiVjhGl4A1BFCFqLbXor3dZ1RjYpgn8UN614cQ6khUiuwKBgQDLhZwn3E3Cufjx5ev7nu9k07Zwt9VBiuUh5aj+LqUROPxF0BkpT+JZwhtj9rPK/82DeAc1v7S6XFNVsGoV1h2iSbhmFgB/f5b/lIp70LfiCWMB3QdmKhV5mJSor9Meur6GuMWlwd6s2Bf/VsveOcgNpF/bNzZII4gAWFE0ERCqIQKBgDBwwX6Frf4D8Yjibf3FZR5AULYuYbc9eL/fdpXrq0c/Aptk/ZiQ2D35dggyXyWVthm5VVMIQv0pERfNEiDtwE6jeGkaIr4zCRpwvOQYocmkDsOX6+rBydc202pc42pg+vyJ90a1whrUPs3AeBmvyRc6kR43doOjZ3wDEk7AzKZVAoGAYPu0ZBJJQ+fXR35nK6qbLZLtt10C1I7ZjYOhOi8EHebn4GS/+L3GgTtMTiB7oNqxFlsWJHjMKJpiY80DYJB94p36rzsA1cD5NaWVMFdPqPkMIxEuu3KGdUFOZewTUOtwa0aVeN2fvQHkvZqg367tNAMGYrI2vb2gy3lKiOMw68ECgYAmmHkiBG4NoA3A73fi3HETpYTXhaE229xBNWrIIhMLFEZNdIkW2Antwqc2Pe0LTrrcLKbs7ocDiHYwdF6WyJGK+ayyy7FAHQ0XIY5CWq9rxUpMqL46JoJVTPwZzHgp8KeDxbKM25zK4ItOD5KZnBqr8TSE92aWtWEvgK1ZIqGUSg==";
			//随行付公钥
			String sxfPublic =
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOmsrFtFPTnEzfpJ/hDl5RODBxw4i9Ex3NmmG/N7A1+by032zZZgLLpdNh8y5otjFY0E37Nyr4FGKFRSSuDiTk8vfx3pv6ImS1Rxjjg4qdVHIfqhCeB0Z2ZPuBD3Gbj8hHFEtXZq8+msAFu/5ZQjiVhgs5WWBjh54LYWSum+d9+wIDAQAB";
			//请求json
			//String req= "{\"orgId\":\"67290416\",\"reqData\":{\"mno\":\"836102376310006\",\"ordNo\":\"48e138ed7db245a666a490941b6c09b2\"},\"reqId\":\"f67271bedd2041e79ed3754dcc5319db\",\"signType\":\"RSA\",\"timestamp\":\"201809112345\",\"version\":\"1.0\"}";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String dataStr = df.format(new Date());
			ApiRequestBean<JSONObject> reqBean = new ApiRequestBean<JSONObject>();
			reqBean.setOrgId("13813035");
			reqBean.setReqId(UUID.randomUUID().toString().replaceAll("-",""));
			reqBean.setSignType("RSA");
			reqBean.setTimestamp(dataStr);
			reqBean.setVersion("1.0");
			JSONObject reqData = new JSONObject();

			//业务参数
			reqData.put("mno","399190626000323");
			reqData.put("tradeSource","01");
			reqData.put("trmIp","172.16.2.1");
			Random random = new Random();
		//获取openid
			/*reqData.put("subMchId","283049494");
			reqData.put("merchantId","226801000000220093509");
			reqData.put("authCode","135062202397701751");
			reqData.put("subAppId","wx0c0bc056e26b9981");
			reqData.put("wechatChannel","24006513");*/


				/*//退款
			reqData.put("ordNo",(int)((Math.random()*9+1)*100000)+"20"+random.nextInt(7));
			reqData.put("origOrderNo","f21f5e1a7c844ab080ac068fa5dc9894");
			reqData.put("merchantId","226801000000220093509");
			reqData.put("amt","0.01");*/

			//退款查询
//			reqData.put("ordNo","f21f5e1a7c844ab080ac068fa5dc9894");
//			reqData.put("merchantId","226801000000220093509");
//			reqData.put("amt","0.01");

		/*	//正交易查询
			reqData.put("ordNo","64340668861294191");
			reqData.put("merchantId","226801000000197524657");*/
			/*//聚合支付
			reqData.put("ordNo",random.nextInt(8)+"20"+random.nextInt(7));
			reqData.put("merchantId","226801000000197524657");
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
//			reqData.put("userId","2088802546142954");
			reqData.put("subOpenid","oJ8HkviqjqKYoytI7_DM0fqv_M04");
			reqData.put("operatorId","test");
			reqData.put("storeId","test");
			reqData.put("extend","test");
			//reqData.put("goodsTag","test");
			reqData.put("trmNo","test");
			reqData.put("limitPay","01");*/




			//主扫
			reqData.put("ordNo",(int)((Math.random()*9+1)*100000)+"20"+random.nextInt(7));

			//			reqData.put("merchantId","226801000000197524657");
			//			reqData.put("merchantId","226801000000221076637");

			reqData.put("amt","0.01");
			//reqData.put("subAppid","wx0c0bc056e26b9981");
			reqData.put("goodsId","fy10002");
			reqData.put("payType","WECHAT");
			//			reqData.put("payWay","02");
			reqData.put("subject","test");
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
//			reqData.put("timeExpire","6");
//			reqData.put("quantity","3");
//			reqData.put("limitPay","00");


			/*//被扫
			reqData.put("ordNo",(int)((Math.random()*9+1)*100000)+"20"+random.nextInt(7));
			//			reqData.put("merchantId","226801000000220093509");
			reqData.put("merchantId","700000001195567");
			reqData.put("amt","0.01");
			reqData.put("subAppid","wxbd6c8015a642ad22");
			//reqData.put("goodsId","1111");
			reqData.put("payType","WECHAT");
			reqData.put("authCode","282243384925341934");
			reqData.put("subject","2222");
			reqData.put("detail","222222");
			//reqData.put("trmIp","127.0.0.1");
			//			reqData.put("subMechId","226801000000197524657");
			//			reqData.put("channelId","test");
			//reqData.put("userId","test");
			//reqData.put("subOpenid","test");
			//reqData.put("operatorId","test");
			//reqData.put("storeId","test");
			reqData.put("extend","test");*/
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
			//String url = "http://172.16.155.45:8080/order/activeScan";
			String url = "https://openapi.tianquetech.com/order/activeScan";

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
