package com.parkspace.common.exception;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.parkspace.common.OperationResult;
/**
 * rest错误默认处理类.
 * @author lidongliang
 *
 */
public class DefaultRestErrorResolver implements RestErrorResolver, InitializingBean, Serializable {
	/** LOGGER. **/
	private static final Logger LOG = LoggerFactory.getLogger(DefaultRestErrorResolver.class);
	/** 接口类型不匹配. **/
	private static final long ERROR_SPRING_TYPE_MISMATCH=10400L;
	/** 未知. **/
	private static final long ERROR_SPRING_UNKNOWN=10401L;
	/** 非法路径. **/
	private static final long ERROR_SPRING_UNKNOWURL=10402L;
	/** 可以加载预定义的所有异常类型. **/
	//private Map<String, String> exceptionMappings = Collections.emptyMap();
	/** 配置文件中自定义扩展错误. **/
	//private Map<String, String> exceptionMappingDefinitions = Collections.emptyMap();
	
	
	public OperationResult resolveError(HttpServletRequest request,
			Object handler, Exception excep) {
	    OperationResult result = new OperationResult();
	    try {
	      if (TypeMismatchException.class.isAssignableFrom(excep.getClass())) {
	        result.setErrCode(String.valueOf(10400L));
	        TypeMismatchException exception = (TypeMismatchException)excep;
	        Throwable root = null;
	        if (exception.getRootCause() == null)
	          root = exception;
	        else {
	          root = exception.getRootCause();
	        }
	        LOG.debug(root.getMessage());
	        String message = root.getMessage().split("\n")[0];
	        result.setResData(message);
	      } else if (HttpRequestMethodNotSupportedException.class.isAssignableFrom(excep.getClass()))
	      {
	        result.setErrCode(String.valueOf(10400L));
	        HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException)excep;
	        Throwable root = null;
	        if (exception.getRootCause() == null)
	          root = exception;
	        else {
	          root = exception.getRootCause();
	        }
	        LOG.debug(root.getMessage());
	        String message = root.getMessage().split("\n")[0];
	        result.setResData(message);
	      } else if (MissingServletRequestParameterException.class.isAssignableFrom(excep.getClass()))
	      {
	        result.setErrCode(String.valueOf(10400L));
	        MissingServletRequestParameterException exception = (MissingServletRequestParameterException)excep;
	        Throwable root = null;
	        if (exception.getRootCause() == null)
	          root = exception;
	        else {
	          root = exception.getRootCause();
	        }
	        LOG.debug(root.getMessage());
	        String message = root.getMessage().split("\n")[0];
	        result.setResData(message);
	      } else if (HttpMessageNotReadableException.class.isAssignableFrom(excep.getClass()))
	      {
	        result.setErrCode(String.valueOf(10400L));
	        HttpMessageNotReadableException exception = (HttpMessageNotReadableException)excep;
	        Throwable root = null;
	        if (exception.getRootCause() == null)
	          root = exception;
	        else {
	          root = exception.getRootCause();
	        }
	        LOG.debug(root.getMessage());
	        String message = root.getMessage().split("\n")[0];
	        result.setResData(message);
	      } else {
	        Exception exception = excep;
	        LOG.debug("处理用户请求中出现异常：" + exception.getMessage());
	        LOG.error("请求出现的异常：" + excep.getClass(), excep);
	        result.setErrCode(String.valueOf(10401L));
	        result.setResData(exception.getMessage());
	      }
	    } catch (Exception e) {
	      LOG.error("公共异常所在的模块：" + request.getContextPath(), e);
	    }
	    result.setFlag(false);
	    return result;
	}
	
	public void afterPropertiesSet() throws Exception {
		
	}

}
