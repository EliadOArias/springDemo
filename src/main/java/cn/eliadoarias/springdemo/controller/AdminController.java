package cn.eliadoarias.springdemo.controller;


import cn.eliadoarias.springdemo.dto.ApprovalRequest;
import cn.eliadoarias.springdemo.dto.PostRequest;
import cn.eliadoarias.springdemo.dto.PostResponse;
import cn.eliadoarias.springdemo.dto.ReportResponse;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.service.PostService;
import cn.eliadoarias.springdemo.util.JwtUtil;
import com.sun.security.jgss.InquireType;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {
    @Resource
    private PostService postService;
    @Resource
    private JwtUtil jwtUtil;
    @GetMapping("/report")
    public BasicResult<ReportResponse> viewAll(){
        return BasicResult.success(postService.adminViewReport());
    }
    @PostMapping("/report")
    public BasicResult<Object> decide(
            @Valid @RequestBody ApprovalRequest request,
            @RequestHeader("Authorization") String baseToken){
        Integer userId = jwtUtil.getUserIdFromAuthorization(baseToken);
        postService.adminDecideReport(
                userId,
                request.getReportId(),
                request.getApproval()
        );
        return BasicResult.success();
    }
}
