package com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo;

import com.fuiou.mchnt.entry.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @project wbp
 * @description 
 * @author abel.li
 * @creation 2017年7月4日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version
 */
public class WechatConfigRetDto {
    
    private String mchntCd;// 商户号
    private String merchantName;// 商户名称(营业执照上的公司名称)
    private String code;// 返回码
    private String message;// 返回码说明
    private String merchantNo;// 商户号
    private String desc;// 一般描述
    private String error;// 错误描述
    private String statusCode;// 商户状态
    private String status;// 商户状态描述
    
    private String jsapiCode;// 商户状态描述
    private String jsapiMsg;// 商户状态描述
    private String subAppidCode;// 商户状态描述
    private String subAppidMsg;// 商户状态描述
    private String subscribeAppidCode;// 商户状态描述
    private String subscribeAppidMsg;// 商户状态描述
    
    private String jsapiPathList;
    private String appidConfigList;
    
    public final String getMchntCd() {
        return mchntCd;
    }
    public final void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }
    public final String getMerchantName() {
        return merchantName;
    }
    public final void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMerchantNo() {
        return merchantNo;
    }
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getJsapiCode() {
        return jsapiCode;
    }
    public void setJsapiCode(String jsapiCode) {
        this.jsapiCode = jsapiCode;
    }
    public String getJsapiMsg() {
        return jsapiMsg;
    }
    public void setJsapiMsg(String jsapiMsg) {
        this.jsapiMsg = jsapiMsg;
    }
    public String getSubAppidCode() {
        return subAppidCode;
    }
    public void setSubAppidCode(String subAppidCode) {
        this.subAppidCode = subAppidCode;
    }
    public String getSubAppidMsg() {
        return subAppidMsg;
    }
    public void setSubAppidMsg(String subAppidMsg) {
        this.subAppidMsg = subAppidMsg;
    }
    public String getSubscribeAppidCode() {
        return subscribeAppidCode;
    }
    public void setSubscribeAppidCode(String subscribeAppidCode) {
        this.subscribeAppidCode = subscribeAppidCode;
    }
    public String getSubscribeAppidMsg() {
        return subscribeAppidMsg;
    }
    public void setSubscribeAppidMsg(String subscribeAppidMsg) {
        this.subscribeAppidMsg = subscribeAppidMsg;
    }
    public String getJsapiPathList() {
        return jsapiPathList;
    }
    public void setJsapiPathList(String jsapiPathList) {
        this.jsapiPathList = jsapiPathList;
    }
    public String getAppidConfigList() {
        return appidConfigList;
    }
    public void setAppidConfigList(String appidConfigList) {
        this.appidConfigList = appidConfigList;
    }
    
    @Override
    public String toString(){
        try {
            BeanInfo info = Introspector.getBeanInfo(this.getClass(), Object.class);
            
            List<String> list = new ArrayList<String>();
            for (PropertyDescriptor f : info.getPropertyDescriptors()) {
                if(f.getReadMethod().invoke(this) != null 
                        && StringUtils.isNotEmpty(f.getReadMethod().invoke(this).toString())
                        && !"sign".equals(f.getName())){
                    list.add(f.getName() + ":" + f.getReadMethod().invoke(this));
                }
            }
            int size = list.size();
            String [] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            int stopFlag = size - 1;
            for(int i = 0; i < size; i ++) {
                sb.append(arrayToSort[i]);
                if(i < stopFlag){
                    sb.append(",");
                }
            }
            sb.append("}");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Introspector.flushFromCaches(this.getClass());
        }
        return null;
    }
}
