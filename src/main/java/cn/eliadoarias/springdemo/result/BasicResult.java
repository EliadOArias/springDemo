package cn.eliadoarias.springdemo.result;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.util.JwtUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BasicResult<T> {
    public final static String SUCCESS = "success";
    private int code;
    @JsonProperty("msg")
    private String message;
    private T data;

    public static <N> BasicResult<N> success(){
        return new BasicResult<>(HttpStatus.OK.value(), SUCCESS, null);
    }


    public static <N> BasicResult<N> success(N data){
        return new BasicResult<>(HttpStatus.OK.value(), SUCCESS, data);
    }

    public static <N> BasicResult<N> error(Integer code, String message){
        return new BasicResult<>(code, message, null);
    }

    public static <N> BasicResult<N> error(ExceptionEnum e){
        return new BasicResult<>(e.getCode(), e.getMessage(), null);
    }

}
