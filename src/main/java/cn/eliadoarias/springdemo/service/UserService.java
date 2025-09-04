package cn.eliadoarias.springdemo.service;


public interface UserService {
    public Integer login(String username,String password);
    public Integer register(String username,String password, String name, Integer userType);
}
