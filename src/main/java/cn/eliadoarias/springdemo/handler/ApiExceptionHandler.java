package cn.eliadoarias.springdemo.handler;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.exception.ApiException;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.util.ExceptionUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(50)
public class ApiExceptionHandler {
    @ExceptionHandler({
            ApiException.class,
    })
    @ResponseBody
    public BasicResult<Object> handleApiException(ApiException e) {
        ExceptionUtil.printError(e);
        return BasicResult.error(e.getCode(), e.getMessage());
    }
}
