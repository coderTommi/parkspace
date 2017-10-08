package com.parkspace.model;

import java.io.Serializable;
import java.util.Arrays;

import com.parkspace.util.Constants;

/**
 * @Title: ReportCommonModel.java
 * @Package com.parkspace.model
 * <p>Description:
 * 报表通用类，主要记录年月和统计次数
 * </p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月8日 下午8:55:27</p>
*/

public class ReportCommonModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String yearMonth;
	
	private Integer count;
	
	//查询月份
	private String[] yearMonthQuery;
	//查询统计最近的月份,默认六个月
	private Integer nearCount = Constants.REPORT_NEAR_COUNT;
	
	//状态查询条件
	
	private String[] statusQuery;
	
	public String[] getStatusQuery() {
		return statusQuery;
	}

	public void setStatusQuery(String[] statusQuery) {
		this.statusQuery = statusQuery;
	}

	public String[] getYearMonthQuery() {
		return yearMonthQuery;
	}

	public void setYearMonthQuery(String[] yearMonthQuery) {
		this.yearMonthQuery = yearMonthQuery;
	}

	public Integer getNearCount() {
		return nearCount;
	}

	public void setNearCount(Integer nearCount) {
		this.nearCount = nearCount;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReportCommonModel [yearMonth=" + yearMonth + ", count=" + count + ", yearMonthQuery="
				+ Arrays.toString(yearMonthQuery) + ", nearCount=" + nearCount + ", statusQuery="
				+ Arrays.toString(statusQuery) + "]";
	}
	

}
