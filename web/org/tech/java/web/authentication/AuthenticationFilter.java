package org.tech.java.web.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import  org.tech.java.web.common.ParamId;

/***************************************************************************************
 * @author <<Name>>
 * @Date 2022-08-20
 * @Version 1.0
 * @Purpose <<to check user permission and redirect to another page>>*  *    
 ***************************************************************************************/
public class AuthenticationFilter implements Filter {
	
	public AuthenticationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);
			String reqURI = reqt.getRequestURI();

			if (reqURI.indexOf("/index.xhtml") >= 0 || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")
					|| reqURI.contains("images") || reqURI.contains("css") || reqURI.contains("js")) {
				chain.doFilter(request, response);
			} else if ((ses != null && ses.getAttribute(ParamId.LOGIN_USER) != null)) {
				boolean authenticate = true;
				if (authenticate) {
					chain.doFilter(request, response);
				} else {
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} else {
				resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}
