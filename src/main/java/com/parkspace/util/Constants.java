package com.parkspace.util;

import java.math.BigDecimal;

public class Constants {
	
	public static final BigDecimal PLEDGEAMOUNT = new BigDecimal("100");
	
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
		
		/** 微信转账失败 **/
		REMOTE_TRS_IS_ERROR(10006),
		/** 充值失败 **/
		TRS_ERROR(10007),
		/** 體現調用遠程接口失敗 **/
		REMOTE_WITHDRAW_FAILED(10008),
		/** 體現失敗 **/
		WITHDRAW_ERROR(10009),
		/** 交押金失敗 **/
		PLEDGEIN_ERROR(10010),
		/** 提押金失敗 **/
		PLEDGEOUT_ERROR(10011),
		/** 內部轉賬失敗 **/
		INNER_TRS_ERROR(10012),
		/** 发放奖金失败 **/
		BONUSOUT_ERROR(10013),
		/** 押金不足 **/
		PLEDGE_AMT_NOT_ENOUPH(10014),
		
		
		
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
	/** 参数 billType: 0：充值 1：提现  2：交押金  3：提取押金	
	 * 					4：分配出账 5：分配入账  6：出账（订单扣款）
						7：入账（订单收入）	8:奖金出账
						9： 奖金入账' **/
	public enum AMTTYPE {
		RECHARGE(0),
		WITHDRAW(1),
		PLEDGEIN(2),
		PLEDGEOUT(3),
		PAYOUT(4),
		PAYIN(5),
		ORDERPAY(6),
		BONUS(7);
		private int value;
		public int getValue() {
			return value;
		}
		private AMTTYPE(int value) {
			this.value = value;
		}
	}
	
	public enum BillSide {
		IN(0),
		OUT(1);
		private int value;
		public int getValue() {
			return value;
		}
		private BillSide(int value) {
			this.value = value;
		}
	}
	
	public enum BillState {
		/** 成功 **/
		SUCCESS(0), 
		/** 远端成功，内部失败  **/
		FAILED(1),
		INNER_ERROR(2), 
		INNER_SUCCESS(3),
		REMOTE_SUCC(4),
		REMOTE_FAILED(5),
		INIT(6);
		
		
		private int value;
		public int getValue() {
			return value;
		}
		private BillState(int value) {
			this.value = value;
		}
	}
	
	public enum PayChannel {
		WEIXIN(0), 
		ZHIFUBAO(1);
		private int value;
		public int getValue() {
			return value;
		}
		private PayChannel(int value) {
			this.value = value;
		}
	}
}
