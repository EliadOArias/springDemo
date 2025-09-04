package cn.eliadoarias.springdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {
    @JsonProperty("username")
    private String name;
}
