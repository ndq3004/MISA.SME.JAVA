package com.webencyclop.demo.auth.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
	//@Value("${spring.auth.jwtTokenCookieName}")
    private static String jwtTokenCookieName="authorization";
	
//	@Value("${auth.domain}")
    private static String domain="localhost";
	
    private static String path="/api/login";
    private static int maxAge=-1;
    private static boolean secure=false;
	
    public static void create(HttpServletResponse httpServletResponse, String value) {
        Cookie cookie = new Cookie(jwtTokenCookieName, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath(path);
        httpServletResponse.addCookie(cookie);
    }

    public static void clear(HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie(jwtTokenCookieName, null);
        cookie.setPath(path);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest httpServletRequest) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, jwtTokenCookieName);
        return cookie != null ? cookie.getValue() : null;	
    }

}