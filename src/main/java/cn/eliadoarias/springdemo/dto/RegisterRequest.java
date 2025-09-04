package cn.eliadoarias.springdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @Size(min = 1, max = 20)
    private String username;
    @Size(min = 1, max = 20)
    private String name;
    @Size(min = 1, max = 20)
    private String password;
    @JsonProperty("user_type")
    private Integer userType;
}
