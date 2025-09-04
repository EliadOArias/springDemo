package cn.eliadoarias.springdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.eliadoarias.springdemo.entity.Report;
import cn.eliadoarias.springdemo.service.ReportService;
import cn.eliadoarias.springdemo.mapper.ReportMapper;
import org.springframework.stereotype.Service;

/**
* @author EArias
* @description 针对表【report】的数据库操作Service实现
* @createDate 2025-09-04 22:54:33
*/
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report>
    implements ReportService {

}




