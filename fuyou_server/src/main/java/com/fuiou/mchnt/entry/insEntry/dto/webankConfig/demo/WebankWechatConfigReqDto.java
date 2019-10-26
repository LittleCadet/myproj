/**
 * 
 */
package com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo;

import java.util.List;

/**
 * @project entryMchnt
 * @description 
 * @author abel.li
 * @creation 2017年8月22日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version	 
 */
public class WebankWechatConfigReqDto {
    private String traceNo;
    private String insCd;
    private String sign;
    private String agencyType;// 渠道类型
    
    private List<WebankConfigReqDto> configs;

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getInsCd() {
        return insCd;
    }

    public void setInsCd(String insCd) {
        this.insCd = insCd;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<WebankConfigReqDto> getConfigs() {
        return configs;
    }

    public void setConfigs(List<WebankConfigReqDto> configs) {
        this.configs = configs;
    }

    public final String getAgencyType() {
        return agencyType;
    }

    public final void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }
    
}
