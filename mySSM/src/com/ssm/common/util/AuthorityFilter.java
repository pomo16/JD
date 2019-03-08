package com.ssm.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssm.common.entity.UserInfo;
	
/**
 * Servlet Filter implementation class AuthorityFilter
 */
@WebFilter("/manager/*")
public class AuthorityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("进入权限过滤器");
		String uri = ((HttpServletRequest)request).getRequestURI();
		System.out.println("uri=" + uri);
		String[] exceptPaths = {"login.do","addUserInfo.do","userNameValidate.do","createValidateCode.do","checkValidateCode.do"};
		boolean flag = false;
		for(String path : exceptPaths) {
			if(uri.indexOf(path) != -1) {
				flag = true;
			}
		}
		if(flag) {
			chain.doFilter(request, response);
		}else {
			HttpSession session = ((HttpServletRequest)request).getSession();
			UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			if(userInfo == null) {

				((HttpServletResponse)response).sendRedirect("/mySSM/login.html");
				System.out.println("您还没有登录，无权访问。");
			}else {
				chain.doFilter(request, response);
			}	
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
