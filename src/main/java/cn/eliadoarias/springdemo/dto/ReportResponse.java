package cn.eliadoarias.springdemo.dto;

import cn.eliadoarias.springdemo.entity.Post;
import cn.eliadoarias.springdemo.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportResponse {
    private List<Report> reports;
}
