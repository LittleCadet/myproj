package com.fuiou.mchnt.entry.insEntry.dto.webankConfig.demo;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fuiou.mchnt.entry.util.StringUtils;

/**
 * 
 * @project wbp
 * @description 
 * @author abel.li
 * @creation 2017年7月4日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version
 */
public class WebankConfigReqDto {
    
    private String mchntCd;// 商户号
    private String jsapiPath;// 子商户公众账号JS API支付授权目录
    private String subAppid;// 子商户SubAPPID
    private String subscribeAppid;// 子商户推荐关注公众账号APPID
    
    public final String getMchntCd() {
        return mchntCd;
    }
    public final void setMchntCd(String mchntCd) {
        this.mchntCd = mchntCd;
    }
    public final String getJsapiPath() {
        return jsapiPath;
    }
    public final void setJsapiPath(String jsapiPath) {
        this.jsapiPath = jsapiPath;
    }
    public final String getSubAppid() {
        return subAppid;
    }
    public final void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }
    public final String getSubscribeAppid() {
        return subscribeAppid;
    }
    public final void setSubscribeAppid(String subscribeAppid) {
        this.subscribeAppid = subscribeAppid;
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
