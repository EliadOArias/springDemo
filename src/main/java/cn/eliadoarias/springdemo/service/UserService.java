package cn.eliadoarias.springdemo.service;


public interface UserService {
    Integer login(String username, String password);
    Integer register(String username, String password, String name, Integer userType);
}
