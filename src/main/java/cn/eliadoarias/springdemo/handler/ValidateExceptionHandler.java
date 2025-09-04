package cn.eliadoarias.springdemo.handler;

import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.result.BasicResult;
import cn.eliadoarias.springdemo.util.ExceptionUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Order(10)
public class ValidateExceptionHandler {
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            JsonMappingException.class,
            HttpMessageNotReadableException.class,
            ServletRequestBindingException.class
    })
    @ResponseBody
    public BasicResult<Object> handleValidateException(Exception e) {
        ExceptionUtil.printError(e);
        return BasicResult.error(ExceptionEnum.INVALID_PARAMETERS);
    }

    @ExceptionHandler({
            NoResourceFoundException.class,
            HttpRequestMethodNotSupportedException.class
    })
    @ResponseBody
    public BasicResult<Object> handleNotFoundException(Exception e) {
        ExceptionUtil.printError(e);
        return BasicResult.error(ExceptionEnum.NOT_FOUND);
    }
}
