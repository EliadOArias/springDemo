package cn.eliadoarias.springdemo.mapper;

import cn.eliadoarias.springdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
* @author EArias
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-09-04 16:48:46
* @Entity cn.eliadoarias.springdemo.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




