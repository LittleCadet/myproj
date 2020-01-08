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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.myproj.suixinfu.suixingpay.entity.IncomeResponse;
import com.myproj.suixinfu.suixingpay.utils.HttpUtils;
import com.myproj.suixinfu.suixingpay.utils.RSASignature;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @description: 进件
 * @author: zhouchen[zhou_chen1@suixingpay.com]
 * @date: 2019/08/22 10:32
 */
public class InCome {

    /**
     * 企业对公三证合一
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        //合作方私钥(替换成自己的)
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJv9NlljOejr52re\n" +
                "BodAyDIqmWKUd41XLIg9i4IzQG3gR9JiiKRc/mMJuKpPul3+MSBmU7SEZLcAlvuo\n" +
                "f2SG8mafF/qu1hze3mBtH8ByimYX170Zdl/6oCNOABw3IBIuCPlAA/j416ZS4Atj\n" +
                "6/2tNNvKl2Y44Ayqv/GTDJKV4G7pAgMBAAECgYBinN45/j/C3zfKWJg58xtck4+q\n" +
                "QX+ey+hsLLRAWFQ34hylhQMrHVr4k1G54XNJLoQyHl52dWgSRP4uFCSNEb61Ysbn\n" +
                "pBt2coEvzvLi+xFLwddT8G74jr0mYRYeLxgi0y/kNtXX/LdL6uNWgHwngHjbNEfl\n" +
                "2sP19Xu9HmeJKuuC8QJBAM1L9XGU6RxyXvDh7qSqvSiHkmRsVnyo/OxK+m7IxoqT\n" +
                "2szBpnbAenL1eCYmZQ1JUlkwwOkTYkZc+Hv9osir+r0CQQDCg74jVuyHXA5WMYLy\n" +
                "4K/K6ADjuMJeM4YRQSuDW3RTK4TeNDXu6ZKw9Gjd/n31anxBmZi1cCmKL2MuhZ5R\n" +
                "JV2dAkBDO8gjLYh2d4JeUy/Ln9sZJeUsnEpqwxEp9Zg0pe5XvfpfKmBw7bJsabWy\n" +
                "kMpxYnzkltHmwyFhN9ttB/DC1YflAkAqLCgQP2te2Z2eIHP0trRGeNCgjtFNXTxn\n" +
                "B0so2q44zgmrp4Q9/VQ5m3QVFXU3vVOdYXDTfbLz69xEHOI7U6+dAkEAuKmSK299\n" +
                "f01HbDvTfdjDDNupfdg4BtYuCyXWWs/PrheVBR81S0zECBqwKu25T6CynvsAGHkL\n" +
                "iSF2I+8Dnq4tcA==";

        //随行付公钥
        String sxfPublic =
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOmsrFtFPTnEzfpJ/hDl5RODBxw4i9Ex3NmmG/N7A1+by032zZZgLLpdNh8y5otjFY0E37Nyr4FGKFRSSuDiTk8vfx3pv6ImS1Rxjjg4qdVHIfqhCeB0Z2ZPuBD3Gbj8hHFEtXZq8+msAFu/5ZQjiVhgs5WWBjh54LYWSum+d9+wIDAQAB";

        //企业对公
        //String applicationId = income(privateKey,sxfPublic);
        //企业对私
        //String applicationId = income2(privateKey,sxfPublic);
        //个体户对公
        //String applicationId = income3(privateKey,sxfPublic);
        //个体户对私
        //String applicationId = income4(privateKey,sxfPublic);
        //自然人
        String applicationId = income5(privateKey,sxfPublic);

        //Thread.sleep(5*1000L);

        //getResult(privateKey,sxfPublic,"1011f220320a498a8c6641ddb117c926");

        //edit(privateKey,sxfPublic,"399200102337879");

        //getUpdateResult(privateKey,sxfPublic,"08b7f8c6032f4d17987f6d8b4c6880df");

        //update(privateKey,sxfPublic,"399200102337879");

        //merchantSetUp(privateKey,"399200102337879");

        //merchantInfoQuery(privateKey,"399200102337879");

        //addConf(privateKey,"399200102337879");

        //queryConf(privateKey,"399200102337879");
    }

    /**
     * 企业对公三证合一
     * @param privateKey
     * @param sxfPublic
     * @return
     * @throws Exception
     */
    public static String income(String privateKey,String sxfPublic) throws Exception {
        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mecDisNm", "商户简称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "03");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "法人姓名");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "钟楼区邹区佐一照明灯具商行");//结算账户名
        reqData.put("actTyp", "00");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.3", "0.3", "0.3", "0.3"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

