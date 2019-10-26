/**
 * 
 */
package com.fuiou.mchnt.entry.exception;


/**
 * 
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version
 */
public class FieldException extends Exception {

	/**
     * 
     */
    private static final long serialVersionUID = -6431110657674361894L;

	private String retCode;
	private String retMsg;
	
	public FieldException (String msg){
	    super(msg);
	    this.retCode = "";
	    this.retMsg = msg;
	}
	
	public FieldException (String code, String msg){
		super(msg);
		this.retCode = code;
		this.retMsg = msg;
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
}
