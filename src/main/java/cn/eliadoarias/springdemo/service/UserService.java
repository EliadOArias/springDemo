package cn.eliadoarias.springdemo.service;


public interface UserService {
    String login(String username, String password);
    String register(String username, String password, String name, Integer userType);
}
