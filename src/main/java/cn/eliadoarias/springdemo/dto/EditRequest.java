package cn.eliadoarias.springdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditRequest {
    @JsonProperty("post_id")
    Integer postId;
    @Size(min = 1, max = 50)
    private String title;
    @Size(min = 1, max = 500)
    private String content;
    @JsonProperty("user_id")
    Integer userId;
}
