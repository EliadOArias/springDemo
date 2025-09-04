package cn.eliadoarias.springdemo.mapper;

import cn.eliadoarias.springdemo.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
* @author EArias
* @description 针对表【post】的数据库操作Mapper
* @createDate 2025-09-04 16:48:46
* @Entity cn.eliadoarias.springdemo.entity.Post
*/
public interface PostMapper extends BaseMapper<Post> {
    @Select("select * from post")
    List<Post> findAll();
}




