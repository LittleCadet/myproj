/**
 * 
 */
package com.fuiou.mchnt.entry.insEntry.service.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo.WebankConfigReqDto;
import com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo.WebankWechatConfigReqDto;
import com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo.WebankWechatConfigRetDto;
import com.fuiou.mchnt.entry.insEntry.util.HttpClientUtil;
import com.fuiou.mchnt.entry.insEntry.util.VerifySignUtil;
import com.google.gson.Gson;

/**
 * @project insEntry
 * @description 
 * @author abel.li
 * @creation 2018年1月4日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version	 
 */
public class WechatConfigService {

    public static void main(String[] args) {
        wechatConfigSet();
        wechatConfigGet();
    }

    protected static WebankWechatConfigRetDto wechatConfigGet() {
        String insCd = "08A9999999";
        String agencyType = "0";
        String mchntCd = "";
        String mngKey = "12345678901234567890123456789012";
        
        WebankWechatConfigReqDto reqDto = new WebankWechatConfigReqDto();
        reqDto.setTraceNo(String.valueOf(System.currentTimeMillis()));
        reqDto.setInsCd(insCd);
        reqDto.setAgencyType(agencyType);
        
        List<WebankConfigReqDto> configs = new ArrayList<WebankConfigReqDto>();
        WebankConfigReqDto config = new WebankConfigReqDto();
        config.setMchntCd(mchntCd);
        configs.add(config);
        reqDto.setConfigs(configs);
        reqDto.setSign(VerifySignUtil.getSign(reqDto, mngKey, "GBK"));
        
        String param = new Gson().toJson(reqDto);
        try {
            System.out.println(param);
            String url = "http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wechatConfigGet";
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("req", param);
            String result = HttpClientUtil.postJsonWithMap(url, paramMap);
            System.out.println("公众号配置返回报文：" + result);
            WebankWechatConfigRetDto retDto = new Gson().fromJson(result, WebankWechatConfigRetDto.class);
            return retDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return 
     * 
     */
    protected static WebankWechatConfigRetDto wechatConfigSet() {
        String insCd = "08A9999999";
        String agencyType = "0";
        String mchntCd = "";
        String jsapiPath = "0";
        String SubAppid = "0";
        String SubscribeAppid = "0";
        String mngKey = "12345678901234567890123456789012";
        
        WebankWechatConfigReqDto reqDto = new WebankWechatConfigReqDto();
        reqDto.setTraceNo(String.valueOf(System.currentTimeMillis()));
        reqDto.setInsCd(insCd);
        reqDto.setAgencyType(agencyType);
        
        List<WebankConfigReqDto> configs = new ArrayList<WebankConfigReqDto>();
        WebankConfigReqDto config = new WebankConfigReqDto();
        config.setMchntCd(mchntCd);
        config.setJsapiPath(jsapiPath);
        config.setSubAppid(SubAppid);
        config.setSubscribeAppid(SubscribeAppid);
        configs.add(config);
        reqDto.setConfigs(configs);
        reqDto.setSign(VerifySignUtil.getSign(reqDto, mngKey, "GBK"));
        
        String param = new Gson().toJson(reqDto);
        try {
            System.out.println(param);
            String url = "http://www-1.fuiou.com:28090/wmp/wxMchntMng.fuiou?action=wechatConfigSet";
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("req", param);
            String result = HttpClientUtil.postJsonWithMap(url, paramMap);
            System.out.println("公众号配置返回报文：" + result);
            WebankWechatConfigRetDto retDto = new Gson().fromJson(result, WebankWechatConfigRetDto.class);
            return retDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
