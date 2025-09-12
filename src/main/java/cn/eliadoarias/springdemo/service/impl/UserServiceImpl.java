package cn.eliadoarias.springdemo.service.impl;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.entity.User;
import cn.eliadoarias.springdemo.exception.ApiException;
import cn.eliadoarias.springdemo.mapper.UserMapper;
import cn.eliadoarias.springdemo.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements cn.eliadoarias.springdemo.service.UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) {
        if (username == null || password == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND);
        }
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            throw new ApiException(ExceptionEnum.WRONG_USER);
        }else {
            if (!user.getPassword().equals(password)) {
                throw new ApiException(ExceptionEnum.WRONG_PASSWORD);
            }
        }
        return jwtUtil.generateToken(username,user.getId());
    }

    @Override
    public String register(String username, String password, String name, Integer userType) {
        if (password.length() < 8 || password.length() > 16) {
            throw new ApiException(ExceptionEnum.WRONG_PASSWORD_LENGTH);
        }
        if ((userType != 1) && (userType!= 2)) {
            throw new ApiException(ExceptionEnum.WRONG_USER_TYPE);
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(username);
        if (!isNum.matches()) {
            log.info(username + " is not a number.");
            throw new ApiException(ExceptionEnum.USERNAME_NOT_A_NUM);
        }
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user != null) {
            throw new ApiException(ExceptionEnum.DUPLICATED_USER);
        }else {
            user = User.builder().
                    username(username).
                    password(password).
                    createdAt(LocalDateTime.now()).
                    name(name).
                    userType(userType).
                    build();
            userMapper.insert(user);
            return jwtUtil.generateToken(username,user.getId());
        }
    }
}
