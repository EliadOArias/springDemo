package cn.eliadoarias.springdemo.service;

import cn.eliadoarias.springdemo.dto.PostResponse;
import cn.eliadoarias.springdemo.dto.ReportResponse;

public interface PostService {
    void post(String title, String content, Integer userId);
    PostResponse viewAll();
    void delete(Integer postId, Integer userId);
    void report(Integer postId, Integer userId, String reason);
    void edit(Integer postId, String title, String content, Integer userId);
    ReportResponse viewReport(Integer userId);
    ReportResponse adminViewReport();

    void adminDecideReport(Integer userId, Integer reportId, Integer approval);
}
