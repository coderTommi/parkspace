package com.parkspace.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.parkspace.util.Constants;

public class UserFilter implements Filter {
	
    private static final String[] WHITE_URL_LIST = new String[] {"/user/register", "/user/login" };
    private static final String sessionKey = "_USER";
    private static List<String> whiteList = new ArrayList<String>();
    static {
    	whiteList = Arrays.asList(WHITE_URL_LIST);
    }


	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String servletPath = req.getServletPath();
		/* 判断url是否需要登录 */
		if(whiteList.contains(servletPath)) {
			chain.doFilter(req, res);
            return;
		}
		
		Object user= req.getSession().getAttribute(sessionKey);
		if(user == null){
			OperationResult result = new OperationResult();
			result.setFlag(false);
			result.setErrCode(Constants.ERRORCODE.URL_NEED_LOGIN.toString());
			res.setContentType("application/json");
			res.getWriter().print(JSONObject.toJSONString(result));
            return;
        }
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
