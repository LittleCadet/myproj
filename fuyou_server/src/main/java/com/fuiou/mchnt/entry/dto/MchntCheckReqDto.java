package com.fuiou.mchnt.entry.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MchntCheckReqDto extends BaseReqDto{
    
    private String trace_no = "";
    private String mchnt_name = "";
    private String sign = "";
    public String getTrace_no() {
        return trace_no;
    }
    public void setTrace_no(String trace_no) {
        this.trace_no = trace_no;
    }
    public String getMchnt_name() {
        return mchnt_name;
    }
    public void setMchnt_name(String mchnt_name) {
        this.mchnt_name = mchnt_name;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
}