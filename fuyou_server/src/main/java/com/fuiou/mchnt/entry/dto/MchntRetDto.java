package com.fuiou.mchnt.entry.dto;


/**
 * 
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version
 */
public class MchntRetDto{
	private String trace_no = "";
	private String ret_code = "";
	private String ret_msg = "";
	private String fy_mchnt_cd = "";
	private String wx_mchnt_cd = ""; 
	private String wxapp_mchnt_cd = ""; 
	private String acnt_upd_no = ""; 
	private String acnt_upd_st = ""; 
	private String acnt_upd_msg = ""; 
	
	public String getTrace_no() {
		return trace_no;
	}

	public void setTrace_no(String trace_no) {
		this.trace_no = trace_no;
	}

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

	public String getFy_mchnt_cd() {
		return fy_mchnt_cd;
	}

	public void setFy_mchnt_cd(String fy_mchnt_cd) {
		this.fy_mchnt_cd = fy_mchnt_cd;
	}

	public String getWx_mchnt_cd() {
		return wx_mchnt_cd;
	}

	public void setWx_mchnt_cd(String wx_mchnt_cd) {
		this.wx_mchnt_cd = wx_mchnt_cd;
	}

	public String getWxapp_mchnt_cd() {
		return wxapp_mchnt_cd;
	}

	public void setWxapp_mchnt_cd(String wxapp_mchnt_cd) {
		this.wxapp_mchnt_cd = wxapp_mchnt_cd;
	}

	public String getAcnt_upd_no() {
		return acnt_upd_no;
	}

	public void setAcnt_upd_no(String acnt_upd_no) {
		this.acnt_upd_no = acnt_upd_no;
	}

	public String getAcnt_upd_st() {
		return acnt_upd_st;
	}

	public void setAcnt_upd_st(String acnt_upd_st) {
		this.acnt_upd_st = acnt_upd_st;
	}

	public String getAcnt_upd_msg() {
		return acnt_upd_msg;
	}

	public void setAcnt_upd_msg(String acnt_upd_msg) {
		this.acnt_upd_msg = acnt_upd_msg;
	}
}
