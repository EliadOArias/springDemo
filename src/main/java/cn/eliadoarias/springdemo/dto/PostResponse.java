package cn.eliadoarias.springdemo.dto;

import cn.eliadoarias.springdemo.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private List<Post> posts;
}
