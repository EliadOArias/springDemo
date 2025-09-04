package cn.eliadoarias.springdemo.mapper;

import cn.eliadoarias.springdemo.entity.Post;
import cn.eliadoarias.springdemo.entity.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author EArias
* @description 针对表【report】的数据库操作Mapper
* @createDate 2025-09-04 22:54:33
* @Entity cn.eliadoarias.springdemo.entity.Report
*/
public interface ReportMapper extends BaseMapper<Report> {
    @Select("select * from report")
    List<Report> findAll();
}



