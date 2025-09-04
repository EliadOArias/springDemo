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
 * @TableName post
 */
@TableName(value ="post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    @JsonProperty("user_id")
    private Integer userId;

    private Integer likes;

    private LocalDateTime time;
}