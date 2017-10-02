package com.parkspace.util;

public class Constants {
	/**
	 * 错误码.
	 * @author lidongliang
	 *
	 */
	public enum ERRORCODE {
		/** 手机号被占用 **/
		TELEPHONE_IS_EXISTS(10001),
		/** 车位编号为不能空 **/
		SPACENO_IS_NOT_NULL(20001),
		/** 小区编号为不能空 **/
		COMID_IS_NOT_NULL(20002),
		/** 行政区域编号为不能空 **/
		ZONEID_IS_NOT_NULL(20003);
		
		
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
