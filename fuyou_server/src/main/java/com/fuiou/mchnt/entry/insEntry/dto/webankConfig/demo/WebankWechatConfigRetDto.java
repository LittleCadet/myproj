/**
 * 
 */
package com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo;

import java.util.List;

import com.fuiou.mchnt.entry.insEntry.util.VerifySignUtil;
import com.google.gson.Gson;

/**
 * @project entryMchnt
 * @description 
 * @author abel.li
 * @creation 2017年8月22日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version	 
 */
public class WebankWechatConfigRetDto {
    private String traceNo;
    private String retCode;
    private String retMsg;
    private String sign;
    
    private List<WechatConfigRetDto> configs;

    public WebankWechatConfigRetDto() {
        super();
    }

    public WebankWechatConfigRetDto(String traceNo, String retCode, String retMsg) {
        super();
        this.traceNo = traceNo;
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public WebankWechatConfigRetDto(String retCode, String retMsg) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<WechatConfigRetDto> getConfigs() {
        return configs;
    }

    public void setConfigs(List<WechatConfigRetDto> configs) {
        this.configs = configs;
    }
    
    public String toResponseStr() {
        return new Gson().toJson(this);
    }

    public String toResponseStr(String key) {
        String sign = VerifySignUtil.getSign(this, key, "GBK");
        this.setSign(sign);
        String retStr = new Gson().toJson(this);
        return retStr;
    }
    
}
