package cn.eliadoarias.springdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApprovalRequest {
    @JsonProperty("report_id")
    private Integer reportId;
    private Integer approval;
}
