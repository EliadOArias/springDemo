package cn.eliadoarias.springdemo.controller;

import cn.eliadoarias.springdemo.config.JwtConfig;
import cn.eliadoarias.springdemo.dto.LoginRequest;
import cn.eliadoarias.springdemo.dto.RegisterRequest;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.dto.LoginResponse;
import cn.eliadoarias.springdemo.dto.RegisterResponse;
import cn.eliadoarias.springdemo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private JwtConfig jwtConfig;

    @PostMapping("/register")
    public BasicResult<RegisterResponse> register(@Valid @RequestBody RegisterRequest request,
                                                  HttpSession session,
                                                  HttpServletResponse response) {
        log.info("user register:"+request.getUsername()+" password:"+request.getPassword());
        String token = userService.register(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getUserType()
        );
        session.setAttribute("username",request.getUsername());
        session.setAttribute("refresh_time",System.currentTimeMillis()+jwtConfig.getRefreshLimit());

        Cookie cookie = new Cookie("access-token",token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(360000);
        response.addCookie(cookie);

        return BasicResult.success();
    }

    @PostMapping("/login")
    public BasicResult<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                            HttpSession session,
                                            HttpServletResponse response){
        log.info("user:"+request.getUsername()+" try to login.");
        String token = userService.login(request.getUsername(),request.getPassword());
        session.setAttribute("username",request.getUsername());
        session.setAttribute("refresh_time",System.currentTimeMillis()+jwtConfig.getRefreshLimit());

        Cookie cookie = new Cookie("access-token",token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(360000);
        log.info(token+" login success");
        response.addCookie(cookie);

        return BasicResult.success();
    }
}
