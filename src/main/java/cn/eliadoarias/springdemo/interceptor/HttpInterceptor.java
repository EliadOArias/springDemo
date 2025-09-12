package cn.eliadoarias.springdemo.interceptor;

import cn.eliadoarias.springdemo.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        //从Header中获取登录token
        String token = request.getHeader("Authorization");
        if (token == null) {
            return false;
        }
        Integer userId = jwtUtil.getUserIdFromAuthorization(token);
        //将userId加入request中供service进行可能的处理
        request.setAttribute("user_id", userId);
        //获取session
        HttpSession session = request.getSession();

        //session中维护了无感刷新（免登录）的最长时间。满足条件后，若当前token快要过期则发送新token到cookie中
        if (jwtUtil.canRefresh(session) && jwtUtil.needRefresh(token)) {
            String newToken = jwtUtil.refreshToken(token);
            if (!newToken.equals(token)) {
                Cookie cookie = new Cookie("access-token", newToken);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setMaxAge(360000);
                cookie.setSecure(false);
                response.addCookie(cookie);
            }
        }
        return true;
    }


}
