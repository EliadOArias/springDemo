package cn.eliadoarias.springdemo.controller;

import cn.eliadoarias.springdemo.dto.LoginRequest;
import cn.eliadoarias.springdemo.dto.RegisterRequest;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.dto.LoginResponse;
import cn.eliadoarias.springdemo.dto.RegisterResponse;
import cn.eliadoarias.springdemo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BasicResult<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        log.info("user register:"+request.getUsername()+" password:"+request.getPassword());
        Integer id = userService.register(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getUserType()
        );
        return BasicResult.success(new RegisterResponse(request.getUsername()));
    }

    @PostMapping("/login")
    public BasicResult<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        log.info("user:"+request.getUsername()+" try to login.");
        Integer id = userService.login(request.getUsername(),request.getPassword());
        return BasicResult.success(new LoginResponse(id));
    }
}
