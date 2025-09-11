package cn.eliadoarias.springdemo.service.impl;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.dto.PostResponse;
import cn.eliadoarias.springdemo.dto.ReportResponse;
import cn.eliadoarias.springdemo.entity.Post;
import cn.eliadoarias.springdemo.entity.Report;
import cn.eliadoarias.springdemo.exception.ApiException;
import cn.eliadoarias.springdemo.mapper.PostMapper;
import cn.eliadoarias.springdemo.mapper.ReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PostServiceImpl implements cn.eliadoarias.springdemo.service.PostService {

    @Resource
    private PostMapper postMapper;
    @Resource
    private ReportMapper reportMapper;

    @Override
    public void post(String title, String content, Integer userId) {
        if(title==null || content==null || userId == null){
            throw new ApiException(ExceptionEnum.INVALID_PARAMETERS);
        }
        postMapper.insert(Post.builder().
                title(title).
                content(content).
                userId(userId).
                likes(0).
                time(LocalDateTime.now()).
                build());
    }
    @Override
    public PostResponse viewAll() {
        return new PostResponse(postMapper.findAll());
    }
    @Override
    public void delete(Integer postId, Integer userId) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getId, postId);
        Post post = postMapper.selectOne(wrapper);
        if (post == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND);
        }
        if (!post.getUserId().equals(userId)) {
            throw new ApiException(ExceptionEnum.NO_OP);
        }
        postMapper.deleteById(postId);
    }

    @Override
    public void report(Integer postId, Integer userId, String reason) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getId, postId);
        Post post = postMapper.selectOne(wrapper);
        if (post == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND);
        }
        postMapper.updateById(post);
        reportMapper.insert(
                Report.builder().
                        reporterId(userId).
                        postId(postId).
                        status(1).
                        reason(reason).
                        build()
        );
    }

    @Override
    public void edit(Integer postId, String title, String content, Integer userId) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getId, postId);
        Post post = postMapper.selectOne(wrapper);
        if (post == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND);
        }
        if (!post.getUserId().equals(userId)) {
            throw new ApiException(ExceptionEnum.NO_OP);
        }
        post.setTitle(title);
        post.setContent(content);
        postMapper.updateById(post);
    }

    @Override
    public ReportResponse viewReport(Integer userId) {
        LambdaQueryWrapper<Report> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Report::getReporterId, userId);
        return new ReportResponse(reportMapper.selectList(wrapper));
    }

    @Override
    public ReportResponse adminViewReport() {
        LambdaQueryWrapper<Report> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Report::getStatus, 1);
        return new ReportResponse(reportMapper.selectList(wrapper));
    }

    @Override
    public void adminDecideReport(Integer userId, Integer reportId, Integer approval) {
        LambdaQueryWrapper<Report> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Report::getId, reportId);
        Report report = reportMapper.selectOne(wrapper);
        if (report == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND);
        }
        if ((approval != 1) && (approval != 2)) {
            throw new ApiException(ExceptionEnum.INVALID_PARAMETERS);
        }
        if (approval == 1) {
            report.setStatus(2);
            reportMapper.updateById(report);
            postMapper.deleteById(report.getPostId());
            //数据库事务
        }
        if (approval == 2) {
            report.setStatus(3);
            reportMapper.updateById(report);
            LambdaQueryWrapper<Post> postWrapper = new LambdaQueryWrapper<>();
            postWrapper.eq(Post::getId, report.getPostId());
        }
    }

}
