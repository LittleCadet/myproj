package com.fuiou.mchnt.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description:
 * @Author: shenxie
 * @CreateDate: 2019/10/24
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="xml")
public class MerchantResponse {

    private String trace_no;

    private String mchnt_name;

    private String fy_mchnt_cd;

    private String wxapp_mchnt_cd;

    private String acnt_upd_no;

    private String ret_code;

    private String ret_msg;

    private String wx_mchnt_cd;

    private String auto_buy_cd;

    private String auto_buy_msg;

    private String wechat_mchnt_cd;

    private String acnt_upd_st;

    private String acnt_upd_msg;

    private String acnt_upd_ts;

}
