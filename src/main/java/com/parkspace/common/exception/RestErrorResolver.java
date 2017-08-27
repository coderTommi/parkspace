package com.parkspace.common.exception;

import javax.servlet.http.HttpServletRequest;

import com.parkspace.common.OperationResult;


/**
 * rest异常和错误对象的转换接口.
 * @author lidongliang
 *
 */
public interface RestErrorResolver {
	/**
	 * 异常处理方法.
	 * @param request
	 * @param handler
	 * @param ex
	 * @return
	 */
	OperationResult resolveError(HttpServletRequest request, Object handler, Exception ex);
}
