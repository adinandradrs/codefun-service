package id.codefun.service.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class WebUtility {

    public void doWriteCookie(HttpServletResponse response, String cookieName, String cookieValue, Integer expiry, Boolean secure, Boolean httpOnly){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        if(ObjectUtils.isNotEmpty(expiry)){
            cookie.setMaxAge(expiry);
        }
        cookie.setPath("/");
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    public void doDeleteCookie( HttpServletResponse response, Boolean secure, Boolean httpOnly, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setSecure(secure);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    public String getCookie( HttpServletRequest request,  String cookieName){
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if(ObjectUtils.isNotEmpty(cookie)){
            return cookie.getValue();
        }
        return null;
    }
    
}
