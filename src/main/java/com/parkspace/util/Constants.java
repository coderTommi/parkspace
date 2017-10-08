package com.parkspace.util;

import java.math.BigDecimal;

public class Constants {
	/** 押金 **/
	public static final BigDecimal PLEDGEAMOUNT = new BigDecimal("100");
	/** 预留时间，单位是分钟 **/
	public static int RESERVE_TIME = 15;
	/** 快到期时间，单位是分钟 **/
	public static int SOON_EXPIRE_TIME = 11;
	/** 业主预留时间,车位提前到期时间，单位是分钟 **/
	public static int SO_RESERVE_TIME = 30;
	
	/** 未来几小时计算押金+余额是否满足支付，单位是小时 **/
	public static int FUTURE_HOURS_MONEY = 24;
	/** 报表统计月份**/
	public static int REPORT_NEAR_COUNT = 6;
	
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
		ZONEID_IS_NOT_NULL(20003),
		/** 认证信息不能空 **/
		APPROVE_IS_NOT_NULL(20004),
		/** 订单信息不能为空 **/
		ORDER_IS_NOT_NULL(20005),
		/** 该车位不能被预定 **/
		SPACE_IS_NOT_ORDER(20006),
		/** 订单状态不合法 **/
		ORDER_STATUS_IS_ILLLEGAL(20007),
		/** 该车位不能被使用 **/
		SPACE_IS_NOT_USE(20008),
		/** 车位不可延期使用,请重新选择车位会减少延期时间 **/
		SPACENO_IS_NO_DELAY(20009);
		
		
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
