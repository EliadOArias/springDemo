package cn.eliadoarias.springdemo.controller;

import cn.eliadoarias.springdemo.dto.*;
import cn.eliadoarias.springdemo.exception.ApiException;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.service.PostService;
import cn.eliadoarias.springdemo.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@Slf4j
public class StudentController {
    @Resource
    private PostService postService;
    @Resource
    private JwtUtil jwtUtil;
    @PostMapping("/post")
    public BasicResult<Object> post(@Valid @RequestBody PostRequest request,
                                    @RequestHeader("Authorization") String baseToken,
                                    HttpServletResponse response){
        Integer userId = jwtUtil.getUserIdFromAuthorization(baseToken);
        log.info("response: "+response);
        postService.post(request.getTitle(),request.getContent(),userId);
        return BasicResult.success();
    }
    @GetMapping("/post")
    public BasicResult<PostResponse> viewAll(){
        return BasicResult.success(postService.viewAll());
    }
    @DeleteMapping("/post")
    public BasicResult<Object> delete(
            @RequestParam("post_id") Integer postId,
            @RequestHeader("Authorization") String baseToken){
        Integer userId = jwtUtil.getUserIdFromAuthorization(baseToken);
        postService.delete(postId,userId);
        return BasicResult.success();
    }
    @PostMapping("/report-post")
    public BasicResult<Object> report(
            @RequestParam("post_id") Integer postId,
            @RequestHeader("Authorization") String baseToken,
            @RequestParam("reason") String reason
    ){
        Integer userId = jwtUtil.getUserIdFromAuthorization(baseToken);
        postService.report(postId,userId,reason);
        return BasicResult.success();
    }
    @PutMapping("/post")
    public BasicResult<Object> edit(
            @Valid @RequestBody EditRequest request,
            @RequestHeader("Authorization") String baseToken
    ){
        Integer userId = jwtUtil.getUserIdFromAuthorization(baseToken);
        postService.edit(
                request.getPostId(),
                request.getTitle(),
                request.getContent(),
                userId
        );
        return BasicResult.success();
    }
    @GetMapping("/report-post")
    public BasicResult<Object> viewReports(
            @RequestHeader("Authorization") String baseToken
    ){
        Integer userId = jwtUtil.getUserIdFromAuthorization(baseToken);
        return BasicResult.success(postService.viewReport(userId));
    }
}
