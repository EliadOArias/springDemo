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

/**
 * @TableName report
 */
@TableName(value ="report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonProperty("reporter_id")
    private Integer reporterId;

    @JsonProperty("post_id")
    private Integer postId;

    private Integer status;

    private String reason;
}