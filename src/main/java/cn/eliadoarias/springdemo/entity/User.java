package cn.eliadoarias.springdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @TableId(type = IdType.AUTO)
    //身份鉴权
    private Integer id;

    private String username;

    private String password;

    private LocalDateTime createdAt;

    @JsonProperty("user_type")
    private Integer userType;

    private String name;
}