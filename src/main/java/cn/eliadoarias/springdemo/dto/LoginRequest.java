package cn.eliadoarias.springdemo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @Size(min = 1, max = 20)
    private String username;
    @Size(min = 1, max = 20)
    private String password;
}
