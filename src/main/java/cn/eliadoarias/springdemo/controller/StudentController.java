package cn.eliadoarias.springdemo.controller;

import cn.eliadoarias.springdemo.dto.*;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.service.PostService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@Slf4j
public class StudentController {
    @Resource
    private PostService postService;
    @PostMapping("/post")
    public BasicResult<Object> register(@Valid @RequestBody PostRequest request){
        log.info("post sended:"+request.getTitle()+" user id:"+request.getUserId());
        postService.post(request.getTitle(),request.getContent(),request.getUserId());
        return BasicResult.success();
    }
    @GetMapping("/post")
    public BasicResult<PostResponse> viewAll(){
        return BasicResult.success(postService.viewAll());
    }
    @DeleteMapping("/post")
    public BasicResult<Object> delete(
            @RequestParam("post_id") Integer postId,
            @RequestParam("user_id") Integer userId
    ){
        postService.delete(postId,userId);
        return BasicResult.success();
    }
    @PostMapping("/report-post")
    public BasicResult<Object> report(
            @RequestParam("post_id") Integer postId,
            @RequestParam("user_id") Integer userId,
            @RequestParam("reason") String reason
    ){
        postService.report(postId,userId,reason);
        return BasicResult.success();
    }
    @PutMapping("/post")
    public BasicResult<Object> edit(
            @Valid @RequestBody EditRequest request
    ){
        postService.edit(
                request.getPostId(),
                request.getTitle(),
                request.getContent(),
                request.getUserId()
        );
        return BasicResult.success();
    }
    @GetMapping("/report-post")
    public BasicResult<Object> viewReports(
            @RequestParam("user_id") Integer userId
    ){
        return BasicResult.success(postService.viewReport(userId));
    }
}