//        String url = "https://openapi.suixingpay.com/merchant/income";
        //       String url = "http://172.16.138.162:5041/merchant/income";
        String url = "https://openapi-test.suixingpay.com/merchant/income";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        /*if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }*/

        IncomeResponse response = JSON.parseObject(resultJson, IncomeResponse.class);

        return response.getRespData().getApplicationId();
    }

    /**
     * 企业对私
     * @param privateKey
     * @param sxfPublic
     * @return
     * @throws Exception
     */
    public static String income2(String privateKey,String sxfPublic) throws Exception {
        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mecDisNm", "商户简称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "03");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "法人姓名");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "法人姓名");//结算账户名
        reqData.put("actTyp", "01");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        reqData.put("bankCardPositivePic", "f9667a050a964cf98d5c62a414437a93");//银行卡正面
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.3", "0.3", "0.3", "0.3"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

//        String url = "https://openapi.suixingpay.com/merchant/income";
        //       String url = "http://172.16.138.162:5041/merchant/income";
        String url = "https://openapi-test.suixingpay.com/merchant/income";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        /*if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }*/

        IncomeResponse response = JSON.parseObject(resultJson, IncomeResponse.class);

        return response.getRespData().getApplicationId();
    }

    /**
     * 个体户对公
     * @param privateKey
     * @param sxfPublic
     * @return
     * @throws Exception
     */
    public static String income3(String privateKey,String sxfPublic) throws Exception {
        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mecDisNm", "商户简称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "02");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "法人姓名");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "钟楼区邹区佐一照明灯具商行");//结算账户名
        reqData.put("actTyp", "00");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        reqData.put("bankCardPositivePic", "f9667a050a964cf98d5c62a414437a93");//银行卡正面
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.3", "0.3", "0.3", "0.3"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

//        String url = "https://openapi.suixingpay.com/merchant/income";
        //       String url = "http://172.16.138.162:5041/merchant/income";
        String url = "https://openapi-test.suixingpay.com/merchant/income";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        /*if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }*/

        IncomeResponse response = JSON.parseObject(resultJson, IncomeResponse.class);

        return response.getRespData().getApplicationId();
    }

    /**
     * 个体户对私
     * @param privateKey
     * @param sxfPublic
     * @return
     * @throws Exception
     */
    public static String income4(String privateKey,String sxfPublic) throws Exception {
        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mecDisNm", "商户简称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "02");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "法人姓名");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "法人姓名");//结算账户名
        reqData.put("actTyp", "01");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        reqData.put("bankCardPositivePic", "f9667a050a964cf98d5c62a414437a93");//银行卡正面
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.3", "0.3", "0.3", "0.3"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

//        String url = "https://openapi.suixingpay.com/merchant/income";
        //       String url = "http://172.16.138.162:5041/merchant/income";
        String url = "https://openapi-test.suixingpay.com/merchant/income";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        /*if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }*/

        IncomeResponse response = JSON.parseObject(resultJson, IncomeResponse.class);

        return response.getRespData().getApplicationId();
    }

    /**
     * 自然人
     * @param privateKey
     * @param sxfPublic
     * @return
     * @throws Exception
     */
    public static String income5(String privateKey,String sxfPublic) throws Exception {
        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mecDisNm", "商户简称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "01");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "法人姓名");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "法人姓名");//结算账户名
        reqData.put("actTyp", "01");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        reqData.put("bankCardPositivePic", "f9667a050a964cf98d5c62a414437a93");//银行卡正面
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.3", "0.3", "0.3", "0.3"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

