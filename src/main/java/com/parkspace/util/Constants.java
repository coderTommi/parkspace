package com.parkspace.util;

public class Constants {
	/**
	 * 错误码.
	 * @author lidongliang
	 *
	 */
	public enum ERRORCODE {
		/** 未知异常 **/
		UNKNOWERROR(10000),
		/** 未登录 **/
		URL_NEED_LOGIN(10001),
		/** 手机号被占用 **/
		TELEPHONE_IS_EXISTS(10002), 
		/** 手機號為空 **/
		PARAM_IS_NULL(10003), 
		/** 手機號或密碼錯誤 **/
		PHONE_OR_PWD_ERROR(10004), 
		/** 短信验证码错误 **/
		SMSCODE_IS_ERROR(10005),
		/** 车位编号为不能空 **/
		SPACENO_IS_NOT_NULL(20001),
		/** 小区编号为不能空 **/
		COMID_IS_NOT_NULL(20002),
		/** 行政区域编号为不能空 **/
		ZONEID_IS_NOT_NULL(20003),
		/** 认证信息不能空 **/
		APPROVE_IS_NOT_NULL(20004);
		
		;
		
		
		
		/** 错误码 **/
		private int value;

		public int getValue() {
			return value;
		}
		private ERRORCODE(int val) {
            this.value = val;
        }
		@Override
		public String toString() {
			return String.valueOf(this.value);
		}
	}
	
}
