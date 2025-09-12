package cn.eliadoarias.springdemo.interceptor;

import cn.eliadoarias.springdemo.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    @Resource
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return false;
        }
        Integer userId = jwtUtil.getUserIdFromAuthorization(token);
        request.setAttribute("user_id", userId);
        String newToken = jwtUtil.refreshToken(token);
        if (!newToken.equals(token)) {
            Cookie cookie = new Cookie("refresh-token", newToken);
            cookie.setPath("/");
            cookie.setHttpOnly(false);
            cookie.setMaxAge(360000);
            cookie.setSecure(false);
            response.addCookie(cookie);
        }
        return true;
    }


}