//        String url = "https://openapi.suixingpay.com/merchant/income";
        //       String url = "http://172.16.138.162:5041/merchant/income";
        String url = "https://openapi-test.suixingpay.com/merchant/income";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        /*if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }*/

        IncomeResponse response = JSON.parseObject(resultJson, IncomeResponse.class);

        return response.getRespData().getApplicationId();
    }

    /**
     * 进件成功后，修改
     * @param privateKey
     * @param sxfPublic
     * @throws Exception
     */
    public static void edit(String privateKey,String sxfPublic,String mno) throws Exception {
        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mno",mno);
        reqData.put("mecDisNm", "签购单的的名称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "03");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "范德萨");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "钟楼区邹区佐一照明灯具商行");//结算账户名
        reqData.put("actTyp", "00");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        reqData.put("merchantInfoModifyPic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.2", "0.2", "0.2", "0.2"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

        String url = "https://openapi-test.suixingpay.com/merchant/editMerchantInfo";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }
    }


    public static JSONObject getJsonParam(HashMap reqData) {
        long timestamp = System.currentTimeMillis();
        String reqid = (String) reqData.get("reqId");
        JSONObject reqJson = new JSONObject();
        reqJson.put("orgId", reqData.get("orgId"));
        reqJson.put("reqId", reqid);
        reqJson.put("version", "1.0");//OEM和代理商的要传2.0，服务商传1.0
        reqJson.put("signType", "RSA");
        reqJson.put("timestamp", timestamp);
        //定义业务参数
        JSONObject reqDataJson = new JSONObject();
        reqDataJson.put("mblNo", reqData.get("mblNo"));//商户联系电话
        reqDataJson.put("mecDisNm", reqData.get("mecDisNm"));//商户简称
        reqDataJson.put("operationalType", reqData.get("operationalType"));//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqDataJson.put("attachMerces3hantNo", reqData.get("attachMerchantNo"));//挂靠平台商编
        reqDataJson.put("mecTypeFlag", reqData.get("mecTypeFlag"));//商户类型（新： 00普通单店商户（非连锁商户）01连锁总 02连锁分 03 1+n总 04 1+n分;旧：01线上平台入驻 02普通 03连锁总店 04连锁分店  05 1+n总  06 1+n分）
        reqDataJson.put("haveLicenseNo", reqData.get("haveLicenseNo"));//资质类型 ( 01自然人 02个体户 03企业)
        reqDataJson.put("parentMno", reqData.get("parentMno"));//所属总店商户编号
        reqDataJson.put("independentModel", reqData.get("independentModel"));//分店是否独立结算(00是 01否)
        reqDataJson.put("qrcodeList", reqData.get("qrcodeList"));//二维码费率（01微信 02支付宝）
        reqDataJson.put("settleType", reqData.get("settleType"));//结算类型(新：03 T1 04 D1;旧：01-T1 02-D1)---默认值为D1
        reqDataJson.put("supportPayChannels", reqData.get("supportPayChannels"));//支持的支付渠道(01微信 02支付宝 03银联)不填默认全开
        reqDataJson.put("supportTradeTypes", reqData.get("supportTradeTypes"));//支持的交易类型(01主扫 02被扫 03公众号 04退货 05APP)不填默认全开
        reqDataJson.put("specifyWechatChannel", reqData.get("specifyWechatChannel"));//指定微信渠道号
        reqDataJson.put("onlineType", reqData.get("onlineType"));//线上普通商户类型 ( 01APP 02网站 03公众号)
        reqDataJson.put("onlineName", reqData.get("onlineName"));//线上普通商户名称 (APP名称/网站网址/公众号名称)
        reqDataJson.put("onlineTypeInfo", reqData.get("onlineTypeInfo"));//线上普通商户信息 (APP下载地址及账号信息)
        reqDataJson.put("cprRegNmCn", reqData.get("cprRegNmCn"));//营业执照注册名称
        reqDataJson.put("registCode", reqData.get("registCode"));//营业执照注册号
        reqDataJson.put("licenseMatch", reqData.get("licenseMatch"));//是否三证合一（00是  01否）
        reqDataJson.put("orgCode", reqData.get("orgCode"));//组织机构代码
        reqDataJson.put("taxRegNo", reqData.get("taxRegNo"));//税务登记号
        reqDataJson.put("businessLicStt", reqData.get("businessLicStt"));//营业执照起始日
        reqDataJson.put("businessLicEnt", reqData.get("businessLicEnt"));//营业执照到期日
        reqDataJson.put("cprRegAddr", reqData.get("cprRegAddr"));//地址
        reqDataJson.put("regProvCd", reqData.get("regProvCd"));//省编码（国标）
        reqDataJson.put("regCityCd", reqData.get("regCityCd"));//市编码（国标）
        reqDataJson.put("regDistCd", reqData.get("regDistCd"));//区编码（国标）
        reqDataJson.put("mccCd", reqData.get("mccCd"));//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqDataJson.put("csTelNo", reqData.get("csTelNo"));//客服电话
        reqDataJson.put("identityName", reqData.get("identityName"));//法人姓名
        reqDataJson.put("identityTyp", reqData.get("identityTyp"));//法人证件类型
        reqDataJson.put("identityNo", reqData.get("identityNo"));//法人证件号
        reqDataJson.put("legalPersonLicStt", reqData.get("legalPersonLicStt"));//法人身份证开始日期
        reqDataJson.put("legalPersonLicEnt", reqData.get("legalPersonLicEnt"));//法人身份证结束日期
        reqDataJson.put("actNm", reqData.get("actNm"));//结算账户名
        reqDataJson.put("actTyp", reqData.get("actTyp"));//结算账户类型（00对公 01对私）
        reqDataJson.put("stmManIdNo", reqData.get("stmManIdNo"));//账户人身份证号
        reqDataJson.put("accountLicStt", reqData.get("accountLicStt"));//账户人证件号起始日
        reqDataJson.put("accountLicEnt", reqData.get("accountLicEnt"));//账户人证件号到期日
        reqDataJson.put("actNo", reqData.get("actNo"));//结算卡号
        reqDataJson.put("lbnkNo", reqData.get("lbnkNo"));//开户支行联行行号
        reqDataJson.put("lbnkNm", reqData.get("lbnkNm"));//开户支行名称
        reqDataJson.put("licensePic", reqData.get("licensePic"));//营业执照
        reqDataJson.put("taxRegistLicensePic", reqData.get("taxRegistLicensePic"));//税务登记证
        reqDataJson.put("orgCodePic", reqData.get("orgCodePic"));//组织机构代码证
        reqDataJson.put("legalPersonidPositivePic", reqData.get("legalPersonidPositivePic"));//法人身份证正面
        reqDataJson.put("legalPersonidOppositePic", reqData.get("legalPersonidOppositePic"));//法人身份证反面
        reqDataJson.put("openingAccountLicensePic", reqData.get("openingAccountLicensePic"));//开户许可证
        reqDataJson.put("bankCardPositivePic", reqData.get("bankCardPositivePic"));//银行卡正面
        reqDataJson.put("bankCardOppositePic", reqData.get("bankCardOppositePic"));//银行卡反面
        reqDataJson.put("settlePersonIdcardOpposite", reqData.get("settlePersonIdcardOpposite"));//结算人身份证反面
        reqDataJson.put("settlePersonIdcardPositive", reqData.get("settlePersonIdcardPositive"));//结算人身份证正面
        reqDataJson.put("merchantAgreementPic", reqData.get("merchantAgreementPic"));//商户协议照片
        reqDataJson.put("storePic", reqData.get("storePic"));//门头照片
        reqDataJson.put("insideScenePic", reqData.get("insideScenePic"));//真实商户内景图片
        reqDataJson.put("businessPlacePic", reqData.get("businessPlacePic"));//经营场所-含收银台
        reqDataJson.put("merchantEnterProtocol", reqData.get("merchantEnterProtocol"));//商家入驻协议
        reqDataJson.put("icpLicence", reqData.get("icpLicence"));//ICP许可证
        reqDataJson.put("handIdcardPic", reqData.get("handIdcardPic"));//手持身份证照片
        reqDataJson.put("leaseAgreementThreePic", reqData.get("leaseAgreementThreePic"));//租赁协议三（签章页）
        reqDataJson.put("leaseAgreementTwoPic", reqData.get("leaseAgreementTwoPic"));//租赁协议二（面积、有效期页）
        reqDataJson.put("leaseAgreementOnePic", reqData.get("leaseAgreementOnePic"));//租赁协议一（封面）
        reqDataJson.put("agentPersonSignature", reqData.get("agentPersonSignature"));//代理人签名
        reqDataJson.put("confirmPersonSignature", reqData.get("confirmPersonSignature"));//确认人签名
        reqDataJson.put("letterOfAuthPic", reqData.get("letterOfAuthPic"));//非法人结算授权函
        reqDataJson.put("unionSettleWithoutLicense", reqData.get("unionSettleWithoutLicense"));//统一结算无营业执照说明
        reqDataJson.put("societyGroupLegPerPic", reqData.get("societyGroupLegPerPic"));//社会团体法人证书
        reqDataJson.put("foundationLegPerRegPic", reqData.get("foundationLegPerRegPic"));//基金会法人登记证书
        reqDataJson.put("schoolLicese", reqData.get("schoolLicese"));//办学许可证
        reqDataJson.put("medicalInstitutionLicense", reqData.get("medicalInstitutionLicense"));//医疗机构办学许可证
        reqDataJson.put("insuranceLicese", reqData.get("insuranceLicese"));//经营保险业务许可证
        reqDataJson.put("insuranceLegPerGradePic", reqData.get("insuranceLegPerGradePic"));//保险业务法人等级证书
        reqDataJson.put("privateEducationLicense", reqData.get("privateEducationLicense"));//民办教育许可证
        reqDataJson.put("chargeProofPic", reqData.get("chargeProofPic"));//收费证明文件
        reqDataJson.put("otherPicOne", reqData.get("otherPicOne"));//其他资料照片1
        reqDataJson.put("otherPicTwo", reqData.get("otherPicTwo"));//其他资料照片2
        reqDataJson.put("otherPicThree", reqData.get("otherPicThree"));//其他资料照片3
        reqDataJson.put("otherPicFour", reqData.get("otherPicFour"));//其他资料照片4
        reqDataJson.put("otherPicFive", reqData.get("otherPicFive"));//其他资料照片5
        reqDataJson.put("togetherPic", reqData.get("togetherPic"));//新旧结算人手持身份证合照
        reqDataJson.put("infoChangePic", reqData.get("infoChangePic"));//商户信息变更表

        reqJson.put("reqData", reqDataJson);

        return reqJson;
    }

    /**
     * 获取进件结果
     */
    public static void getResult(String privateKey, String sxfPublic,String applicationId) throws Exception {


        Map<String,Object> req = new HashMap<>();
        Map<String,String> reqData = new HashMap<>();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId","97564534");
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());
        req.put("reqData",reqData);
        reqData.put("applicationId",applicationId);
        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        req.put("sign", sign);
        String url = "https://openapi-test.suixingpay.com/merchant/queryMerchantInfo";
        String reqStr = JSON.toJSONString(req);
        System.out.println("请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);

        if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }
    }

    /**
     * 获取修改结果
     */
    public static void getUpdateResult(String privateKey, String sxfPublic, String applicationId) throws Exception {

        Map<String,Object> req = new HashMap<>();
        Map<String,String> reqData = new HashMap<>();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId","97564534");
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());
        reqData.put("applicationId",applicationId);
        req.put("reqData",reqData);
        //Feature.OrderedField：防止json解析后，出现乱序的情况【实际上默认就是有序的】
        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        req.put("sign", sign);
        String url = "https://openapi-test.suixingpay.com/merchant/queryModifyResult";
        String reqStr = JSON.toJSONString(req);
        System.out.println("请求参数:" + reqStr);
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);

        if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }
    }

    /**
     * 入驻更新
     * @param privateKey
     * @param sxfPublic
     */
    public static void update(String privateKey, String sxfPublic, String mno) throws Exception {
        //https://openapi-test.suixingpay.com/merchant/updateMerchantInfo

        //请求json
        HashMap req = new HashMap();
        HashMap reqData = new HashMap();
        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        reqData.put("mno",mno);
        reqData.put("mecDisNm", "签购单的的名称"); //商户简称
        reqData.put("mblNo", "18655754286");//商户联系电话
        reqData.put("operationalType", "01");//经营类型（01线下 02线上 03非盈利类 04缴费类 05保险类 06私立院校类 ）
        reqData.put("haveLicenseNo", "03");//资质类型 ( 01自然人 02个体户 03企业)
        reqData.put("mecTypeFlag", "00");//商户类型（00普通单店商户（非连锁商户） 01连锁总 02连锁分 03 1+n总 04 1+n分
        reqData.put("cprRegNmCn", "钟楼区邹区佐一照明灯具商行");//营业执照注册名称
        reqData.put("registCode", "92320404MA1WN62M2R");//营业执照注册号
        reqData.put("licenseMatch", "00");//是否三证合一（00是  01否）
        reqData.put("cprRegAddr", "地址");//地址
        reqData.put("regProvCd", "340000000000");//省编码（国标）
        reqData.put("regCityCd", "340100000000");//市编码（国标）
        reqData.put("regDistCd", "340102000000");//区编码（国标）
        reqData.put("mccCd", "5411");//微信类目（和mcc传其一，如果都传，以mcc为准）
        reqData.put("csTelNo", "18655245786");//客服电话

        reqData.put("identityName", "范德萨");//法人姓名
        reqData.put("identityTyp", "00");//法人证件类型
        reqData.put("identityNo", "340111199825748587");//法人证件号
        reqData.put("actNm", "钟楼区邹区佐一照明灯具商行");//结算账户名
        reqData.put("actTyp", "00");//结算账户类型（00对公 01对私）
        reqData.put("stmManIdNo", "340111199825748587");//账户人身份证号
        reqData.put("actNo", "6228485568565422358");//结算卡号
        reqData.put("lbnkNo", "905290000008");//开户支行联行行号xxxxxx

        reqData.put("licensePic", "f9667a050a964cf98d5c62a414437a93");//营业执照
        reqData.put("legalPersonidPositivePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证正面
        reqData.put("legalPersonidOppositePic", "f9667a050a964cf98d5c62a414437a93");//法人身份证反面
        reqData.put("storePic", "f9667a050a964cf98d5c62a414437a93");//门头照片
        reqData.put("insideScenePic", "f9667a050a964cf98d5c62a414437a93");//真实商户内景图片
        reqData.put("openingAccountLicensePic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        reqData.put("merchantInfoModifyPic", "f9667a050a964cf98d5c62a414437a93");//开户许可证
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.2", "0.2", "0.2", "0.2"};
        ArrayList qrcodeList = new ArrayList();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap qrcode = new HashMap();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }



        reqData.put("qrcodeList", qrcodeList);

        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        //JSONObject jsonObject = getJsonParam(req);

        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

        String url = "https://openapi-test.suixingpay.com/merchant/editMerchantInfo";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

        HashMap<String, Object> result = JSON.parseObject(resultJson, LinkedHashMap.class, Feature.OrderedField);
        if ("SXF0000".equals(result.get("code"))) {
            //验签
            String signResult = result.get("sign").toString();
            result.remove("sign");

            String resultStr = RSASignature.getOrderContent(result);
            System.out.println(resultStr);
            //sign
            String resultSign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
            System.out.println("resultSign:" + resultSign);
            //组装加密串
            if (RSASignature.doCheck(resultStr, signResult, sxfPublic)) {
                System.out.println("验签成功");
            }
        }
    }


    /**
     * 商户费率设置
     */
    public static void merchantSetUp(String privateKey, String mno) throws Exception {
        Map<String ,Object> req = new HashMap<>();
        Map<String,Object> reqData = new HashMap<>();
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.35", "0.25", "0.25", "0.25"};
        ArrayList<Map<String,String>> qrcodeList = new ArrayList<>();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap<String,String> qrcode = new HashMap<>();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }

        String[] supportPayChannels = {"01","02"};
        String[] supportTradeTypes = {"01","02","03","04", "05"};

        reqData.put("mno",mno);
        reqData.put("qrcodeList", qrcodeList);
        reqData.put("settleType","03");
        reqData.put("supportPayChannels",supportPayChannels);
        reqData.put("supportTradeTypes",supportTradeTypes);

        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqMap);
        System.out.println("请求参数:" + reqStr);

        String url = "https://openapi-test.suixingpay.com/merchant/merchantSetup";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);
    }

    /**
     * 商户信息查询接口
     * @param privateKey
     * @param mno
     * @throws Exception
     */
    public static void merchantInfoQuery(String privateKey, String mno) throws Exception {
        Map<String ,Object> req = new HashMap<>();
        Map<String,Object> reqData = new HashMap<>();
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.2", "0.2", "0.2", "0.2"};
        ArrayList<Map<String,String>> qrcodeList = new ArrayList<>();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap<String,String> qrcode = new HashMap<>();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }

        String[] supportPayChannels = {"01,02"};
        String[] supportTradeTypes = {"01","02","03","04", "05"};

        reqData.put("mno",mno);

        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqStr);

        String url = "https://openapi-test.suixingpay.com/merchant/merchantInfoQuery";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);
    }

    /**
     * 微信子商户支付参数配置接口
     * @param privateKey
     * @param mno
     */
    public static void addConf(String privateKey, String mno) throws Exception {
        Map<String ,Object> req = new HashMap<>();
        Map<String,Object> reqData = new HashMap<>();
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.2", "0.2", "0.2", "0.2"};
        ArrayList<Map<String,String>> qrcodeList = new ArrayList<>();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap<String,String> qrcode = new HashMap<>();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }

        String[] type = {"02,03"};

        reqData.put("mno",mno);
        reqData.put("type",type);
        reqData.put("subMchId","4657894564897");
        reqData.put("subscribeAppid","4657894564897");
        reqData.put("jsapiPath","https://api-test.hzyudaokeji.com/wx/h5/ ");

        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqStr);

        String url = "https://openapi-test.suixingpay.com/merchant/weChatPaySet/addConf";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);

    }

    /**
     * 微信子商户查询支付配置接口
     * @param privateKey
     * @param mno
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     */
    public static void queryConf(String privateKey, String mno) throws Exception {
        Map<String ,Object> req = new HashMap<>();
        Map<String,Object> reqData = new HashMap<>();
        String[] qrcodeType = {"01", "02", "06", "07"};
        String[] qrcoderate = {"0.2", "0.2", "0.2", "0.2"};
        ArrayList<Map<String,String>> qrcodeList = new ArrayList<>();
        for (int i = 0; i < qrcodeType.length; i++) {
            HashMap<String,String> qrcode = new HashMap<>();
            qrcode.put("rate", qrcoderate[i]);
            qrcode.put("rateType", qrcodeType[i]);
            qrcodeList.add(qrcode);
        }

        String[] type = {"02,03"};

        reqData.put("mno",mno);
        reqData.put("subMchId","4657894564897");

        req.put("reqId", UUID.randomUUID().toString().replaceAll("-", ""));
        req.put("orgId", "97564534");
        req.put("reqData",reqData);
        req.put("signType","RSA");
        req.put("version","1.0");
        req.put("timestamp",System.currentTimeMillis());

        HashMap reqMap = JSON.parseObject(JSONObject.toJSONString(req), LinkedHashMap.class, Feature.OrderedField);

        String signContent = RSASignature.getOrderContent(reqMap);
        System.out.println("拼接后的参数：" + signContent);
        //sign
        String sign = RSASignature.encryptBASE64(RSASignature.sign(signContent, privateKey));
        System.out.println(sign);

        reqMap.put("sign", sign);


        String reqStr = JSON.toJSONString(reqMap);
        System.out.println("请求参数:" + reqStr);

        String url = "https://openapi-test.suixingpay.com/merchant/weChatPaySet/queryConf";
        String resultJson = HttpUtils.connectPostUrl(url, reqStr);
        System.out.println("返回信息：" + resultJson);
    }


}
