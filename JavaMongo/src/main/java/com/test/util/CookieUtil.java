package com.test.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
	
	private static final Log logger = LogFactory.getLog(CookieUtil.class);
	
	public static void addCookie(String name, String value, HttpServletRequest request, HttpServletResponse reponse) {
		addCookie(name, value, false, request, reponse);
	}
	
	public static void addCookie(String name, String value, boolean httpOnly, HttpServletRequest request, HttpServletResponse response) {
		addCookie(name, value, httpOnly, null, null, request, response);
	}
	
	public static void addCookie(String name, String value, boolean httpOnly, String path, String domain, HttpServletRequest request, HttpServletResponse response) {
		// FIXME: cookie.setMaxAge(expiry);
		StringBuilder cookieValue = new StringBuilder(name + "=" + value + ";");
		if (domain != null && !"".equals(domain)) {
			cookieValue.append("domain=" + domain + ";");
		}
		
		if (path != null && !"".equals(path)) {
			cookieValue.append("path=" + path + ";");
		} else {
			cookieValue.append("path=" + request.getContextPath() + ";");
		}
		
		
		if (httpOnly) {
			cookieValue.append("HttpOnly"); // 设置 http only
		}
		
		response.addHeader("SET-COOKIE", cookieValue.toString());
	}
	
	public static void delCookie(String name, HttpServletRequest request, HttpServletResponse response) {
		Cookie _delCookie = null;
		
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					_delCookie = cookie;
					break;
				}
			}
		}
		
		_delCookie.setMaxAge(0);
		response.addCookie(_delCookie);
	}

}
